package app.components.complex.frameparts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import app.components.buttons.ICXButton;
import app.components.panels.AbstractXPanel;
import app.frame.XFrame;
import support.appdata.AssetsData;
import support.appdata.SizeData;
import support.framework.appearances.BasicAppearance;
import support.framework.interfaces.Appearance;
import support.util.Util;

public abstract class AbstractTitleBar extends AbstractXPanel {
    // Main buttons
    protected final ICXButton exitButton;
    protected final ICXButton iconifyButton;

    // "Full" constructor
    public AbstractTitleBar(Dimension preferredSize, ImageIcon icon, String title, XFrame frame, Appearance appearance) {
        super(preferredSize, frame, appearance);
        this.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

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
        this.iconifyButton.addActionListener(e -> this.frame.setExtendedState(JFrame.ICONIFIED));

        // Add components to the TitleBar
        this.addComponent(this.iconifyButton);
        this.addComponent(this.exitButton);

        // Add the TitleBar to the frame
        this.frame.addComponent(this, BorderLayout.NORTH);
    }
}
