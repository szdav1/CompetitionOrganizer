package app.components.complex.frameparts;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import app.components.buttons.FCXButton;
import app.frame.XFrame;
import support.constants.PositionConstants;
import support.framework.interfaces.Appearance;

public final class TitleBar extends AbstractTitleBar {
    public TitleBar(ImageIcon icon, String title, XFrame frame, Appearance appearance) {
        super(icon, title, frame, appearance);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object source = e.getSource();
        // Loop through the createMenuButton's options and check for events
        final FCXButton sourceButton = (FCXButton) this.createMenuButton.getOptions().stream()
            .filter(tempButton -> source.equals(((FCXButton) tempButton).getButton()))
            .toList()
            .get(0);

        // Toggled the PouleEditor
        if (sourceButton.equals(this.createMenuButton.getOptions().get(0))) {
            this.createMenuButton.setToggled(false);
            this.frame.togglePouleEditor();
        }
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
