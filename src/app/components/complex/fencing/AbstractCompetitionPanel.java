package app.components.complex.fencing;

import app.components.panels.AbstractXPanel;
import app.frame.XFrame;
import support.appdata.SizeData;
import support.framework.interfaces.Appearance;

public abstract class AbstractCompetitionPanel extends AbstractXPanel {
    public AbstractCompetitionPanel(XFrame frame, Appearance appearance) {
        super(SizeData.HALF_WIDTH - (SizeData.COMPETITION_PANEL_WIDTH / 2), SizeData.BORDER_SIZE, SizeData.COMPETITION_PANEL_WIDTH,
            SizeData.COMPETITION_PANEL_HEIGHT, frame, appearance);
    }
}