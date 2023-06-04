package app.components.complex.fencing;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextField;

import app.components.panels.AbstractXPanel;
import app.components.textcontainers.XTextField;
import app.frame.XFrame;
import support.appdata.AppearanceData;
import support.appdata.SizeData;
import support.framework.appearances.BasicAppearance;
import support.framework.interfaces.Appearance;

public abstract class AbstractPoule extends AbstractXPanel {
    // Box list
    protected final List<XTextField> boxList = new LinkedList<>();
    // Amount of fencers
    protected int amount;

    protected AbstractPoule(XFrame frame, int amount, Appearance appearance) {
        super(SizeData.POULE_DIMENSION, null, frame, appearance);

        this.amount = amount;
        this.createPouleStructure();
    }

    protected AbstractPoule(int x, int y, XFrame frame, int amount, Appearance appearance) {
        this(frame, amount, appearance);
        this.setBounds(x, y, SizeData.POULE_WIDTH, SizeData.POULE_HEIGHT);
    }

    private void createPouleStructure() {
        for (int y = 0; y < this.amount + 1; y++) {
            for (int x = 0; x < this.amount + 7; x++) {
                // Create the box
                final XTextField box = new XTextField(
                    x > 0 ? (x - 1) * SizeData.BOX_WIDTH + SizeData.NAME_BOX_WIDTH : 0, y * SizeData.BOX_HEIGHT,
                    x == 0 ? SizeData.NAME_BOX_WIDTH : SizeData.BOX_WIDTH, SizeData.BOX_HEIGHT, this.frame,
                    BasicAppearance.BLACK_BORDERED);
                box.setHorizontalAlignment(JTextField.CENTER);

                // Do the modifications to the box
                // First row
                if (y == 0) {
                    box.setFocusable(false);
                    box.setBackground(AppearanceData.DARK_GRAY);
                    if (x == 0) {
                        box.setText("Fencer Name");
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
                this.boxList.add(box);
                this.add(box);
            }
        }
    }
}
