package support.interfaces;

import app.frame.XFrame;
import support.framework.interfaces.Appearance;

public interface XComponent {
    void repaintFrame();
    Appearance getAppearance();
    XFrame getFrame();
}
