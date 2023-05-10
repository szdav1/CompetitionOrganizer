package app.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JComponent;

import app.components.complex.frameparts.TitleBar;
import support.appdata.SizeData;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;

public final class XFrame extends AbstractXFrame {
    // Frame parts
    private final TitleBar titleBar;

    public XFrame(Image iconImage, String title) {
        super(iconImage, title);

        // Frame parts - Automatically added to the frame
        this.titleBar = new TitleBar(new Dimension(this.getWidth(), SizeData.BUTTON_HEIGHT), null, "", this, BasicAppearance.BLACK);

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
