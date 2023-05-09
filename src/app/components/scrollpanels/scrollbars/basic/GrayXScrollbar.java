package app.components.scrollpanels.scrollbars.basic;

import java.awt.Color;
import java.awt.Dimension;

import app.components.scrollpanels.scrollbars.AbstractXScrollbar;
import app.frame.XFrame;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public final class GrayXScrollbar extends AbstractXScrollbar {
    public GrayXScrollbar(Dimension preferredSize, int orientation) {
        super(preferredSize, orientation,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.darkGray)
                .addMainForeground(Color.gray)
                .build());
    }

    public GrayXScrollbar(int orientation) {
        super(orientation,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.darkGray)
                .addMainForeground(Color.gray)
                .build());
    }

    public GrayXScrollbar(Dimension preferredSize, int roundX, int roundY, int orientation) {
        super(preferredSize, roundX, roundY, orientation,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.darkGray)
                .addMainForeground(Color.gray)
                .build());
    }

    public GrayXScrollbar(int roundX, int roundY, int orientation) {
        super(roundX, roundY, orientation,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.darkGray)
                .addMainForeground(Color.gray)
                .build());
    }

    public GrayXScrollbar(AbstractXScrollbar scrollBar) {
        super(scrollBar);
    }

    @Override
    public Appearance getAppearance() {
        return this.appearance;
    }

    // These methods are empty  because the must be an XComponent, but does not have
    // to have a reference to the main frame.
    @Override
    public void repaintFrame() {

    }

    @Override
    public XFrame getFrame() {
        return null;
    }
}
