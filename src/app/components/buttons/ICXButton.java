package app.components.buttons;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import app.frame.XFrame;
import support.framework.interfaces.Appearance;
/*
    When the mouse enters the button, the icon will change to the secondaryIcon.
    When the mouse is pressed, the icon changes back to the mainIcon and when released it changes back
    to the secondaryIcon. When the mouse exits the button, the mainIcon will be visible again.
*/
public class ICXButton extends AbstractXButton {
    protected final ImageIcon mainIcon;
    protected final ImageIcon secondaryIcon;

    public ICXButton(Dimension preferredSize, ImageIcon mainIcon, ImageIcon secondaryIcon, XFrame frame, Appearance mainAppearance) {
        super(preferredSize, mainIcon, frame, mainAppearance, mainAppearance);
        this.button.setContentAreaFilled(false);

        this.mainIcon = mainIcon;
        this.secondaryIcon = secondaryIcon;
    }

    public ICXButton(int x, int y, int width, int height, ImageIcon mainIcon, ImageIcon secondaryIcon, XFrame frame, Appearance mainAppearance) {
        super(x, y, width, height, mainIcon, frame, mainAppearance, mainAppearance);
        this.button.setContentAreaFilled(false);

        this.mainIcon = mainIcon;
        this.secondaryIcon = secondaryIcon;
    }

    public ICXButton(ICXButton button) {
        super(button);

        this.mainIcon = button.getMainIcon();
        this.secondaryIcon = button.getSecondaryIcon();
    }

    public ImageIcon getMainIcon() {
        return this.mainIcon;
    }

    public ImageIcon getSecondaryIcon() {
        return this.secondaryIcon;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Not used
    }

    @Override
    public void mousePressed(MouseEvent e) {
        final Object source = e.getSource();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        final Object source = e.getSource();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        final Object source = e.getSource();
        if (e.getSource().equals(this.button)) {
            this.button.setIcon(null);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        final Object source = e.getSource();
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
