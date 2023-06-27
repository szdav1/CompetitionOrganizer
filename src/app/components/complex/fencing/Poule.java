// TODO: Implement the buttons' functionalities
package app.components.complex.fencing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;

import app.frame.XFrame;
import support.constants.PositionConstants;
import support.framework.interfaces.Appearance;

public final class Poule extends AbstractPoule {
    public Poule(XFrame frame, int amount, Appearance appearance) {
        super(frame, amount, appearance);
    }

    public Poule(int x, int y, XFrame frame, int amount, Appearance appearance) {
        super(x, y, frame, amount, appearance);
    }

    public void insertValues() {
        try {
            if (this.fencer1TouchInput.getText().isBlank()) {
                throw new Exception("Invalid touch1");
            }

            if (this.fencer2TouchInput.getText().isBlank()) {
                throw new Exception("Invalid touch2");
            }

            int fencer1 = Integer.parseInt(this.fencer1NameInput.getText());
            int fencer2 = Integer.parseInt(this.fencer2NameInput.getText());
            int touch1 = Integer.parseInt(this.fencer1TouchInput.getText());
            int touch2 = Integer.parseInt(this.fencer2TouchInput.getText());

            if (fencer1 > this.amount) {
                this.fencer1NameInput.displayError();
                return;
            }

            if (fencer2 > this.amount) {
                this.fencer2NameInput.displayError();
                return;
            }

            if (fencer1 == fencer2) {
                this.fencer1NameInput.displayError();
                this.fencer2NameInput.displayError();
                return;
            }

            if (touch1 < 0 || touch1 > 5) {
                throw new Exception("Invalid touch1");
            }

            if (touch2 < 0 || touch2 > 5) {
                throw new Exception("Invalid touch2");
            }

            if (touch1 == 5 && touch2 == 5) {
                this.fencer1TouchInput.displayError();
                this.fencer2TouchInput.displayError();
                return;
            }

            // Insert fencer1's touch to its row
            this.boxArray[fencer1][fencer2 + 1].setText(touch1 == 5 ? "V" : String.valueOf(touch1));
            // Insert fencer2's touch to its row
            this.boxArray[fencer2][fencer1 + 1].setText(touch2 == 5 ? "V" : String.valueOf(touch2));
        }
        catch (Exception exc) {
            if (exc.getMessage().equals("Invalid touch1")) {
                this.fencer1TouchInput.displayError();
            }
            else if (exc.getMessage().equals("Invalid touch2")) {
                this.fencer2TouchInput.displayError();
            }
            else {
                String fencer1 = this.fencer1NameInput.getText();
                String fencer2 = this.fencer2NameInput.getText();
                int touch1 = Integer.parseInt(this.fencer1TouchInput.getText());
                int touch2 = Integer.parseInt(this.fencer2TouchInput.getText());

                if (touch1 < 0 || touch1 > 5) {
                    this.fencer1TouchInput.displayError();
                    return;
                }

                if (touch2 < 0 || touch2 > 5) {
                    this.fencer2TouchInput.displayError();
                    return;
                }

                if (touch1 == 5 && touch2 == 5) {
                    this.fencer1TouchInput.displayError();
                    this.fencer2TouchInput.displayError();
                    return;
                }

                // Search for the fencers by name
                int fencer1Index = 0;
                int fencer2Index = 0;
                for (int i = 1; i < this.amount + 1; i++) {
                    if (this.boxArray[i][0].getText().equalsIgnoreCase(fencer1)) {
                        fencer1Index = i;
                    }
                    else if (this.boxArray[i][0].getText().equalsIgnoreCase(fencer2)) {
                        fencer2Index = i;
                    }
                }

                if (fencer1Index > 0 && fencer2Index > 0 && fencer1Index != fencer2Index) {
                    // Insert fencer1's touch to its row
                    this.boxArray[fencer1Index][fencer2Index + 1].setText(touch1 == 5 ? "V" : String.valueOf(touch1));
                    // Insert fencer2's touch to its row
                    this.boxArray[fencer2Index][fencer1Index + 1].setText(touch2 == 5 ? "V" : String.valueOf(touch2));
                }
                else if (fencer1Index == 0) {
                    this.fencer1NameInput.displayError();
                }
                else if (fencer2Index == 0) {
                    this.fencer2NameInput.displayError();
                }
                else if (fencer1Index == fencer2Index) {
                    this.fencer1NameInput.displayError();
                    this.fencer2NameInput.displayError();
                }
            }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object source = e.getSource();
        if (source.equals(this.insertButton.getButton())) {
//            this.insertValues();
            this.calculateFencerData();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.insertValues();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
