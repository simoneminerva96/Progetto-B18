package Client.Graphics.Fonts;

import org.newdawn.slick.util.ResourceLoader;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Stefano
 * Classe del font utilizzato nel gioco.
 */

public class TriviaFont {
    private InputStream inputStream;

    public TriviaFont () {
        inputStream	= ResourceLoader.getResourceAsStream("res/fonts/Silkscreen/slkscr.ttf");
    }

    public java.awt.Font getFont() {
        java.awt.Font font = null;
        try {
            font= java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,inputStream);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return font;
    }
}
