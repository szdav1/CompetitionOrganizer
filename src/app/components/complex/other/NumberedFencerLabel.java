package app.components.complex.other;

import java.awt.BorderLayout;

import javax.swing.JComponent;

import app.frame.XFrame;
import support.constants.PositionConstants;
import support.framework.interfaces.Appearance;

public final class NumberedFencerLabel extends AbstractNumberedFencerLabel {
    public NumberedFencerLabel(String number, String text, XFrame frame, Appearance appearance) {
        super(number, text, frame, appearance);
    }

    public NumberedFencerLabel(int x, int y, String number, String text, XFrame frame, Appearance appearance) {
        super(x, y, number, text, frame, appearance);
    }

    public NumberedFencerLabel(int x, int y, String number, String text, XFrame frame, Appearance appearance, Appearance secondaryAppearance) {
        super(x, y, number, text, frame, appearance, secondaryAppearance);
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
