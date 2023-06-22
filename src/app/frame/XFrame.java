package app.frame;

import app.components.complex.editors.PouleEditor;
import app.components.complex.frameparts.CenterPanel;
import app.components.complex.frameparts.ContentPanel;
import app.components.complex.frameparts.TitleBar;
import support.appdata.AppearanceData;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;

import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public final class XFrame extends AbstractXFrame implements KeyListener {
    // Frame parts
    // Content panel
    private final ContentPanel contentPanel;
    private final TitleBar titleBar;
    private final CenterPanel centerPanel;

    // Editors
    private final PouleEditor pouleEditor;

    // Competition panel
    private final CompetitionPanel competitionPanel;

    public XFrame(Image iconImage, String title) {
        super(iconImage, title);
        this.setAutoRequestFocus(true);
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);

        // Frame parts
        // Content panel
        this.contentPanel = new ContentPanel(this, BasicAppearance.BLACK);
        // Set the contentPanel as the content pane of the frame
        this.setContentPane(this.contentPanel);

        this.titleBar = new TitleBar(null, "", this, BasicAppearance.BLACK);
        this.centerPanel = new CenterPanel(this, BasicAppearance.BLACK);

        // Poule editor
        this.pouleEditor = new PouleEditor(this,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addBorder(AppearanceData.RED_BORDER)
                .build());

        // Competition panel
        this.competitionPanel = new CompetitionPanel(this,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addBorder(AppearanceData.RED_BORDER)
                .build());

        // Add components to the frame

        // Set the visibility of the frame
        this.setVisible(true);
    }

    public void togglePouleEditor() {
        if (!this.stateMap.get(XFrameConstants.EDITOR_OPENED)) {
            this.insertComponent(this.pouleEditor, PositionConstants.TOP_POS);
            this.setFrameState(XFrameConstants.EDITOR_OPENED, true);
        }
    }

    public void closePouleEditor() {
        this.requestFocusInWindow();
        this.setFrameState(XFrameConstants.EDITOR_OPENED, false);
        this.extractComponent(this.pouleEditor);
    }

    public void toggleCompetitionPanel(List<String> valueList) {
        if (!this.stateMap.get(XFrameConstants.ON_GOING_COMPETITION)) {
            this.competitionPanel.generatePoules(valueList);
            this.insertComponent(this.competitionPanel);
            this.setFrameState(XFrameConstants.ON_GOING_COMPETITION, true);
        }
    }

    public void closeCompetitionPanel() {
        this.requestFocusInWindow();
        this.competitionPanel.clearAll();
        this.setFrameState(XFrameConstants.ON_GOING_COMPETITION, false);
        this.extractComponent(this.competitionPanel);
    }

    public void insertComponent(JComponent component, PositionConstants positionConstants) {
        this.centerPanel.addComponent(component, positionConstants);
        this.repaint();
    }

    public void insertComponent(JComponent component) {
        this.insertComponent(component, PositionConstants.MID_POS);
        this.repaint();
    }

    public void extractComponent(JComponent component) {
        this.centerPanel.removeComponent(component);
    }

    @Override
    public void addComponent(JComponent component, PositionConstants positionConstants) {
        this.add(component, positionConstants.getzIndex());
        this.repaint();
    }

    @Override
    public void addComponent(JComponent component, String borderLayoutPosition) {
        if (!(this.getLayout() instanceof BorderLayout)) {
            return;
        }

        this.add(component, borderLayoutPosition);
        this.repaint();
    }

    @Override
    public void addComponent(JComponent component) {
        this.addComponent(component, PositionConstants.MID_POS);
        this.repaint();
    }

    @Override
    public JComponent removeComponent(JComponent component) {
        this.remove(component);
        this.repaint();
        return component;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F1) {
            this.togglePouleEditor();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
