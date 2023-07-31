package app.components.complex.other;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import app.components.buttons.FCXButton;
import app.components.panels.AbstractXPanel;
import app.components.panels.XPanel;
import app.components.scrollpanels.XScrollPanel;
import app.frame.XFrame;
import app.other.Fencer;
import support.appdata.AppearanceData;
import support.appdata.SizeData;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;

public abstract class AbstractSelectionPanel extends AbstractXPanel {
    // Inner containers
    protected final XPanel topSection;
    protected final XPanel centerPanel;
    protected final XPanel bottomSection;

    // Close button
    protected final FCXButton closeButton;
    // Scroll panel
    protected final XScrollPanel scrollPanel;

    // Quick access buttons
    protected final FCXButton checkAllButton;
    protected final FCXButton uncheckAllButton;

    // List of components
    protected final List<Checkbox> checkboxList = new ArrayList<>();

    protected AbstractSelectionPanel(Dimension preferredSize, XFrame frame, Appearance appearance) {
        super(preferredSize, new BorderLayout(), frame, appearance);

        // Inner containers
        this.topSection = new XPanel(SizeData.WIDE_BUTTON_DIMENSION, new FlowLayout(FlowLayout.TRAILING, 0, 0),
            this.frame, BasicAppearance.BLACK);

        this.centerPanel = new XPanel(preferredSize, new FlowLayout(FlowLayout.CENTER, SizeData.GAP, 0), this.frame,
            BasicAppearance.BLACK);

        this.bottomSection = new XPanel(new Dimension(preferredSize.width, SizeData.BUTTON_HEIGHT), new FlowLayout(FlowLayout.TRAILING, 0, 0),
            this.frame, BasicAppearance.BLACK);

        // Close button
        this.closeButton = new FCXButton(SizeData.NARROW_BUTTON_DIMENSION, "X", this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.white)
                .addSecondaryForeground(Color.red)
                .addFont(AppearanceData.MAIN_FONT_P)
                .build(),
            new CustomAppearanceBuilder()
                .addMainForeground(Color.red)
                .addSecondaryForeground(Color.yellow)
                .build());

        // Scroll panel
        this.scrollPanel = new XScrollPanel(new Dimension(preferredSize.width,
            preferredSize.height - (SizeData.BUTTON_HEIGHT * 2)), this.frame, SizeData.GAP, SizeData.GAP,
            new CustomAppearanceBuilder()
                .addMainBackground(appearance.getMainBackground())
                .build(),
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.darkGray)
                .build());
        this.scrollPanel.getVerticalScrollBar().setUnitIncrement(SizeData.BUTTON_HEIGHT * 2);
        this.scrollPanel.getHorizontalScrollBar().setUnitIncrement(SizeData.BUTTON_HEIGHT * 2);

        // Quick access buttons
        this.checkAllButton = new FCXButton(SizeData.BUTTON_DIMENSION, "Select All", this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.white)
                .addSecondaryForeground(Color.red)
                .addBorder(AppearanceData.RED_BORDER)
                .addFont(AppearanceData.MAIN_FONT_P)
                .build(),
            new CustomAppearanceBuilder()
                .addMainForeground(Color.red)
                .addSecondaryForeground(Color.yellow)
                .build());
        this.checkAllButton.addActionListener(e -> this.selectAll());

        this.uncheckAllButton = new FCXButton(SizeData.BUTTON_DIMENSION, "Unselect All", this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addMainForeground(Color.white)
                .addSecondaryForeground(Color.red)
                .addBorder(AppearanceData.RED_BORDER)
                .addFont(AppearanceData.MAIN_FONT_P)
                .build(),
            new CustomAppearanceBuilder()
                .addMainForeground(Color.red)
                .addSecondaryForeground(Color.yellow)
                .build());
        this.uncheckAllButton.addActionListener(e -> this.unSelectAll());

        // Add components to the inner containers
        this.topSection.addComponent(this.closeButton);

        this.centerPanel.addComponent(this.scrollPanel);
        this.centerPanel.addComponent(this.checkAllButton);
        this.centerPanel.addComponent(this.uncheckAllButton);

        // Add the inner containers
        this.addComponent(this.topSection, BorderLayout.NORTH);
        this.addComponent(this.centerPanel, BorderLayout.CENTER);
    }

    public List<Checkbox> getCheckboxList() {
        return this.checkboxList;
    }

    public FCXButton getCheckAllButton() {
        return this.checkAllButton;
    }

    public FCXButton getUncheckAllButton() {
        return this.uncheckAllButton;
    }

    public void removeBottomSection() {
        this.removeComponent(this.bottomSection);
        this.scrollPanel.setPreferredSize(new Dimension(this.getPreferredSize().width,
            this.getPreferredSize().height));
    }

    public void removeTopSection() {
        this.removeComponent(this.topSection);
        this.scrollPanel.setPreferredSize(new Dimension(this.getPreferredSize().width,
            this.getPreferredSize().height - SizeData.BUTTON_HEIGHT));
    }

    public FCXButton getCloseButton() {
        return this.closeButton;
    }

    public void removeFromScrollPanel(Checkbox checkbox) {
        this.scrollPanel.removeComponent(checkbox);
        this.checkboxList.remove(checkbox);
    }

    public void removeAllFromScrollPanel() {
        this.scrollPanel.getViewPanel().removeAll();
        this.scrollPanel.getViewPanel().setPreferredSize(new Dimension(0, 0));
        this.checkboxList.clear();
    }

    public void addToScrollPanel(Checkbox checkbox) {
        this.checkboxList.add(checkbox);
        this.scrollPanel.addComponent(checkbox);
    }

    public List<Fencer> getSelectedFencers() {
        final List<Fencer> fencerList = new ArrayList<>();
        for (Checkbox checkbox : this.checkboxList) {
            if (checkbox.isChecked()) {
                fencerList.add(new Fencer(checkbox.getText(), 0, 0, 0, 0));
            }
        }
        return fencerList;
    }

    public List<Fencer> getUnselectedFencers() {
        final List<Fencer> fencerList = new ArrayList<>();
        for (Checkbox checkbox : this.checkboxList) {
            if (!checkbox.isChecked()) {
                fencerList.add(new Fencer(checkbox.getText(), 0, 0, 0, 0));
            }
        }
        return fencerList;
    }

    public void selectAll() {
        this.checkboxList.forEach(Checkbox::check);
    }

    public void unSelectAll() {
        this.checkboxList.forEach(Checkbox::unCheck);
    }
}
