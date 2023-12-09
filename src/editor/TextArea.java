package editor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TextArea extends JTextArea {
    public File file;
    public String filename;
    private String contents;
    private boolean isTextFile;
    public final JFrame parent;
    private final Configs configs;
    public TextArea(JFrame parent, String filename, Configs configs) {
        this.filename = filename;
        this.file = this.getFileObj();
        this.contents = this.getFileContent();
        this.isTextFile = this.isTextFile();
        this.parent = parent;
        this.configs = configs;

        this.initialize();
    }
    private boolean isTextFile() {
        Pattern pattern = Pattern.compile("[[:alnum:]]*.txt");
        return pattern.matcher(this.filename).find();
    }

    private void fillContents() {
        this.append(this.contents);
        int caretStart;
        if (this.contents.isEmpty())
            caretStart = 0;
        else
            caretStart = this.contents.length()-1;
        this.setCaretPosition(caretStart);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Enable anti-aliasing for text rendering
        if (g instanceof Graphics2D) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }

        super.paintComponent(g);
    }

    private void initialize() {
        this.fillContents();
        this.setEditable(true);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);

        String fontName;
        if (this.configs.fontName.isEmpty()) {
            fontName = this.isTextFile ? "Noto Sans CJK KR" : "FiraCode Nerd Font";
        } else {
            fontName = this.configs.fontName;
        }
        this.setFont(new Font(fontName, Font.PLAIN, this.configs.fontSize));

        this.setBackground(this.configs.bgColor);
        this.setForeground(this.configs.fgColor);
        this.setCaretColor(this.configs.cursorColor);
        this.setMargin(new Insets(this.configs.marginUp,
                                  this.configs.marginLeft,
                                  this.configs.marginDown,
                                  this.configs.marginRight));
        if (this.getGraphics() instanceof Graphics2D) {
            ((Graphics2D) this.getGraphics()).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
    }

    private File getFileObj() {
        File file;
        if (this.filename.isEmpty()) {
            file = this.showFileSelectorDialog();
            this.filename = file.getPath();
        } else {
            file = new File(this.filename);
        }
        return file;
    }
    private String getFileContent() {
        StringBuilder out = new StringBuilder();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                out.append(line);
                out.append("\n");
            }
            scanner.close();
        } catch (FileNotFoundException noFile) {
            System.err.println("File \"" + this.filename + "\" not found.");
            System.exit(1);
        }
        return out.toString();
    }

    public File showFileSelectorDialog() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this.parent);
        String selectedFilePath = "";
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
        } else if (result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this.parent, "File selection canceled.");
        }
        return new File(selectedFilePath);
    }
}
