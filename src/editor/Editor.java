package editor;

import editor.Panels.ButtonPanel;

import javax.swing.*;
import java.awt.*;

public class Editor {
    public final JFrame frame;
    public final JPanel mainPanel;
    public final BottomPanel bottomPanel;
    public final TopPanel topPanel;
    public final TextArea window;

    public Editor(String filename, Configs configs) {
        this.frame = new JFrame("simedit");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(configs.width, configs.height);

        this.window = new TextArea(this.frame, filename, configs);
        this.bottomPanel = new BottomPanel(this.window);
        this.topPanel = new TopPanel(this.window);

        this.mainPanel = new JPanel(new BorderLayout());
        this.initPanels();
        this.frame.setVisible(true);
    }

    private void initPanels() {
        this.mainPanel.add(this.topPanel, BorderLayout.NORTH);
        this.mainPanel.add(new JScrollPane(this.window), BorderLayout.CENTER);
        this.mainPanel.add(this.bottomPanel, BorderLayout.SOUTH);
        this.frame.getContentPane().add(mainPanel);
    }
}
