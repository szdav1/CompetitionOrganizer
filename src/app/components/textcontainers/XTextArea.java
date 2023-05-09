package app.components.textcontainers;

import java.awt.Dimension;

import app.frame.XFrame;
import support.framework.interfaces.Appearance;

public final class XTextArea extends AbstractXTextArea {
    public XTextArea(XFrame frame) {
        super(frame);
    }

    public XTextArea(Dimension preferredSize, XFrame frame, Appearance appearance) {
        super(preferredSize, frame, appearance);
    }

    public XTextArea(int x, int y, int width, int height, XFrame frame, Appearance appearance) {
        super(x, y, width, height, frame, appearance);
    }

    public XTextArea(AbstractXTextArea textArea) {
        super(textArea);
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
