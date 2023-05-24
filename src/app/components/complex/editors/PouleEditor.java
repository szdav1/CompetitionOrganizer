package app.components.complex.editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComponent;

import app.components.labels.XLabel;
import app.components.panels.XPanel;
import app.frame.XFrame;
import support.appdata.SizeData;
import support.constants.PositionConstants;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public final class PouleEditor extends AbstractEditor {
    // Title labels
    private final XLabel inputLabel;
    private final XLabel previewLabel;

    // Inner containers
    private final XPanel inputPanel;
    private final XPanel previewPanel;

    public PouleEditor(XFrame frame, Appearance appearance) {
        super("Poule Editor", frame, appearance);

        // Title labels
        this.inputLabel = new XLabel(new Dimension(this.getWidth() * 45 / 100, SizeData.BUTTON_HEIGHT), "Enter Desired Values",
            this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(this.getAppearance().getMainBackground())
                .build());

        this.previewLabel = new XLabel(this.inputLabel.getPreferredSize(), "Poule Preview", this.frame,
            this.inputLabel.getAppearance());

        // Inner containers
        this.inputPanel = new XPanel(new Dimension(this.inputLabel.getWidth(),
            this.centerPanel.getHeight() * 75 / 100), new FlowLayout(FlowLayout.CENTER, 0, 0), this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(this.getAppearance().getMainBackground())
                .addBorder(this.getAppearance().getBorder())
                .build());

        this.previewPanel = new XPanel(this.inputPanel.getPreferredSize(), this.inputPanel.getLayout(), this.frame,
            this.inputPanel.getAppearance());

        // Add the components to the centerPanel
        this.centerPanel.addComponent(this.inputLabel);
        this.centerPanel.addComponent(this.previewLabel);
        // Add the inner containers to the centerPanel
        this.centerPanel.addComponent(this.inputPanel);
        this.centerPanel.addComponent(this.previewPanel);
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
}
