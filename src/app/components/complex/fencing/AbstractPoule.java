package app.components.complex.fencing;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JTextField;

import app.components.buttons.FCXButton;
import app.components.complex.inputs.InputField;
import app.components.panels.AbstractXPanel;
import app.components.textcontainers.XTextField;
import app.frame.XFrame;
import app.other.Fencer;
import support.appdata.AppearanceData;
import support.appdata.SizeData;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public abstract class AbstractPoule extends AbstractXPanel implements ActionListener, KeyListener {
    // Box array
    protected final XTextField[][] boxArray = new XTextField[9][15];
    // Fencer list
    protected List<Fencer> fencerList = new ArrayList<>();
    // Amount of fencers
    protected int amount;
    // Inner container
    // Input fields for the inner container
    protected final InputField fencer1NameInput;
    protected final InputField fencer2NameInput;
    protected final InputField fencer1TouchInput;
    protected final InputField fencer2TouchInput;
    // Buttons for the inner container
    protected final FCXButton insertButton;

    protected AbstractPoule(XFrame frame, int amount, Appearance appearance) {
        super(SizeData.POULE_DIMENSION, null, frame, appearance);

        this.amount = amount;

        // Input fields for the inner container
        this.fencer1NameInput = new InputField(SizeData.GAP,
            this.getHeight() - (SizeData.INPUT_FIELD_HEIGHT * 2) - SizeData.GAP, "Fencer1", this.frame, BasicAppearance.BLACK,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.white)
                .addBorder(AppearanceData.RED_BORDER)
                .build());
        this.fencer1NameInput.addKeyListener(this);

        this.fencer2NameInput = new InputField(this.fencer1NameInput.getX(),
            this.fencer1NameInput.getY() + SizeData.INPUT_FIELD_HEIGHT, "Fencer2", this.frame, BasicAppearance.BLACK,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.white)
                .addBorder(AppearanceData.RED_BORDER)
                .build());
        this.fencer2NameInput.addKeyListener(this);

        this.fencer1TouchInput = new InputField(this.fencer1NameInput.getX() + SizeData.INPUT_FIELD_WIDTH + SizeData.GAP,
            this.fencer1NameInput.getY(), "Point1", this.frame, BasicAppearance.BLACK,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.white)
                .addBorder(AppearanceData.RED_BORDER)
                .build());
        this.fencer1TouchInput.addKeyListener(this);

        this.fencer2TouchInput = new InputField(this.fencer1NameInput.getX() + SizeData.INPUT_FIELD_WIDTH + SizeData.GAP,
            this.fencer2NameInput.getY(), "Point2", this.frame, BasicAppearance.BLACK,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.white)
                .addBorder(AppearanceData.RED_BORDER)
                .build());
        this.fencer2TouchInput.addKeyListener(this);

        // Buttons for the inner container
        this.insertButton = new FCXButton(this.fencer1TouchInput.getX() + this.fencer1TouchInput.getWidth() + SizeData.GAP,
            this.fencer1NameInput.getY() + SizeData.BUTTON_HEIGHT, SizeData.BUTTON_WIDTH, SizeData.BUTTON_HEIGHT, "Insert", this.frame,
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
        this.insertButton.addActionListener(this);

        // Add components to the inner container
        this.addComponent(this.fencer1NameInput);
        this.addComponent(this.fencer1TouchInput);
        this.addComponent(this.fencer2NameInput);
        this.addComponent(this.fencer2TouchInput);
        this.addComponent(this.insertButton);

        this.createPouleStructure();

        this.addKeyListener(this);
    }

    protected AbstractPoule(int x, int y, XFrame frame, int amount, Appearance appearance) {
        this(frame, amount, appearance);
        this.setBounds(x, y, SizeData.POULE_WIDTH, SizeData.POULE_HEIGHT);
    }

    public void calculateFencerData() {
        // Reset the fencerList
        this.fencerList.clear();

        // Calculate the tr
        int current = 1;
        for (int x = 2; x < this.amount + 2; x++) {
            int tr = 0;
            for (int y = 1; y < this.amount + 1; y++) {
                if (!this.boxArray[y][x].getText().isBlank()) {
                    tr += this.boxArray[y][x].getText().equals("V") ? 5 : Integer.parseInt(this.boxArray[y][x].getText());
                }
            }
            this.boxArray[current][this.amount + 4].setText(String.valueOf(tr));
            current++;
        }

        for (int y = 1; y < this.amount + 1; y++) {
            int wins = 0;
            int ts = 0;
            int index = 0;
            int place = 0;

            for (int x = 2; x < this.amount + 2; x++) {
                // Calculate the wins
                if (this.boxArray[y][x].getText().equals("V")) {
                    wins++;
                }

                // Calculate the ts
                if (!this.boxArray[y][x].getText().isBlank()) {
                    ts += this.boxArray[y][x].getText().equals("V") ? 5 : Integer.parseInt(this.boxArray[y][x].getText());
                }
            }

            // Insert the values to the corresponding place
            // Wins
            this.boxArray[y][this.amount + 2].setText(String.valueOf(wins));
            // Ts
            this.boxArray[y][this.amount + 3].setText(String.valueOf(ts));
            // Index
            this.boxArray[y][this.amount + 5].setText(
                String.valueOf(Integer.parseInt(this.boxArray[y][this.amount + 3].getText()) -
                    Integer.parseInt(this.boxArray[y][this.amount + 4].getText()))
            );
        }

        // Collect the fencers
        for (int y = 1; y < this.amount + 1; y++) {
            String name;
            int wins;
            int ts;
            int tr;
            int index;
            int place;

            this.fencerList.add(new Fencer(
                this.boxArray[y][0].getText(),
                Integer.parseInt(this.boxArray[y][this.amount + 2].getText()),
                Integer.parseInt(this.boxArray[y][this.amount + 3].getText()),
                Integer.parseInt(this.boxArray[y][this.amount + 4].getText()),
                Integer.parseInt(this.boxArray[y][this.amount + 5].getText())
            ));
        }

        // Sort the fencerList - descending
        this.fencerList = this.fencerList.stream()
            .sorted(Comparator.comparing(Fencer::getIndex).reversed())
            .collect(Collectors.toList());
        // Set the place for the fencers
        this.fencerList.forEach(fencer -> fencer.setPlace(this.fencerList.indexOf(fencer) + 1));

        // Insert the fencer's place into the poule
        int i = 0;
        for (int y = 1; y < this.amount + 1; y++) {
            int yTemp = y;
            this.fencerList.forEach(fencer -> {
                if (this.boxArray[yTemp][0].getText().equals(fencer.getName())) {
                    this.boxArray[yTemp][this.amount + 6].setText(String.valueOf(fencer.getPlace()));
                }
            });
        }

        this.fencerList.forEach(System.out::println);
    }

    private void createPouleStructure() {
        for (int y = 0; y < this.amount + 1; y++) {
            for (int x = 0; x < this.amount + 7; x++) {
                // Create the box
                final XTextField box = new XTextField(
                    x > 0 ? (x - 1) * SizeData.BOX_WIDTH + SizeData.NAME_BOX_WIDTH : SizeData.BORDER_SIZE, y * SizeData.BOX_HEIGHT,
                    x == 0 ? SizeData.NAME_BOX_WIDTH : SizeData.BOX_WIDTH, SizeData.BOX_HEIGHT, this.frame,
                    BasicAppearance.BLACK_BORDERED);
                box.setHorizontalAlignment(JTextField.CENTER);

                // Do the modifications to the box
                // First row
                if (y == 0) {
                    box.setFocusable(false);
                    box.setBackground(AppearanceData.DARK_GRAY);
                    if (x == 0) {
                        box.setText("Name");
                    }
                    else if (x == 1) {
                        box.setText("#");
                    }
                    else if (x <= this.amount + 1){
                        box.setText(String.valueOf(x - 1));
                    }
                    else if (x == this.amount + 2){
                        box.setText("V");
                    }
                    else if (x == this.amount + 3) {
                        box.setText("TS");
                    }
                    else if (x == this.amount + 4) {
                        box.setText("TR");
                    }
                    else if (x == this.amount + 5) {
                        box.setText("Ind");
                    }
                    else {
                        box.setText("Pl");
                    }
                }
                // Disable accessibility to the last 5 columns
                if (x > this.amount + 1) {
                    box.setFocusable(false);
                    box.setBackground(AppearanceData.DARK_GRAY);
                }

                if (x == 1 && y > 0) {
                    box.setFocusable(false);
                    box.setBackground(AppearanceData.DARK_GRAY);
                    box.setText(String.valueOf(y));
                }

                // Disable accessibility to the same column and row numbers
                if (x == y + 1) {
                    box.setFocusable(false);
                    box.setBackground(AppearanceData.DARK_GRAY);
                }

                // Add the box to the list and to the panel
                this.boxArray[y][x] = box;
                this.add(box);
            }
        }

        for (int y = 0; y < this.amount + 1; y++) {
            this.boxArray[y][this.amount + 6].setForeground(Color.red);
        }
    }

    public void reConstruct(int amount) {
        // Remove previous boxes
        for (int i = 0; i < this.amount; i++) {
            Arrays.fill(this.boxArray[i], null);
        }

        this.removeAll();

        this.addComponent(this.fencer1NameInput);
        this.addComponent(this.fencer1TouchInput);
        this.addComponent(this.fencer2NameInput);
        this.addComponent(this.fencer2TouchInput);
        this.addComponent(this.insertButton);

        // Create structure with new amount
        this.amount = amount;
        this.createPouleStructure();
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
