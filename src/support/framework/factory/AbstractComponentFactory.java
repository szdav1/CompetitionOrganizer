package support.framework.factory;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import support.framework.interfaces.Appearance;

public abstract class AbstractComponentFactory {
    protected final JComponent component;

    protected AbstractComponentFactory(JComponent component) {
        this.component = component;
    }
    // Implement the given Appearance to the given JComponent
    public static  JComponent implementAppearance(JComponent component, Appearance appearance) {
        if (component instanceof JLabel label) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
        }

        return appearance.implementAppearance(appearance, component);
    }

    // Basic colored component creation
    protected abstract JComponent createBlackComponent();
    protected abstract JComponent createLightGrayComponent();
    protected abstract JComponent createGrayComponent();
    protected abstract JComponent createDarkGrayComponent();

    // Colorful component creation
    protected abstract JComponent createRedComponent();
    protected abstract JComponent createOrangeComponent();
    protected abstract JComponent createYellowComponent();
}