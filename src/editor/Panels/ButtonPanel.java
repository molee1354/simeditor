package editor.Panels;

import editor.TextArea;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ButtonPanel extends JPanel {
    public ButtonPanel(TextArea currentBuffer) {
        super(new FlowLayout());
        this.add(makeSaveButton(currentBuffer));
        this.add(makeSaveQuitButton(currentBuffer));
        this.add(makeQuitButton());
    }

    private static void saveFile(TextArea textArea) {
        File outFile = new File(textArea.filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            writer.write(textArea.getText());
            JOptionPane.showMessageDialog(null, "File saved successfully.");
        } catch (IOException fileSaveError) {
            JOptionPane.showMessageDialog(null, "Error saving file.");
        }
    }

    private static JButton makeSaveButton(TextArea buffer) {
        JButton out = new JButton("save");
        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile(buffer);
            }
        });
        return out;
    }

    private static JButton makeQuitButton() {
        JButton out = new JButton("quit");
        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return out;
    }

    private static JButton makeSaveQuitButton(TextArea buffer) {
        JButton out = new JButton("save & exit");
        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile(buffer);
                System.exit(0);
            }
        });
        return out;
    }
}