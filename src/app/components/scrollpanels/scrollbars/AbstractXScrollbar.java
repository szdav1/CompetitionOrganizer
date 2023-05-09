package app.components.scrollpanels.scrollbars;

import java.awt.Dimension;

import javax.swing.JScrollBar;

import app.components.scrollpanels.scrollbars.ui.XScrollbarUI;
import support.appdata.SizeData;
import support.framework.interfaces.Appearance;
import support.interfaces.XComponent;

public abstract class AbstractXScrollbar extends JScrollBar implements XComponent {
    protected final Appearance appearance;

    // Construct a scrollbar with the given dimension and orientation
    protected AbstractXScrollbar(Dimension preferredSize, int orientation, Appearance appearance) {
        this.appearance = appearance;

        this.setOrientation(orientation);
        this.setPreferredSize(preferredSize);
        this.setUI(new XScrollbarUI(appearance));
        this.setBorder(null);
    }

    // Construct a scrollbar with the default dimensions and given orientation
    protected AbstractXScrollbar(int orientation, Appearance appearance) {
        this(orientation == JScrollBar.VERTICAL ?
            SizeData.VERTICAL_SCROLLBAR_DIMENSION :
            SizeData.HORIZONTAL_SCROLLBAR_DIMENSION, orientation, appearance);
    }

    // Construct a scrollbar with the given dimension, orientation and roundness
    protected AbstractXScrollbar(Dimension preferredSize, int roundX, int roundY, int orientation, Appearance appearance) {
        this.appearance = appearance;

        this.setOrientation(orientation);
        this.setPreferredSize(preferredSize);
        this.setUI(new XScrollbarUI(roundX, roundY, appearance));
        this.setBorder(null);
    }

    // Construct a scrollbar with the default dimension and given orientation and roundness
    protected AbstractXScrollbar(int roundX, int roundY, int orientation, Appearance appearance) {
        this(orientation == JScrollBar.VERTICAL ?
            SizeData.VERTICAL_SCROLLBAR_DIMENSION :
            SizeData.HORIZONTAL_SCROLLBAR_DIMENSION, roundX, roundY, orientation, appearance);
    }

    // Special constructor
    protected AbstractXScrollbar(AbstractXScrollbar scrollBar) {
        this.appearance = scrollBar.getAppearance();

        this.setOrientation(scrollBar.getOrientation());
        this.setPreferredSize(scrollBar.getPreferredSize());
        this.setUI(scrollBar.getUI());
        this.setBorder(null);
    }
}
