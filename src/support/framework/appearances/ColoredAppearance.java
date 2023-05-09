package support.framework.appearances;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.Border;

import support.appdata.AppearanceData;
import support.framework.interfaces.Appearance;

public enum ColoredAppearance implements Appearance {
    // Appearances
    RED(Color.red, Color.gray, Color.white, Color.lightGray, null),
    RED_BORDERED(Color.red, Color.gray, Color.white, Color.lightGray, AppearanceData.GRAY_BORDER),

    ORANGE(Color.orange, Color.gray, Color.white, Color.lightGray, null),
    ORANGE_BORDERED(Color.orange, Color.gray, Color.white, Color.lightGray, AppearanceData.GRAY_BORDER),

    YELLOW(Color.yellow, Color.gray, Color.white, Color.lightGray, null),
    YELLOW_BORDERED(Color.yellow, Color.gray, Color.white, Color.lightGray, AppearanceData.GRAY_BORDER);

    // Appearance attributes
    private final Color mainBackground;
    private final Color secondaryBackground;
    private final Color mainForeground;
    private final Color secondaryForeground;
    private final Border border;
    private final Font font;

    ColoredAppearance(Color mainBackground, Color secondaryBackground, Color mainForeground, Color secondaryForeground, Border border) {
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
