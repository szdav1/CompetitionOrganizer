package app.components.complex.editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import app.components.buttons.FCXButton;
import app.components.labels.XLabel;
import app.components.panels.AbstractXPanel;
import app.components.panels.XPanel;
import app.frame.XFrame;
import support.appdata.AppearanceData;
import support.appdata.AssetsData;
import support.appdata.SizeData;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;
import support.util.Util;

public abstract class AbstractEditor extends AbstractXPanel implements ActionListener {
    // Editor parts
    protected final XPanel titleBar;
    protected final XPanel centerPanel;
    protected final XPanel footerPanel;

    // Title bar parts
    protected final XPanel titleBarInnerContainer;
    protected final FCXButton closeButton;

    // Footer panel parts
    protected final FCXButton createButton;

    protected AbstractEditor(String title, XFrame frame, Appearance appearance) {
        super(SizeData.HALF_WIDTH - (SizeData.EDITOR_WIDTH / 2), SizeData.HALF_HEIGHT - (SizeData.EDITOR_HEIGHT / 2),
            SizeData.EDITOR_WIDTH, SizeData.EDITOR_HEIGHT, new BorderLayout(), frame, appearance);

        // Editor parts
        this.titleBar = new XPanel(new Dimension(this.getWidth(), SizeData.BUTTON_HEIGHT), new FlowLayout(FlowLayout.LEADING, 0, 0),
            this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(appearance.getMainBackground())
                .build());

        this.centerPanel = new XPanel(new Dimension(this.getWidth(), this.getHeight()), new FlowLayout(FlowLayout.CENTER, SizeData.GAP, 0),
            this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(appearance.getMainBackground())
                .build());

        this.footerPanel = new XPanel(new Dimension(this.getWidth(), SizeData.BUTTON_HEIGHT), new FlowLayout(FlowLayout.CENTER),
            this.frame,
            new CustomAppearanceBuilder()
                .addMainBackground(appearance.getMainBackground())
                .build());


        // Title bar parts
        this.titleBarInnerContainer = new XPanel(new Dimension(this.getWidth() - SizeData.NARROW_BUTTON_WIDTH - 10, SizeData.BUTTON_HEIGHT),
            new FlowLayout(FlowLayout.LEADING, 0, 0), this.frame, this.titleBar.getAppearance());
        // Add the icon and the title to the editor
        this.titleBarInnerContainer.addComponent(new XLabel(SizeData.WIDE_BUTTON_DIMENSION,
            Util.loadBigIcon(AssetsData.LABEL_ICONS.concat("create")), title, this.frame, BasicAppearance.OPAQUE));

        this.closeButton = new FCXButton(SizeData.NARROW_BUTTON_DIMENSION, "X", this.frame,
            new CustomAppearanceBuilder()
                .addMainForeground(Color.white)
                .addSecondaryForeground(Color.red)
                .build(),
            new CustomAppearanceBuilder()
                .addMainForeground(Color.red)
                .addSecondaryForeground(Color.white)
                .build());
        this.closeButton.addActionListener(e -> this.frame.closePouleEditor());

        // Footer panel parts
        this.createButton = new FCXButton(SizeData.BUTTON_DIMENSION, "Create", this.frame,
            new CustomAppearanceBuilder()
                .addMainForeground(Color.white)
                .addSecondaryForeground(Color.red)
                .addBorder(AppearanceData.RED_BORDER)
                .build(),
            new CustomAppearanceBuilder()
                .addMainForeground(Color.red)
                .addSecondaryForeground(Color.orange)
                .build());
        this.createButton.addActionListener(this);

        // Add components to the titleBar
        this.titleBar.addComponent(this.titleBarInnerContainer);
        this.titleBar.addComponent(this.closeButton);

        // Add components to the footer panel
        this.footerPanel.addComponent(this.createButton);

        // Add components to the editor
        this.addComponent(this.titleBar, BorderLayout.NORTH);
        this.addComponent(this.centerPanel, BorderLayout.CENTER);
        this.addComponent(this.footerPanel, BorderLayout.SOUTH);
    }
}
