package support.appdata;

import java.awt.Dimension;
import java.awt.Toolkit;

public final class SizeData {
    private SizeData() {

    }

    // Screen dimension and size
    public static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = SizeData.SCREEN_DIMENSION.width;
    public static final int HEIGHT = SizeData.SCREEN_DIMENSION.height;
    // Half size
    public static final int HALF_WIDTH = SizeData.WIDTH / 2;
    public static final int HALF_HEIGHT = SizeData.HEIGHT / 2;
    public static final Dimension HALF_WINDOW_SIZE = new Dimension(SizeData.HALF_WIDTH, SizeData.HALF_HEIGHT);
    // Half dimension
    public static final Dimension HALF_DIMENSION = new Dimension(SizeData.HALF_WIDTH, SizeData.HALF_HEIGHT);

    // Border size
    public static final int BORDER_SIZE = 1;
    // Font size
    public static final int FONT_SIZE = SizeData.WIDTH / 100;

    // Button sizes
    public static final int NARROW_BUTTON_WIDTH = SizeData.WIDTH * 3 / 100;
    public static final int BUTTON_WIDTH = SizeData.WIDTH * 6 / 100;
    public static final int WIDE_BUTTON_WIDTH = SizeData.WIDTH * 9 / 100;
    public static final int BUTTON_HEIGHT = SizeData.HEIGHT * 4 / 100;
    // Button dimensions
    public static final Dimension NARROW_BUTTON_DIMENSION = new Dimension(SizeData.NARROW_BUTTON_WIDTH, SizeData.BUTTON_HEIGHT);
    public static final Dimension BUTTON_DIMENSION = new Dimension(SizeData.BUTTON_WIDTH, SizeData.BUTTON_HEIGHT);
    public static final Dimension WIDE_BUTTON_DIMENSION = new Dimension(SizeData.WIDE_BUTTON_WIDTH, SizeData.BUTTON_HEIGHT);

    // Scrollbar size
    public static final int SCROLLBAR_WIDTH = SizeData.WIDTH / 135;
    public static final int SCROLLBAR_HEIGHT = SizeData.HEIGHT * 4 / 100;
    // Scrollbar dimension
    public static final Dimension VERTICAL_SCROLLBAR_DIMENSION = new Dimension(SizeData.SCROLLBAR_WIDTH, SizeData.HEIGHT);
    public static final Dimension HORIZONTAL_SCROLLBAR_DIMENSION = new Dimension(SizeData.WIDTH, SizeData.SCROLLBAR_WIDTH);

    // Dropdown panel size
    public static final int DROPDOWN_WIDTH = SizeData.BUTTON_WIDTH + (SizeData.BORDER_SIZE * 2);
    public static final int DROPDOWN_HEIGHT = SizeData.BUTTON_HEIGHT + (SizeData.BORDER_SIZE * 2);
    // Dropdown panel dimension
    public static final Dimension DROPDOWN_DIMENSION = new Dimension(SizeData.DROPDOWN_WIDTH, SizeData.DROPDOWN_HEIGHT);

    // Editor size
    public static final int EDITOR_WIDTH = SizeData.WIDTH / 2;
    public static final int EDITOR_HEIGHT = (SizeData.HEIGHT / 2) + SizeData.GAP;
    // Editor dimension
    public static final Dimension EDITOR_DIMENSION = new Dimension(SizeData.EDITOR_WIDTH, SizeData.EDITOR_HEIGHT);

    // Input field size
    public static final int INPUT_FIELD_WIDTH = SizeData.WIDE_BUTTON_WIDTH;
    public static final int  INPUT_FIELD_HEIGHT = SizeData.BUTTON_HEIGHT * 2;
    // Input field dimension
    public static final Dimension INPUT_FIELD_DIMENSION = new Dimension(SizeData.INPUT_FIELD_WIDTH, SizeData.INPUT_FIELD_HEIGHT);

    // Poule box size
    public static final int BOX_WIDTH = SizeData.WIDTH * 2 / 100;
    public static final int NAME_BOX_WIDTH = SizeData.WIDTH * 8 / 100;
    public static final int BOX_HEIGHT = SizeData.BOX_WIDTH;
    // Poule box dimension
    public static final Dimension BOX_DIMENSION = new Dimension(SizeData.BOX_WIDTH, SizeData.BOX_HEIGHT);

    // Poule panel size
    public static final int POULE_WIDTH = (SizeData.BOX_WIDTH * 14) + SizeData.NAME_BOX_WIDTH + (SizeData.BORDER_SIZE * 2);
    public static final int POULE_HEIGHT = (SizeData.BOX_HEIGHT * 9) + (SizeData.BUTTON_HEIGHT * 5);
    public static final int PREVIEW_POULE_HEIGHT = SizeData.BOX_HEIGHT * 9;
    // Poule panel dimension
    public static final Dimension POULE_DIMENSION = new Dimension(SizeData.POULE_WIDTH, SizeData.POULE_HEIGHT);

    // Competition panel size
    public static final int COMPETITION_PANEL_WIDTH = SizeData.WIDTH;
    public static final int COMPETITION_PANEL_HEIGHT = SizeData.HEIGHT - SizeData.BUTTON_HEIGHT - SizeData.BORDER_SIZE;
    // Competition panel dimension
    public static final Dimension COMPETITION_PANEL_DIMENSION = new Dimension(SizeData.COMPETITION_PANEL_WIDTH, SizeData.COMPETITION_PANEL_HEIGHT);

    // Gaps
    public static final int GAP = SizeData.FONT_SIZE;
}
