package app.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import support.appdata.SizeData;
import support.interfaces.ContainerType;

public abstract class AbstractXFrame extends JFrame implements ContainerType {
    protected final JLayeredPane contentPane;
    protected HashMap<XFrameConstants, Boolean> stateMap = new HashMap<>();

    // Default JFrame setup
    protected AbstractXFrame() {
        this.contentPane = new JLayeredPane();
        // Setting the states
        this.stateMap.put(XFrameConstants.NORMAL, true);
        this.stateMap.put(XFrameConstants.EDITOR_OPENED, false);
        this.stateMap.put(XFrameConstants.ON_GOING_COMPETITION, false);

        // Basic JFrame setup
        {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.setContentPane(this.contentPane);
            this.setUndecorated(true);
            this.setBackground(Color.black);
            this.getContentPane().setBackground(this.getBackground());
            this.setForeground(Color.white);
            this.setLayout(new BorderLayout());
            this.setBounds(0, 0, SizeData.WIDTH, SizeData.HEIGHT);
        }
    }

    // Default JFrame setup with icon and title
    protected AbstractXFrame(Image iconImage, String title) {
        this();
        this.setIconImage(iconImage);
        this.setTitle(title);
    }

    public HashMap<XFrameConstants, Boolean> getFrameStates() {
        return this.stateMap;
    }

    public boolean getFrameState(XFrameConstants key) {
        return this.stateMap.get(key);
    }

    public void setFrameState(XFrameConstants key, boolean value) {
        if (this.stateMap.containsKey(key)) {
            this.stateMap.put(key, value);
        }
    }
}
