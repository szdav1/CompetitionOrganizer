package app.components.complex.fencing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.SwingConstants;

import app.components.labels.XLabel;
import app.components.panels.AbstractXPanel;
import app.frame.XFrame;
import support.appdata.AppearanceData;
import support.appdata.SizeData;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public abstract class AbstractFencerDisplayLabel extends AbstractXPanel {
    // Label for the name
    protected final XLabel nameLabel;
    // Label for the place
    protected final XLabel placeLabel;

    protected AbstractFencerDisplayLabel(String name, String place, XFrame frame, Appearance appearance) {
        super(new Dimension(SizeData.HALF_WIDTH, SizeData.BUTTON_HEIGHT), new FlowLayout(FlowLayout.LEADING, -SizeData.BORDER_SIZE, 0),
            frame, appearance);

        // Label for the name
        this.nameLabel = new XLabel(new Dimension(this.getPreferredSize().width - SizeData.NARROW_BUTTON_DIMENSION.width,
            SizeData.BUTTON_HEIGHT), name, this.frame, BasicAppearance.BLACK);
        this.nameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Label for the place
        this.placeLabel = new XLabel(SizeData.NARROW_BUTTON_DIMENSION, place, this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .addBorder(AppearanceData.RED_BORDER)
                .build());

        // Add the labels to the display
        this.addComponent(this.nameLabel);
        this.addComponent(this.placeLabel);
    }
}
