package support.framework.factory;

import javax.swing.JComponent;

import support.framework.appearances.BasicAppearance;
import support.framework.appearances.ColoredAppearance;

public final class ComponentFactory extends AbstractComponentFactory {
    public ComponentFactory(JComponent component) {
        super(component);
    }

    @Override
    public JComponent createBlackComponent() {
        return ComponentFactory.implementAppearance(this.component, BasicAppearance.BLACK);
    }

    @Override
    public JComponent createLightGrayComponent() {
        return ComponentFactory.implementAppearance(this.component, BasicAppearance.LIGHT_GRAY);
    }

    @Override
    public JComponent createGrayComponent() {
        return ComponentFactory.implementAppearance(this.component, BasicAppearance.GRAY);
    }

    @Override
    public JComponent createDarkGrayComponent() {
        return ComponentFactory.implementAppearance(this.component, BasicAppearance.DARK_GRAY);
    }

    @Override
    public JComponent createRedComponent() {
        return ComponentFactory.implementAppearance(this.component, ColoredAppearance.RED);
    }

    @Override
    public JComponent createOrangeComponent() {
        return ComponentFactory.implementAppearance(this.component, ColoredAppearance.ORANGE);
    }

    @Override
    public JComponent createYellowComponent() {
        return ComponentFactory.implementAppearance(this.component, ColoredAppearance.YELLOW);
    }
}