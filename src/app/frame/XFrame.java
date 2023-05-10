package app.frame;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JComponent;

import app.components.complex.frameparts.CenterPanel;
import app.components.complex.frameparts.TitleBar;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;

public final class XFrame extends AbstractXFrame {
    // Frame parts
    private final TitleBar titleBar;
    private final CenterPanel centerPanel;

    public XFrame(Image iconImage, String title) {
        super(iconImage, title);

        // Frame parts - Automatically added to the frame
        this.titleBar = new TitleBar(null, "", this, BasicAppearance.BLACK);
        this.centerPanel = new CenterPanel(this, BasicAppearance.BLACK);

        // Add components to the frame

        // Set the visibility of the frame
        this.setVisible(true);
    }

    public void insertComponent(JComponent component, PositionConstants positionConstants) {
        this.centerPanel.addComponent(component, positionConstants);
        this.repaint();
    }

    public void insertComponent(JComponent component) {
        this.insertComponent(component, PositionConstants.MID_POS);
        this.repaint();
    }

    public void extractComponent(JComponent component) {
        this.centerPanel.removeComponent(component);
    }

    @Override
    public void addComponent(JComponent component, PositionConstants positionConstants) {
        this.add(component, positionConstants.getzIndex());
        this.repaint();
    }

    @Override
    public void addComponent(JComponent component, String borderLayoutPosition) {
        if (!(this.getLayout() instanceof BorderLayout)) {
            return;
        }

        this.add(component, borderLayoutPosition);
        this.repaint();
    }

    @Override
    public void addComponent(JComponent component) {
        this.addComponent(component, PositionConstants.MID_POS);
        this.repaint();
    }

    @Override
    public JComponent removeComponent(JComponent component) {
        this.remove(component);
        this.repaint();
        return component;
    }
}
