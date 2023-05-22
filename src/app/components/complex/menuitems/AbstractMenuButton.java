package app.components.complex.menuitems;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import app.components.buttons.ICXButton;
import app.frame.XFrame;
import support.framework.interfaces.Appearance;

public abstract class AbstractMenuButton extends ICXButton {
    protected boolean toggled;
    // Dropdown panel
    protected final DropdownPanel dropdownPanel;

    // "Full" constructor
    protected AbstractMenuButton(Dimension preferredSize, int x, int y, ImageIcon mainIcon, ImageIcon secondaryIcon, XFrame frame,
            Appearance mainAppearance, Appearance dropdownAppearance) {
        super(preferredSize, mainIcon, secondaryIcon, frame, mainAppearance);

        this.toggled = false;

        // Dropdown panel
        this.dropdownPanel = new DropdownPanel(x, y, this.frame, dropdownAppearance);
    }
}
