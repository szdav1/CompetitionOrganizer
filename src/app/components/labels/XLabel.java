package app.components.labels;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import app.frame.XFrame;
import support.framework.interfaces.Appearance;

public final class XLabel extends AbstractXLabel {
    public XLabel(XFrame frame) {
        super(frame);
    }

    public XLabel(Dimension preferredSize, ImageIcon icon, String text, XFrame frame, Appearance appearance) {
        super(preferredSize, icon, text, frame, appearance);
    }

    public XLabel(Dimension preferredSize, ImageIcon icon, XFrame frame, Appearance appearance) {
        super(preferredSize, icon, frame, appearance);
    }

    public XLabel(Dimension preferredSize, String text, XFrame frame, Appearance appearance) {
        super(preferredSize, text, frame, appearance);
    }

    public XLabel(int x, int y, int width, int height, ImageIcon icon, String text, XFrame frame, Appearance appearance) {
        super(x, y, width, height, icon, text, frame, appearance);
    }

    public XLabel(int x, int y, int width, int height, ImageIcon icon, XFrame frame, Appearance appearance) {
        super(x, y, width, height, icon, frame, appearance);
    }

    public XLabel(int x, int y, int width, int height, String text, XFrame frame, Appearance appearance) {
        super(x, y, width, height, text, frame, appearance);
    }

    public XLabel(AbstractXLabel label) {
        super(label);
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
