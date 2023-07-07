package app.components.complex.fencing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;

import app.components.buttons.FCXButton;
import app.components.panels.AbstractXPanel;
import app.components.panels.XPanel;
import app.components.scrollpanels.XScrollPanel;
import app.frame.XFrame;
import app.other.Fencer;
import support.appdata.AppearanceData;
import support.appdata.SizeData;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public abstract class AbstractCompetitionPanel extends AbstractXPanel implements ActionListener {
    // List for the poules
    protected final List<Poule> pouleList = new LinkedList<>();

    // Scroll panel
    protected final XScrollPanel scrollPanel;

    // Inner container for the bottom section
    private final XPanel bottomSection;

    // Buttons
    protected final FCXButton finishButton;
    protected final FCXButton closeButton;

    public AbstractCompetitionPanel(XFrame frame, Appearance appearance) {
        super(0, SizeData.BORDER_SIZE, SizeData.COMPETITION_PANEL_WIDTH,
            SizeData.COMPETITION_PANEL_HEIGHT, new BorderLayout(), frame, appearance);

        // Scroll panel
        this.scrollPanel = new XScrollPanel(new Dimension(this.getWidth(), this.getHeight()),
            new FlowLayout(FlowLayout.CENTER, SizeData.GAP, SizeData.GAP), this.frame, SizeData.GAP, SizeData.GAP,
            new CustomAppearanceBuilder()
                .addMainBackground(this.getAppearance().getMainBackground())
                .addBorder(this.getAppearance().getBorder())
                .build(),
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .build());

        // Inner container for the bottom section
        this.bottomSection = new XPanel(new Dimension(this.getWidth(), SizeData.BUTTON_HEIGHT),
            new FlowLayout(FlowLayout.CENTER, SizeData.GAP, 0), this.frame, BasicAppearance.BLACK);

        // Buttons
        this.finishButton = new FCXButton(SizeData.BUTTON_DIMENSION, "Finish All", this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.white)
                .addSecondaryForeground(Color.red)
                .addBorder(AppearanceData.RED_BORDER)
                .build(),
            new CustomAppearanceBuilder()
                .addMainForeground(Color.red)
                .addSecondaryForeground(Color.yellow)
                .build());
        this.finishButton.addActionListener(this);

        this.closeButton = new FCXButton(SizeData.BUTTON_DIMENSION, "Close", this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.white)
                .addSecondaryForeground(Color.red)
                .addBorder(AppearanceData.RED_BORDER)
                .build(),
            new CustomAppearanceBuilder()
                .addMainForeground(Color.red)
                .addSecondaryForeground(Color.yellow)
                .build());
        this.closeButton.addActionListener(e -> this.frame.closeCompetitionPanel());

        // Add components to the bottom section
        this.bottomSection.addComponent(this.finishButton);
        this.bottomSection.addComponent(this.closeButton);

        // Add the scroll panel to the panel
        this.addComponent(this.scrollPanel, BorderLayout.CENTER);
        this.addComponent(this.bottomSection, BorderLayout.SOUTH);
    }

    public void generatePoules(List<String> valueList, List<Fencer> fencerList) {
        // Poule number
        int number = 1;
        // Collect the data from the list
        final String round = valueList.get(0);
        int fencersPoule = valueList.get(1).isBlank() ? 5 : Integer.parseInt(valueList.get(1));
        int numberOfFencers = Integer.parseInt(valueList.get(2));
        final String date = valueList.get(3);

        final boolean isDefaultValuesUsed = valueList.get(1).isBlank();

        // Generating poules with the optimal amount of fencers in them
        // This happens when the fencers/poule input field is left empty
        if (isDefaultValuesUsed) {
            if (numberOfFencers <= fencersPoule) {
                final Poule poule = new Poule(this.frame, numberOfFencers,
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
                if (numberOfFencers % fencersPoule == 0) {
                    int numberOfPoules = numberOfFencers / fencersPoule;

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
                else if (numberOfFencers % 2 == 0) {
                    while (numberOfFencers % fencersPoule != 0 && fencersPoule < 8) {
                        fencersPoule++;
                    }

                    int numberOfPoules = numberOfFencers / fencersPoule;
                    numberOfFencers -= numberOfPoules * fencersPoule;
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
                    while (numberOfFencers % fencersPoule < 4 && fencersPoule < 8) {
                        fencersPoule++;
                    }

                    int numberOfPoules = numberOfFencers / fencersPoule;
                    numberOfFencers -= numberOfPoules * fencersPoule;
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
                if (numberOfFencers >= 4 && numberOfFencers <= 8) {
                    final Poule poule = new Poule(this.frame, numberOfFencers,
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
            int numberOfPoules = numberOfFencers / fencersPoule;
            numberOfFencers -= numberOfPoules * fencersPoule;
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
            if (numberOfFencers >= 4 && numberOfFencers <= 8) {
                final Poule poule = new Poule(this.frame, numberOfFencers,
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
            else if (numberOfFencers < 4) {
                if (this.pouleList.get(0).getAmount() + numberOfFencers <= 8) {
                    this.pouleList.get(0).reConstruct(this.pouleList.get(0).getAmount() + numberOfFencers);
                }
                else if (this.pouleList.get(0).getAmount() + numberOfFencers > 8) {
                    this.pouleList.get(0).reConstruct(this.pouleList.get(0).getAmount() - (4 - numberOfFencers));
                    numberOfFencers += (4 - numberOfFencers);
                    final Poule poule = new Poule(this.frame, numberOfFencers,
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

    public void insertComponent(JComponent component, PositionConstants positionConstants) {
        this.scrollPanel.addComponent(component, positionConstants);
    }

    public void insertComponent(JComponent component) {
        this.insertComponent(component, PositionConstants.MID_POS);
    }

    public JComponent extractComponent(JComponent component) {
        return this.scrollPanel.removeComponent(component);
    }

    public void clearAll() {
        this.pouleList.forEach(this::extractComponent);
        this.pouleList.clear();
    }
}