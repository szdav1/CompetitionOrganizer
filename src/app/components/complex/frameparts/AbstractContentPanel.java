package app.components.complex.frameparts;

import java.awt.BorderLayout;

import app.components.panels.AbstractXPanel;
import app.frame.XFrame;
import support.appdata.SizeData;
import support.framework.interfaces.Appearance;

public abstract class AbstractContentPanel extends AbstractXPanel {
    public AbstractContentPanel(XFrame frame, Appearance appearance) {
        super(0, 0, SizeData.WIDTH, SizeData.HEIGHT, new BorderLayout(), frame, appearance);
    }
}
