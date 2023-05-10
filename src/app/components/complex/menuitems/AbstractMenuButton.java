package app.components.complex.menuitems;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import app.components.buttons.ICXButton;
import app.components.panels.XPanel;
import app.frame.XFrame;
import support.appdata.SizeData;
import support.framework.interfaces.Appearance;
import support.interfaces.ContainerType;

public abstract class AbstractMenuButton extends ICXButton implements ContainerType {
    protected boolean toggled;
    // List for the components in the dropdown panel
    protected final List<JComponent> componentList = new LinkedList<>();
    // Dropdown panel
    protected final XPanel dropdownPanel;

    // "Full" constructor
    protected AbstractMenuButton(Dimension preferredSize, int x, int y, ImageIcon mainIcon, ImageIcon secondaryIcon, XFrame frame,
            Appearance mainAppearance, Appearance dropdownAppearance) {
        super(preferredSize, mainIcon, secondaryIcon, frame, mainAppearance);
        this.setLocation(x, y);

        this.toggled = false;

        // Dropdown panel
        this.dropdownPanel = new XPanel(this.getX(), this.getY(), SizeData.BUTTON_WIDTH, SizeData.BUTTON_HEIGHT * 2,
            null, this.frame, dropdownAppearance);
    }

    public List<JComponent> getComponentList() {
        return this.componentList;
    }

    public XPanel getDropdownPanel() {
        return this.dropdownPanel;
    }
}
