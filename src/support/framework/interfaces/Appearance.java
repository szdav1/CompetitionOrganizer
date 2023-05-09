package support.framework.interfaces;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public interface Appearance {
    default JComponent implementAppearance(Appearance appearance, JComponent component) {
        component.setBackground(appearance.getMainBackground());
        component.setForeground(appearance.getMainForeground());
        component.setBorder(appearance.getBorder());
        component.setFont(appearance.getFont());
        // Focusability is only enabled for JTextField and JTextArea
        component.setFocusable(component instanceof JTextField || component instanceof JTextArea);

        // Component specific options
        if (component instanceof JTextField textField) {
            textField.setCaretColor(appearance.getMainForeground());
        }
        else if (component instanceof JTextArea textArea) {
            textArea.setCaretColor(appearance.getMainForeground());
        }

        return component;
    }

    Color getMainBackground();
    Color getSecondaryBackground();
    Color getMainForeground();
    Color getSecondaryForeground();
    Border getBorder();
    Font getFont();
}
