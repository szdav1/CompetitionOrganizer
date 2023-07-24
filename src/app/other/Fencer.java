package app.other;

public final class Fencer extends AbstractFencer {
    public Fencer(String name, int wins, int ts, int tr, int index) {
        super(name, wins, ts, tr, index);
    }

    public Fencer(String name, int wins, int ts, int tr, int index, int place) {
        super(name, wins, ts, tr, index, place);
    }
}
