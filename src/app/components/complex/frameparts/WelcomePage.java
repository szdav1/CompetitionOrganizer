package app.components.complex.frameparts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.concurrent.Flow;

import javax.swing.JComponent;
import javax.swing.JLabel;

import app.components.labels.XLabel;
import app.components.panels.AbstractXPanel;
import app.components.scrollpanels.XScrollPanel;
import app.frame.XFrame;
import support.appdata.AppearanceData;
import support.appdata.SizeData;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public final class WelcomePage extends AbstractXPanel {
    // Scroll panel
    private final XScrollPanel scrollPanel;
    // Welcome label
    private final XLabel welcomeLabel;

    public WelcomePage(XFrame frame, Appearance appearance) {
        super(SizeData.HALF_WIDTH / 4, SizeData.HALF_HEIGHT / 4, SizeData.HALF_WIDTH, SizeData.HALF_HEIGHT, null, frame, appearance);

        this.scrollPanel = new XScrollPanel(0, 0, SizeData.HALF_WIDTH, SizeData.HALF_HEIGHT, new FlowLayout(FlowLayout.CENTER, 0, 0),
            this.frame,
            BasicAppearance.BLACK_BORDERED, BasicAppearance.BLACK);

        this.welcomeLabel = new XLabel(SizeData.WIDE_BUTTON_DIMENSION, "Welcome to XYZ!", this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .addFont(AppearanceData.MAIN_FONT_B)
                .build());
        this.welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add the components to the scroll panel
        this.scrollPanel.addComponent(this.welcomeLabel);

        // Add the components
        this.addComponent(this.scrollPanel);
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
}
