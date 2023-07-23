package app.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JComponent;

import app.components.complex.editors.DatabaseEditor;
import app.components.complex.editors.PouleEditor;
import app.components.complex.editors.TableEditor;
import app.components.complex.fencing.PouleOnlyCompetitionPanel;
import app.components.complex.fencing.TableOnlyCompetitionPanel;
import app.components.complex.frameparts.CenterPanel;
import app.components.complex.frameparts.ContentPanel;
import app.components.complex.frameparts.TitleBar;
import app.other.Fencer;
import support.appdata.AppearanceData;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;

public final class XFrame extends AbstractXFrame implements KeyListener {
    // Frame parts
    // Content panel
    private final ContentPanel contentPanel;
    // Title bar
    private final TitleBar titleBar;
    // Center panel
    private final CenterPanel centerPanel;

    // Editors
    private final PouleEditor pouleEditor;
    private final TableEditor tableEditor;
    private final DatabaseEditor databaseEditor;

    // Competition panels
    private final PouleOnlyCompetitionPanel pouleOnlyCompetitionPanel;
    private final TableOnlyCompetitionPanel tableOnlyCompetitionPanel;

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

        this.tableEditor = new TableEditor(this, this.pouleEditor.getAppearance());

        // Database editor
        this.databaseEditor = new DatabaseEditor(this, this.pouleEditor.getAppearance());

        // Competition panels
        this.pouleOnlyCompetitionPanel = new PouleOnlyCompetitionPanel(this, BasicAppearance.BLACK);
        this.tableOnlyCompetitionPanel = new TableOnlyCompetitionPanel(this, BasicAppearance.BLACK);

        // Add components to the frame

        // Set the visibility of the frame
        this.setVisible(true);
    }

    public void toggleTableEditor() {
        if (!this.stateMap.get(XFrameConstants.EDITOR_OPENED)) {
            this.tableEditor.readFencersFromFile();
            this.insertComponent(this.tableEditor, PositionConstants.TOP_POS);
            this.setFrameState(XFrameConstants.EDITOR_OPENED, true);
        }
    }

    public void closeTableEditor() {
        this.requestFocusInWindow();
        this.setFrameState(XFrameConstants.EDITOR_OPENED, false);
        this.pouleEditor.reset();
        this.extractComponent(this.tableEditor);
    }

    public void toggleTableOnlyCompetitionPanel() {
        if (!this.stateMap.get(XFrameConstants.ON_GOING_COMPETITION)) {
            this.insertComponent(this.tableOnlyCompetitionPanel);
            this.setFrameState(XFrameConstants.ON_GOING_COMPETITION, true);
        }
    }

    public void closeTableOnlyCompetitionPanel() {
        this.requestFocusInWindow();
        this.pouleOnlyCompetitionPanel.clearAll();
        this.setFrameState(XFrameConstants.ON_GOING_COMPETITION, false);
        this.extractComponent(this.tableOnlyCompetitionPanel);
    }

    public void toggleDatabaseEditor() {
        if (!this.stateMap.get(XFrameConstants.EDITOR_OPENED)) {
            this.databaseEditor.readFencersFromFile();
            this.insertComponent(this.databaseEditor, PositionConstants.TOP_POS);
            this.setFrameState(XFrameConstants.EDITOR_OPENED, true);
        }
    }

    public void closeDatabaseEditor() {
        this.requestFocusInWindow();
        this.databaseEditor.clearSelectionsInSelectionPanel();
        this.setFrameState(XFrameConstants.EDITOR_OPENED, false);
        this.extractComponent(this.databaseEditor);
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
        this.pouleEditor.reset();
        this.extractComponent(this.pouleEditor);
    }

    public void togglePouleOnlyCompetitionPanel(List<String> valueList, List<Fencer> fencerList) {
        if (!this.stateMap.get(XFrameConstants.ON_GOING_COMPETITION)) {
            this.pouleOnlyCompetitionPanel.generatePoules(valueList, fencerList);
            this.insertComponent(this.pouleOnlyCompetitionPanel);
            this.setFrameState(XFrameConstants.ON_GOING_COMPETITION, true);
        }
    }

    public void closePouleOnlyCompetitionPanel() {
        this.requestFocusInWindow();
        this.pouleOnlyCompetitionPanel.clearAll();
        this.setFrameState(XFrameConstants.ON_GOING_COMPETITION, false);
        this.extractComponent(this.pouleOnlyCompetitionPanel);
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

        }
        else if (e.getKeyCode() == KeyEvent.VK_F2) {
            this.togglePouleEditor();
        }
        else if (e.getKeyCode() == KeyEvent.VK_F3) {
            this.toggleTableEditor();
        }
        else if (e.getKeyCode() == KeyEvent.VK_F5) {
            this.toggleDatabaseEditor();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
