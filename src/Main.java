import editor.Configs;
import editor.Editor;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        String filename;
        if (args.length == 0) {
            filename = "";
        } else {
            filename = args[0];
        }
        Configs configs = new Configs(
                800,
                600,
                16,
                "",
                Color.BLACK,
                Color.WHITE,
                Color.YELLOW,
                10,
                10,
                10,
                10
        );
        Editor editor = new Editor(filename, configs);
    }
}