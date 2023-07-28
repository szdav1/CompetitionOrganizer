package app.components.complex.frameparts;

import javax.swing.JComponent;

import app.components.labels.XLabel;
import app.frame.XFrame;
import support.appdata.AssetsData;
import support.constants.PositionConstants;
import support.framework.appearances.BasicAppearance;
import support.framework.interfaces.Appearance;
import support.util.Util;

public final class CenterPanel extends AbstractCenterPanel {
    public CenterPanel(XFrame frame, Appearance appearance) {
        super(frame, appearance);

        // Background label
        final XLabel backgroundLabel = new XLabel(1, 1, this.getWidth() - 1, this.getHeight() - 1,
            Util.loadIcon(AssetsData.BACKGROUNDS.concat("mainBackgroundVersion09"), this.getWidth(), this.getHeight()), this.frame,
            BasicAppearance.OPAQUE);

        this.addComponent(backgroundLabel, PositionConstants.BACKGROUND_POS);
        this.repaintFrame();
    }

    @Override
    public void addComponent(JComponent component, PositionConstants positionConstants) {
        this.add(component, positionConstants.getzIndex());
        this.repaintFrame();
    }

    @Override
    public void addComponent(JComponent component, String borderLayoutPosition) {
        // The center panel's layout manager is always null.
    }

    @Override
    public void addComponent(JComponent component) {
        this.addComponent(component, PositionConstants.MID_POS);
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
