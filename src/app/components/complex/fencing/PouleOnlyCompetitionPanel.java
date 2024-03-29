package app.components.complex.fencing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JComponent;

import app.components.panels.XPanel;
import app.frame.XFrame;
import app.other.Fencer;
import support.appdata.AppearanceData;
import support.appdata.SizeData;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public final class PouleOnlyCompetitionPanel extends AbstractCompetitionPanel {
    // Number of fencers
    private int numberOfFencers;
    // List for the poules
    private final List<Poule> pouleList = new LinkedList<>();
    // List for the fencers
    private List<Fencer> fencerList = new LinkedList<>();
    // Panel for the results
    private final XPanel resultsPanel;

    public PouleOnlyCompetitionPanel(XFrame frame, Appearance appearance) {
        super(frame, appearance);


        // Panel for the results
        this.resultsPanel = new XPanel(new Dimension(SizeData.HALF_WIDTH + (SizeData.GAP * 2), SizeData.BUTTON_HEIGHT), new FlowLayout(FlowLayout.CENTER,
            SizeData.GAP, SizeData.GAP), this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addBorder(AppearanceData.RED_BORDER)
                .build());

        // ActionListener for the finish button
        this.finishButton.addActionListener(e -> {
            if (this.finishButton.getButton().getText().equalsIgnoreCase("finish all")) {
                for (Poule poule : this.pouleList) {
                    if (poule.calculateFencerData()) {
                        return;
                    }
                }
                this.finishButton.getButton().setText("Results");
            }
            else if (this.finishButton.getButton().getText().equalsIgnoreCase("results")) {
                this.calculateAndShowResults();
            }
        });
    }

    public void calculateAndShowResults() {
        // Clear the fencer list
        this.fencerList.clear();
        // Collect the fencers into one big list
        this.pouleList.forEach(poule -> this.fencerList.addAll(poule.getFencerList()));
        // Sort the big fencer list based on place and then index
        this.fencerList = this.fencerList.stream()
            .sorted(Comparator.comparing(Fencer::getPlace))
            .sorted(Comparator.comparing(Fencer::getIndex).reversed())
            .collect(Collectors.toList());
        // Rewrite the place of the fencers
        this.fencerList.forEach(fencer -> fencer.setPlace(this.fencerList.indexOf(fencer) + 1));

        // Resize the resultsPanel
        this.resultsPanel.setPreferredSize(new Dimension(SizeData.HALF_WIDTH + (SizeData.GAP * 2),
            (SizeData.BUTTON_HEIGHT * this.fencerList.size()) + (SizeData.GAP * (this.fencerList.size() + 1))));

        // Add the fencers to the results panel
        this.fencerList.forEach(fencer -> {
            this.resultsPanel.addComponent(new FencerDisplayLabel(fencer.getName(), String.valueOf(fencer.getPlace()),
                this.frame, BasicAppearance.BLACK));
        });

        // Remove the poules and add the results panel
        this.pouleList.forEach(this.scrollPanel::removeComponent);
        this.scrollPanel.addComponent(this.resultsPanel);

        this.bottomSection.removeComponent(this.finishButton);
    }

    public void generatePoules(List<String> valueList, List<Fencer> fencerList) {
        // Poule number
        int number = 1;
        try {
            // Collect the data from the list
            final String round = valueList.get(0);
            int fencersPoule = valueList.get(1).isBlank() ? 5 : Integer.parseInt(valueList.get(1));
            this.numberOfFencers = Integer.parseInt(valueList.get(2));
            final String date = valueList.get(3);

            final boolean isDefaultValuesUsed = valueList.get(1).isBlank();

            // Generating poules with the optimal amount of fencers in them
            // This happens when the fencers/poule input field is left empty
            if (isDefaultValuesUsed) {
                if (this.numberOfFencers <= fencersPoule) {
                    final Poule poule = new Poule(this.frame, this.numberOfFencers,
                        new CustomAppearanceBuilder()
                            .addMainBackground(Color.black)
                            .addBorder(AppearanceData.RED_BORDER)
                            .build());
                    poule.setNumber(String.valueOf(number));
                    this.pouleList.add(poule);
                    this.insertComponent(poule);

                    number++;
                }
                // Generate poules normally
                else {
                    if (this.numberOfFencers % fencersPoule == 0) {
                        int numberOfPoules = this.numberOfFencers / fencersPoule;

                        for (int i = 0; i < numberOfPoules; i++) {
                            final Poule poule = new Poule(this.frame, fencersPoule,
                                new CustomAppearanceBuilder()
                                    .addMainBackground(Color.black)
                                    .addBorder(AppearanceData.RED_BORDER)
                                    .build());
                            poule.setNumber(String.valueOf(number));
                            this.pouleList.add(poule);
                            this.insertComponent(poule);
                            number++;
                        }
                    }
                    else if (this.numberOfFencers % 2 == 0) {
                        while (this.numberOfFencers % fencersPoule != 0 && fencersPoule < 8) {
                            fencersPoule++;
                        }

                        int numberOfPoules = this.numberOfFencers / fencersPoule;
                        this.numberOfFencers -= numberOfPoules * fencersPoule;
                        for (int i = 0; i < numberOfPoules; i++) {
                            final Poule poule = new Poule(this.frame, fencersPoule,
                                new CustomAppearanceBuilder()
                                    .addMainBackground(Color.black)
                                    .addBorder(AppearanceData.RED_BORDER)
                                    .build());
                            poule.setNumber(String.valueOf(number));
                            this.pouleList.add(poule);
                            this.insertComponent(poule);
                            number++;
                        }
                    }
                    else {
                        while (this.numberOfFencers % fencersPoule < 4 && fencersPoule < 8) {
                            fencersPoule++;
                        }

                        int numberOfPoules = this.numberOfFencers / fencersPoule;
                        this.numberOfFencers -= numberOfPoules * fencersPoule;
                        for (int i = 0; i < numberOfPoules; i++) {
                            final Poule poule = new Poule(this.frame, fencersPoule,
                                new CustomAppearanceBuilder()
                                    .addMainBackground(Color.black)
                                    .addBorder(AppearanceData.RED_BORDER)
                                    .build());
                            poule.setNumber(String.valueOf(number));
                            this.pouleList.add(poule);
                            this.insertComponent(poule);
                            number++;
                        }
                    }

                    // Generate remaining poule
                    if (this.numberOfFencers >= 4 && this.numberOfFencers <= 8) {
                        final Poule poule = new Poule(this.frame, this.numberOfFencers,
                            new CustomAppearanceBuilder()
                                .addMainBackground(Color.black)
                                .addBorder(AppearanceData.RED_BORDER)
                                .build());
                        poule.setNumber(String.valueOf(number));
                        this.pouleList.add(poule);
                        this.insertComponent(poule);
                        number++;
                    }
                }
            }
            // Generating poules without optimization
            // This happens when the used enters a value for the fencers/poule inut field
            else {
                int numberOfPoules = this.numberOfFencers / fencersPoule;
                this.numberOfFencers -= numberOfPoules * fencersPoule;
                for (int i = 0; i < numberOfPoules; i++) {
                    final Poule poule = new Poule(this.frame, fencersPoule,
                        new CustomAppearanceBuilder()
                            .addMainBackground(Color.black)
                            .addBorder(AppearanceData.RED_BORDER)
                            .build());
                    poule.setNumber(String.valueOf(number));
                    this.pouleList.add(poule);
                    this.insertComponent(poule);
                    number++;
                }

                // Generate the last poule from the remaining fencers
                // 1. When the reminder is enough to make another poule
                if (this.numberOfFencers >= 4 && this.numberOfFencers <= 8) {
                    final Poule poule = new Poule(this.frame, this.numberOfFencers,
                        new CustomAppearanceBuilder()
                            .addMainBackground(Color.black)
                            .addBorder(AppearanceData.RED_BORDER)
                            .build());
                    poule.setNumber(String.valueOf(number));
                    this.pouleList.add(poule);
                    this.insertComponent(poule);
                    number++;
                }
                // 2. When the reminder is not enough to make another poule
                else if (this.numberOfFencers < 4) {
                    if (this.pouleList.get(0).getAmount() + this.numberOfFencers <= 8) {
                        this.pouleList.get(0).reConstruct(this.pouleList.get(0).getAmount() + this.numberOfFencers);
                    }
                    else if (this.pouleList.get(0).getAmount() + this.numberOfFencers > 8) {
                        this.pouleList.get(0).reConstruct(this.pouleList.get(0).getAmount() - (4 - this.numberOfFencers));
                        this.numberOfFencers += (4 - this.numberOfFencers);
                        final Poule poule = new Poule(this.frame, this.numberOfFencers,
                            new CustomAppearanceBuilder()
                                .addMainBackground(Color.black)
                                .addBorder(AppearanceData.RED_BORDER)
                                .build());
                        poule.setNumber(String.valueOf(number));
                        this.pouleList.add(poule);
                        this.insertComponent(poule);
                        number++;
                    }
                }
            }

            // Fill the poules with the names if given
            int nameIndex = 0;
            if (!fencerList.isEmpty()) {
                for (Poule poule : pouleList) {
                    for (int y = 1; y < poule.getAmount() + 1; y++) {
                        poule.boxArray[y][0].setText(fencerList.get(nameIndex).getName());
                        nameIndex++;
                    }
                }
            }

            // Resize the scroll panel to the desired dimensions
            this.scrollPanel.getViewPanel().setPreferredSize(new Dimension(
                this.scrollPanel.getViewPanel().getPreferredSize().width,
                this.pouleList.size() * SizeData.POULE_HEIGHT + SizeData.GAP
            ));
            this.repaintFrame();
        }
        catch (Exception exc) {

        }
    }

    public void clearAll() {
        this.finishButton.getButton().setText("Finish All");
        this.scrollPanel.removeComponent(this.resultsPanel);
        this.pouleList.forEach(this::extractComponent);
        this.pouleList.clear();
    }

    public List<Poule> getPouleList() {
        return this.pouleList;
    }

    public List<Fencer> getFencerList() {
        return this.fencerList;
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
