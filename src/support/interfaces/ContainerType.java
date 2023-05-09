package support.interfaces;

import javax.swing.JComponent;

import support.constants.PositionConstants;

public interface ContainerType {
    void addComponent(JComponent component, PositionConstants positionConstants);
    void addComponent(JComponent component, String borderLayoutPosition);
    void addComponent(JComponent component);
    JComponent removeComponent(JComponent component);
}
