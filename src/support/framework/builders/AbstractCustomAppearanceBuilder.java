package support.framework.builders;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.Border;

import support.framework.appearances.CustomAppearance;

public abstract class AbstractCustomAppearanceBuilder {
    protected final CustomAppearance customAppearance;

    protected AbstractCustomAppearanceBuilder() {
        this.customAppearance = new CustomAppearance();
    }

    protected abstract AbstractCustomAppearanceBuilder addMainBackground(Color mainBackground);
    protected abstract AbstractCustomAppearanceBuilder addSecondaryBackground(Color secondaryBackground);
    protected abstract AbstractCustomAppearanceBuilder addMainForeground(Color mainForeground);
    protected abstract AbstractCustomAppearanceBuilder addSecondaryForeground(Color secondaryForeground);
    protected abstract AbstractCustomAppearanceBuilder addBorder(Border border);
    protected abstract AbstractCustomAppearanceBuilder addFont(Font font);
    protected abstract CustomAppearance build();
}
