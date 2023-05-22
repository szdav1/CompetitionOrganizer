package app.components.complex.frameparts;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

import app.frame.XFrame;
import support.constants.PositionConstants;
import support.framework.interfaces.Appearance;

public final class ContentPanel extends AbstractContentPanel {
    public ContentPanel(XFrame frame, Appearance appearance) {
        super(frame, appearance);
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
    public void paintComponent(Graphics graphics) {
        super.paintComponents(graphics);
//
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        graphics2D.drawImage(Util.loadIcon(AssetsData.BACKGROUNDS.concat("mainBackground"), this.getWidth(), this.getHeight())
//                .getImage(), 0, 0, null);
//        graphics2D.setPaint(new GradientPaint(0, 0, Color.red, this.getWidth(), this.getHeight(), Color.yellow));
//        graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
