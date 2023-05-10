package app.components.complex.frameparts;

import java.awt.BorderLayout;
import java.awt.Dimension;

import app.components.panels.AbstractXPanel;
import app.frame.XFrame;
import support.appdata.SizeData;
import support.framework.interfaces.Appearance;

public abstract class AbstractCenterPanel extends AbstractXPanel {
    // "Full" constructor
    protected AbstractCenterPanel(XFrame frame, Appearance appearance) {
        super(new Dimension(SizeData.WIDTH, SizeData.HEIGHT - SizeData.BUTTON_HEIGHT), null, frame, appearance);

        // Add the center panel to the frame
        this.frame.addComponent(this, BorderLayout.CENTER);
    }
}
