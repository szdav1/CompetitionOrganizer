package support.framework.appearances;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.Border;

import support.framework.interfaces.Appearance;

public final class CustomAppearance implements Appearance {
    private Color mainBackground;
    private Color secondaryBackground;
    private Color mainForeground;
    private Color secondaryForeground;
    private Border border;
    private Font font;

    public CustomAppearance() {}

    @Override
    public Color getMainBackground() {
        return this.mainBackground;
    }

    public void setMainBackground(Color mainBackground) {
        this.mainBackground = mainBackground;
    }

    @Override
    public Color getSecondaryBackground() {
        return this.secondaryBackground;
    }

    public void setSecondaryBackground(Color secondaryBackground) {
        this.secondaryBackground = secondaryBackground;
    }

    @Override
    public Color getMainForeground() {
        return this.mainForeground;
    }

    public void setMainForeground(Color mainForeground) {
        this.mainForeground = mainForeground;
    }

    @Override
    public Color getSecondaryForeground() {
        return this.secondaryForeground;
    }

    public void setSecondaryForeground(Color secondaryForeground) {
        this.secondaryForeground = secondaryForeground;
    }

    @Override
    public Border getBorder() {
        return this.border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    @Override
    public Font getFont() {
        return this.font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
