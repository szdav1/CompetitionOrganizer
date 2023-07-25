package app.components.complex.fencing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JComponent;

import app.components.complex.other.NumberedFencerLabel;
import app.components.textcontainers.XTextField;
import app.frame.XFrame;
import app.other.Fencer;
import support.appdata.AppearanceData;
import support.appdata.SizeData;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public final class TableOnlyCompetitionPanel extends AbstractCompetitionPanel {
    // List for the first column's inputs
    private List<NumberedFencerLabel> numberedFencerLabelList = new ArrayList<>();

    public TableOnlyCompetitionPanel(XFrame frame, Appearance appearance) {
        super(frame, appearance);

        // Add functionality to the closeButton
        this.closeButton.addActionListener(e -> this.frame.closeTableOnlyCompetitionPanel());
        // Remove the finishAll button
        this.bottomSection.removeComponent(this.finishButton);

        this.scrollPanel.getViewPanel().setLayout(null);
    }

    public void generateStructure(final List<Fencer> fencerList) {
        this.scrollPanel.getViewPanel().removeAll();
        this.scrollPanel.getViewPanel().setPreferredSize(new Dimension(0, 0));
        final int fencerListSize = fencerList.size();
        // File name
        String correspondingFileName = "database/tableau8.csv";

        // Starting table size
        int nPowerOf2 = 3;
        int tableSize = (int) Math.pow(2, nPowerOf2);

        // Fencer indexes
        int lowFencerNumber = 1;
        int highFencerNumber = fencerListSize;

        // Find the corresponding number for the table size
        if (fencerListSize > 8) {
            while (Math.pow(2, nPowerOf2) < fencerListSize) {
                nPowerOf2++;
            }
            tableSize = (int) Math.pow(2, nPowerOf2);

            // Init the numberedFencerLabelList with the size of fencerList
            this.numberedFencerLabelList = new ArrayList<>(tableSize);

            // Generate the first column with the corresponding table size
            for (int i = 0; i < tableSize; i++) {
                final NumberedFencerLabel fencerLabel = new NumberedFencerLabel(SizeData.GAP, ((SizeData.BUTTON_HEIGHT) * (i) + (SizeData.BUTTON_HEIGHT * i)),
                    "", "", this.frame, BasicAppearance.BLACK);

                this.scrollPanel.addComponent(fencerLabel);
                this.numberedFencerLabelList.add(fencerLabel);
            }

            // Generate table of 8
            for (int y = 0; y < tableSize; y += 2) {
                final XTextField fencerInputField = new XTextField(
                    SizeData.GAP + SizeData.NARROW_BUTTON_WIDTH + (SizeData.WIDE_BUTTON_WIDTH),
                    (SizeData.BUTTON_HEIGHT * (y + 1)) + (SizeData.BUTTON_HEIGHT * (y)),
                    SizeData.WIDE_BUTTON_WIDTH,
                    SizeData.BUTTON_HEIGHT,
                    this.frame,
                    BasicAppearance.BLACK_BORDERED
                );

                this.scrollPanel.addComponent(fencerInputField);
            }

            // Generate table of 16
            if (tableSize >= 8) {
                for (int y = 0; y < tableSize; y += 4) {
                    final XTextField fencerInputField = new XTextField(
                        SizeData.GAP + SizeData.NARROW_BUTTON_WIDTH + (SizeData.WIDE_BUTTON_WIDTH * 2),
                        (SizeData.BUTTON_HEIGHT * (y + 2)) + (SizeData.BUTTON_HEIGHT * (y + 1)),
                        SizeData.WIDE_BUTTON_WIDTH,
                        SizeData.BUTTON_HEIGHT,
                        this.frame,
                        BasicAppearance.BLACK_BORDERED
                    );

                    this.scrollPanel.addComponent(fencerInputField);
                }
            }
            // Generate table of 32
            if (tableSize >= 16) {
                for (int y = 0; y < tableSize; y += 8) {
                    final XTextField fencerInputField = new XTextField(
                        SizeData.GAP + SizeData.NARROW_BUTTON_WIDTH + (SizeData.WIDE_BUTTON_WIDTH * 3),
                        (SizeData.BUTTON_HEIGHT * (y + 4)) + (SizeData.BUTTON_HEIGHT * (y + 3)),
                        SizeData.WIDE_BUTTON_WIDTH,
                        SizeData.BUTTON_HEIGHT,
                        this.frame,
                        BasicAppearance.BLACK_BORDERED
                    );

                    this.scrollPanel.addComponent(fencerInputField);
                }
            }
            // Generate table of 64
            if (tableSize >= 32) {
                for (int y = 0; y < tableSize; y += 16) {
                    final XTextField fencerInputField = new XTextField(
                        SizeData.GAP + SizeData.NARROW_BUTTON_WIDTH + (SizeData.WIDE_BUTTON_WIDTH * 4),
                        (SizeData.BUTTON_HEIGHT * (y + 8)) + (SizeData.BUTTON_HEIGHT * (y + 7)),
                        SizeData.WIDE_BUTTON_WIDTH,
                        SizeData.BUTTON_HEIGHT,
                        this.frame,
                        BasicAppearance.BLACK_BORDERED
                    );

                    this.scrollPanel.addComponent(fencerInputField);
                }
            }
            // Generate table of 128
            if (tableSize >= 64) {
                for (int y = 0; y < tableSize; y += 32) {
                    final XTextField fencerInputField = new XTextField(
                        SizeData.GAP + SizeData.NARROW_BUTTON_WIDTH + (SizeData.WIDE_BUTTON_WIDTH * 5),
                        (SizeData.BUTTON_HEIGHT * (y + 16)) + (SizeData.BUTTON_HEIGHT * (y + 15)),
                        SizeData.WIDE_BUTTON_WIDTH,
                        SizeData.BUTTON_HEIGHT,
                        this.frame,
                        BasicAppearance.BLACK_BORDERED
                    );

                    this.scrollPanel.addComponent(fencerInputField);
                }
            }

            // Create the gold medal bout's input
            final XTextField goldMedalInputField = new XTextField(
                SizeData.GAP + SizeData.NARROW_BUTTON_WIDTH + (SizeData.WIDE_BUTTON_WIDTH * nPowerOf2),
                SizeData.BUTTON_HEIGHT * (tableSize - 1),
                SizeData.WIDE_BUTTON_WIDTH,
                SizeData.BUTTON_HEIGHT,
                this.frame,
                new CustomAppearanceBuilder()
                    .addMainBackground(Color.black)
                    .addMainForeground(Color.red)
                    .addBorder(AppearanceData.RED_BORDER)
                    .addFont(AppearanceData.MAIN_FONT_B)
                    .build()
            );

            this.scrollPanel.addComponent(goldMedalInputField);

            // Find the corresponding filename
            if (tableSize > 64) {
                correspondingFileName = "database/tableau128.csv";
            }
            else if (tableSize > 32) {
                correspondingFileName = "database/tableau64.csv";
            }
            else if (tableSize > 16) {
                correspondingFileName = "database/tableau32.csv";
            }
            else if (tableSize > 8) {
                correspondingFileName = "database/tableau16.csv";
            }

            // Give the first column's inputField's the corresponding number
            try {
                // Read in the line
                final Scanner scanner = new Scanner(new File(correspondingFileName));
                final String[] values = scanner.nextLine().split(";");
                scanner.close();

                for (int i = 0; i < this.numberedFencerLabelList.size(); i++) {
                    this.numberedFencerLabelList.get(i).setNumber(values[i]);
                }
            }
            catch (Exception exc) {
                exc.printStackTrace();
            }

            // Fill the first column with the names
            this.numberedFencerLabelList.forEach(numberedFencerLabel -> {
                fencerList.forEach(fencer -> {
                    if (String.valueOf(fencer.getPlace()).equalsIgnoreCase(numberedFencerLabel.getNumber())) {
                        numberedFencerLabel.setFencer(fencer.getName());
                    }
                });
            });
        }
        // Generate the whole table with a size of 8
        else {
            this.numberedFencerLabelList = new ArrayList<>(tableSize);

            for (int i = 0; i < tableSize; i++) {
                final NumberedFencerLabel fencerLabel = new NumberedFencerLabel(SizeData.GAP, ((SizeData.BUTTON_HEIGHT * i) + (SizeData.BUTTON_HEIGHT * i)),
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
                        (SizeData.BUTTON_HEIGHT * yOffset),
                        SizeData.WIDE_BUTTON_WIDTH,
                        SizeData.BUTTON_HEIGHT,
                        this.frame,
                        BasicAppearance.BLACK_BORDERED
                    );
                    yOffset += 4 * x;
                    this.scrollPanel.addComponent(fencerInputField);
                }
                tempTableSize /= 2;
                yOffset = 3;
            }

            // Create the gold medal bout's input
            final XTextField goldMedalInputField = new XTextField(
                SizeData.GAP + SizeData.NARROW_BUTTON_WIDTH + (SizeData.WIDE_BUTTON_WIDTH * nPowerOf2),
                SizeData.BUTTON_HEIGHT * (tableSize - 1),
                SizeData.WIDE_BUTTON_WIDTH,
                SizeData.BUTTON_HEIGHT,
                this.frame,
                new CustomAppearanceBuilder()
                    .addMainBackground(Color.black)
                    .addMainForeground(Color.red)
                    .addBorder(AppearanceData.RED_BORDER)
                    .addFont(AppearanceData.MAIN_FONT_B)
                    .build()
            );

            this.scrollPanel.addComponent(goldMedalInputField);

            // Give the first column's inputField's the corresponding number
            try {
                // Read in the line
                final Scanner scanner = new Scanner(new File(correspondingFileName));
                final String[] values = scanner.nextLine().split(";");

                scanner.close();

                for (int i = 0; i < this.numberedFencerLabelList.size(); i++) {
                    this.numberedFencerLabelList.get(i).setNumber(values[i]);
                }
            }
            catch (Exception exc) {
                exc.printStackTrace();
            }

            // Fill the first column with the names
            this.numberedFencerLabelList.forEach(numberedFencerLabel -> {
                numberedFencerLabel.setFencer("----");
                fencerList.forEach(fencer -> {
                    if (String.valueOf(fencer.getPlace()).equalsIgnoreCase(numberedFencerLabel.getNumber())) {
                        numberedFencerLabel.setFencer(fencer.getName());
                    }
                });
            });
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
