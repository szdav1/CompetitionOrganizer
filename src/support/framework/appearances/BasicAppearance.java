package support.framework.appearances;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.Border;

import support.appdata.AppearanceData;
import support.framework.interfaces.Appearance;

public enum BasicAppearance implements Appearance {
    // Appearances
    BLACK(Color.black, Color.darkGray, Color.white, Color.gray, null),
    BLACK_BORDERED(Color.black, Color.darkGray, Color.white, Color.gray, AppearanceData.GRAY_BORDER),

    WHITE(Color.white, Color.gray, Color.black, Color.darkGray, null),
    WHITE_BORDERED(Color.white, Color.gray, Color.black, Color.darkGray, AppearanceData.BLACK_BORDER),

    LIGHT_GRAY(Color.lightGray, Color.darkGray, Color.white, Color.gray, null),
    LIGHT_GRAY_BORERED(Color.lightGray, Color.darkGray, Color.white, Color.gray, AppearanceData.GRAY_BORDER),

    GRAY(Color.gray, Color.darkGray, Color.white, Color.lightGray, null),
    GRAY_BORDERED(Color.gray, Color.darkGray, Color.white, Color.lightGray, AppearanceData.DARK_GRAY_BORDER),

    DARK_GRAY(Color.darkGray, Color.gray, Color.white, Color.lightGray, null),
    DARK_GRAY_BORDERED(Color.darkGray, Color.gray, Color.white, Color.lightGray, AppearanceData.GRAY_BORDER),

    OPAQUE(AppearanceData.OPAQUE, AppearanceData.OPAQUE, Color.white, Color.gray, null),
    OPAQUE_BORDERED(AppearanceData.OPAQUE, AppearanceData.OPAQUE, Color.white, Color.gray, AppearanceData.GRAY_BORDER);

    // Appearance attributes
    private final Color mainBackground;
    private final Color secondaryBackground;
    private final Color mainForeground;
    private final Color secondaryForeground;
    private final Border border;
    private final Font font;

    BasicAppearance(Color mainBackground, Color secondaryBackground, Color mainForeground, Color secondaryForeground, Border border) {
        this.mainBackground = mainBackground;
        this.secondaryBackground = secondaryBackground;
        this.mainForeground = mainForeground;
        this.secondaryForeground = secondaryForeground;
        this.border = border;
        // Font is the same for every appearance
        this.font = AppearanceData.MAIN_FONT_P;
    }

    @Override
    public Color getMainBackground() {
        return this.mainBackground;
    }

    @Override
    public Color getSecondaryBackground() {
        return this.secondaryBackground;
    }

    @Override
    public Color getMainForeground() {
        return this.mainForeground;
    }

    @Override
    public Color getSecondaryForeground() {
        return this.secondaryForeground;
    }

    @Override
    public Border getBorder() {
        return this.border;
    }

    @Override
    public Font getFont() {
        return this.font;
    }
}
