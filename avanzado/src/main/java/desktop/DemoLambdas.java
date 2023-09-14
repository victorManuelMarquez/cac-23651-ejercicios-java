package desktop;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class DemoLambdas extends JFrame implements Runnable {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new DemoLambdas("Demo"));
    }

    public DemoLambdas(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {

        setLayout(new BorderLayout(6, 0));

        JPanel contenedorNorte = new JPanel();
        add(contenedorNorte, BorderLayout.NORTH);

        contenedorNorte.add(new JLabel("Generar "));

        int diferencial = 50;
        JSpinner cantNum = new JSpinner(new SpinnerNumberModel(diferencial, diferencial, 1000, 10));
        contenedorNorte.add(cantNum);

        contenedorNorte.add(new JLabel("números entre : "));

        JSpinner inicio = new JSpinner(new SpinnerNumberModel(0, 0, diferencial, 1));
        contenedorNorte.add(inicio);

        contenedorNorte.add(new JLabel("hasta"));

        JSpinner fin = new JSpinner(new SpinnerNumberModel(diferencial, diferencial, 100, 1));
        contenedorNorte.add(fin);

        JButton generar = new JButton("Generar");
        generar.setMnemonic(KeyEvent.VK_G);
        contenedorNorte.add(generar);

        JList<Integer> lista = new JList<>(new DefaultListModel<>());
        JScrollPane scrollPane = new JScrollPane(lista);
        Border compundBorder = new CompoundBorder(new TitledBorder("Datos:"), new CompoundBorder(new EmptyBorder(4, 4, 4, 3), scrollPane.getBorder()));
        scrollPane.setBorder(compundBorder);
        add(scrollPane);

        List<Integer> original = new ArrayList<>();

        JPanel contenedorEste = new JPanel(new GridBagLayout());
        add(contenedorEste, BorderLayout.EAST);

        GridBagConstraints gbc = new GridBagConstraints();

        int y = 0;

        JPanel panelOrdenamiento = new JPanel(new GridLayout(0, 1, 6, 6));
        panelOrdenamiento.setBorder(new TitledBorder("Ordenar:"));
        contenedorEste.add(panelOrdenamiento, gbc);

        Map<String, ActionListener> ordenes = getOpciones(original, lista);

        ButtonGroup buttonGroup = new ButtonGroup();

        for (String k : ordenes.keySet()) {
            JRadioButton radioButton = new JRadioButton(k);
            radioButton.addActionListener(ordenes.get(k));
            buttonGroup.add(radioButton);
            radioButton.setSelected(k.equalsIgnoreCase("original"));
            panelOrdenamiento.add(radioButton);
        }

        y++;

        JPanel panelFiltro = new JPanel(new GridLayout(0, 1, 6, 6));
        panelFiltro.setBorder(new TitledBorder("Filtros:"));
        gbc.gridy = y;
        contenedorEste.add(panelFiltro, gbc);

        Map<String, ActionListener> filtros = getFiltros(original, lista);

        for (String k : filtros.keySet()) {
            JRadioButton radioButton = new JRadioButton(k);
            radioButton.addActionListener(filtros.get(k));
            buttonGroup.add(radioButton);
            panelFiltro.add(radioButton);
        }

        // usar al final
        y++;
        gbc.gridy = y;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.weighty = 1.0;
        contenedorEste.add(Box.createVerticalBox(), gbc);

        // eventos

        Function<JSpinner, Integer> valorSig;
        valorSig = spinner -> spinner.getValue() != null ? (Integer) spinner.getModel().getNextValue() : (Integer) spinner.getValue();
        Function<JSpinner, Integer> valorAnt;
        valorAnt = spinner -> spinner.getValue() != null ? (Integer) spinner.getModel().getPreviousValue() : (Integer) spinner.getValue();

        BiConsumer<JSpinner, Function<JSpinner, Integer>> aplicar = (spinner, function) -> spinner.setValue(function.apply(spinner));

        Predicate<JSpinner> limite = spinner -> spinner.getValue().equals(diferencial);

        inicio.addChangeListener(evt -> {
            if (limite.test(inicio) && limite.test(fin))
                aplicar.accept(fin, valorSig);
        });

        fin.addChangeListener(evt -> {
            if (limite.test(fin) && limite.test(inicio))
                aplicar.accept(inicio, valorAnt);
        });

        generar.addActionListener(evt -> {
            Random random = new Random();
            original.clear();
            for (int i=0; i<(Integer) cantNum.getValue(); i++) {
                int origin = (int) inicio.getValue();
                int bound = (int) fin.getValue() + 1;
                original.add(random.nextInt(origin, bound));
            }
            Iterator<AbstractButton> iterator = buttonGroup.getElements().asIterator();
            while (iterator.hasNext()) {
                AbstractButton button = iterator.next();
                if (button.isSelected()) {
                    button.doClick();
                }
            }
        });

        cantNum.addChangeListener(evt -> generar.doClick());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                generar.doClick();
            }
        });

    }

    private Map<String, ActionListener> getFiltros(List<Integer> original, JList<Integer> lista) {
        Function<List<Integer>, List<Integer>> pares = list -> list.stream().filter(v -> v % 2 == 0).collect(Collectors.toList());
        Function<List<Integer>, List<Integer>> impares = list -> list.stream().filter(v -> v % 2 == 1).collect(Collectors.toList());

        Predicate<Integer> primo = n -> {
            int divisores = 0;
            int i = 0;
            while (i <= n && divisores != 2) {
                ++i;
                divisores += n % i == 0 ? 1 : 0;
            }
            return divisores == 2 && i == n;
        };
        Function<List<Integer>, List<Integer>> primos = list -> list.stream().filter(primo).collect(Collectors.toList());

        DefaultListModel<Integer> modelo = (DefaultListModel<Integer>) lista.getModel();

        ActionListener listarPares = actionEvent -> {
            modelo.clear();
            pares.apply(original).forEach(modelo::addElement);
            lista.setModel(modelo);
        };

        ActionListener listarImpares = actionEvent -> {
            modelo.clear();
            impares.apply(original).forEach(modelo::addElement);
            lista.setModel(modelo);
        };

        ActionListener listarPrimos = actionEvent -> {
            modelo.clear();
            primos.apply(original).forEach(modelo::addElement);
            lista.setModel(modelo);
        };

        return Map.ofEntries(
                Map.entry("Pares", listarPares),
                Map.entry("Impares", listarImpares),
                Map.entry("Primos", listarPrimos)
        );
    }

    private Map<String, ActionListener> getOpciones(List<Integer> original, JList<Integer> lista) {
        Function<List<Integer>, List<Integer>> asc = arreglo -> arreglo.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        Function<List<Integer>, List<Integer>> desc = arreglo -> arreglo.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        DefaultListModel<Integer> modelo = (DefaultListModel<Integer>) lista.getModel();

        BiFunction<DefaultListModel<Integer>, List<Integer>, DefaultListModel<Integer>> modeloPoblado;
        modeloPoblado = (model, list) -> {
            model.clear();
            list.forEach(model::addElement); return model;
        };

        ActionListener desordenado = actionEvent -> {
            modelo.clear();
            original.forEach(modelo::addElement);
            lista.setModel(modelo);
        };

        ActionListener ordenarAsc = actionEvent -> lista.setModel(modeloPoblado.apply(modelo, asc.apply(original)));

        ActionListener ordenarDesc = actionEvent -> lista.setModel(modeloPoblado.apply(modelo, desc.apply(original)));

        return new HashMap<>(Map.ofEntries(
                Map.entry("Original", desordenado),
                Map.entry("ASC", ordenarAsc),
                Map.entry("DESC", ordenarDesc)
        ));
    }

    @Override
    public void run() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
