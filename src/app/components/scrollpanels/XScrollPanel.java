package app.components.scrollpanels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JComponent;

import app.frame.XFrame;
import support.constants.PositionConstants;
import support.framework.interfaces.Appearance;

public final class XScrollPanel extends AbstractXScrollPanel {
    public XScrollPanel(XFrame frame) {
        super(frame);
    }

    public XScrollPanel(Dimension preferredSize, LayoutManager layoutManager, XFrame frame, int roundX, int roundY, Appearance panelAppearance, Appearance scrollbarAppearance) {
        super(preferredSize, layoutManager, frame, roundX, roundY, panelAppearance, scrollbarAppearance);
    }

    public XScrollPanel(Dimension preferredSize, LayoutManager layoutManager, XFrame frame, Appearance panelAppearance, Appearance scrollbarAppearance) {
        super(preferredSize, layoutManager, frame, panelAppearance, scrollbarAppearance);
    }

    public XScrollPanel(Dimension preferredSize, XFrame frame, int roundX, int roundY, Appearance panelAppearance, Appearance scrollbarAppearance) {
        super(preferredSize, frame, roundX, roundY, panelAppearance, scrollbarAppearance);
    }

    public XScrollPanel(Dimension preferredSize, XFrame frame, Appearance panelAppearance, Appearance scrollbarAppearance) {
        super(preferredSize, frame, panelAppearance, scrollbarAppearance);
    }

    public XScrollPanel(int x, int y, int width, int height, LayoutManager layoutManager, XFrame frame, int roundX, int roundY, Appearance panelAppearance, Appearance scrollbarAppearance) {
        super(x, y, width, height, layoutManager, frame, roundX, roundY, panelAppearance, scrollbarAppearance);
    }

    public XScrollPanel(int x, int y, int width, int height, LayoutManager layoutManager, XFrame frame, Appearance panelAppearance, Appearance scrollbarAppearance) {
        super(x, y, width, height, layoutManager, frame, panelAppearance, scrollbarAppearance);
    }

    public XScrollPanel(int x, int y, int width, int height, XFrame frame, int roundX, int roundY, Appearance panelAppearance, Appearance scrollbarAppearance) {
        super(x, y, width, height, frame, roundX, roundY, panelAppearance, scrollbarAppearance);
    }

    public XScrollPanel(int x, int y, int width, int height, XFrame frame, Appearance panelAppearance, Appearance scrollbarAppearance) {
        super(x, y, width, height, frame, panelAppearance, scrollbarAppearance);
    }

    public XScrollPanel(AbstractXScrollPanel scrollPanel) {
        super(scrollPanel);
    }

    @Override
    public void addComponent(JComponent component, PositionConstants positionConstants) {
        // Calculate overhang and modify viewPanel size
        final int overhangX = component.getX() + component.getWidth();
        final int overhangY = component.getY() + component.getHeight();

        if (overhangX > this.viewPanel.getPreferredSize().width) {
            this.viewPanel.setPreferredSize(new Dimension(overhangX, this.viewPanel.getPreferredSize().height));
        }

        if (overhangY > this.viewPanel.getPreferredSize().height) {
            this.viewPanel.setPreferredSize(new Dimension(this.viewPanel.getPreferredSize().width, overhangY));
        }

        // Add the component
        this.viewPanel.add(component, positionConstants.getzIndex());
    }

    @Override
    public void addComponent(JComponent component, String borderLayoutPosition) {
        if (this.getLayout() instanceof BorderLayout) {
            this.viewPanel.add(component, borderLayoutPosition);
        }
    }

    @Override
    public void addComponent(JComponent component) {
        this.addComponent(component, PositionConstants.MID_POS);
    }

    @Override
    public JComponent removeComponent(JComponent component) {
        this.viewPanel.remove(component);
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
