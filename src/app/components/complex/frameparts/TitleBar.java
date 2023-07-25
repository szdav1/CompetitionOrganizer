package app.components.complex.frameparts;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import app.components.buttons.FCXButton;
import app.frame.XFrame;
import support.constants.PositionConstants;
import support.framework.interfaces.Appearance;

public final class TitleBar extends AbstractTitleBar {
    public TitleBar(ImageIcon icon, String title, XFrame frame, Appearance appearance) {
        super(icon, title, frame, appearance);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object source = e.getSource();
        boolean fromCreateMenu = false;
        int indexOfSourceButton = -1;

        // Loop through the createMenuButton's options and check for events
        for (JComponent component : this.createMenuButton.getOptions()) {
            if (component instanceof FCXButton fcxButton) {
                if (source.equals(fcxButton.getButton())) {
                    indexOfSourceButton = this.createMenuButton.getOptions().indexOf(fcxButton);
                    fromCreateMenu = true;
                }
            }
        }
        // Execute events
        if (fromCreateMenu) {
            if (indexOfSourceButton == 0) {
                this.createMenuButton.setToggled(false);
                this.frame.togglePouleEditor("competition");
            }
            else if (indexOfSourceButton == 1) {
                this.createMenuButton.setToggled(false);
                this.frame.togglePouleEditor("pouleOnly");
            }
            else if (indexOfSourceButton == 2) {
                this.createMenuButton.setToggled(false);
                this.frame.toggleTableEditor();
            }
        }

        // Loop through the utilMenuButton's options and check for events
        for (JComponent component : this.utilMenuButton.getOptions()) {
            if (component instanceof FCXButton fcxButton) {
                if (source.equals(fcxButton.getButton())) {
                    indexOfSourceButton = this.utilMenuButton.getOptions().indexOf(fcxButton);
                    fromCreateMenu = false;
                }
            }
        }
        // Execute events
        if (!fromCreateMenu) {
            if (indexOfSourceButton == 0) {
                this.utilMenuButton.setToggled(false);
                this.frame.toggleDatabaseEditor();
            }
        }
    }

    @Override
    public void addComponent(JComponent component, PositionConstants positionConstants) {
        this.add(component, positionConstants.getzIndex());
        this.repaintFrame();
    }

    @Override
    public void addComponent(JComponent component, String borderLayoutPosition) {
        if (this.getLayout() instanceof BorderLayout) {
            this.addComponent(component, borderLayoutPosition);
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
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        final Object source = e.getSource();

        this.menuButtonList.stream()
            .filter(menuButton -> !source.equals(menuButton.getButton()))
            .forEach(menuButton -> menuButton.setToggled(false));
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
