package app.components.textcontainers;

import java.awt.Dimension;

import javax.swing.JTextArea;

import app.frame.XFrame;
import support.appdata.SizeData;
import support.framework.appearances.BasicAppearance;
import support.framework.factory.ComponentFactory;
import support.framework.interfaces.Appearance;
import support.interfaces.XComponent;

public abstract class AbstractXTextArea extends JTextArea implements XComponent {
    protected final Appearance appearance;
    protected final XFrame frame;

    // Use default parameters everywhere
    protected AbstractXTextArea(XFrame frame) {
        this(new Dimension(SizeData.WIDE_BUTTON_WIDTH, SizeData.WIDE_BUTTON_WIDTH), frame, BasicAppearance.BLACK_BORDERED);
    }

    // "Full" constructor
    protected AbstractXTextArea(Dimension preferredSize, XFrame frame, Appearance appearance) {
        ComponentFactory.implementAppearance(this, appearance)
            .setPreferredSize(preferredSize);
        this.setBounds(0, 0, preferredSize.width, preferredSize.height);

        this.appearance = appearance;
        this.frame = frame;
    }
    /*
        Constructors above are using a Dimension for preferred size, which is fine if the containing ContainerType's
        layout manager is not null.
        Constructors below are using  x, y, width and height for bounds, which can be used when there's no layout manager.
    */
    // "Full" constructor
    protected AbstractXTextArea(int x, int y, int width, int height, XFrame frame, Appearance appearance) {
        ComponentFactory.implementAppearance(this, appearance)
            .setBounds(x, y, width, height);
        this.setPreferredSize(new Dimension(width, height));

        this.appearance = appearance;
        this.frame = frame;
    }

    // Special constructor
    protected AbstractXTextArea(AbstractXTextArea textArea) {
        ComponentFactory.implementAppearance(this, textArea.getAppearance())
            .setPreferredSize(textArea.getPreferredSize());
        this.setBounds(textArea.getBounds());

        this.appearance = textArea.getAppearance();
        this.frame = textArea.getFrame();
    }
}
