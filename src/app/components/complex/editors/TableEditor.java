package app.components.complex.editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.JComponent;

import app.components.complex.other.Checkbox;
import app.components.complex.other.SelectionPanel;
import app.components.labels.XLabel;
import app.frame.XFrame;
import app.other.Fencer;
import support.appdata.AppearanceData;
import support.appdata.SizeData;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public final class TableEditor extends AbstractEditor implements MouseListener {
    // Check index
    private int fencerIndex;
    // Fencer list
    private List<Fencer> fencerList = new LinkedList<>();
    // Info label
    private final XLabel infoLabel;
    // Selection panel
    private final SelectionPanel selectionPanel;

    public TableEditor(XFrame frame, Appearance appearance) {
        super("Table Editor", frame, appearance);

        // Add functionality to the closeButton
        this.closeButton.addActionListener(e -> this.frame.closeTableEditor());
        // Remove gaps from the alignments
        this.centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        this.createButton.addActionListener(e -> {
            if (!this.getSelectedFencers().isEmpty()) {
                this.frame.closeTableEditor();
                this.frame.toggleTableOnlyCompetitionPanel(this.getSelectedFencers());
            }
        });

        // Check index
        this.fencerIndex = 1;

        // Selection panel
        this.selectionPanel = new SelectionPanel(new Dimension(this.getWidth(), this.getHeight() - (SizeData.BUTTON_HEIGHT * 3) - SizeData.GAP),
            this.frame, BasicAppearance.BLACK);
        this.selectionPanel.removeTopSection();
        this.selectionPanel.removeBottomSection();

        this.selectionPanel.getCheckAllButton().addActionListener(e -> {
            this.fencerIndex = 1;
            for (Checkbox checkbox : this.selectionPanel.getCheckboxList()) {
                checkbox.getCheckLabel().setText(String.valueOf(this.fencerIndex));
                this.fencerIndex++;
            }
        });

        this.selectionPanel.getUncheckAllButton().addActionListener(e -> {
            this.fencerIndex = 1;
            for (Checkbox checkbox : this.selectionPanel.getCheckboxList()) {
                checkbox.unCheck();
                checkbox.getCheckLabel().setText("");
            }
        });

        // Info label
        this.infoLabel = new XLabel(new Dimension(this.getWidth() * 70 / 100, SizeData.BUTTON_HEIGHT), "Select competing fencers in ORDER",
            this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addBorder(AppearanceData.RED_BORDER)
                .build());

        // Add components to the centerPanel
        this.centerPanel.addComponent(this.infoLabel);
        this.centerPanel.addComponent(this.selectionPanel);

        // Add components to the bottomSection
        this.footerPanel.removeComponent(this.createButton);
        this.footerPanel.addComponent(this.selectionPanel.getCheckAllButton());
        this.footerPanel.addComponent(this.createButton);
        this.footerPanel.addComponent(this.selectionPanel.getUncheckAllButton());
    }

    public List<Fencer> getSelectedFencers() {
        final List<Fencer> selectedFencerList = new LinkedList<>();

        this.selectionPanel.getCheckboxList().forEach(checkbox -> {
            if (checkbox.isChecked()) {
                selectedFencerList.add(new Fencer(checkbox.getTextLabel().getText(), 0, 0, 0, 0,
                    Integer.valueOf(checkbox.getCheckLabel().getText())));
            }
        });

        return selectedFencerList.stream()
            .sorted(Comparator.comparing(Fencer::getPlace))
            .collect(Collectors.toList());
    }

    public void readFencersFromFile() {
        this.fencerIndex = 1;
        this.fencerList.clear();
        this.selectionPanel.removeAllFromScrollPanel();

        try {
            final Scanner scanner = new Scanner(new File("database/fencers.csv"));
            while (scanner.hasNextLine()) {
                final String[] data = scanner.nextLine().split(";");
                for (String datum : data) {
                    this.fencerList.add(new Fencer(datum, 0, 0, 0, 0));
                }
            }
            scanner.close();

            for (int i = 0; i < this.fencerList.size(); i++) {
                final Checkbox checkbox = new Checkbox(SizeData.GAP, (SizeData.BUTTON_HEIGHT * i) + (SizeData.GAP * i),
                    this.selectionPanel.getPreferredSize().width - (SizeData.GAP * 2), SizeData.BUTTON_HEIGHT,
                    this.fencerList.get(i).getName(), this.frame,BasicAppearance.BLACK_BORDERED);
                checkbox.getCheckLabel().addMouseListener(this);
                checkbox.getCheckLabel().setForeground(Color.red);
                checkbox.getCheckLabel().setFont(AppearanceData.MAIN_FONT_B);

                this.selectionPanel.addToScrollPanel(checkbox);
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
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

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.selectionPanel.getCheckboxList().forEach(checkbox -> {
            if (e.getSource().equals(checkbox.getCheckLabel())) {
                if (checkbox.isChecked()) {
                    checkbox.getCheckLabel().setText(String.valueOf(this.fencerIndex));
                    this.fencerIndex++;
                }
                else {
                    checkbox.getCheckLabel().setText("");
                    this.fencerIndex--;
                }
            }
        });
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}