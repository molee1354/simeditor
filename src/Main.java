import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Editor editor;
        if (args.length == 0) {
            editor = new Editor("");
        } else {
            editor = new Editor(args[0]);
        }
        editor.run();
    }
}
