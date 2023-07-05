package app.components.complex.menuitems;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import app.frame.XFrame;
import support.constants.PositionConstants;
import support.framework.interfaces.Appearance;

public final class MenuButton extends AbstractMenuButton {
    public MenuButton(Dimension preferredSize, int x, int y, ImageIcon mainIcon, ImageIcon secondaryIcon, XFrame frame, Appearance mainAppearance, Appearance dropdownAppearance) {
        super(preferredSize, x, y, mainIcon, secondaryIcon, frame, mainAppearance, dropdownAppearance);
    }

    public void addOption(JComponent component) {
        this.dropdownPanel.addComponent(component);
    }

    public JComponent removeOption(JComponent component) {
        return this.dropdownPanel.removeComponent(component);
    }

    public List<JComponent> getOptions() {
        return this.dropdownPanel.getComponentList();
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;

        if (!this.toggled) {
            this.button.setIcon(this.mainIcon);
            this.frame.extractComponent(this.dropdownPanel);
        }
        else {
            this.button.setIcon(this.secondaryIcon);
            this.frame.insertComponent(this.dropdownPanel, PositionConstants.TOP_POS);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        this.toggled = !this.toggled;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e); // The button's functionality
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e); // The button's functionality
        final Object source = e.getSource();
        if (source.equals(this.button) && !this.toggled) {
            this.frame.insertComponent(this.dropdownPanel, PositionConstants.TOP_POS);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // The button's functionality
        if (!this.toggled) {
            super.mouseExited(e);
        }

        final Object source = e.getSource();
        if (source.equals(this.button) && !this.toggled) {
            this.frame.extractComponent(this.dropdownPanel);
        }
    }
}
