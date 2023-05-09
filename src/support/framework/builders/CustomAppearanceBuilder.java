package support.framework.builders;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.Border;

import support.framework.appearances.CustomAppearance;

public final class CustomAppearanceBuilder extends AbstractCustomAppearanceBuilder {
    public CustomAppearanceBuilder() {

    }

    @Override
    public CustomAppearanceBuilder addMainBackground(Color mainBackground) {
        this.customAppearance.setMainBackground(mainBackground);
        return this;
    }

    @Override
    public CustomAppearanceBuilder addSecondaryBackground(Color secondaryBackground) {
        this.customAppearance.setSecondaryBackground(secondaryBackground);
        return this;
    }

    @Override
    public CustomAppearanceBuilder addMainForeground(Color mainForeground) {
    this.customAppearance.setMainForeground(mainForeground);
    return this;
    }

    @Override
    public CustomAppearanceBuilder addSecondaryForeground(Color secondaryForeground) {
        this.customAppearance.setSecondaryForeground(secondaryForeground);
        return this;
    }

    @Override
    public CustomAppearanceBuilder addBorder(Border border) {
        this.customAppearance.setBorder(border);
        return this;
    }

    @Override
    public CustomAppearanceBuilder addFont(Font font) {
        this.customAppearance.setFont(font);
        return this;
    }

    @Override
    public CustomAppearance build() {
        return this.customAppearance;
    }
}
