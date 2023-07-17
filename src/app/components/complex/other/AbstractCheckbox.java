package app.components.complex.other;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import app.components.labels.XLabel;
import app.components.panels.AbstractXPanel;
import app.frame.XFrame;
import support.appdata.AssetsData;
import support.appdata.SizeData;
import support.framework.appearances.BasicAppearance;
import support.framework.interfaces.Appearance;
import support.util.Util;

public abstract class AbstractCheckbox extends AbstractXPanel implements MouseListener {
    // Checked value
    protected boolean checked;
    // Label for the text
    protected final XLabel textLabel;
    // Label for the check
    protected final XLabel checkLabel;
    // Check icon
    protected final ImageIcon checkIcon;

    protected AbstractCheckbox(Dimension preferredSize, String text, XFrame frame, Appearance appearance) {
        super(preferredSize, new FlowLayout(FlowLayout.LEADING, 0, 0), frame, appearance);

        // Checked value
        this.checked = false;

        // Label for the text
        this.textLabel = new XLabel(new Dimension(preferredSize.width - SizeData.NARROW_BUTTON_WIDTH, preferredSize.height),
            text, this.frame, BasicAppearance.BLACK);

        // Label for the check
        this.checkLabel = new XLabel(SizeData.NARROW_BUTTON_DIMENSION, "", this.frame, BasicAppearance.BLACK_BORDERED);
        this.checkLabel.addMouseListener(this);

        // Check icon
        this.checkIcon = Util.loadIcon(AssetsData.BUTTON_ICONS.concat("check"));

        // Add the components
        this.addComponent(this.textLabel);
        this.addComponent(this.checkLabel);
    }

    public AbstractCheckbox(int x, int y, int width, int height, String text, XFrame frame, Appearance appearance) {
        super(x, y, width, height, new FlowLayout(FlowLayout.LEADING, 0, 0), frame, appearance);

        // Checked value
        this.checked = false;

        // Label for the text
        this.textLabel = new XLabel(new Dimension(width - SizeData.NARROW_BUTTON_WIDTH - (SizeData.BORDER_SIZE * 2),
            height), text, this.frame, BasicAppearance.BLACK);

        // Label for the check
        this.checkLabel = new XLabel(SizeData.NARROW_BUTTON_DIMENSION, "", this.frame, BasicAppearance.BLACK_BORDERED);
        this.checkLabel.addMouseListener(this);

        // Check icon
        this.checkIcon = Util.loadIcon(AssetsData.BUTTON_ICONS.concat("check"));

        // Add the components
        this.addComponent(this.textLabel);
        this.addComponent(this.checkLabel);
    }

    public void check() {
        this.checked = true;
        this.checkLabel.setIcon(this.checkIcon);
    }

    public void unCheck() {
        this.checked = false;
        this.checkLabel.setIcon(null);
    }

    public String getText() {
        return this.textLabel.getText();
    }

    public boolean isChecked() {
        return this.checked;
    }

    @Override
    public String toString() {
        return "AbstractCheckbox{" +
            "checked=" + checked +
            '}';
    }
}
