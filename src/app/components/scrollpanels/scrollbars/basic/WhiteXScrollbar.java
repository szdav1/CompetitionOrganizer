package app.components.scrollpanels.scrollbars.basic;

import java.awt.Dimension;

import app.components.scrollpanels.scrollbars.AbstractXScrollbar;
import app.frame.XFrame;
import support.framework.appearances.BasicAppearance;
import support.framework.interfaces.Appearance;

public final class WhiteXScrollbar extends AbstractXScrollbar {
    public WhiteXScrollbar(Dimension preferredSize, int orientation) {
        super(preferredSize, orientation, BasicAppearance.BLACK);
    }

    public WhiteXScrollbar(int orientation) {
        super(orientation, BasicAppearance.BLACK);
    }

    public WhiteXScrollbar(Dimension preferredSize, int roundX, int roundY, int orientation) {
        super(preferredSize, roundX, roundY, orientation, BasicAppearance.BLACK);
    }

    public WhiteXScrollbar(int roundX, int roundY, int orientation) {
        super(roundX, roundY, orientation, BasicAppearance.BLACK);
    }

    public WhiteXScrollbar(AbstractXScrollbar scrollBar) {
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
