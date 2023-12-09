package editor;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    public TextArea currentBuffer;
    public TopPanel(TextArea currentBuffer) {
        super(new FlowLayout());
        this.currentBuffer = currentBuffer;
    }
}
