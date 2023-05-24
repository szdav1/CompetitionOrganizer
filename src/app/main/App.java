package app.main;

import app.frame.XFrame;
import support.appdata.AssetsData;
import support.util.Util;

public final class App {
    public static void main(String[] args) {
        new XFrame(Util.loadIcon(AssetsData.APP_ICONS.concat("mainIcon")).getImage(), "Project1_20230501");
    }
}
