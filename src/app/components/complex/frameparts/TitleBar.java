package app.components.complex.frameparts;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import app.frame.XFrame;
import support.constants.PositionConstants;
import support.framework.interfaces.Appearance;

public final class TitleBar extends AbstractTitleBar {
    public TitleBar(Dimension preferredSize, ImageIcon icon, String title, XFrame frame, Appearance appearance) {
        super(preferredSize, icon, title, frame, appearance);
    }

    @Override
    public void addComponent(JComponent component, PositionConstants positionConstants) {
        this.add(component, positionConstants.getzIndex());
        this.repaintFrame();
    }

    @Override
    public void addComponent(JComponent component, String borderLayoutPosition) {
        if (this.getLayout() instanceof BorderLayout) {
            this.addComponent(component, borderLayoutPosition);
            this.repaintFrame();
        }
    }

    @Override
    public void addComponent(JComponent component) {
        this.addComponent(component, PositionConstants.MID_POS);
        this.repaintFrame();
    }

    @Override
    public JComponent removeComponent(JComponent component) {
        this.remove(component);
        this.repaintFrame();
        return component;
    }

    @Override
    public void repaintFrame() {
        if (this.frame != null) {
            this.frame.repaint();
        }
    }

    @Override
    public Appearance getAppearance() {
        return this.appearance;
    }

    @Override
    public XFrame getFrame() {
        return this.frame;
    }
}
