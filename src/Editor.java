import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Editor {
    private final int width;
    private final int height;
    private final String filename;
    private File file;

    public Editor(String filename) {
        this.filename = filename;
        this.width = 800;
        this.height = 600;
    }
    private String readFile(JFrame frame) {
        File file;
        if (!this.filename.isEmpty()) {
            file = new File(this.filename);
        } else {
            file = new File(showFileSelectorDialog(frame));
        }
        this.file = file;
        StringBuilder out = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                out.append(line);
                out.append("\n");
            }
            scanner.close();
        } catch  (FileNotFoundException nofile){
            System.err.println("File not found");
            nofile.printStackTrace();
        }
        return out.toString();
    }

    private static JButton openButton(JFrame frame) {
        JButton fileSelectorButton = new JButton("open");
        fileSelectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFileSelectorDialog(frame);
            }
        });
        return fileSelectorButton;
    }
    private static String showFileSelectorDialog(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();

        // Optionally, set the starting directory
        // fileChooser.setCurrentDirectory(new File("C:\\"));

        int result = fileChooser.showOpenDialog(frame);

        String selectedFilePath = "";
        if (result == JFileChooser.APPROVE_OPTION) {
            // User selected a file
            selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            JOptionPane.showMessageDialog(frame, "Selected File: " + selectedFilePath);
        } else if (result == JFileChooser.CANCEL_OPTION) {
            // User canceled the operation
            JOptionPane.showMessageDialog(frame, "File selection canceled.");
        }
        return selectedFilePath;
    }

    private static void saveFile(TextArea textArea, File target) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            writer.write(textArea.textArea.getText());
            JOptionPane.showMessageDialog(null, "File saved successfully.");
        } catch (IOException fileSaveError) {
            fileSaveError.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving file.");
        }
    }

    private JButton saveButton(TextArea contents, File target) {
        JButton out = new JButton("save");
        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile(contents, target);
            }
        });
        return out;
    }

    private JButton savequitButton(TextArea contents, File target) {
        JButton out = new JButton("save & exit");
        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile(contents, target);
                System.exit(0);
            }
        });
        return out;
    }
    private JButton quitButton() {
        JButton out = new JButton("quit");
        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return out;
    }

    public void run() {
        JFrame frame = new JFrame("Text Editor");
        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add a JLabel (label) to the frame
        String fileContents = this.readFile(frame);
        TextArea textArea = getTextArea(fileContents);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());

        File currentfile = this.file;
        JButton saveButton = this.saveButton(textArea, currentfile);
        JButton savequitButton = this.savequitButton(textArea, currentfile);
        JButton openButton = this.openButton(frame);
        JButton quitButton = this.quitButton();

        buttonPanel.add(saveButton, BorderLayout.WEST);
        buttonPanel.add(savequitButton, BorderLayout.CENTER);
        buttonPanel.add(quitButton, BorderLayout.EAST);
        buttonPanel.add(openButton, BorderLayout.EAST);
        panel.add(textArea.scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(panel);

        // Set the size of the frame
        frame.setSize(this.width, this.height);

        // Make the frame visible
        frame.setVisible(true);
    }

    private static TextArea getTextArea(String fileContents) {
        JTextArea jTextArea = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                // Enable anti-aliasing for text rendering
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                // Let the superclass handle the rest of the painting
                super.paintComponent(g);
            }
        };

        TextArea textArea = new TextArea(jTextArea, fileContents);
        return textArea;
    }
}
