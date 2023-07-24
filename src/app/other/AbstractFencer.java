package app.other;

public abstract class AbstractFencer {
    protected final String name;
    protected final int wins;
    protected final int ts;
    protected final int tr;
    protected final int index;
    protected int place;

    protected AbstractFencer(String name, int wins, int ts, int tr, int index) {
        this.name = name;
        this.wins = wins;
        this.ts = ts;
        this.tr = tr;
        this.index = index;
    }

    protected AbstractFencer(String name, int wins, int ts, int tr, int index, int place) {
        this.name = name;
        this.wins = wins;
        this.ts = ts;
        this.tr = tr;
        this.index = index;
        this.place = place;
    }

    public String getName() {
        return this.name;
    }

    public int getWins() {
        return this.wins;
    }

    public int getTs() {
        return this.ts;
    }

    public int getTr() {
        return this.tr;
    }

    public int getIndex() {
        return this.index;
    }

    public int getPlace() {
        return this.place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return name + ", " + wins + ", " + ts + ", " + tr + ", " + index + ", " + place;
    }
}
