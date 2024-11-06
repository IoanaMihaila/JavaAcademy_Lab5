/*
1. Nu ar avea sens sa instantiem o FiguraGeometrica.
2. Orice figura geometrica ar trebui sa ofere posibilitatea calcului ariei
3.
 */

abstract class FiguraGeometrica {
    public abstract int getArie();
}

class Patrat extends FiguraGeometrica {
    private int latura;

    public Patrat(int latura) {
        this.latura = latura;
    }

    public int getArie() {
        return latura * latura;
    }
}

public class MainAbstracte {
    public static void main(String[] args) {
        FiguraGeometrica fig = new Patrat(5);
        System.out.println(fig.getArie());
    }
}