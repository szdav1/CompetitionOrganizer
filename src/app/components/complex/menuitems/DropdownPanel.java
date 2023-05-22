package app.components.complex.menuitems;

import java.awt.*;

import javax.swing.JComponent;

import app.frame.XFrame;
import support.appdata.SizeData;
import support.constants.PositionConstants;
import support.framework.interfaces.Appearance;

public final class DropdownPanel extends AbstractDropdownPanel {
    public DropdownPanel(int x, int y, XFrame frame, Appearance appearance) {
        super(x, y, frame, appearance);
    }

    @Override
    public void addComponent(JComponent component, PositionConstants positionConstants) {
        // Calculate overhangs and resize the dropdown panel automatically
        final int overhangX = component.getX() + component.getWidth();
        final int overhangY = component.getY() + component.getHeight();

        if (overhangX > this.getWidth()) {
            this.setSize(overhangX + SizeData.BORDER_SIZE, this.getHeight());
        }

        if (overhangY > this.getHeight()) {
            this.setSize(this.getWidth(), overhangY + SizeData.BORDER_SIZE);
        }

        this.componentList.add(component);
        this.add(component, positionConstants);
    }

    @Override
    public void addComponent(JComponent component, String borderLayoutPosition) {
        // The dropdown panel's layout manager is always a FlowLayout manager
    }

    @Override
    public void addComponent(JComponent component) {
        this.addComponent(component, PositionConstants.MID_POS);
    }

    @Override
    public JComponent removeComponent(JComponent component) {
        this.componentList.remove(component);
        this.remove(component);
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

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

//        Graphics2D graphics2D = (Graphics2D) graphics;
//        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        graphics2D.setPaint(new GradientPaint(0, 0, Color.red, this.getWidth(), this.getHeight(), Color.yellow));
//        graphics2D.drawLine(0, 0, this.getWidth(), 0);
//        graphics2D.drawLine(0, this.getHeight(), this.getWidth(), this.getHeight());
//        graphics2D.drawLine(0, 0, 0, this.getHeight());
//        graphics2D.drawLine(this.getWidth(), 0, this.getWidth(), this.getHeight());
    }
}
