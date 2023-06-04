package app.components.complex.editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;

import app.components.complex.fencing.Poule;
import app.components.complex.inputs.InputField;
import app.components.labels.XLabel;
import app.components.panels.XPanel;
import app.components.scrollpanels.XScrollPanel;
import app.frame.XFrame;
import support.appdata.AppearanceData;
import support.appdata.SizeData;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public final class PouleEditor extends AbstractEditor {
    // Title labels
    private final XLabel inputLabel;
    private final XLabel previewLabel;

    // Inner containers
    private final XPanel inputPanel;
    private final XPanel previewPanel;

    // Preview poule
    private final Poule previewPoule;

    // Input scroll panel
    private final XScrollPanel inputScrollPanel;
    // Preview scroll panel
    private final XScrollPanel previewScrollPanel;

    // List for the inputs
    private final List<InputField> inputList = new LinkedList<>();

    public PouleEditor(XFrame frame, Appearance appearance) {
        super("Poule Editor", frame, appearance);

        // Title labels
        this.inputLabel = new XLabel(new Dimension(this.getWidth() * 45 / 100, SizeData.BUTTON_HEIGHT), "Enter Values",
            this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(this.getAppearance().getMainBackground())
                .build());

        this.previewLabel = new XLabel(this.inputLabel.getPreferredSize(), "Poule Preview", this.frame,
            this.inputLabel.getAppearance());

        // Inner containers
        this.inputPanel = new XPanel(new Dimension(this.inputLabel.getWidth(),
            this.centerPanel.getHeight() * 75 / 100), new BorderLayout(), this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(this.getAppearance().getMainBackground())
                .addBorder(this.getAppearance().getBorder())
                .build());

        this.previewPanel = new XPanel(this.inputPanel.getPreferredSize(), this.inputPanel.getLayout(), this.frame,
            this.inputPanel.getAppearance());

        // Preview poule
        this.previewPoule = new Poule(0, 0, this.frame, 8, BasicAppearance.BLACK_BORDERED);

        // Input scroll panel
        this.inputScrollPanel = new XScrollPanel(this.inputPanel.getPreferredSize(), new FlowLayout(FlowLayout.CENTER, SizeData.GAP, SizeData.GAP),
            this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .build(),
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .build());
        // Add the inputFields
        this.createInputFields(new String[] {"Round*", "Poule*", "Referee", "Fencers*", "Fencers/Poule", "Date"});

        // Preview scroll panel
        this.previewScrollPanel = new XScrollPanel(this.previewPanel.getPreferredSize(), this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .build(),
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.red)
                .build());
        // Add the previewPoule
        this.previewScrollPanel.addComponent(this.previewPoule);

        // Add the inputScrollPanel to the inputPanel
        this.inputPanel.addComponent(this.inputScrollPanel, BorderLayout.CENTER);

        // Add the previewScrollPanel to the previewPanel
        this.previewPanel.addComponent(this.previewScrollPanel, BorderLayout.CENTER);

        // Add the components to the centerPanel
        this.centerPanel.addComponent(this.inputLabel);
        this.centerPanel.addComponent(this.previewLabel);
        // Add the inner containers to the centerPanel
        this.centerPanel.addComponent(this.inputPanel);
        this.centerPanel.addComponent(this.previewPanel);
    }

    private void createInputFields(String[] options) {
        for (int i = 0; i < options.length; i++) {
            final InputField inputField = new InputField(options[i], this.frame,
                BasicAppearance.BLACK,
                new CustomAppearanceBuilder()
                    .addMainBackground(Color.black)
                    .addMainForeground(Color.white)
                    .addBorder(AppearanceData.RED_BORDER)
                    .build());

            this.inputScrollPanel.addComponent(inputField);
            this.inputScrollPanel.getViewPanel().setPreferredSize(new Dimension(
                this.inputScrollPanel.getViewPanel().getPreferredSize().width,
                i * SizeData.INPUT_FIELD_HEIGHT
            ));

            this.inputList.add(inputField);
        }
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
