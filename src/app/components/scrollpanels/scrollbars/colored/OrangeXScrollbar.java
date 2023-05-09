package app.components.scrollpanels.scrollbars.colored;

import java.awt.Color;
import java.awt.Dimension;

import app.components.scrollpanels.scrollbars.AbstractXScrollbar;
import app.frame.XFrame;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public final class OrangeXScrollbar extends AbstractXScrollbar {
    public OrangeXScrollbar(Dimension preferredSize, int orientation) {
        super(preferredSize, orientation,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.orange)
                .build());
    }

    public OrangeXScrollbar(int orientation) {
        super(orientation,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.orange)
                .build());
    }

    public OrangeXScrollbar(Dimension preferredSize, int roundX, int roundY, int orientation) {
        super(preferredSize, roundX, roundY, orientation,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.orange)
                .build());
    }

    public OrangeXScrollbar(int roundX, int roundY, int orientation) {
        super(roundX, roundY, orientation,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.orange)
                .build());
    }

    public OrangeXScrollbar(AbstractXScrollbar scrollBar) {
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
