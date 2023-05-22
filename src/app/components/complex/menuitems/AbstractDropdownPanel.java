package app.components.complex.menuitems;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import app.components.panels.AbstractXPanel;
import app.frame.XFrame;
import support.appdata.SizeData;
import support.framework.interfaces.Appearance;

public abstract class AbstractDropdownPanel extends AbstractXPanel {
    protected final List<JComponent> componentList = new ArrayList<>();

    protected AbstractDropdownPanel(int x, int y, XFrame frame, Appearance appearance) {
        super(x, y, SizeData.DROPDOWN_WIDTH, SizeData.DROPDOWN_HEIGHT, null, frame, appearance);
    }

    public List<JComponent> getComponentList() {
        return this.componentList;
    }
}
