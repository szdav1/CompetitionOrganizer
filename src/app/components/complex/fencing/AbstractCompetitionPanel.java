package app.components.complex.fencing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;

import app.components.panels.AbstractXPanel;
import app.components.scrollpanels.XScrollPanel;
import app.frame.XFrame;
import support.appdata.SizeData;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public abstract class AbstractCompetitionPanel extends AbstractXPanel {
    // List for the poules
    protected final List<Poule> pouleList = new LinkedList<>();

    // Scroll panel
    protected final XScrollPanel scrollPanel;

    public AbstractCompetitionPanel(XFrame frame, Appearance appearance) {
        super(0, SizeData.BORDER_SIZE, SizeData.COMPETITION_PANEL_WIDTH,
            SizeData.COMPETITION_PANEL_HEIGHT, null, frame, appearance);

        this.scrollPanel = new XScrollPanel(0, 0, this.getWidth(), this.getHeight(),
            new FlowLayout(FlowLayout.CENTER, SizeData.GAP, SizeData.GAP), this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(this.getAppearance().getMainBackground())
                .addBorder(this.getAppearance().getBorder())
                .build(),
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .build());

        // Add the scroll panel to the panel
        this.addComponent(this.scrollPanel);
    }

    public void generatePoules(List<String> valueList) {
        // Collect the data from the list
        final String round = valueList.get(0);
        final String pouleName = valueList.get(1);
        final int fencersPoule = Integer.parseInt(valueList.get(2));
        int numberOfFencers = Integer.parseInt(valueList.get(3));
        final String referee = valueList.get(4);
        final String date = valueList.get(5);

        // Calculations and Generations
        // Number of poules with the preferred amount of fencers
        if (numberOfFencers <= fencersPoule) {
            final Poule poule = new Poule(this.frame, numberOfFencers, BasicAppearance.BLACK_BORDERED);
            this.pouleList.add(poule);
            this.insertComponent(poule);
        }
        else {
            int numberOfPoules = numberOfFencers / fencersPoule;
            numberOfFencers -= numberOfPoules * fencersPoule;
            for (int i = 0; i < numberOfPoules; i++) {
                final Poule poule = new Poule(this.frame, fencersPoule, BasicAppearance.BLACK_BORDERED);
                this.pouleList.add(poule);
                this.insertComponent(poule);
            }

            if (numberOfFencers > numberOfPoules) {
                final Poule poule = new Poule(this.frame, numberOfFencers, BasicAppearance.BLACK_BORDERED);
                this.pouleList.add(poule);
                this.insertComponent(poule);
            }
            else {
                // Spread the reminders among the poules
                int index = 0;
                while (numberOfFencers > 0) {
                    if (this.pouleList.get(index).getAmount() < 8) {
                        this.pouleList.get(index).reConstruct(this.pouleList.get(0).getAmount() + 1);
                    }

                    index++;

                    if (index >= this.pouleList.size()) {
                        index = 0;
                    }
                    numberOfFencers--;
                }
            }
            // TODO: Handle edge case: When the reminder is smaller than 4 and every poule has 8 fencers and a few more...
        }

        this.scrollPanel.getViewPanel().setPreferredSize(new Dimension(
            this.scrollPanel.getViewPanel().getPreferredSize().width,
            this.pouleList.size() * SizeData.POULE_HEIGHT + SizeData.GAP
        ));
    }

    public void insertComponent(JComponent component, PositionConstants positionConstants) {
        this.scrollPanel.addComponent(component, positionConstants);
    }

    public void insertComponent(JComponent component) {
        this.insertComponent(component, PositionConstants.MID_POS);
    }

    public JComponent extractComponent(JComponent component) {
        this.scrollPanel.removeComponent(component);
        return component;
    }
}