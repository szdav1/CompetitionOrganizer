package app.components.complex.fencing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import app.components.buttons.FCXButton;
import app.components.complex.other.NumberedFencerLabel;
import app.components.panels.XPanel;
import app.components.textcontainers.XTextField;
import app.frame.XFrame;
import app.other.Fencer;
import support.appdata.AppearanceData;
import support.appdata.SizeData;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public final class TableOnlyCompetitionPanel extends AbstractCompetitionPanel implements KeyListener {
    // Top panel
    private final XPanel topPanel;
    // Secondary view port panel
    private final XPanel placeholderPanel;
    // List for the column's inputs
    private List<NumberedFencerLabel> numberedFencerLabelList = new ArrayList<>();
    private List<XTextField> table8InputList = new ArrayList<>();
    private List<XTextField> fencerInputFieldList = new ArrayList<>();
    private List<XTextField> placeholderList = new ArrayList<>(2);
    // List for the losing fencers
    private List<Fencer> losingFencerList = new ArrayList<>();
    // Fencer list - after the poules
    private List<Fencer> fencerList;
    // Menu buttons
    private final FCXButton mainTableButton;
    private final FCXButton placeholderButton;

    // Gold medal input field
    private NumberedFencerLabel goldMedalInputField;
    private NumberedFencerLabel fencer3rdPlace;

    // Strings for the first column's numbers
    private final String tableau8;
    private final String tableau16;
    private final String tableau32;
    private final String tableau64;
    private final String tablea128;
    private String correspondingString;

    // Results panel
    private final XPanel resultsPanel;

    public TableOnlyCompetitionPanel(XFrame frame, Appearance appearance) {
        super(frame, appearance);

        // Add functionality to the closeButton
        this.closeButton.addActionListener(e -> this.frame.closeTableOnlyCompetitionPanel());

        this.finishButton.getButton().setText("Finish Table");
        this.finishButton.addActionListener(e -> this.finishTable());

        this.scrollPanel.getViewPanel().setLayout(null);
        this.scrollPanel.getVerticalScrollBar().setUnitIncrement(SizeData.BUTTON_HEIGHT * 2);

        // Top panel
        this.topPanel = new XPanel(new Dimension(this.getPreferredSize().width, SizeData.BUTTON_HEIGHT), new FlowLayout(FlowLayout.LEADING, SizeData.GAP, 0),
            this.frame, BasicAppearance.BLACK);

        this.placeholderPanel = new XPanel(new Dimension(0, 0), null, this.frame, BasicAppearance.BLACK);

        // Menu buttons
        this.mainTableButton = new FCXButton(SizeData.WIDE_BUTTON_DIMENSION, "Main Table", this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.BLACK)
                .addMainForeground(Color.white)
                .addSecondaryForeground(Color.yellow)
                .addBorder(AppearanceData.RED_BORDER)
                .addFont(AppearanceData.MAIN_FONT_P)
                .build(),
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .addSecondaryForeground(Color.yellow)
                .build());
        this.mainTableButton.addActionListener(e -> this.scrollPanel.setViewportView(this.scrollPanel.getViewPanel()));

        this.placeholderButton = new FCXButton(SizeData.WIDE_BUTTON_DIMENSION, "Placeholder", this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.BLACK)
                .addMainForeground(Color.white)
                .addSecondaryForeground(Color.yellow)
                .addBorder(AppearanceData.RED_BORDER)
                .addFont(AppearanceData.MAIN_FONT_P)
                .build(),
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .addSecondaryForeground(Color.yellow)
                .build());
        this.placeholderButton.addActionListener(e -> this.scrollPanel.setViewportView(this.placeholderPanel));

        // Strings for the first column's numbers
        this.tableau8 = "1;8;5;4;3;6;7;2";
        this.tableau16 = "1;16;9;8;5;12;13;4;3;14;11;6;7;10;15;2";
        this.tableau32 = "1;32;17;16;9;24;25;8;5;28;21;12;13;20;29;4;3;30;19;14;11;22;27;6;7;26;23;10;15;18;31;2";
        this.tableau64 = "1;64;33;32;17;48;49;16;9;56;41;24;25;40;57;8;5;60;37;28;21;44;53;12;13;52;45;20;29;36;61;4;3;62;35;30;19;46;51;14;11;54;43;22;27;38;59;6;7;58;39;26;23;42;55;10;15;50;47;18;31;34;63;2";
        this.tablea128 = "1;128;65;64;33;96;97;32;17;112;81;48;49;80;113;16;9;120;73;56;41;88;105;24;25;104;89;40;57;72;121;8;5;124;69;60;37;92;101;28;21;108;85;44;53;76;117;12;13;116;77;52;45;84;109;20;29;100;93;36;61;68;125;4;3;126;67;62;35;94;99;30;19;110;83;46;51;78;115;14;11;118;75;54;43;86;107;22;27;102;91;38;59;70;123;6;7;122;71;58;39;90;103;26;23;106;87;42;55;74;119;10;15;114;79;50;47;82;111;18;31;98;95;34;63;66;127;2";

        // Results panel
        this.resultsPanel = new XPanel((this.scrollPanel.getPreferredSize().width / 2) - (SizeData.HALF_WIDTH / 2),
            0, SizeData.HALF_WIDTH + (SizeData.GAP * 2), this.scrollPanel.getPreferredSize().height + (SizeData.GAP * 2),
            new FlowLayout(FlowLayout.CENTER, SizeData.GAP, SizeData.GAP), this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addBorder(AppearanceData.RED_BORDER)
                .build());

        // Add components to the topPanel
        this.topPanel.addComponent(this.mainTableButton);
        this.topPanel.addComponent(this.placeholderButton);

        // Add components to the panel
        this.addComponent(this.topPanel, BorderLayout.NORTH);

        this.addKeyListener(this);
    }

    private void displayResults() {
        // Remove everything
        this.scrollPanel.getViewPanel().removeAll();
        this.removeComponent(this.topPanel);
        this.resultsPanel.removeAll();

        // Fill the resultsPanel
        // I.
        this.resultsPanel.addComponent(new FencerDisplayLabel(this.goldMedalInputField.getFencer(), "1", this.frame,
            BasicAppearance.BLACK));
        // II.
        if (this.goldMedalInputField.getFencer().equalsIgnoreCase(this.fencerInputFieldList.get(this.fencerInputFieldList.size() - 1).getText())) {
            this.resultsPanel.addComponent(new FencerDisplayLabel(this.fencerInputFieldList.get(this.fencerInputFieldList.size() - 2).getText(),
                "2", this.frame, BasicAppearance.BLACK));
        }
        else if (this.goldMedalInputField.getFencer().equalsIgnoreCase(this.fencerInputFieldList.get(this.fencerInputFieldList.size() - 2).getText())) {
            this.resultsPanel.addComponent(new FencerDisplayLabel(this.fencerInputFieldList.get(this.fencerInputFieldList.size() - 1).getText(),
                "2", this.frame, BasicAppearance.BLACK));
        }

        // III.
        if (this.fencer3rdPlace.getFencer().equalsIgnoreCase(this.placeholderList.get(0).getText())) {
            this.resultsPanel.addComponent(new FencerDisplayLabel(this.placeholderList.get(0).getText(), "3", this.frame, BasicAppearance.BLACK));
            this.resultsPanel.addComponent(new FencerDisplayLabel(this.placeholderList.get(1).getText(), "4", this.frame, BasicAppearance.BLACK));
        }
        // IV
        else if (this.fencer3rdPlace.getFencer().equalsIgnoreCase(this.placeholderList.get(1).getText())) {
            this.resultsPanel.addComponent(new FencerDisplayLabel(this.placeholderList.get(1).getText(), "3", this.frame, BasicAppearance.BLACK));
            this.resultsPanel.addComponent(new FencerDisplayLabel(this.placeholderList.get(0).getText(), "4", this.frame, BasicAppearance.BLACK));
        }

        // Other places
        this.losingFencerList = this.losingFencerList.stream()
            .sorted(Comparator.comparing(Fencer::getPlace))
            .filter(fencer -> !fencer.getName().equalsIgnoreCase("----"))
            .collect(Collectors.toList());

        this.losingFencerList.forEach(losingFencer -> losingFencer.setPlace(this.losingFencerList.indexOf(losingFencer) + 5));
        this.losingFencerList.forEach(losingFencer -> this.resultsPanel.addComponent(new FencerDisplayLabel(losingFencer.getName(),
            String.valueOf(losingFencer.getPlace()), this.frame, BasicAppearance.BLACK)));

        // Find the correct height for the resultsPanel
        int resultsPanelHeight = 0;
        if (this.correspondingString.equalsIgnoreCase(this.tableau8)) {
            resultsPanelHeight = ((SizeData.BUTTON_HEIGHT + SizeData.GAP) * 8) + (SizeData.GAP);
        }
        else if (this.correspondingString.equalsIgnoreCase(this.tableau16)) {
            resultsPanelHeight = ((SizeData.BUTTON_HEIGHT + SizeData.GAP) * 16) + (SizeData.GAP);
        }
        else if (this.correspondingString.equalsIgnoreCase(this.tableau32)) {
            resultsPanelHeight = ((SizeData.BUTTON_HEIGHT + SizeData.GAP) * 32) + (SizeData.GAP);
        }
        else if (this.correspondingString.equalsIgnoreCase(this.tableau64)) {
            resultsPanelHeight = ((SizeData.BUTTON_HEIGHT + SizeData.GAP) * 64) + (SizeData.GAP);
        }
        else if (this.correspondingString.equalsIgnoreCase(this.tablea128)) {
            resultsPanelHeight = ((SizeData.BUTTON_HEIGHT + SizeData.GAP) * 128) + (SizeData.GAP);
        }

        this.resultsPanel.setSize(this.resultsPanel.getWidth(), resultsPanelHeight);

        // Add the resultsPanel
        this.scrollPanel.addComponent(this.resultsPanel, PositionConstants.TOP_POS);
    }

    private void generatePlaceholders() {
        // Generate the placeholder for the 3rd place
        for (int i = 0; i < 3; i += 2) {
            final XTextField inputField = new XTextField(
                SizeData.GAP,
                SizeData.BUTTON_HEIGHT + (SizeData.BUTTON_HEIGHT * i),
                SizeData.WIDE_BUTTON_WIDTH,
                SizeData.BUTTON_HEIGHT,
                this.frame,
                BasicAppearance.BLACK_BORDERED
            );
            inputField.setHorizontalAlignment(SwingConstants.CENTER);

            this.placeholderPanel.addComponent(inputField);
            this.placeholderList.add(inputField);
        }

        this.fencer3rdPlace = new NumberedFencerLabel(
            SizeData.GAP + SizeData.WIDE_BUTTON_WIDTH,
            SizeData.BUTTON_HEIGHT * 2,
            "III.",
            "",
            this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .addBorder(AppearanceData.RED_BORDER)
                .addFont(AppearanceData.MAIN_FONT_B)
                .build(),
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .addBorder(AppearanceData.RED_BORDER)
                .addFont(AppearanceData.MAIN_FONT_B)
                .build());

        this.placeholderPanel.addComponent(fencer3rdPlace);
    }

    public void generateStructure(final List<Fencer> fencerList) {
        // Init the fencerList
        this.fencerList = fencerList;
        // Generate placeholders
        this.generatePlaceholders();

        // Strings for the first column's numbers
        this.correspondingString = tableau8;

        // Reset
        this.scrollPanel.getViewPanel().removeAll();
        this.scrollPanel.getViewPanel().setPreferredSize(new Dimension(0, 0));
        final int fencerListSize = fencerList.size();

        // Starting table size
        int nPowerOf2 = 3;
        int tableSize = (int) Math.pow(2, nPowerOf2);

        // Find the corresponding number for the table size
        if (fencerListSize > 8) {
            while (Math.pow(2, nPowerOf2) < fencerListSize) {
                nPowerOf2++;
            }
            tableSize = (int) Math.pow(2, nPowerOf2);

            // Init the numberedFencerLabelList with the size of fencerList
            this.numberedFencerLabelList = new ArrayList<>(tableSize);
            this.fencerInputFieldList = new ArrayList<>(tableSize);

            // Generate the first column with the corresponding table size
            for (int i = 0; i < tableSize; i++) {
                final NumberedFencerLabel fencerLabel = new NumberedFencerLabel(SizeData.GAP, SizeData.BUTTON_HEIGHT + ((SizeData.BUTTON_HEIGHT) * (i) + (SizeData.BUTTON_HEIGHT * i)),
                    "", "", this.frame, BasicAppearance.BLACK);

                this.scrollPanel.addComponent(fencerLabel);
                this.numberedFencerLabelList.add(fencerLabel);
            }

            // Generate table of 8
            for (int y = 0; y < tableSize; y += 2) {
                final XTextField fencerInputField = new XTextField(
                    SizeData.GAP + SizeData.NARROW_BUTTON_WIDTH + (SizeData.WIDE_BUTTON_WIDTH),
                    SizeData.BUTTON_HEIGHT + ((SizeData.BUTTON_HEIGHT * (y + 1)) + (SizeData.BUTTON_HEIGHT * (y))),
                    SizeData.WIDE_BUTTON_WIDTH,
                    SizeData.BUTTON_HEIGHT,
                    this.frame,
                    BasicAppearance.BLACK_BORDERED
                );
                fencerInputField.setHorizontalAlignment(JLabel.CENTER);

                this.scrollPanel.addComponent(fencerInputField);
                this.table8InputList.add(fencerInputField);
                this.fencerInputFieldList.add(fencerInputField);
            }

            // Generate table of 16
            if (tableSize >= 8) {
                for (int y = 0; y < tableSize; y += 4) {
                    final XTextField fencerInputField = new XTextField(
                        SizeData.GAP + SizeData.NARROW_BUTTON_WIDTH + (SizeData.WIDE_BUTTON_WIDTH * 2),
                        SizeData.BUTTON_HEIGHT + ((SizeData.BUTTON_HEIGHT * (y + 2)) + (SizeData.BUTTON_HEIGHT * (y + 1))),
                        SizeData.WIDE_BUTTON_WIDTH,
                        SizeData.BUTTON_HEIGHT,
                        this.frame,
                        BasicAppearance.BLACK_BORDERED
                    );
                    fencerInputField.setHorizontalAlignment(JLabel.CENTER);

                    this.scrollPanel.addComponent(fencerInputField);
                    this.fencerInputFieldList.add(fencerInputField);
                }
            }
            // Generate table of 32
            if (tableSize >= 16) {
                for (int y = 0; y < tableSize; y += 8) {
                    final XTextField fencerInputField = new XTextField(
                        SizeData.GAP + SizeData.NARROW_BUTTON_WIDTH + (SizeData.WIDE_BUTTON_WIDTH * 3),
                        SizeData.BUTTON_HEIGHT + ((SizeData.BUTTON_HEIGHT * (y + 4)) + (SizeData.BUTTON_HEIGHT * (y + 3))),
                        SizeData.WIDE_BUTTON_WIDTH,
                        SizeData.BUTTON_HEIGHT,
                        this.frame,
                        BasicAppearance.BLACK_BORDERED
                    );
                    fencerInputField.setHorizontalAlignment(JLabel.CENTER);

                    this.scrollPanel.addComponent(fencerInputField);
                    this.fencerInputFieldList.add(fencerInputField);
                }
            }
            // Generate table of 64
            if (tableSize >= 32) {
                for (int y = 0; y < tableSize; y += 16) {
                    final XTextField fencerInputField = new XTextField(
                        SizeData.GAP + SizeData.NARROW_BUTTON_WIDTH + (SizeData.WIDE_BUTTON_WIDTH * 4),
                        SizeData.BUTTON_HEIGHT + ((SizeData.BUTTON_HEIGHT * (y + 8)) + (SizeData.BUTTON_HEIGHT * (y + 7))),
                        SizeData.WIDE_BUTTON_WIDTH,
                        SizeData.BUTTON_HEIGHT,
                        this.frame,
                        BasicAppearance.BLACK_BORDERED
                    );
                    fencerInputField.setHorizontalAlignment(JLabel.CENTER);

                    this.scrollPanel.addComponent(fencerInputField);
                    this.fencerInputFieldList.add(fencerInputField);
                }
            }
            // Generate table of 128
            if (tableSize >= 64) {
                for (int y = 0; y < tableSize; y += 32) {
                    final XTextField fencerInputField = new XTextField(
                        SizeData.GAP + SizeData.NARROW_BUTTON_WIDTH + (SizeData.WIDE_BUTTON_WIDTH * 5),
                        SizeData.BUTTON_HEIGHT + ((SizeData.BUTTON_HEIGHT * (y + 16)) + (SizeData.BUTTON_HEIGHT * (y + 15))),
                        SizeData.WIDE_BUTTON_WIDTH,
                        SizeData.BUTTON_HEIGHT,
                        this.frame,
                        BasicAppearance.BLACK_BORDERED
                    );
                    fencerInputField.setHorizontalAlignment(JLabel.CENTER);

                    this.scrollPanel.addComponent(fencerInputField);
                    this.fencerInputFieldList.add(fencerInputField);
                }
            }

            // Add KeyListeners to the inputs
            this.fencerInputFieldList.forEach(fencerInputField -> fencerInputField.addKeyListener(this));

            // Create the gold medal bout's input
            this.goldMedalInputField = new NumberedFencerLabel(
                SizeData.GAP + SizeData.NARROW_BUTTON_WIDTH + (SizeData.WIDE_BUTTON_WIDTH * nPowerOf2),
                SizeData.BUTTON_HEIGHT + (SizeData.BUTTON_HEIGHT * (tableSize - 1)),
                "I.",
                "",
                this.frame,
                new CustomAppearanceBuilder()
                    .addMainBackground(Color.black)
                    .addMainForeground(Color.red)
                    .addBorder(AppearanceData.RED_BORDER)
                    .addFont(AppearanceData.MAIN_FONT_B)
                    .build(),
                new CustomAppearanceBuilder()
                    .addMainBackground(Color.black)
                    .addMainForeground(Color.red)
                    .addBorder(AppearanceData.RED_BORDER)
                    .addFont(AppearanceData.MAIN_FONT_B)
                    .build());
            this.goldMedalInputField.getTextInput().addKeyListener(this);


            this.scrollPanel.addComponent(goldMedalInputField);

            // Make a gap at the bottom of the table
            this.scrollPanel.getViewPanel().setPreferredSize(new Dimension(this.scrollPanel.getViewPanel().getPreferredSize().width,
                this.scrollPanel.getViewPanel().getPreferredSize().height + SizeData.BUTTON_HEIGHT));

            // Find the corresponding string
            if (tableSize > 64) {
                this.correspondingString = this.tablea128;
            }
            else if (tableSize > 32) {
                this.correspondingString = this.tableau64;
            }
            else if (tableSize > 16) {
                this.correspondingString = this.tableau32;
            }
            else if (tableSize > 8) {
                this.correspondingString = this.tableau16;
            }

            // Give the first column's inputField's the corresponding number
            int columnIndex = 0;
            for (String string : correspondingString.split(";")) {
                this.numberedFencerLabelList.get(columnIndex).setNumber(string);
                columnIndex++;
            }

            // Fill the first column with the names
            this.numberedFencerLabelList.forEach(numberedFencerLabel -> {
                numberedFencerLabel.setFencer("----");
                numberedFencerLabel.setTextColor(Color.red);
                fencerList.forEach(fencer -> {
                    if (String.valueOf(fencer.getPlace()).equalsIgnoreCase(numberedFencerLabel.getNumber())) {
                        numberedFencerLabel.setFencer(fencer.getName());
                        numberedFencerLabel.setTextColor(Color.white);
                    }
                });
            });

            // Put the fencers who don't have an opponent to the next round
            int nextRoundIndex = 0;

            for (int i = 0; i < this.numberedFencerLabelList.size() - 1; i += 2) {
                // Check if the lower input field is empty
                if (this.numberedFencerLabelList.get(i + 1).getFencer().equalsIgnoreCase("----")) {
                    this.table8InputList.get(nextRoundIndex).setText(this.numberedFencerLabelList.get(i).getFencer());
                }
                else if (this.numberedFencerLabelList.get(i).getFencer().equalsIgnoreCase("----")) {
                    this.table8InputList.get(nextRoundIndex).setText(this.numberedFencerLabelList.get(i + 1).getFencer());
                }
                nextRoundIndex++;
            }
        }
        // Generate the whole table with a size of 8
        else {
            this.numberedFencerLabelList = new ArrayList<>(tableSize);

            for (int i = 0; i < tableSize; i++) {
                final NumberedFencerLabel fencerLabel = new NumberedFencerLabel(SizeData.GAP,
                    SizeData.BUTTON_HEIGHT + ((SizeData.BUTTON_HEIGHT * i) + (SizeData.BUTTON_HEIGHT * i)),
                    "", "", this.frame, BasicAppearance.BLACK);

                this.scrollPanel.addComponent(fencerLabel);
                this.numberedFencerLabelList.add(fencerLabel);
            }

            int yOffset = 1;
            int tempTableSize = tableSize / 2;

            for (int x = 1; x < nPowerOf2; x++) {
                for (int y = 0; y < tempTableSize; y++) {
                    final XTextField fencerInputField = new XTextField(
                        SizeData.GAP + SizeData.NARROW_BUTTON_WIDTH + (SizeData.WIDE_BUTTON_WIDTH * x),
                        SizeData.BUTTON_HEIGHT + (SizeData.BUTTON_HEIGHT * yOffset),
                        SizeData.WIDE_BUTTON_WIDTH,
                        SizeData.BUTTON_HEIGHT,
                        this.frame,
                        BasicAppearance.BLACK_BORDERED
                    );
                    fencerInputField.setHorizontalAlignment(JLabel.CENTER);
                    fencerInputField.addKeyListener(this);

                    yOffset += 4 * x;
                    this.scrollPanel.addComponent(fencerInputField);
                    this.table8InputList.add(fencerInputField);
                    this.fencerInputFieldList.add(fencerInputField);
                }
                tempTableSize /= 2;
                yOffset = 3;
            }

            // Create the gold medal bout's input
            this.goldMedalInputField = new NumberedFencerLabel(
                SizeData.GAP + SizeData.NARROW_BUTTON_WIDTH + (SizeData.WIDE_BUTTON_WIDTH * nPowerOf2),
                SizeData.BUTTON_HEIGHT + (SizeData.BUTTON_HEIGHT * (tableSize - 1)),
                "I.",
                "",
                this.frame,
                new CustomAppearanceBuilder()
                    .addMainBackground(Color.black)
                    .addMainForeground(Color.red)
                    .addBorder(AppearanceData.RED_BORDER)
                    .addFont(AppearanceData.MAIN_FONT_B)
                    .build(),
                new CustomAppearanceBuilder()
                    .addMainBackground(Color.black)
                    .addMainForeground(Color.red)
                    .addBorder(AppearanceData.RED_BORDER)
                    .addFont(AppearanceData.MAIN_FONT_B)
                    .build());
            this.goldMedalInputField.getTextInput().addKeyListener(this);

            this.scrollPanel.addComponent(goldMedalInputField);

            // Give the first column's inputField's the corresponding number
            int columnIndex = 0;
            for (String string : correspondingString.split(";")) {
                this.numberedFencerLabelList.get(columnIndex).setNumber(string);
                columnIndex++;
            }

            this.numberedFencerLabelList.forEach(numberedFencerLabel -> {
                numberedFencerLabel.setFencer("----");
                numberedFencerLabel.setTextColor(Color.red);
                fencerList.forEach(fencer -> {
                    if (String.valueOf(fencer.getPlace()).equalsIgnoreCase(numberedFencerLabel.getNumber())) {
                        numberedFencerLabel.setFencer(fencer.getName());
                        numberedFencerLabel.setTextColor(Color.white);
                    }
                });
            });

            // Put the fencers who don't have an opponent to the next round
            int nextRoundIndex = 0;

            for (int i = 0; i < this.numberedFencerLabelList.size() - 1; i += 2) {
                // Check if the lower input field is empty
                if (this.numberedFencerLabelList.get(i + 1).getFencer().equalsIgnoreCase("----")) {
                    this.table8InputList.get(nextRoundIndex).setText(this.numberedFencerLabelList.get(i).getFencer());
                }
                else if (this.numberedFencerLabelList.get(i).getFencer().equalsIgnoreCase("----")) {
                    this.table8InputList.get(nextRoundIndex).setText(this.numberedFencerLabelList.get(i + 1).getFencer());
                }
                nextRoundIndex++;
            }
        }
    }

    public void finishTable() {
        // Check for empty inputs
        if (!this.isEverythingIn()) {
            return;
        }

        // Clear tasks
        int index = 0;
        this.losingFencerList.clear();

        // Table 8
        if (this.correspondingString.equalsIgnoreCase(this.tableau8)) {
            for (int i = 0; i < this.numberedFencerLabelList.size() - 1; i += 2) {
                if (this.numberedFencerLabelList.get(i).getFencer().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                    this.losingFencerList.add(new Fencer(this.numberedFencerLabelList.get(i + 1).getFencer(), 0, 0, 0, 0, Integer.parseInt(this.numberedFencerLabelList.get(i + 1).getNumber())));
                }
                else if (!this.numberedFencerLabelList.get(i).getFencer().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                    this.losingFencerList.add(new Fencer(this.numberedFencerLabelList.get(i).getFencer(), 0, 0, 0, 0, Integer.parseInt(this.numberedFencerLabelList.get(i).getNumber())));
                }
                index++;
            }
        }
        // Table 16
        else if (this.correspondingString.equalsIgnoreCase(this.tableau16)) {
            // First column losers
            for (int i = 0; i < this.numberedFencerLabelList.size() - 1; i += 2) {
                if (this.numberedFencerLabelList.get(i).getFencer().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                    this.losingFencerList.add(new Fencer(this.numberedFencerLabelList.get(i + 1).getFencer(), 0, 0, 0, 0, Integer.parseInt(this.numberedFencerLabelList.get(i + 1).getNumber())));
                }
                else if (!this.numberedFencerLabelList.get(i).getFencer().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                    this.losingFencerList.add(new Fencer(this.numberedFencerLabelList.get(i).getFencer(), 0, 0, 0, 0, Integer.parseInt(this.numberedFencerLabelList.get(i).getNumber())));
                }
                index++;
            }
            // Second column losers
            index = 8;
            for (int i = 0; i < (this.numberedFencerLabelList.size() / 2) - 1; i += 2) {
                if (!this.fencerInputFieldList.get(index).getText().isBlank()) {
                    if (this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i + 1).getText()));
                    }
                    else if (!this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i).getText()));
                    }
                }
                index++;
            }
        }
        // Table 32
        else if (this.correspondingString.equalsIgnoreCase(this.tableau32)) {
            // First column losers
            for (int i = 0; i < this.numberedFencerLabelList.size() - 1; i += 2) {
                if (this.numberedFencerLabelList.get(i).getFencer().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                    this.losingFencerList.add(new Fencer(this.numberedFencerLabelList.get(i + 1).getFencer(), 0, 0, 0, 0, Integer.parseInt(this.numberedFencerLabelList.get(i + 1).getNumber())));
                }
                else if (!this.numberedFencerLabelList.get(i).getFencer().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                    this.losingFencerList.add(new Fencer(this.numberedFencerLabelList.get(i).getFencer(), 0, 0, 0, 0, Integer.parseInt(this.numberedFencerLabelList.get(i).getNumber())));
                }
                index++;
            }
            // Second column losers
            /*
            0--size()/2-1
            16--
            */
            index = 16;
            for (int i = 0; i < (this.numberedFencerLabelList.size() / 2) - 1; i += 2) {
                if (!this.fencerInputFieldList.get(index).getText().isBlank()) {
                    if (this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i + 1).getText()));
                    }
                    else if (!this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i).getText()));
                    }
                }
                index++;
            }
            // Third column losers
                /*
                16--22
                24--26
                */
            index = 24;
            for (int i = 16; i <= 22; i += 2) {
                if (!this.fencerInputFieldList.get(i).getText().isBlank()) {
                    if (this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i + 1).getText()));
                    }
                    else if (!this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i).getText()));
                    }
                    index++;
                }
            }
        }
        // Table 64
        else if (this.correspondingString.equalsIgnoreCase(this.tableau64)) {
            // First column losers
            for (int i = 0; i < this.numberedFencerLabelList.size() - 1; i += 2) {
                if (this.numberedFencerLabelList.get(i).getFencer().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                    this.losingFencerList.add(new Fencer(this.numberedFencerLabelList.get(i + 1).getFencer(), 0, 0, 0, 0, Integer.parseInt(this.numberedFencerLabelList.get(i + 1).getNumber())));
                }
                else if (!this.numberedFencerLabelList.get(i).getFencer().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                    this.losingFencerList.add(new Fencer(this.numberedFencerLabelList.get(i).getFencer(), 0, 0, 0, 0, Integer.parseInt(this.numberedFencerLabelList.get(i).getNumber())));
                }
                index++;
            }
            // Second column losers
            /*
            0--size()/2-1
            8--11
            */
            index = 32;
            for (int i = 0; i < (this.numberedFencerLabelList.size() / 2) - 1; i += 2) {
                if (!this.fencerInputFieldList.get(index).getText().isBlank()) {
                    if (this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i + 1).getText()));
                    }
                    else if (!this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i).getText()));
                    }
                }
                index++;
            }
            // Third column losers
                /*
                32--46
                48--
                */
            index = 48;
            for (int i = 32; i <= 46; i += 2) {
                if (!this.fencerInputFieldList.get(i).getText().isBlank()) {
                    if (this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i + 1).getText()));
                    }
                    else if (!this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i).getText()));
                    }
                    index++;
                }
            }
            // Fourth column losers
            /*
            48--54
            56--60
            */
            index = 56;
            for (int i = 48; i <= 54; i += 2) {
                if (!this.fencerInputFieldList.get(i).getText().isBlank()) {
                    if (this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i + 1).getText()));
                    }
                    else if (!this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i).getText()));
                    }
                    index++;
                }
            }
        }
        // Table 128
        else if (this.correspondingString.equalsIgnoreCase(this.tablea128)) {
            // First column losers
            for (int i = 0; i < this.numberedFencerLabelList.size() - 1; i += 2) {
                if (this.numberedFencerLabelList.get(i).getFencer().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                    this.losingFencerList.add(new Fencer(this.numberedFencerLabelList.get(i + 1).getFencer(), 0, 0, 0, 0, Integer.parseInt(this.numberedFencerLabelList.get(i + 1).getNumber())));
                }
                else if (!this.numberedFencerLabelList.get(i).getFencer().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                    this.losingFencerList.add(new Fencer(this.numberedFencerLabelList.get(i).getFencer(), 0, 0, 0, 0, Integer.parseInt(this.numberedFencerLabelList.get(i).getNumber())));
                }
                index++;
            }
            // Second column losers
            /*
            0--size()/2-1
            64--
            */
            index = 64;
            for (int i = 0; i < (this.numberedFencerLabelList.size() / 2) - 1; i += 2) {
                if (!this.fencerInputFieldList.get(index).getText().isBlank()) {
                    if (this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i + 1).getText()));
                    }
                    else if (!this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i).getText()));
                    }
                }
                index++;
            }
            // Third column losers
                /*
                64--94
                96--
                */
            index = 96;
            for (int i = 64; i <= 94; i += 2) {
                if (!this.fencerInputFieldList.get(i).getText().isBlank()) {
                    if (this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i + 1).getText()));
                    }
                    else if (!this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i).getText()));
                    }
                    index++;
                }
            }
            // Fourth column losers
            /*
            95--109
            110
            */
            index = 110;
            for (int i = 95; i <= 109; i += 2) {
                if (!this.fencerInputFieldList.get(i).getText().isBlank()) {
                    if (this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i + 1).getText()));
                    }
                    else if (!this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i).getText()));
                    }
                    index++;
                }
            }
            // Fifth column losers
            /*
            110--116
            118
            */
            index = 118;
            for (int i = 110; i <= 116; i++) {
                if (!this.fencerInputFieldList.get(i).getText().isBlank()) {
                    if (this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i + 1).getText()));
                    }
                    else if (!this.fencerInputFieldList.get(i).getText().equalsIgnoreCase(this.fencerInputFieldList.get(index).getText())) {
                        this.losingFencerList.add(this.getFencerByName(this.fencerInputFieldList.get(i).getText()));
                    }
                    index++;
                }
            }
        }

        this.displayResults();
    }

    private Fencer getFencerByName(String fencerName) {
        if (fencerName.isBlank()) return null;

        return this.fencerList.stream()
            .filter(fencer -> fencer.getName().equalsIgnoreCase(fencerName))
            .collect(Collectors.toList())
            .get(0);
    }

    public boolean isEverythingIn() {
        for (XTextField textField : this.fencerInputFieldList) {
            if (textField.getText().isBlank()) {
                return false;
            }
        }

        if (this.goldMedalInputField.getFencer().isBlank() || this.fencer3rdPlace.getFencer().isBlank()) {
            return false;
        }

        return true;
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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        final Object source = e.getSource();
        // Set the two competitors who fence for the 3rd place
        int fencerInputFieldListSize = this.fencerInputFieldList.size();
        int sourceIndex = this.fencerInputFieldList.contains(source) ? this.fencerInputFieldList.indexOf(source) : -1;

        if (sourceIndex == fencerInputFieldListSize - 2) {
            if (this.fencerInputFieldList.get(sourceIndex).getText().equalsIgnoreCase(this.fencerInputFieldList.get(fencerInputFieldListSize - 6).getText())) {
                this.placeholderList.get(0).setText(this.fencerInputFieldList.get(fencerInputFieldListSize - 5).getText());
            }
            else if (this.fencerInputFieldList.get(sourceIndex).getText().equalsIgnoreCase(this.fencerInputFieldList.get(fencerInputFieldListSize - 5).getText())) {
                this.placeholderList.get(0).setText(this.fencerInputFieldList.get(fencerInputFieldListSize - 6).getText());
            }
        }
        else if (sourceIndex == fencerInputFieldListSize - 1) {
            if (this.fencerInputFieldList.get(sourceIndex).getText().equalsIgnoreCase(this.fencerInputFieldList.get(fencerInputFieldListSize - 4).getText())) {
                this.placeholderList.get(1).setText(this.fencerInputFieldList.get(fencerInputFieldListSize - 3).getText());
            }
            else if (this.fencerInputFieldList.get(sourceIndex).getText().equalsIgnoreCase(this.fencerInputFieldList.get(fencerInputFieldListSize - 3).getText())) {
                this.placeholderList.get(1).setText(this.fencerInputFieldList.get(fencerInputFieldListSize - 4).getText());
            }
        }
    }
}
