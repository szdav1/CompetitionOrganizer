package support.appdata;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public final class AppearanceData {
    private AppearanceData() {

    }

    // Colors
    public static final Color OPAQUE = new Color(0.0f, 0.0f, 0.0f, 0.0f);
    public static final Color DARK_GRAY = new Color(0x131313);

    // Borders
    // Basic colored borders
    public static final Border BLACK_BORDER = new LineBorder(Color.black, SizeData.BORDER_SIZE);
    public static final Border WHITE_BORDER = new LineBorder(Color.white, SizeData.BORDER_SIZE);
    public static final Border LIGHT_GRAY_BORDER = new LineBorder(Color.lightGray, SizeData.BORDER_SIZE);
    public static final Border GRAY_BORDER = new LineBorder(Color.gray, SizeData.BORDER_SIZE);
    public static final Border DARK_GRAY_BORDER = new LineBorder(Color.darkGray, SizeData.BORDER_SIZE);
    // Colorful borders
    public static final Border RED_BORDER = new LineBorder(Color.red, SizeData.BORDER_SIZE);
    public static final Border ORANGE_BORDER = new LineBorder(Color.orange, SizeData.BORDER_SIZE);
    public static final Border YELLOW_BORDER = new LineBorder(Color.yellow, SizeData.BORDER_SIZE);

    // Fonts
    public static final Font MAIN_FONT_P = new Font(Font.SANS_SERIF, Font.PLAIN, SizeData.FONT_SIZE);
    public static final Font MAIN_FONT_B = new Font(Font.SANS_SERIF, Font.BOLD, SizeData.FONT_SIZE);
    public static final Font MAIN_FONT_I = new Font(Font.SANS_SERIF, Font.ITALIC, SizeData.FONT_SIZE);
}
