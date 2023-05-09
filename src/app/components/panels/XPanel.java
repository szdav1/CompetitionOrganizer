package app.components.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JComponent;

import app.frame.XFrame;
import support.constants.PositionConstants;
import support.framework.interfaces.Appearance;

public final class XPanel extends AbstractXPanel {
    public XPanel(XFrame frame) {
        super(frame);
    }

    public XPanel(Dimension preferredSize, LayoutManager layoutManager, XFrame frame, Appearance appearance) {
        super(preferredSize, layoutManager, frame, appearance);
    }

    public XPanel(Dimension preferredSize, XFrame frame, Appearance appearance) {
        super(preferredSize, frame, appearance);
    }

    public XPanel(int x, int y, int width, int height, LayoutManager layoutManager, XFrame frame, Appearance appearance) {
        super(x, y, width, height, layoutManager, frame, appearance);
    }

    public XPanel(int x, int y, int width, int height, XFrame frame, Appearance appearance) {
        super(x, y, width, height, frame, appearance);
    }

    public XPanel(AbstractXPanel panel) {
        super(panel);
    }

    @Override
    public void addComponent(JComponent component, PositionConstants positionConstants) {
        this.add(component, positionConstants.getzIndex());
    }

    @Override
    public void addComponent(JComponent component, String borderLayoutPosition) {
        if (this.getLayout() instanceof BorderLayout) {
            this.add(component, borderLayoutPosition);
        }
    }

    @Override
    public void addComponent(JComponent component) {
        this.addComponent(component, PositionConstants.MID_POS);
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
