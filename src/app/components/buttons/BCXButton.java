package app.components.buttons;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import app.frame.XFrame;
import support.framework.interfaces.Appearance;
/*
    The mainAppearance's mainBackground will be visible by default. When the mouse enters the button, it's background
    changes to the mainAppearance's secondaryBackground. When the mouse is pressed, the background becomes the secondaryAppearance's
    mainBackground, when released it turns to the secondaryDesign's secondaryBackground
*/
public class BCXButton extends AbstractXButton {
    public BCXButton(XFrame frame) {
        super(frame);
    }

    public BCXButton(Dimension preferredSize, ImageIcon icon, String text, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        super(preferredSize, icon, text, frame, mainAppearance, secondaryAppearance);
    }

    public BCXButton(Dimension preferredSize, ImageIcon icon, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        super(preferredSize, icon, frame, mainAppearance, secondaryAppearance);
    }

    public BCXButton(Dimension preferredSize, String text, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        super(preferredSize, text, frame, mainAppearance, secondaryAppearance);
    }

    public BCXButton(int x, int y, int width, int height, ImageIcon icon, String text, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        super(x, y, width, height, icon, text, frame, mainAppearance, secondaryAppearance);
    }

    public BCXButton(int x, int y, int width, int height, ImageIcon icon, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        super(x, y, width, height, icon, frame, mainAppearance, secondaryAppearance);
    }

    public BCXButton(int x, int y, int width, int height, String text, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        super(x, y, width, height, text, frame, mainAppearance, secondaryAppearance);
    }

    public BCXButton(AbstractXButton button) {
        super(button);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Not used
    }

    @Override
    public void mousePressed(MouseEvent e) {
        final Object source = e.getSource();
        if (source.equals(this.button)) {
            this.button.setContentAreaFilled(false);
            this.setBackground(this.secondaryAppearance.getMainBackground());
            this.repaintFrame();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        final Object source = e.getSource();
        if (source.equals(this.button)) {
            this.button.setContentAreaFilled(true);
            this.setBackground(this.secondaryAppearance.getSecondaryBackground());
            this.repaintFrame();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        final Object source = e.getSource();
        if (source.equals(this.button)) {
            this.button.setBackground(this.mainAppearance.getSecondaryBackground());
            this.repaintFrame();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        final Object source = e.getSource();
        if (source.equals(this.button)) {
            this.button.setBackground(this.mainAppearance.getMainBackground());
            this.repaintFrame();
        }
    }

    @Override
    public void repaintFrame() {
        if (this.frame != null) {
            this.frame.repaint();
        }
    }

    @Override
    public Appearance getAppearance() {
        return this.mainAppearance;
    }

    @Override
    public XFrame getFrame() {
        return this.frame;
    }
}
