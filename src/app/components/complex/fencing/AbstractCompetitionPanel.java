package app.components.complex.fencing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComponent;

import app.components.buttons.FCXButton;
import app.components.panels.AbstractXPanel;
import app.components.panels.XPanel;
import app.components.scrollpanels.XScrollPanel;
import app.frame.XFrame;
import support.appdata.AppearanceData;
import support.appdata.SizeData;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public abstract class AbstractCompetitionPanel extends AbstractXPanel {
    // Scroll panel
    protected final XScrollPanel scrollPanel;

    // Inner container for the bottom section
    protected final XPanel bottomSection;

    // Buttons
    protected final FCXButton finishButton;
    protected final FCXButton closeButton;

    public AbstractCompetitionPanel(XFrame frame, Appearance appearance) {
        super(0, SizeData.BORDER_SIZE, SizeData.COMPETITION_PANEL_WIDTH,
            SizeData.COMPETITION_PANEL_HEIGHT, new BorderLayout(), frame, appearance);

        // Scroll panel
        this.scrollPanel = new XScrollPanel(new Dimension(this.getWidth(), this.getHeight()),
            new FlowLayout(FlowLayout.CENTER, SizeData.GAP, SizeData.GAP), this.frame, SizeData.GAP, SizeData.GAP,
            new CustomAppearanceBuilder()
                .addMainBackground(this.getAppearance().getMainBackground())
                .addBorder(this.getAppearance().getBorder())
                .build(),
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .build());
        this.scrollPanel.getVerticalScrollBar().setUnitIncrement(SizeData.BUTTON_HEIGHT * 2);
        this.scrollPanel.getHorizontalScrollBar().setUnitIncrement(SizeData.BUTTON_HEIGHT * 2);

        // Inner container for the bottom section
        this.bottomSection = new XPanel(new Dimension(this.getWidth(), SizeData.BUTTON_HEIGHT),
            new FlowLayout(FlowLayout.CENTER, SizeData.GAP, 0), this.frame, BasicAppearance.BLACK);

        // Buttons
        this.finishButton = new FCXButton(SizeData.BUTTON_DIMENSION, "Finish All", this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.white)
                .addSecondaryForeground(Color.red)
                .addBorder(AppearanceData.RED_BORDER)
                .build(),
            new CustomAppearanceBuilder()
                .addMainForeground(Color.red)
                .addSecondaryForeground(Color.yellow)
                .build());

        this.closeButton = new FCXButton(SizeData.BUTTON_DIMENSION, "Close", this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.white)
                .addSecondaryForeground(Color.red)
                .addBorder(AppearanceData.RED_BORDER)
                .build(),
            new CustomAppearanceBuilder()
                .addMainForeground(Color.red)
                .addSecondaryForeground(Color.yellow)
                .build());
        this.closeButton.addActionListener(e -> this.frame.closePouleOnlyCompetitionPanel());

        // Add components to the bottom section
        this.bottomSection.addComponent(this.finishButton);
        this.bottomSection.addComponent(this.closeButton);

        // Add the scroll panel to the panel
        this.addComponent(this.scrollPanel, BorderLayout.CENTER);
        this.addComponent(this.bottomSection, BorderLayout.SOUTH);
    }

    public void insertComponent(JComponent component, PositionConstants positionConstants) {
        this.scrollPanel.addComponent(component, positionConstants);
    }

    public void insertComponent(JComponent component) {
        this.insertComponent(component, PositionConstants.MID_POS);
    }

    public JComponent extractComponent(JComponent component) {
        return this.scrollPanel.removeComponent(component);
    }
}