package app.components.complex.editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JComponent;

import app.components.buttons.FCXButton;
import app.components.complex.inputs.InputField;
import app.components.complex.other.Checkbox;
import app.components.complex.other.SelectionPanel;
import app.components.labels.XLabel;
import app.components.panels.XPanel;
import app.frame.XFrame;
import app.other.Fencer;
import support.appdata.AppearanceData;
import support.appdata.SizeData;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public final class DatabaseEditor extends AbstractEditor implements KeyListener {
    // List for the fencers in the database
    private final List<Fencer> fencerList = new ArrayList<>();
    // Labels
    // For the scroll panel
    private final XLabel scrollPanelLabel;
    // For the input
    private final XLabel inputLabel;
    // Selection panel that will display the fencers in the database
    private final SelectionPanel selectionPanel;
    // Inner container for the fencerNameInput
    private final XPanel innerContainer;
    // Input field to insert fencers into the database
    private final InputField fencerNameInput;
    // Button to remove fencers
    private final FCXButton removeButton;

    public DatabaseEditor(XFrame frame, Appearance appearance) {
        super("Database Editor", frame, appearance);

        this.closeButton.addActionListener(e -> this.frame.closeDatabaseEditor());
        this.centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, SizeData.GAP / 2, 0));

        // Labels
        // For the scroll panel
        this.scrollPanelLabel = new XLabel(new Dimension(this.getPreferredSize().width / 2 - SizeData.GAP, SizeData.BUTTON_HEIGHT),
            "Remove Fencers", this.frame, BasicAppearance.BLACK);

        this.inputLabel = new XLabel(this.scrollPanelLabel.getPreferredSize(), "Insert Fencers", this.frame, BasicAppearance.BLACK);

        // Selection panel that will display the fencers in the database
        this.selectionPanel = new SelectionPanel(new Dimension(this.getWidth() / 2 - SizeData.GAP,
            this.getHeight() * 70 / 100), this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addBorder(AppearanceData.GRAY_BORDER)
                .build());
        this.selectionPanel.removeTopSection();

        // Inner container for the fencerNameInput
        this.innerContainer = new XPanel(this.selectionPanel.getPreferredSize(), new FlowLayout(FlowLayout.CENTER, 0, 0),
            this.frame, BasicAppearance.BLACK_BORDERED);

        // Input field to insert fencers into the database
        this.fencerNameInput = new InputField("Fencer Name", this.frame, BasicAppearance.BLACK,
            this.selectionPanel.getAppearance());
        this.fencerNameInput.getInputField().addKeyListener(this);

        // Button to remove fencers
        this.removeButton = new FCXButton(SizeData.BUTTON_DIMENSION, "Remove", this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.white)
                .addSecondaryForeground(Color.red)
                .addBorder(AppearanceData.RED_BORDER)
                .addFont(AppearanceData.MAIN_FONT_P)
                .build(),
            new CustomAppearanceBuilder()
                .addMainForeground(Color.red)
                .addSecondaryForeground(Color.yellow)
                .build());
        this.removeButton.addActionListener(e -> this.removeFencerFromFile());

        // Recreate the createButton
        this.createButton.getButton().setText("Insert");
        this.createButton.addActionListener(e -> this.insertFencerToFile());

        // Add components to the innerContainer
        this.innerContainer.addComponent(this.fencerNameInput);

        // Add components to the editor
        this.centerPanel.addComponent(this.inputLabel);
        this.centerPanel.addComponent(this.scrollPanelLabel);
        this.centerPanel.addComponent(this.innerContainer);
        this.centerPanel.addComponent(this.selectionPanel);

        // Add components to the footer panel
        this.footerPanel.addComponent(this.removeButton);

        this.addKeyListener(this);
    }

    private void removeFencerFromFile() {
        try {
            // Remove all the fencers
            this.fencerList.clear();

            // Gather the unselected fencers
            final List<Fencer> unselectedFencerList = this.selectionPanel.getUnselectedFencers();

            // Write the fencers to the file
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("database/fencers.csv"));
            for (Fencer fencer : unselectedFencerList) {
                bufferedWriter.write(fencer.getName().concat(";"));
            }
            bufferedWriter.close();
            this.readFencersFromFile();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void insertFencerToFile() {
        final String fencerName = this.fencerNameInput.getText();
        StringBuilder data = new StringBuilder();

        // Check for empty name value
        if (fencerName.isBlank() || fencerName.equalsIgnoreCase("missing name")) {
            this.fencerNameInput.displayError("Missing Name");
            return;
        }

        try {
            // Open the file for reading
            final Scanner scanner = new Scanner(new File("database/fencers.csv"));
            // Read in all the existing data
            while (scanner.hasNextLine()) {
                data.append(scanner.nextLine());
            }
            scanner.close();
            // Concat the fencer name to the existing data
            data.append(fencerName.concat(";"));

            // Open the file for writing
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("database/fencers.csv"));
            bufferedWriter.write(data.toString());
            bufferedWriter.close();

            this.readFencersFromFile();
            this.fencerNameInput.clearText();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void readFencersFromFile() {
        // Clear the fencer list
        this.fencerList.clear();
        // Clear the fencerDisplayPanel
        this.selectionPanel.removeAllFromScrollPanel();

        try {
            // Open the file
            final Scanner scanner = new Scanner(new File("database/fencers.csv"));
            // Read the fencers from the file
            while (scanner.hasNextLine()) {
                final String[] data = scanner.nextLine().split(";");
                for (String datum : data) {
                    this.fencerList.add(new Fencer(datum, 0, 0, 0, 0));
                }
            }
            scanner.close();

            // fill the dropdown panel with the fencers
            for (int i = 0; i < this.fencerList.size(); i++) {
                final Checkbox fencerCheckbox = new Checkbox(SizeData.GAP, (SizeData.BUTTON_HEIGHT * i) + (SizeData.GAP * (i + 1)),
                    this.selectionPanel.getWidth() - (SizeData.GAP * 2), SizeData.BUTTON_HEIGHT,
                    this.fencerList.get(i).getName(), this.frame,
                    new CustomAppearanceBuilder()
                        .addMainBackground(Color.black)
                        .addMainForeground(Color.white)
                        .addFont(AppearanceData.MAIN_FONT_P)
                        .addBorder(AppearanceData.GRAY_BORDER)
                        .build());

                this.selectionPanel.addToScrollPanel(fencerCheckbox);
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        this.frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.insertFencerToFile();
        }
        else if (e.getKeyCode() == KeyEvent.VK_F12) {
            this.removeFencerFromFile();
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.frame.closeDatabaseEditor();
        }
    }
}
