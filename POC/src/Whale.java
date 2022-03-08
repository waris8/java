public class Whale extends Mammal{
    int fins;
    boolean kweek;

    public int getFins() {
        return fins;
    }

    public void setFins(int fins) {
        this.fins = fins;
    }

    public boolean isKweek() {
        return kweek;
    }

    public void setKweek(boolean kweek) {
        this.kweek = kweek;
    }

    public static void main(String[] args) {
        Whale w1 = new Whale();
        w1.setFins(4);
        Mammal m1 = w1;
        Whale w2 = (Whale) m1;
        System.out.println(w2.getFins());
    }
}
