package app.components.labels;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import app.frame.XFrame;
import support.appdata.SizeData;
import support.framework.appearances.BasicAppearance;
import support.framework.factory.ComponentFactory;
import support.framework.interfaces.Appearance;
import support.interfaces.XComponent;

public abstract class AbstractXLabel extends JLabel implements XComponent {
    protected final Appearance appearance;
    protected final XFrame frame;

    // Use default parameters everywhere
    protected AbstractXLabel(XFrame frame) {
        this(SizeData.BUTTON_DIMENSION, null, "New Label", frame, BasicAppearance.OPAQUE_BORDERED);
    }

    // Icon and Text constructor
    protected AbstractXLabel(Dimension preferredSize, ImageIcon icon, String text, XFrame frame, Appearance appearance) {
        ComponentFactory.implementAppearance(this, appearance)
            .setPreferredSize(preferredSize);
        this.setIcon(icon);
        this.setText(text);
        this.setBounds(0, 0, preferredSize.width, preferredSize.height);

        this.appearance = appearance;
        this.frame = frame;
    }

    // Icon and no text constructor
    protected AbstractXLabel(Dimension preferredSize, ImageIcon icon, XFrame frame, Appearance appearance) {
        this(preferredSize, icon, "", frame, appearance);
    }

    // Text and no icon constructor
    protected AbstractXLabel(Dimension preferredSize, String text, XFrame frame, Appearance appearance) {
        this(preferredSize, null, text, frame, appearance);
    }
    /*
        Constructors above are using a Dimension for preferred size, which is fine if the containing ContainerType's
        layout manager is not null.
        Constructors below are using  x, y, width and height for bounds, which can be used when there's no layout manager.
    */
    // Icon and Text constructor
    protected AbstractXLabel(int x, int y, int width, int height, ImageIcon icon, String text, XFrame frame, Appearance appearance) {
        ComponentFactory.implementAppearance(this, appearance)
            .setBounds(x, y, width, height);
        this.setIcon(icon);
        this.setText(text);
        this.setPreferredSize(new Dimension(width, height));

        this.appearance = appearance;
        this.frame = frame;
    }

    // Icon and no text constructor
    protected AbstractXLabel(int x, int y, int width, int height, ImageIcon icon, XFrame frame, Appearance appearance) {
        this(x, y, width, height, icon, "", frame, appearance);
    }

    // Text and no icon constructor
    protected AbstractXLabel(int x, int y, int width, int height, String text, XFrame frame, Appearance appearance) {
        this(x, y, width, height, null, text, frame, appearance);
    }

    // Special constructor
    protected AbstractXLabel(AbstractXLabel label) {
        ComponentFactory.implementAppearance(this, label.getAppearance())
            .setPreferredSize(label.getPreferredSize());
        this.setIcon(label.getIcon());
        this.setText(label.getText());
        this.setBounds(label.getBounds());

        this.appearance = label.getAppearance();
        this.frame = label.getFrame();
    }
}
