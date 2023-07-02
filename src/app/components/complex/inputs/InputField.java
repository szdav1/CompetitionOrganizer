package app.components.complex.inputs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import app.components.textcontainers.XTextField;
import app.frame.XFrame;
import support.constants.PositionConstants;
import support.framework.interfaces.Appearance;

public final class InputField extends AbstractInputField {
    public InputField(String title, XFrame frame, Appearance panelAppearance, Appearance inputAppearance) {
        super(title, frame, panelAppearance, inputAppearance);
    }

    public InputField(int x, int y, String title, XFrame frame, Appearance panelAppearance, Appearance inputAppearance) {
        super(x, y, title, frame, panelAppearance, inputAppearance);
    }

    public Appearance getInputAppearance() {
        return this.inputField.getAppearance();
    }

    public String getText() {
        return this.inputField.getText();
    }

    public void seText(String text) {
        this.inputField.setText(text);
    }

    public void clearText() {
        this.seText("");
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isRequired() {
        return this.required;
    }

    public void displayError() {
        this.displayError("Invalid Data");
    }

    public void displayError(String message) {
        this.inputField.setText(message);
        this.isErrorPresent = true;
        // Visual display of the error
        this.inputField.setBackground(Color.red);
        this.titleLabel.setForeground(Color.red);
    }

    public void removeError() {
        if (this.isErrorPresent) {
            this.inputField.setText("");
            this.isErrorPresent = false;
            // Reset visual display of the error
            this.inputField.setBackground(this.appearance.getMainBackground());
            this.titleLabel.setForeground(this.appearance.getMainForeground());
        }
    }

    public XTextField getInputField() {
        return this.inputField;
    }

    public boolean isErrorPresent() {
        return this.isErrorPresent;
    }

    public String getDescription() {
        return this.titleLabel.getText();
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

    @Override
    public void focusGained(FocusEvent e) {
        this.removeError();
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.removeError();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.isErrorPresent) {
            this.removeError();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
