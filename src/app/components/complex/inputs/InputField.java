package app.components.complex.inputs;

import java.awt.BorderLayout;

import javax.swing.JComponent;

import app.frame.XFrame;
import support.constants.PositionConstants;
import support.framework.interfaces.Appearance;

public class InputField extends AbstractInputField {
    public InputField(String title, XFrame frame, Appearance panelAppearance, Appearance inputAppearance) {
        super(title, frame, panelAppearance, inputAppearance);
    }

    public InputField(int x, int y, String title, XFrame frame, Appearance panelAppearance, Appearance inputAppearance) {
        super(x, y, title, frame, panelAppearance, inputAppearance);
    }

    public Appearance getInputAppearance() {
        return this.inputField.getAppearance();
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
