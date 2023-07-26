package app.components.complex.fencing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

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

    // Strings for the first column's numbers
    private final String tableau8;
    private final String tableau16;
    private final String tableau32;
    private final String tableau64;
    private final String tablea128;

    public TableOnlyCompetitionPanel(XFrame frame, Appearance appearance) {
        super(frame, appearance);

        // Add functionality to the closeButton
        this.closeButton.addActionListener(e -> this.frame.closeTableOnlyCompetitionPanel());
        // Remove the finishAll button
        this.bottomSection.removeComponent(this.finishButton);

        this.scrollPanel.getViewPanel().setLayout(null);

        // Strings for the first column's numbers
        this.tableau8 = "1;8;5;4;3;6;7;2";
        this.tableau16 = "1;16;9;8;5;12;13;4;3;14;11;6;7;10;15;2";
        this.tableau32 = "1;32;17;16;9;24;25;8;5;28;21;12;13;20;29;4;3;30;19;14;11;22;27;6;7;26;23;10;15;18;31;2";
        this.tableau64 = "1;64;33;32;17;48;49;16;9;56;41;24;25;40;57;8;5;60;37;28;21;44;53;12;13;52;45;20;29;36;61;4;3;62;35;30;19;46;51;14;11;54;43;22;27;38;59;6;7;58;39;26;23;42;55;10;15;50;47;18;31;34;63;2";
        this.tablea128 = "1;128;65;64;33;96;97;32;17;112;81;48;49;80;113;16;9;120;73;56;41;88;105;24;25;104;89;40;57;72;121;8;5;124;69;60;37;92;101;28;21;108;85;44;53;76;117;12;13;116;77;52;45;84;109;20;29;100;93;36;61;68;125;4;3;126;67;62;35;94;99;30;19;110;83;46;51;78;115;14;11;118;75;54;43;86;107;22;27;102;91;38;59;70;123;6;7;122;71;58;39;90;103;26;23;106;87;42;55;74;119;10;15;114;79;50;47;82;111;18;31;98;95;34;63;66;127;2";
    }

    public void generateStructure(final List<Fencer> fencerList) {
        // Strings for the first column's numbers
        String correspondingString = tableau8;

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
                correspondingString = this.tablea128;
            }
            else if (tableSize > 32) {
                correspondingString = this.tableau64;
            }
            else if (tableSize > 16) {
                correspondingString = this.tableau32;
            }
            else if (tableSize > 8) {
                correspondingString = this.tableau16;
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
            int columnIndex = 0;
            for (String string : correspondingString.split(";")) {
                this.numberedFencerLabelList.get(columnIndex).setNumber(string);
                columnIndex++;
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
