package app.components.complex.other;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import app.frame.XFrame;
import support.constants.PositionConstants;
import support.framework.interfaces.Appearance;

public final class Checkbox extends AbstractCheckbox {
    public Checkbox(Dimension preferredSize, String text, XFrame frame, Appearance appearance) {
        super(preferredSize, text, frame, appearance);
    }

    public Checkbox(int x, int y, int width, int height, String text, XFrame frame, Appearance appearance) {
        super(x, y, width, height, text, frame, appearance);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(this.checkLabel)) {
            this.isChecked = !this.isChecked;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource().equals(this.checkLabel)) {
            if (this.isChecked) {
                this.checkLabel.setIcon(this.checkIcon);
            }
            else {
                this.checkLabel.setIcon(null);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void addComponent(JComponent component, PositionConstants positionConstants) {
        this.add(component, positionConstants.getzIndex());
        this.repaintFrame();
    }

    @Override
    public void addComponent(JComponent component, String borderLayoutPosition) {
        if (this.getLayout() instanceof BorderLayout) {
            this.add(component, borderLayoutPosition);
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
