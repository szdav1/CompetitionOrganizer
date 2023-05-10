package app.components.buttons;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import app.frame.XFrame;
import support.appdata.SizeData;
import support.framework.appearances.BasicAppearance;
import support.framework.factory.ComponentFactory;
import support.framework.interfaces.Appearance;
import support.interfaces.XComponent;

public abstract class AbstractXButton extends JPanel implements XComponent, MouseListener {
    protected final JButton button;
    protected final Appearance mainAppearance;
    protected final Appearance secondaryAppearance;
    protected final XFrame frame;

    // Use default parameters everywhere
    protected AbstractXButton(XFrame frame) {
        this(SizeData.BUTTON_DIMENSION, null, "", frame, BasicAppearance.BLACK_BORDERED, BasicAppearance.LIGHT_GRAY_BORERED);
    }

    // Icon and Text constructor
    protected AbstractXButton(Dimension preferredSize, ImageIcon icon, String text, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        ComponentFactory.implementAppearance(this, secondaryAppearance)
            .setPreferredSize(preferredSize);
        this.setLayout(null);
        this.setBounds(0, 0, preferredSize.width, preferredSize.height);

        this.button = new JButton(text, icon);
        ComponentFactory.implementAppearance(this.button, mainAppearance)
            .setBounds(0, 0, preferredSize.width, preferredSize.height);
        this.button.addMouseListener(this);

        this.mainAppearance = mainAppearance;
        this.secondaryAppearance = secondaryAppearance;
        this.frame = frame;

        // Add the actual button
        this.add(this.button);
    }

    // Icon and no text constructor
    protected AbstractXButton(Dimension preferredSize, ImageIcon icon, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        this(preferredSize, icon, "", frame, mainAppearance, secondaryAppearance);
    }

    // Text and no icon constructor
    protected AbstractXButton(Dimension preferredSize, String text, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        this(preferredSize, null, text, frame, mainAppearance, secondaryAppearance);
    }
    /*
        Constructors above are using a Dimension for preferred size, which is fine if the containing ContainerType's
        layout manager is not null.
        Constructors below are using  x, y, width and height for bounds, which can be used when there's no layout manager.
    */
    // Icon and Text constructor
    protected AbstractXButton(int x, int y, int width, int height, ImageIcon icon, String text, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        ComponentFactory.implementAppearance(this, secondaryAppearance)
            .setBounds(x, y, width, height);
        this.setLayout(null);
        this.setBounds(x, y, width, height);

        this.button = new JButton(text, icon);
        ComponentFactory.implementAppearance(this.button, mainAppearance)
            .setBounds(0, 0, width, height);this.button.addMouseListener(this);
        this.button.addMouseListener(this);

        this.mainAppearance = mainAppearance;
        this.secondaryAppearance = secondaryAppearance;
        this.frame = frame;

        // Add the actual button
        this.add(this.button);
    }

    // Icon and no text constructor
    protected AbstractXButton(int x, int y, int width, int height, ImageIcon icon, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        this(x, y, width, height, icon, "", frame, mainAppearance, secondaryAppearance);
    }

    // Text and no icon constructor
    protected AbstractXButton(int x, int y, int width, int height, String text, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        this(x, y, width, height, null, text, frame, mainAppearance, secondaryAppearance);
    }

    // Special constructor
    protected AbstractXButton(AbstractXButton button) {
        ComponentFactory.implementAppearance(this, button.getSecondaryAppearance())
            .setPreferredSize(button.getPreferredSize());
        this.setBounds(button.getBounds());

        this.button = button.getButton();

        this.mainAppearance = button.getAppearance();
        this.secondaryAppearance = button.getSecondaryAppearance();
        this.frame = button.getFrame();
    }

    public void addActionListener(ActionListener actionListener) {
        this.button.addActionListener(actionListener);
    }

    public JButton getButton() {
        return this.button;
    }

    // The getMainAppearance() method will be in the child classes, because of the XComponent interface

    public Appearance getSecondaryAppearance() {
        return this.secondaryAppearance;
    }
}
