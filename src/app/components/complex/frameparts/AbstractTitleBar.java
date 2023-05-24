package app.components.complex.frameparts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import app.components.buttons.FCXButton;
import app.components.buttons.ICXButton;
import app.components.complex.menuitems.MenuButton;
import app.components.panels.AbstractXPanel;
import app.components.panels.XPanel;
import app.frame.XFrame;
import support.appdata.AssetsData;
import support.appdata.SizeData;
import support.framework.appearances.BasicAppearance;
import support.framework.builders.CustomAppearanceBuilder;
import support.framework.interfaces.Appearance;
import support.util.Util;

public abstract class AbstractTitleBar extends AbstractXPanel implements ActionListener {
    // Main buttons
    protected final ICXButton exitButton;
    protected final ICXButton iconifyButton;

    // Inner container
    protected final XPanel innerContainer;

    // Menu buttons
    protected final MenuButton createMenuButton;

    // Bottom border

    // "Full" constructor
    protected AbstractTitleBar(ImageIcon icon, String title, XFrame frame, Appearance appearance) {
        super(new Dimension(SizeData.WIDTH, SizeData.BUTTON_HEIGHT), new FlowLayout(FlowLayout.LEADING, 0, 0),
            frame, appearance);

        // Main buttons
        this.exitButton = new ICXButton(SizeData.NARROW_BUTTON_DIMENSION,
            Util.loadBigIcon(AssetsData.BUTTON_ICONS.concat("exit1")),
            Util.loadBigIcon(AssetsData.BUTTON_ICONS.concat("exit2")), this.frame, BasicAppearance.OPAQUE);
        // Close the frame and exit the application
        this.exitButton.addActionListener(e -> {
            this.frame.dispose();
            System.exit(0);
        });

        this.iconifyButton = new ICXButton(SizeData.NARROW_BUTTON_DIMENSION,
            Util.loadBigIcon(AssetsData.BUTTON_ICONS.concat("iconify1")),
            Util.loadBigIcon(AssetsData.BUTTON_ICONS.concat("iconify2")), this.frame, BasicAppearance.OPAQUE);
        // Set the frame's state to iconified
        this.iconifyButton.addActionListener(e -> this.frame.setExtendedState(JFrame.ICONIFIED));

        // Inner container
        this.innerContainer = new XPanel(new Dimension(this.getPreferredSize().width - (SizeData.NARROW_BUTTON_WIDTH * 2),
            this.getPreferredSize().height), this.getLayout(), this.frame, appearance);

        // Menu buttons
        this.createMenuButton = new MenuButton(SizeData.NARROW_BUTTON_DIMENSION, 0, 0,
            Util.loadBigIcon(AssetsData.BUTTON_ICONS.concat("menu1")),
            Util.loadBigIcon(AssetsData.BUTTON_ICONS.concat("menu2")), this.frame, BasicAppearance.OPAQUE,
            new CustomAppearanceBuilder()
                .addMainBackground(Color.black)
                .addBorder(new LineBorder(Color.red, SizeData.BORDER_SIZE))
                .build());
        // Fill the createMenuButton's dropdown panel
        this.addCreateMenuOptions();

        // Add components to the innerContainer
        this.innerContainer.addComponent(this.createMenuButton);

        // Add components to the TitleBar
        this.addComponent(this.innerContainer);
        this.addComponent(this.iconifyButton);
        this.addComponent(this.exitButton);

        // Add the TitleBar to the frame
        this.frame.addComponent(this, BorderLayout.NORTH);
    }

    private void addCreateMenuOptions() {
        final String[] options = {"New Poule", "New Table"};

        for (int i = 0; i < options.length; i++) {
            final FCXButton optionButton = new FCXButton(SizeData.BORDER_SIZE, SizeData.BORDER_SIZE + (i * SizeData.BUTTON_HEIGHT),
                SizeData.BUTTON_WIDTH, SizeData.BUTTON_HEIGHT, options[i], this.frame,
                new CustomAppearanceBuilder()
                    .addMainForeground(Color.white)
                    .addSecondaryBackground(Color.red)
                    .build(),
                new CustomAppearanceBuilder()
                    .addMainForeground(Color.red)
                    .addSecondaryForeground(Color.yellow)
                    .build());
            optionButton.addActionListener(this);

            this.createMenuButton.addOption(optionButton);
        }
    }
}
