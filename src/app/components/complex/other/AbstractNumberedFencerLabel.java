package app.components.complex.other;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

import app.components.labels.XLabel;
import app.components.panels.AbstractXPanel;
import app.components.textcontainers.XTextField;
import app.frame.XFrame;
import app.other.Fencer;
import support.appdata.AppearanceData;
import support.appdata.SizeData;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public abstract class AbstractNumberedFencerLabel extends AbstractXPanel {
    // Number label
    protected final XLabel numberLabel;
    // Text input
    protected final XTextField textInput;

    protected AbstractNumberedFencerLabel(String number, String text, XFrame frame, Appearance appearance) {
        super(new Dimension(SizeData.WIDE_BUTTON_WIDTH + SizeData.NARROW_BUTTON_WIDTH, SizeData.BUTTON_HEIGHT), null, frame, appearance);

        // Number label
        this.numberLabel = new XLabel(0, 0, SizeData.NARROW_BUTTON_WIDTH, SizeData.BUTTON_HEIGHT, number, this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .addBorder(AppearanceData.RED_BORDER)
                .addFont(AppearanceData.MAIN_FONT_B)
                .build());

        this.textInput = new XTextField(this.numberLabel.getWidth(), 0, SizeData.WIDE_BUTTON_WIDTH, SizeData.BUTTON_HEIGHT,
            this.frame, BasicAppearance.BLACK_BORDERED);
        this.textInput.setHorizontalAlignment(JTextField.CENTER);
        this.textInput.setText(text);

        // Add the components
        this.addComponent(this.numberLabel);
        this.addComponent(this.textInput);
    }

    protected AbstractNumberedFencerLabel(int x, int y, String number, String text, XFrame frame, Appearance appearance) {
        super(x, y, SizeData.WIDE_BUTTON_WIDTH + SizeData.NARROW_BUTTON_WIDTH, SizeData.BUTTON_HEIGHT, null, frame, appearance);

        // Number label
        this.numberLabel = new XLabel(0, 0, SizeData.NARROW_BUTTON_WIDTH, SizeData.BUTTON_HEIGHT, number, this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .addBorder(AppearanceData.RED_BORDER)
                .addFont(AppearanceData.MAIN_FONT_B)
                .build());

        this.textInput = new XTextField(this.numberLabel.getWidth(), 0, SizeData.WIDE_BUTTON_WIDTH, SizeData.BUTTON_HEIGHT,
            this.frame, BasicAppearance.BLACK_BORDERED);
        this.textInput.setHorizontalAlignment(JTextField.CENTER);
        this.textInput.setText(text);

        // Add the components
        this.addComponent(this.numberLabel);
        this.addComponent(this.textInput);
    }

    public void setTextColor(Color color) {
        this.textInput.setForeground(color);
    }

    public String getNumber() {
        return this.numberLabel.getText();
    }

    public void setNumber(String number) {
        this.numberLabel.setText(number);
    }

    public void setNumber(int number) {
        this.numberLabel.setText(String.valueOf(number));
    }

    public String getFencer() {
        return this.textInput.getText();
    }

    public void setFencer(String fencerName) {
        this.textInput.setText(fencerName);
    }
}
