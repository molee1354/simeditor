package editor;

import java.awt.*;

public class Configs {
    public final int width;
    public final int height;
    public final int fontSize;
    public final String fontName;
    public final Color bgColor;
    public final Color fgColor;
    public final Color cursorColor;

    public final int marginUp;
    public final int marginDown;
    public final int marginRight;
    public final int marginLeft;

    public Configs(int width, int height, int fontSize, String fontName, Color bgColor, Color fgColor, Color cursorColor, int marginUp, int marginDown, int marginRight, int marginLeft) {
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
        this.fontName = fontName;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.cursorColor = cursorColor;
        this.marginUp = marginUp;
        this.marginDown = marginDown;
        this.marginRight = marginRight;
        this.marginLeft = marginLeft;
    }
}
