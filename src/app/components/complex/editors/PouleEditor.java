package app.components.complex.editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.*;

import javax.swing.JComponent;

import app.components.buttons.FCXButton;
import app.components.complex.fencing.Poule;
import app.components.complex.inputs.InputField;
import app.components.complex.other.Checkbox;
import app.components.complex.other.SelectionPanel;
import app.components.labels.XLabel;
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

public final class PouleEditor extends AbstractEditor implements KeyListener {
    private boolean isErrorPresent;
    private boolean isFromSelection;

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

    // Button for the "enter values via database" window
    private final FCXButton enterValuesViaDatabaseButton;

    // Label for the input scroll panel that displays the method of poule generation
    private final XLabel pouleGenerationInfoLabel;

    // Selection panel
    private final SelectionPanel selectionPanel;

    // List for the inputs
    private final List<InputField> inputList = new LinkedList<>();
    // List for the final values
    private final List<String> valueList = new ArrayList<>();
    // List of fencers
    private List<Fencer> fencerList = new ArrayList<>();

    public PouleEditor(XFrame frame, Appearance appearance) {
        super("Poule Editor", frame, appearance);

        this.closeButton.addActionListener(e -> this.frame.closePouleEditor());

        this.isErrorPresent = false;
        this.isFromSelection = false;

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
                .addBorder(AppearanceData.GRAY_BORDER)
                .build());

        this.previewPanel = new XPanel(this.inputPanel.getPreferredSize(), this.inputPanel.getLayout(), this.frame,
            this.inputPanel.getAppearance());

        // Preview poule
        this.previewPoule = new Poule(0, 0, this.frame, 8, BasicAppearance.BLACK_BORDERED);
        this.previewPoule.setSize(SizeData.POULE_WIDTH, SizeData.PREVIEW_POULE_HEIGHT);

