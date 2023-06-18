package app.components.scrollpanels.scrollbars;

import java.awt.Dimension;

import app.frame.XFrame;
import support.framework.interfaces.Appearance;

public final class XScrollbar extends AbstractXScrollbar {
    public XScrollbar(Dimension preferredSize, int orientation, Appearance appearance) {
        super(preferredSize, orientation, appearance);
    }

    public XScrollbar(int orientation, Appearance appearance) {
        super(orientation, appearance);
    }

    public XScrollbar(Dimension preferredSize, int roundX, int roundY, int orientation, Appearance appearance) {
        super(preferredSize, roundX, roundY, orientation, appearance);
    }

    public XScrollbar(int roundX, int roundY, int orientation, Appearance appearance) {
        super(roundX, roundY, orientation, appearance);
    }

    public XScrollbar(AbstractXScrollbar scrollBar) {
        super(scrollBar);
    }

    @Override
    public Appearance getAppearance() {
        return this.appearance;
    }

    @Override
    public void repaintFrame() {

    }

    @Override
    public XFrame getFrame() {
        return null;
    }
}
