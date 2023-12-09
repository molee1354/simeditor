import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;

public class TextArea {
    public final JScrollPane scrollPane;
    public final JTextArea textArea;

    public TextArea(JTextArea textArea, String fileContents) {
        this.textArea = textArea;
        this.initialize();
        this.fillContents(fileContents);
        this.scrollPane = new JScrollPane(textArea);
    }

    private void fillContents(String fileContents) {
        this.textArea.append(fileContents);
        this.textArea.setCaretPosition(fileContents.length()-1);
    }

    private void initialize() {
        this.textArea.setEditable(true);
        this.textArea.setLineWrap(true);
        this.textArea.setWrapStyleWord(true);
        this.textArea.setFont(new Font("Consolas Mono", Font.PLAIN, 14));

        this.textArea.setBackground(Color.BLACK);
        this.textArea.setForeground(Color.WHITE);
        this.textArea.setCaretColor(Color.YELLOW);
        this.textArea.getCaret().setVisible(true);

        this.textArea.setMargin(new Insets(10, 10, 10, 10));
    }

    public void setFont(Font font) {
        this.textArea.setFont(font);
    }
}
