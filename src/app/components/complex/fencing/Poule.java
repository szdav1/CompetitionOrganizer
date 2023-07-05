package app.components.complex.fencing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Arrays;

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
        boolean isErrorPresent = false;

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
                isErrorPresent = true;
            }

            if (fencer2 > this.amount) {
                this.fencer2NameInput.displayError();
                isErrorPresent = true;
            }

            if (fencer1 == fencer2) {
                this.fencer1NameInput.displayError();
                this.fencer2NameInput.displayError();
                isErrorPresent = true;
            }

            if (touch1 < 0 || touch1 > 5) {
                throw new Exception("Invalid touch1");
            }

            if (touch2 < 0 || touch2 > 5) {
                throw new Exception("Invalid touch2");
            }

            if (touch1 == touch2) {
                this.fencer1TouchInput.displayError();
                this.fencer2TouchInput.displayError();
                isErrorPresent = true;
            }

            if (touch1 < 5 && touch2 < 5) {
                this.fencer1TouchInput.displayError();
                this.fencer2TouchInput.displayError();
                isErrorPresent = true;
            }

            if (isErrorPresent) {
                return;
            }

            // Insert fencer1's touch to its row
            this.boxArray[fencer1][fencer2 + 1].setText(touch1 == 5 ? "V" : String.valueOf(touch1));
            // Insert fencer2's touch to its row
            this.boxArray[fencer2][fencer1 + 1].setText(touch2 == 5 ? "V" : String.valueOf(touch2));

            this.fencer1NameInput.clearText();
            this.fencer2NameInput.clearText();
            this.fencer1TouchInput.clearText();
            this.fencer2TouchInput.clearText();
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

                    this.fencer1NameInput.clearText();
                    this.fencer2NameInput.clearText();
                    this.fencer1TouchInput.clearText();
                    this.fencer2TouchInput.clearText();
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

    public void setNumber(String number) {
        this.pouleNumberLabel.setText(this.pouleNumberLabel.getText().concat(number));
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
            this.insertValues();
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
        final Object source = e.getSource();
        int indexX = 0;
        int indexY = 0;

        for (int y = 1; y < this.amount + 1; y++) {
            for (int x = 2; x < this.amount + 2; x++) {
                if (e.getSource().equals(this.boxArray[y][x])) {
                    if (Arrays.asList(new Integer[] {KeyEvent.VK_0, KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3,
                        KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_V}).contains(e.getKeyCode())) {
                        this.boxArray[y][x].setText(String.valueOf(e.getKeyChar()).toUpperCase());

                        if (this.boxArray[x - 1][y + 1].getText().equalsIgnoreCase(this.boxArray[y][x].getText())) {
                            this.boxArray[y][x].setText("");
                        }
                    }
                    else if (e.getKeyCode() == KeyEvent.VK_F2) {

                    }
                    else {
                        this.boxArray[y][x].setText("");
                    }
                }
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_F2) {
            for (int y = 1; y < this.amount + 1; y++) {
                for (int x = 2; x < this.amount + 2; x++) {
                    if (source.equals(this.boxArray[y][x]) && this.boxArray[y][x].isEnabled() && this.boxArray[y][x].isFocusable()) {
                        indexX = x;
                        indexY = y;
                        break;
                    }
                }
            }

            if (indexX >= 1 && indexX < this.amount + 2 && indexY >= 1 && indexY < this.amount + 1) {
                this.boxArray[indexX - 1][indexY + 1].requestFocusInWindow();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        final Object source = e.getSource();
        int indexX = 0;
        int indexY = 0;

        for (int y = 1; y < this.amount + 1; y++) {
            for (int x = 2; x < this.amount + 2; x++) {
                if (source.equals(this.boxArray[y][x]) && this.boxArray[y][x].isEnabled() && this.boxArray[y][x].isFocusable()) {
                    this.boxArray[y][x].setBackground(Color.lightGray);
                    this.boxArray[y][x].requestFocusInWindow();
                    indexX = x;
                    indexY = y;
                    break;
                }
            }
        }

        if (indexX >= 1 && indexX < this.amount + 2 && indexY >= 1 && indexY < this.amount + 1) {
            this.boxArray[indexX - 1][indexY + 1].setBackground(Color.lightGray);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        final Object source = e.getSource();
        int indexX = 0;
        int indexY = 0;

        for (int y = 1; y < this.amount + 1; y++) {
            for (int x = 2; x < this.amount + 2; x++) {
                if (source.equals(this.boxArray[y][x]) && this.boxArray[y][x].isEnabled() && this.boxArray[y][x].isFocusable()) {
                    if (this.boxArray[y][x].isEnabled()) {
                        this.boxArray[y][x].setBackground(Color.black);
                        indexX = x;
                        indexY = y;
                        break;
                    }
                }
            }
        }

        if (indexX >= 1 && indexX < this.amount + 2 && indexY >= 1 && indexY < this.amount + 1) {
            this.boxArray[indexX - 1][indexY + 1].setBackground(Color.black);
        }
    }
}
