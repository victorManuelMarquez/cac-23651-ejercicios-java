package desktop;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DemoArchivos extends JFrame implements Runnable {

    private DemoArchivos() throws HeadlessException {
        super("Manejo de archivos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {

        setLayout(new BorderLayout(4, 4));

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("Archivo");
        fileMenu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(fileMenu);

        JMenuItem newFile = new JMenuItem("Nuevo...");
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        fileMenu.add(newFile);

        JMenuItem openFile = new JMenuItem("Abrir...");
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        fileMenu.add(openFile);

        JMenuItem saveFile = new JMenuItem("Guardar");
        saveFile.setEnabled(false);
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
        fileMenu.add(saveFile);

        fileMenu.addSeparator();

        JMenuItem closeFile = new JMenuItem("Cerrar");
        closeFile.setEnabled(false);
        closeFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
        fileMenu.add(closeFile);

        fileMenu.addSeparator();

        JMenuItem exit = new JMenuItem("Salir");
        fileMenu.add(exit);

        JTextArea contentArea = new JTextArea();
        contentArea.setEditable(false);
        contentArea.setEnabled(false);
        contentArea.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
        contentArea.setLineWrap(true);
        add(new JScrollPane(contentArea));

        Box inferiorPanel = Box.createHorizontalBox();
        inferiorPanel.setBorder(new EmptyBorder(0, 4, 4, 4));
        add(inferiorPanel, BorderLayout.SOUTH);

        inferiorPanel.add(new JLabel("Destino:"));
        inferiorPanel.add(Box.createHorizontalStrut(6));

        JTextField pathInfo = new JTextField();
        pathInfo.setEditable(false);
        inferiorPanel.add(pathInfo);
        inferiorPanel.add(Box.createHorizontalStrut(6));

        inferiorPanel.add(new JLabel("Tamaño:"));
        inferiorPanel.add(Box.createHorizontalStrut(6));

        JButton saveBtn = new JButton("Guardar");
        saveBtn.setEnabled(false);
        saveBtn.setMnemonic(KeyEvent.VK_G);
        inferiorPanel.add(saveBtn);

        StringBuilder buffer = new StringBuilder();

        contentArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                habilitarGuardado();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                habilitarGuardado();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                habilitarGuardado();
            }

            private void habilitarGuardado() {
                saveBtn.setEnabled(buffer.compareTo(new StringBuilder(contentArea.getText())) != 0);
            }
        });

        contentArea.addPropertyChangeListener(evt -> {
            boolean textAreaStatus = contentArea.isEnabled();
            saveFile.setEnabled(textAreaStatus);
            closeFile.setEnabled(textAreaStatus);
        });

        newFile.addActionListener(evt -> {
            JFileChooser dialog = new JFileChooser();
            if (dialog.showSaveDialog(DemoArchivos.this) == JFileChooser.APPROVE_OPTION) {
                File file = dialog.getSelectedFile();
                buffer.delete(0, buffer.length());
                contentArea.setText("");
                pathInfo.setText(file.getPath());
                contentArea.setEnabled(true);
                contentArea.setEditable(true);
                saveBtn.setEnabled(true);
            }
        });

        openFile.addActionListener(evt -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto", "txt"));
            if (chooser.showOpenDialog(DemoArchivos.this) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                contentArea.setText("");
                buffer.delete(0, buffer.length());
                try (FileReader reader = new FileReader(file)) {
                    contentArea.read(reader, null);
                    buffer.append(contentArea.getText());
                    contentArea.setEnabled(true);
                    contentArea.setEditable(true);
                    saveBtn.setEnabled(true);
                    pathInfo.setText(file.getPath());
                } catch (IOException e) {
                    contentArea.append(e.getMessage());
                }
            } else Toolkit.getDefaultToolkit().beep();
        });

        saveBtn.addActionListener(evt -> {
            if (!pathInfo.getText().isBlank()) {
                File file = new File(pathInfo.getText());
                try (FileWriter writer = new FileWriter(file)) {
                    contentArea.write(writer);
                    saveBtn.setEnabled(false);
                } catch (IOException e) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(DemoArchivos.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        closeFile.addActionListener(evt -> {
            contentArea.setText("");
            pathInfo.setText("");
            contentArea.setEnabled(false);
            contentArea.setEditable(false);
            saveBtn.setEnabled(false);
        });

        exit.addActionListener(evt -> dispose());

        InputMap map = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK), saveBtn.getText());
        rootPane.getActionMap().put(saveBtn.getText(), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (ActionListener listener : saveBtn.getActionListeners()) {
                    listener.actionPerformed(new ActionEvent(saveBtn, ActionEvent.ACTION_PERFORMED, null));
                }
            }
        });

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(640, 480);
    }

    @Override
    public void run() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new DemoArchivos());
    }

}
