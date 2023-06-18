package app.components.scrollpanels.scrollbars.ui;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

import support.framework.interfaces.Appearance;

public final class XScrollbarUI extends BasicScrollBarUI {
    private final int roundX;
    private final int roundY;
    private final Appearance appearance;

    // Use default roundness
    public XScrollbarUI(Appearance appearance) {
        this.roundX = 0;
        this.roundY = 0;
        this.appearance = appearance;
    }

    // Use given roundness
    public XScrollbarUI(int roundX, int roundY, Appearance appearance) {
        this.roundX = roundX;
        this.roundY = roundY;
        this.appearance = appearance;
    }

    private JButton removeButtons() {
        final JButton button = new JButton();
         button.setPreferredSize(new Dimension(0, 0));
         button.setMinimumSize(button.getPreferredSize());
         button.setMaximumSize(button.getPreferredSize());

         return button;
    }

    @Override
    public void paintTrack(Graphics graphics, JComponent component, Rectangle rectangle) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(this.appearance.getMainBackground());
        graphics2D.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Override
    public void paintThumb(Graphics graphics, JComponent component, Rectangle rectangle) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        graphics2D.setColor(this.appearance.getMainForeground());
        graphics2D.setPaint(new GradientPaint(rectangle.x, rectangle.y, this.appearance.getMainForeground(), rectangle.width,
            rectangle.height, this.appearance.getSecondaryForeground() == null ? this.appearance.getMainForeground() :
            this.appearance.getSecondaryForeground()));
        graphics2D.fillRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, this.roundX, this.roundY);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return this.removeButtons();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return this.removeButtons();
    }
}
