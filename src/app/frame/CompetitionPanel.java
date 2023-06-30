package app.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import app.components.complex.fencing.AbstractCompetitionPanel;
import app.components.complex.fencing.Poule;
import support.constants.PositionConstants;
import support.framework.interfaces.Appearance;

public final class CompetitionPanel extends AbstractCompetitionPanel {
    public CompetitionPanel(XFrame frame, Appearance appearance) {
        super(frame, appearance);
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
    public void actionPerformed(ActionEvent e) {
        final Object source = e.getSource();
        if (source.equals(this.finishButton.getButton())) {
            this.pouleList.forEach(Poule::calculateFencerData);
        }
    }
}