        // Input scroll panel
        this.inputScrollPanel = new XScrollPanel(this.inputPanel.getPreferredSize(),
            new FlowLayout(FlowLayout.CENTER, SizeData.GAP, SizeData.GAP), this.frame, SizeData.GAP, SizeData.GAP,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addBorder(this.inputPanel.getAppearance().getBorder())
                .build(),
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.darkGray)
                .build());
        // Add the inputFields
        // The input with a * in its text must be filled
        this.createInputFields(new String[] {"Round*", "Fencers/Poule", "Fencers*", "Date"});

        // Preview scroll panel
        this.previewScrollPanel = new XScrollPanel(this.previewPanel.getPreferredSize(), this.frame, SizeData.GAP, SizeData.GAP,
            this.inputScrollPanel.getAppearance(),
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.darkGray)
                .build());
        // Add the previewPoule
        this.previewScrollPanel.addComponent(this.previewPoule);

        // Button for the "enter values via database" window
        this.enterValuesViaDatabaseButton = new FCXButton(SizeData.WIDE_BUTTON_DIMENSION, "Fill Via Database",
            this.frame,
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
        this.enterValuesViaDatabaseButton.addActionListener(e -> this.toggleSelectionPanel());

        // Label that displays the method of poule generation
        this.pouleGenerationInfoLabel = new XLabel(new Dimension(this.inputScrollPanel.getPreferredSize().width - SizeData.GAP,
            SizeData.BUTTON_HEIGHT), "Generation Method: Search for optimal", this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.white)
                .addBorder(AppearanceData.RED_BORDER)
                .addFont(AppearanceData.MAIN_FONT_P)
                .build());

        // Selection panel
        this.selectionPanel = new SelectionPanel(new Dimension(this.getPreferredSize().width,
            this.getPreferredSize().height - (SizeData.BUTTON_HEIGHT * 2) - SizeData.GAP), this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addBorder(AppearanceData.RED_BORDER)
                .build());
        this.selectionPanel.getCloseButton().addActionListener(e -> this.closeSelectionPanel());

        // Add components to the inputScrollPanel
        this.inputScrollPanel.addComponent(this.enterValuesViaDatabaseButton);
        this.inputScrollPanel.addComponent(this.pouleGenerationInfoLabel);

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

    private void toggleSelectionPanel() {
        this.isFromSelection = true;
        // Read the fencers from the file
        this.readFencersFromFile();

        this.centerPanel.removeAll();
        this.centerPanel.addComponent(this.selectionPanel);
    }

    private void closeSelectionPanel() {
        this.isFromSelection = false;
        this.centerPanel.removeAll();
        this.selectionPanel.unSelectAll();

        // Add the components to the centerPanel
        this.centerPanel.addComponent(this.inputLabel);
        this.centerPanel.addComponent(this.previewLabel);
        // Add the inner containers to the centerPanel
        this.centerPanel.addComponent(this.inputPanel);
        this.centerPanel.addComponent(this.previewPanel);
    }

    private void readFencersFromFile() {
        this.fencerList.clear();
        this.selectionPanel.removeAllFromScrollPanel();

        try {
            final Scanner scanner = new Scanner(new File("database/fencers.csv"));
            while (scanner.hasNextLine()) {
                final String[] data = scanner.nextLine().split(";");
                for (String datum : data) {
                    this.fencerList.add(new Fencer(datum, 0, 0, 0, 0));
                }
            }
            scanner.close();

            for (int i = 0; i < this.fencerList.size(); i++) {
                final Checkbox checkbox = new Checkbox(SizeData.GAP, (SizeData.BUTTON_HEIGHT * i) + (SizeData.GAP * i),
                    this.selectionPanel.getPreferredSize().width - (SizeData.GAP * 2), SizeData.BUTTON_HEIGHT,
                    this.fencerList.get(i).getName(), this.frame,BasicAppearance.BLACK_BORDERED);

                this.selectionPanel.addToScrollPanel(checkbox);
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void createInputFields(String[] options) {
        for (int i = 0; i < options.length; i++) {
            final InputField inputField = new InputField(options[i], this.frame,
                BasicAppearance.BLACK,
                new CustomAppearanceBuilder()
                    .addMainBackground(Color.black)
                    .addMainForeground(Color.white)
                    .addBorder(AppearanceData.GRAY_BORDER)
                    .build());
            inputField.getInputField().addKeyListener(this);

            if (inputField.getDescription().isBlank()) {
                inputField.getInputField().setBorder(null);
                inputField.getInputField().setFocusable(false);
                inputField.getInputField().setEnabled(false);
            }

            this.inputList.add(inputField);

            this.inputScrollPanel.addComponent(inputField);
            this.inputScrollPanel.getViewPanel().setPreferredSize(new Dimension(
                this.inputScrollPanel.getViewPanel().getPreferredSize().width,
                i * SizeData.INPUT_FIELD_HEIGHT + SizeData.BUTTON_HEIGHT
            ));
        }

        final FCXButton clearButton = new FCXButton(SizeData.WIDE_BUTTON_DIMENSION, "Clear", this.frame,
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
        }
    }

    public void reset() {
        this.closeSelectionPanel();
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
        if (e.getSource().equals(this.createButton.getButton()) && !this.isFromSelection) {
            this.checkValues();
            this.frame.togglePouleOnlyCompetitionPanel(this.valueList, new ArrayList<>(0));
        }
        else if (e.getSource().equals(this.createButton.getButton()) && this.isFromSelection) {
            final List<Fencer> selectedFencerList = this.selectionPanel.getSelectedFencers();
            if (selectedFencerList.size() < 4) {
                return;
            }

            this.frame.closePouleEditor();
            this.frame.togglePouleOnlyCompetitionPanel(new ArrayList<>(Arrays.asList("1", "",
                String.valueOf(selectedFencerList.size()), "")), selectedFencerList);
            this.isFromSelection = false;
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
        if (e.getSource().equals(this.inputList.get(1).getInputField())) {
            try {
                int amount = Integer.parseInt(this.inputList.get(1).getText());
                if (amount >= 9 || amount < 4) {
                    throw new Exception();
                }

                this.previewPoule.reConstruct(amount);
                this.repaintFrame();
            }
            catch (Exception exc) {

            }
        }

        this.pouleGenerationInfoLabel.setText(
            this.inputList.get(1).getText().isBlank() ? "Generation Method: Search for optimal"
                : "Generation Method: Based on user data"
        );

        if (e.getKeyCode() == KeyEvent.VK_F2) {
            this.toggleSelectionPanel();
        }
    }
}
