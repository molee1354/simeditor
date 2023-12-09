import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Editor {
    private final int width;
    private final int height;

    private final File file;

    public Editor(String filename) {
        this.file = new File(filename);
        this.width = 800;
        this.height = 600;
    }
    private String readFile() {
        StringBuilder out = new StringBuilder();
        try {
            Scanner scanner = new Scanner(this.file);
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
        JFrame frame = new JFrame("Window Title");
        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add a JLabel (label) to the frame
        String fileContents = this.readFile();
        JTextArea jTextArea = new JTextArea();

        TextArea textArea = new TextArea(jTextArea, fileContents);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());

        File currentfile = this.file;
        JButton saveButton = this.saveButton(textArea, currentfile);
        JButton savequitButton = this.savequitButton(textArea, currentfile);
        JButton quitButton = this.quitButton();

        buttonPanel.add(saveButton, BorderLayout.WEST);
        buttonPanel.add(savequitButton, BorderLayout.CENTER);
        buttonPanel.add(quitButton, BorderLayout.EAST);
        panel.add(textArea.scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(panel);

        // Set the size of the frame
        frame.setSize(this.width, this.height);

        // Make the frame visible
        frame.setVisible(true);
    }
}
