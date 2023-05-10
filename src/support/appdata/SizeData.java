package support.appdata;

import java.awt.Dimension;
import java.awt.Toolkit;

public final class SizeData {
    // Screen dimension and size
    public static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = SizeData.SCREEN_DIMENSION.width;
    public static final int HEIGHT = SizeData.SCREEN_DIMENSION.height;
    // Half size
    public static final int HALF_WIDTH = SizeData.WIDTH / 2;
    public static final int HALF_HEIGHT = SizeData.HEIGHT / 2;
    // Half dimension
    public static final Dimension HALF_DIMENSION = new Dimension(SizeData.HALF_WIDTH, SizeData.HALF_HEIGHT);

    // Border size
    public static final int BORDER_SIZE = (int) (SizeData.WIDTH * 0.2 / 100);
    // Font size
    public static final int FONT_SIZE = SizeData.WIDTH / 100;

    // Button sizes
    public static final int NARROW_BUTTON_WIDTH = SizeData.WIDTH * 3 / 100;
    public static final int BUTTON_WIDTH = SizeData.WIDTH * 3 / 100;
    public static final int WIDE_BUTTON_WIDTH = SizeData.WIDTH * 3 / 100;
    public static final int BUTTON_HEIGHT = SizeData.HEIGHT * 4 / 100;
    // Button dimensions
    public static final Dimension NARROW_BUTTON_DIMENSION = new Dimension(SizeData.NARROW_BUTTON_WIDTH, SizeData.BUTTON_HEIGHT);
    public static final Dimension BUTTON_DIMENSION = new Dimension(SizeData.BUTTON_WIDTH, SizeData.BUTTON_HEIGHT);
    public static final Dimension WIDE_BUTTON_DIMENSION = new Dimension(SizeData.WIDE_BUTTON_WIDTH, SizeData.BUTTON_HEIGHT);

    // Scrollbar size
    public static final int SCROLLBAR_WIDTH = SizeData.WIDTH / 100;
    public static final int SCROLLBAR_HEIGHT = SizeData.HEIGHT * 4 / 100;
    // Scrollbar dimension
    public static final Dimension VERTICAL_SCROLLBAR_DIMENSION = new Dimension(SizeData.SCROLLBAR_WIDTH, SizeData.HEIGHT);
    public static final Dimension HORIZONTAL_SCROLLBAR_DIMENSION = new Dimension(SizeData.WIDTH, SizeData.SCROLLBAR_WIDTH);
}
