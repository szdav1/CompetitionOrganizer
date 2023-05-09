package app.components.textcontainers;

import java.awt.Dimension;

import app.frame.XFrame;
import support.framework.interfaces.Appearance;

public final class XTextField extends AbstractXTextField {
    public XTextField(XFrame frame) {
        super(frame);
    }

    public XTextField(Dimension preferredSize, XFrame frame, Appearance appearance) {
        super(preferredSize, frame, appearance);
    }

    public XTextField(int x, int y, int width, int height, XFrame frame, Appearance appearance) {
        super(x, y, width, height, frame, appearance);
    }

    public XTextField(AbstractXTextField textField) {
        super(textField);
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
