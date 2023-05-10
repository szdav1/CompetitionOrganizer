package app.components.buttons;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import app.frame.XFrame;
import support.framework.interfaces.Appearance;
/*
    The mainAppearance is for the background, the secondaryAppearance is for the foreground.
    The secondaryAppearance's main- and secondaryForeground will be used for the foreground based on the occurring
    mouse event.
    This functionality can be best used with CustomAppearances.
*/
public class FCXButton extends AbstractXButton {
    public FCXButton(XFrame frame) {
        super(frame);
    }

    public FCXButton(Dimension preferredSize, ImageIcon icon, String text, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        super(preferredSize, icon, text, frame, mainAppearance, secondaryAppearance);
    }

    public FCXButton(Dimension preferredSize, ImageIcon icon, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        super(preferredSize, icon, frame, mainAppearance, secondaryAppearance);
    }

    public FCXButton(Dimension preferredSize, String text, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        super(preferredSize, text, frame, mainAppearance, secondaryAppearance);
    }

    public FCXButton(int x, int y, int width, int height, ImageIcon icon, String text, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        super(x, y, width, height, icon, text, frame, mainAppearance, secondaryAppearance);
    }

    public FCXButton(int x, int y, int width, int height, ImageIcon icon, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        super(x, y, width, height, icon, frame, mainAppearance, secondaryAppearance);
    }

    public FCXButton(int x, int y, int width, int height, String text, XFrame frame, Appearance mainAppearance, Appearance secondaryAppearance) {
        super(x, y, width, height, text, frame, mainAppearance, secondaryAppearance);
    }

    public FCXButton(AbstractXButton button) {
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
            this.button.setForeground(this.secondaryAppearance.getSecondaryForeground());
            this.repaintFrame();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        final Object source = e.getSource();
        if (source.equals(this.button)) {
            this.button.setForeground(this.secondaryAppearance.getMainForeground());
            this.repaintFrame();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        final Object source = e.getSource();
        if (source.equals(this.button)) {
            this.button.setContentAreaFilled(false);
            this.button.setForeground(this.secondaryAppearance.getMainForeground());
            this.repaintFrame();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        final Object source = e.getSource();
        if (source.equals(this.button)) {
            this.button.setContentAreaFilled(true);
            this.button.setForeground(this.mainAppearance.getMainForeground());
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
