package app.components.complex.menuitems;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import app.frame.XFrame;
import support.appdata.SizeData;
import support.constants.PositionConstants;
import support.framework.interfaces.Appearance;

public final class MenuButton extends AbstractMenuButton {
    public MenuButton(Dimension preferredSize, int x, int y, ImageIcon mainIcon, ImageIcon secondaryIcon, XFrame frame, Appearance mainAppearance, Appearance dropdownAppearance) {
        super(preferredSize, x, y, mainIcon, secondaryIcon, frame, mainAppearance, dropdownAppearance);
    }

    @Override
    public void addComponent(JComponent component, PositionConstants positionConstants) {
        // Calculate overhangs and resize the dropdown panel automatically
        final int overhangX = component.getX() + component.getWidth();
        final int overhangY = component.getY() + component.getHeight();

        if (overhangX > this.dropdownPanel.getWidth()) {
            this.dropdownPanel.setSize(overhangX + SizeData.BORDER_SIZE, this.dropdownPanel.getHeight());
        }

        if (overhangY > this.dropdownPanel.getHeight()) {
            this.dropdownPanel.setSize(this.dropdownPanel.getWidth(), overhangY + SizeData.BORDER_SIZE);
        }

        this.componentList.add(component);
        this.dropdownPanel.addComponent(component, positionConstants);
    }

    @Override
    public void addComponent(JComponent component, String borderLayoutPosition) {
        // The dropdown panel's layout manager is always a FlowLayout manager
    }

    @Override
    public void addComponent(JComponent component) {
        this.addComponent(component, PositionConstants.MID_POS);
    }

    @Override
    public JComponent removeComponent(JComponent component) {
        this.componentList.remove(component);
        return this.dropdownPanel.removeComponent(component);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e); // The button's functionality
        final Object source = e.getSource();
        if (source.equals(this.button)) {
            this.toggled = !this.toggled;
        }
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
            this.frame.insertComponent(this.dropdownPanel);
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
