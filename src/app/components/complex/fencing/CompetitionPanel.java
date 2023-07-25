package app.components.complex.fencing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JComponent;

import app.components.panels.XPanel;
import app.frame.XFrame;
import app.other.Fencer;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;
import support.framework.interfaces.Appearance;

public final class CompetitionPanel extends AbstractCompetitionPanel {
    // Competition panels - poule, table
    private final TableOnlyCompetitionPanel tablePanel;
    private final PouleOnlyCompetitionPanel poulePanel;

    // Center panel
    private final XPanel centerPanel;

    public CompetitionPanel(XFrame frame, Appearance appearance) {
        super(frame, appearance);
        // Modifications
        this.removeComponent(this.scrollPanel);

        this.closeButton.addActionListener(e -> this.frame.closeCompetitionPanel());

        // Competition panels - poule, table
        this.tablePanel = new TableOnlyCompetitionPanel(this.frame, BasicAppearance.BLACK);

        this.poulePanel = new PouleOnlyCompetitionPanel(this.frame, BasicAppearance.BLACK);

        // Center panel
        this.centerPanel = new XPanel(new Dimension(this.getWidth(), this.getHeight()),
            null, this.frame, BasicAppearance.GRAY);

        this.finishButton.addActionListener(e -> {
            if (this.finishButton.getButton().getText().equalsIgnoreCase("finish all")) {
                this.poulePanel.getPouleList().forEach(Poule::calculateFencerData);
                this.finishButton.getButton().setText("Results");
            }
            else if (this.finishButton.getButton().getText().equalsIgnoreCase("results")) {
                this.poulePanel.calculateAndShowResults();
                this.finishButton.getButton().setText("Continue");
            }
            else {
                this.centerPanel.removeComponent(this.poulePanel);
                this.tablePanel.generateStructure(this.poulePanel.getFencerList());
                this.centerPanel.addComponent(this.tablePanel);
                this.bottomSection.removeComponent(this.finishButton);
            }
        });

        // Add the center panel
        this.addComponent(this.centerPanel, BorderLayout.CENTER);
    }

    public void startCompetition(List<String> valueList, List<Fencer> fencerList) {
        // Generate the poules and add them to the display
        this.poulePanel.generatePoules(valueList, fencerList);
        this.centerPanel.addComponent(this.poulePanel);
    }

    public void reset() {
        this.centerPanel.removeAll();
        this.poulePanel.clearAll();
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
