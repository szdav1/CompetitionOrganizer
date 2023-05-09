package app.components.scrollpanels;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.*;

import app.components.scrollpanels.scrollbars.XScrollbar;
import app.frame.XFrame;
import support.appdata.SizeData;
import support.framework.appearances.BasicAppearance;
import support.framework.factory.ComponentFactory;
import support.framework.interfaces.Appearance;
import support.interfaces.ContainerType;
import support.interfaces.XComponent;

public abstract class AbstractXScrollPanel extends JScrollPane implements XComponent, ContainerType {
    protected final JLayeredPane viewPanel;
    protected final Appearance appearance;
    protected final XFrame frame;

    // Use default parameters everywhere
    protected AbstractXScrollPanel(XFrame frame) {
        this(SizeData.HALF_DIMENSION, null, frame, 0, 0, BasicAppearance.WHITE, BasicAppearance.BLACK);
    }

    // "Full" constructor
    protected AbstractXScrollPanel(Dimension preferredSize, LayoutManager layoutManager, XFrame frame, int roundX, int roundY, Appearance panelAppearance,
            Appearance scrollbarAppearance) {
        this.viewPanel = new JLayeredPane();
        this.viewPanel.setOpaque(true);
        this.viewPanel.setLayout(layoutManager);
        ComponentFactory.implementAppearance(this.viewPanel, panelAppearance)
            .setBounds(0, 0, preferredSize.width - SizeData.SCROLLBAR_WIDTH, preferredSize.height - SizeData.SCROLLBAR_HEIGHT);
        this.viewPanel.setBorder(null);

        this.appearance = panelAppearance;
        this.frame = frame;

        ComponentFactory.implementAppearance(this, panelAppearance)
            .setPreferredSize(preferredSize);
        this.setViewportView(this.viewPanel);
        this.setHorizontalScrollBar(new XScrollbar(roundX, roundY, JScrollBar.HORIZONTAL, scrollbarAppearance));
        this.setVerticalScrollBar(new XScrollbar(roundX, roundY, JScrollBar.VERTICAL, scrollbarAppearance));
        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setBounds(0, 0, preferredSize.width, preferredSize.height);
    }

    // "Full" constructor - no rounded edges on the scrollbar
    protected AbstractXScrollPanel(Dimension preferredSize, LayoutManager layoutManager, XFrame frame, Appearance panelAppearance,
            Appearance scrollbarAppearance) {
        this(preferredSize, layoutManager, frame, 0, 0, panelAppearance, scrollbarAppearance);
    }

    // "Full" constructor - using default layout manager (FlowLayout) with rounded corners on the scrollbar
    protected AbstractXScrollPanel(Dimension preferredSize, XFrame frame, int roundX, int roundY, Appearance panelAppearance,
           Appearance scrollbarAppearance) {
        this(preferredSize, null, frame, roundX, roundY, panelAppearance, scrollbarAppearance);
    }

    // "Full" constructor - using default layout manager (FlowLayout) without rounded corners on the scrollbar
    protected AbstractXScrollPanel(Dimension preferredSize, XFrame frame, Appearance panelAppearance, Appearance scrollbarAppearance) {
        this(preferredSize, null, frame, 0, 0, panelAppearance, scrollbarAppearance);
    }
    /*
        Constructors above are using a Dimension for preferred size, which is fine if the containing ContainerType's
        layout manager is not null.
        Constructors below are using  x, y, width and height for bounds, which can be used when there's no layout manager.
    */

    // "Full" constructor
    protected AbstractXScrollPanel(int x, int y, int width, int height, LayoutManager layoutManager, XFrame frame,
            int roundX, int roundY, Appearance panelAppearance, Appearance scrollbarAppearance) {
        this.viewPanel = new JLayeredPane();
        this.viewPanel.setOpaque(true);
        this.viewPanel.setLayout(layoutManager);
        ComponentFactory.implementAppearance(this.viewPanel, panelAppearance)
            .setBounds(0, 0, width - SizeData.SCROLLBAR_WIDTH, height - SizeData.SCROLLBAR_HEIGHT);
        this.viewPanel.setBorder(null);

        this.appearance = panelAppearance;
        this.frame = frame;

        ComponentFactory.implementAppearance(this, panelAppearance)
            .setBounds(x, y, width, height);
        this.setViewportView(this.viewPanel);
        this.setHorizontalScrollBar(new XScrollbar(roundX, roundY, JScrollBar.HORIZONTAL, scrollbarAppearance));
        this.setVerticalScrollBar(new XScrollbar(roundX, roundY, JScrollBar.VERTICAL, scrollbarAppearance));
        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setPreferredSize(new Dimension(width, height));
    }

    // "Full" constructor - no rounded edges on the scrollbar
    protected AbstractXScrollPanel(int x, int y, int width, int height, LayoutManager layoutManager, XFrame frame,
           Appearance panelAppearance, Appearance scrollbarAppearance) {
        this(x, y, width, height, layoutManager, frame, 0, 0, panelAppearance, scrollbarAppearance);
    }

    // "Full" constructor - using default layout manager (FlowLayout) with rounded corners on the scrollbar
    protected AbstractXScrollPanel(int x, int y, int width, int height, XFrame frame, int roundX, int roundY, Appearance panelAppearance,
           Appearance scrollbarAppearance) {
        this(x, y, width, height, null, frame, roundX, roundY, panelAppearance, scrollbarAppearance);
    }

    // "Full" constructor - using default layout manager (FlowLayout) without rounded corners on the scrollbar
    protected AbstractXScrollPanel(int x, int y, int width, int height, XFrame frame, Appearance panelAppearance,
           Appearance scrollbarAppearance) {
        this(x, y, width, height, null, frame, 0, 0, panelAppearance, scrollbarAppearance);
    }

    // Special constructor
    protected AbstractXScrollPanel(AbstractXScrollPanel scrollPanel) {
        this.viewPanel = (JLayeredPane) scrollPanel.getViewPanel();
        this.appearance = scrollPanel.getAppearance();
        this.frame = scrollPanel.getFrame();

        ComponentFactory.implementAppearance(this, scrollPanel.getAppearance())
            .setPreferredSize(scrollPanel.getPreferredSize());
        this.setViewportView(scrollPanel.getViewPanel());
        this.setHorizontalScrollBar(scrollPanel.getHorizontalScrollBar());
        this.setVerticalScrollBar(scrollPanel.getVerticalScrollBar());
        this.setHorizontalScrollBarPolicy(scrollPanel.getHorizontalScrollBarPolicy());
        this.setVerticalScrollBarPolicy(scrollPanel.getVerticalScrollBarPolicy());
        this.setBounds(scrollPanel.getBounds());
    }

    public JComponent getViewPanel() {
        return this.viewPanel;
    }
}
