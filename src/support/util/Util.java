package support.util;

import java.awt.Image;

import javax.swing.ImageIcon;

import support.appdata.SizeData;

public final class Util {
    private Util() {

    }

    // Load icon with the given size
    public static ImageIcon loadIcon(String path, int width, int height) {
        return new ImageIcon(new ImageIcon(path.concat(".png")).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    // Load icon with same width and height
    public static ImageIcon loadIcon(String path, int ratio) {
        return Util.loadIcon(path, ratio, ratio);
    }

    // Load icon with default size
    public static ImageIcon loadIcon(String path) {
        return Util.loadIcon(path, SizeData.FONT_SIZE);
    }

    // Load icon with default size * 2
    public static ImageIcon loadBigIcon(String path) {
        return Util.loadIcon(path, (int) (SizeData.FONT_SIZE * 1.5));
    }
}
