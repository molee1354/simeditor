package editor;

import editor.Panels.ButtonPanel;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    public BottomPanel(TextArea currentBuffer) {
        super(new BorderLayout());
        this.add(new ButtonPanel(currentBuffer), BorderLayout.EAST);
        this.add(new JLabel("  "+currentBuffer.filename), BorderLayout.WEST);
    }

}
