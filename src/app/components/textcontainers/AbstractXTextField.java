package app.components.textcontainers;

import java.awt.Dimension;

import javax.swing.JTextField;

import app.frame.XFrame;
import support.appdata.SizeData;
import support.framework.appearances.BasicAppearance;
import support.framework.factory.ComponentFactory;
import support.framework.interfaces.Appearance;
import support.interfaces.XComponent;

public abstract class AbstractXTextField extends JTextField implements XComponent {
    protected final Appearance appearance;
    protected final XFrame frame;

    // Use default parameters everywhere
    protected AbstractXTextField(XFrame frame) {
        this(SizeData.WIDE_BUTTON_DIMENSION, JTextField.CENTER, frame, BasicAppearance.BLACK_BORDERED);
    }

    // "Full" constructor
    protected AbstractXTextField(Dimension preferredSize, int textAlignment, XFrame frame, Appearance appearance) {
        ComponentFactory.implementAppearance(this, appearance)
            .setPreferredSize(preferredSize);
        this.setHorizontalAlignment(textAlignment);
        this.setBounds(0, 0, preferredSize.width, preferredSize.height);

        this.appearance = appearance;
        this.frame = frame;
    }

    // Default alignment constructor
    protected AbstractXTextField(Dimension preferredSize, XFrame frame, Appearance appearance) {
        ComponentFactory.implementAppearance(this, appearance)
            .setPreferredSize(preferredSize);
        this.setHorizontalAlignment(JTextField.LEFT);
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
    protected AbstractXTextField(int x, int y, int width, int height, int textAlignment, XFrame frame, Appearance appearance) {
        ComponentFactory.implementAppearance(this, appearance)
            .setBounds(x, y, width, height);
        this.setHorizontalAlignment(textAlignment);
        this.setPreferredSize(new Dimension(width, height));

        this.appearance = appearance;
        this.frame = frame;
    }

    // Default alignment constructor
    protected AbstractXTextField(int x, int y, int width, int height, XFrame frame, Appearance appearance) {
        ComponentFactory.implementAppearance(this, appearance)
            .setBounds(x, y, width, height);
        this.setHorizontalAlignment(JTextField.LEFT);
        this.setPreferredSize(new Dimension(width, height));

        this.appearance = appearance;
        this.frame = frame;
    }

    // Special constructor
    protected AbstractXTextField(AbstractXTextField textField) {
        ComponentFactory.implementAppearance(this, textField.getAppearance())
            .setPreferredSize(textField.getPreferredSize());
        this.setBounds(textField.getBounds());

        this.appearance = textField.getAppearance();
        this.frame = textField.getFrame();
    }
}
