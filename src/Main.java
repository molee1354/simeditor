import java.awt.*;

public class Main {
    public static void main(String[] args) {
        String[] fontFamilies = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        // Print the names of available font families
        for (String fontFamily : fontFamilies) {
            System.out.println(fontFamily);
        }
        Editor editor = new Editor(args[0]);
        editor.run();
    }
}