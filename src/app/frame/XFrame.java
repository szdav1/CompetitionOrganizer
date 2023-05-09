// TODO: Make the XScrollPanel and related classes!!44!!
package app.frame;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JComponent;

import support.constants.PositionConstants;

public final class XFrame extends AbstractXFrame {
    public XFrame(Image iconImage, String title) {
        super(iconImage, title);
        this.setLayout(null);

        // Add components to the frame

        // Set the visibility of the frame
        this.setVisible(true);
    }

    @Override
    public void addComponent(JComponent component, PositionConstants positionConstants) {
        this.add(component, positionConstants.getzIndex());
    }

    @Override
    public void addComponent(JComponent component, String borderLayoutPosition) {
        if (!(this.getLayout() instanceof BorderLayout)) {
            return;
        }

        this.add(component, borderLayoutPosition);
    }

    @Override
    public void addComponent(JComponent component) {
        this.addComponent(component, PositionConstants.MID_POS);
    }

    @Override
    public JComponent removeComponent(JComponent component) {
        this.remove(component);
        return component;
    }
}
