package app.components.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JLayeredPane;

import app.frame.XFrame;
import support.appdata.SizeData;
import support.framework.appearances.BasicAppearance;
import support.framework.factory.ComponentFactory;
import support.framework.interfaces.Appearance;
import support.interfaces.ContainerType;
import support.interfaces.XComponent;

public abstract class AbstractXPanel extends JLayeredPane implements ContainerType, XComponent {
    protected final Appearance appearance;
    protected final XFrame frame;

    // Use default parameters everywhere
    protected AbstractXPanel(XFrame frame) {
        this(new Dimension(SizeData.WIDE_BUTTON_WIDTH, SizeData.WIDE_BUTTON_WIDTH), new FlowLayout(), frame, BasicAppearance.BLACK_BORDERED);
    }

    // "Full" constructor
    protected AbstractXPanel(Dimension preferredSize, LayoutManager layoutManager, XFrame frame, Appearance appearance) {
        ComponentFactory.implementAppearance(this, appearance)
            .setPreferredSize(preferredSize);
        this.setLayout(layoutManager);
        this.setOpaque(true);
        this.setBounds(0, 0, preferredSize.width, preferredSize.height);

        this.appearance = appearance;
        this.frame = frame;
    }

    // Default LayoutManager constructor
    protected AbstractXPanel(Dimension preferredSize, XFrame frame, Appearance appearance) {
        this(preferredSize, new FlowLayout(), frame, appearance);
    }
    /*
        Constructors above are using a Dimension for preferred size, which is fine if the containing ContainerType's
        layout manager is not null.
        Constructors below are using  x, y, width and height for bounds, which can be used when there's no layout manager.
    */
    // "Full" constructor
    protected AbstractXPanel(int x, int y, int width, int height, LayoutManager layoutManager, XFrame frame, Appearance appearance) {
        ComponentFactory.implementAppearance(this, appearance)
            .setBounds(x, y, width, height);
        this.setLayout(layoutManager);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(width, height));

        this.appearance = appearance;
        this.frame = frame;
    }

    // Default LayoutManager constructor
    protected AbstractXPanel(int x, int y, int width, int height, XFrame frame, Appearance appearance) {
        this(x, y, width, height, new FlowLayout(), frame, appearance);
    }

    // Special constructor
    protected AbstractXPanel(AbstractXPanel panel) {
        ComponentFactory.implementAppearance(this, panel.getAppearance())
            .setPreferredSize(panel.getPreferredSize());
        this.setLayout(panel.getLayout());
        this.setOpaque(true);
        this.setBounds(panel.getBounds());

        this.appearance = panel.getAppearance();
        this.frame = panel.getFrame();
    }
}
