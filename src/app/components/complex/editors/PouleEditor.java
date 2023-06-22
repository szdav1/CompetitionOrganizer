package app.components.complex.editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;

import app.components.buttons.FCXButton;
import app.components.complex.fencing.Poule;
import app.components.complex.inputs.InputField;
import app.components.labels.XLabel;
import app.components.panels.XPanel;
import app.components.scrollpanels.XScrollPanel;
import app.frame.XFrame;
import support.appdata.AppearanceData;
import support.appdata.SizeData;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public final class PouleEditor extends AbstractEditor implements KeyListener {
    private boolean isErrorPresent;

    // Title labels
    private final XLabel inputLabel;
    private final XLabel previewLabel;

    // Inner containers
    private final XPanel inputPanel;
    private final XPanel previewPanel;

    // Preview poule
    private final Poule previewPoule;

    // Input scroll panel
    private final XScrollPanel inputScrollPanel;
    // Preview scroll panel
    private final XScrollPanel previewScrollPanel;

    // List for the inputs
    private final List<InputField> inputList = new LinkedList<>();
    // List for the final values
    private final List<String> valueList = new ArrayList<>();

    public PouleEditor(XFrame frame, Appearance appearance) {
        super("Poule Editor", frame, appearance);

        this.isErrorPresent = false;

        // Title labels
        this.inputLabel = new XLabel(new Dimension(this.getWidth() * 45 / 100, SizeData.BUTTON_HEIGHT), "Enter Values",
            this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(this.getAppearance().getMainBackground())
                .build());

        this.previewLabel = new XLabel(this.inputLabel.getPreferredSize(), "Poule Preview", this.frame,
            this.inputLabel.getAppearance());

        // Inner containers
        this.inputPanel = new XPanel(new Dimension(this.inputLabel.getWidth(),
            this.centerPanel.getHeight() * 75 / 100), new BorderLayout(), this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(this.getAppearance().getMainBackground())
                .addBorder(this.getAppearance().getBorder())
                .build());

        this.previewPanel = new XPanel(this.inputPanel.getPreferredSize(), this.inputPanel.getLayout(), this.frame,
            this.inputPanel.getAppearance());

        // Preview poule
        this.previewPoule = new Poule(0, 0, this.frame, 8, BasicAppearance.BLACK_BORDERED);

        // Input scroll panel
        this.inputScrollPanel = new XScrollPanel(this.inputPanel.getPreferredSize(),
            new FlowLayout(FlowLayout.CENTER, SizeData.GAP, SizeData.GAP), this.frame, SizeData.GAP, SizeData.GAP,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addBorder(this.inputPanel.getAppearance().getBorder())
                .build(),
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .build());
        // Add the inputFields
        // The input with a * in its text must be filled
        this.createInputFields(new String[] {"Round*", "Poule*", "Fencers/Poule", "Fencers*", "Referee", "Date"});

        // Preview scroll panel
        this.previewScrollPanel = new XScrollPanel(this.previewPanel.getPreferredSize(), this.frame, SizeData.GAP, SizeData.GAP,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .build(),
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .build());
        // Add the previewPoule
        this.previewScrollPanel.addComponent(this.previewPoule);

        // Add the inputScrollPanel to the inputPanel
        this.inputPanel.addComponent(this.inputScrollPanel, BorderLayout.CENTER);

        // Add the previewScrollPanel to the previewPanel
        this.previewPanel.addComponent(this.previewScrollPanel, BorderLayout.CENTER);

        // Add the components to the centerPanel
        this.centerPanel.addComponent(this.inputLabel);
        this.centerPanel.addComponent(this.previewLabel);
        // Add the inner containers to the centerPanel
        this.centerPanel.addComponent(this.inputPanel);
        this.centerPanel.addComponent(this.previewPanel);

        this.addKeyListener(this);
    }

    private void createInputFields(String[] options) {
        for (int i = 0; i < options.length; i++) {
            final InputField inputField = new InputField(options[i], this.frame,
                BasicAppearance.BLACK,
                new CustomAppearanceBuilder()
                    .addMainBackground(Color.black)
                    .addMainForeground(Color.white)
                    .addBorder(AppearanceData.RED_BORDER)
                    .build());
            inputField.getInputField().addKeyListener(this);

            this.inputList.add(inputField);

            this.inputScrollPanel.addComponent(inputField);
            this.inputScrollPanel.getViewPanel().setPreferredSize(new Dimension(
                this.inputScrollPanel.getViewPanel().getPreferredSize().width,
                i * SizeData.INPUT_FIELD_HEIGHT + SizeData.BUTTON_HEIGHT
            ));
        }

        final FCXButton clearButton = new FCXButton(SizeData.BUTTON_DIMENSION, "Clear", this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.white)
                .addSecondaryForeground(Color.red)
                .addBorder(AppearanceData.RED_BORDER)
                .addFont(AppearanceData.MAIN_FONT_P)
                .build(),
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .addSecondaryForeground(Color.yellow)
                .build());
        clearButton.addActionListener(e -> this.inputList.forEach(input -> {
            input.getInputField().setText("");
            input.removeError();
        }));

        this.inputScrollPanel.addComponent(clearButton);
    }

    public List<String> getValueList() {
        return this.valueList;
    }

    private void checkValues() {
        this.isErrorPresent = false;
        this.valueList.clear();
        this.inputList.forEach(input -> {
            // Check for errors
            if (input.getText().isBlank() && input.getDescription().contains("*")) {
                input.displayError();
                this.isErrorPresent = true;
            }

            // Check for more errors
            if (Arrays.asList(new String[] {"Round*", "Fencers*", "Fencers/Poule"}).contains(input.getDescription())) {
                try {
                    if (!input.getText().isBlank()) {
                        int i = Integer.parseInt(input.getText());
                        if (i < 4 && !input.getDescription().equals("Round*")) {
                            throw new Exception();
                        }

                        if (i > 8 && input.getDescription().equals("Fencers/Poule")) {
                            throw new Exception();
                        }
                    }
                }
                catch (Exception exc) {
                    input.displayError();
                    this.isErrorPresent = true;
                }
            }
        });

        // Fetch the valid values if there are no errors
        if (!this.isErrorPresent) {
            this.inputList.forEach(input -> this.valueList.add(input.getText()));
            this.frame.closePouleEditor();
            // Toggled the competitionPanel on the frame
            this.frame.toggleCompetitionPanel(this.valueList);
        }
    }

    @Override
    public void addComponent(JComponent component, PositionConstants positionConstants) {
        this.add(component, positionConstants.getzIndex());
        this.repaintFrame();
    }

    @Override
    public void addComponent(JComponent component, String borderLayoutPosition) {
        if (!(this.getLayout() instanceof BorderLayout)) {
            return;
        }

        this.add(component, borderLayoutPosition);
        this.repaintFrame();
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
        if (e.getSource().equals(this.createButton.getButton())) {
            this.checkValues();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.checkValues();
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.frame.closePouleEditor();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(this.inputList.get(2).getInputField())) {
            try {
                int amount = Integer.parseInt(this.inputList.get(2).getText());
                if (amount >= 9 || amount < 4) {
                    throw new Exception();
                }

                this.previewPoule.reConstruct(amount);
                this.repaintFrame();
            }
            catch (Exception exc) {

            }
        }
    }
}
