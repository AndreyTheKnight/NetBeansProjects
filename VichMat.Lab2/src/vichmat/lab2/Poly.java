package vichmat.lab2;

public class Poly {
    
    protected class PolyPoint {
        public final double x;
        public final double y;
        public PolyPoint(double x, double y) {
            this.x = x; this.y = y;
        }
    }

    private final PolyPoint[] point;
    private final PolyPoint[] pointInter;    
    private final double[][] dy;
    private final double h;
    private String formula;

    public Poly(double[] x, double[] y) {
        this.point = new PolyPoint[x.length];
        for (int i = 0; i < point.length; i++)
            this.point[i] = new PolyPoint(x[i], y[i]);
        this.pointInter = new PolyPoint[this.point.length - 1];
        this.h = this.point[1].x - this.point[0].x;
        this.dy = new double[this.point.length - 1][];
        this.setDy();
        this.setFormula();
    }
    public final void print() {
        System.out.println("Формула интерполяционного многочлена:\n" + this.formula);
    }
    public final void setInter() {
        for (int i = 0; i < this.pointInter.length; i++)
            this.pointInter[i] = new PolyPoint(this.point[i].x + this.h / 2, 
                    this.eval(this.point[i].x + this.h / 2));
    }
    public final PolyPoint[] getPoints() {
        return this.point;
    }
    public final PolyPoint[] getPointsInter() {
        return this.pointInter;
    }
    public final PolyPoint[] getPointsGraph(int pointsCount) {
        PolyPoint[] pointsGraph = new PolyPoint[pointsCount];
        double x = this.point[0].x;
        double hx = (this.point[this.point.length - 1].x - x) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            pointsGraph[i] = new PolyPoint(x, this.eval(x));
            x += hx;
        }
        return pointsGraph;
    }
    private void setDy() {
        this.dy[0] = new double[this.point.length - 1];
        for (int i = 0; i < this.dy[0].length; i++)
            this.dy[0][i] = this.point[i+1].y - this.point[i].y;
        for (int i = 1; i < this.dy.length; i++) {
            this.dy[i] = new double[this.dy[i-1].length - 1];
            for (int j = 0; j < this.dy[i].length; j++)
                this.dy[i][j] = this.dy[i-1][j+1] - this.dy[i-1][j];
        }
    }
    private void setFormula() {
        String t = "t";
        this.formula = "P(" + this.point[0].x + "+" + this.h + "t) = " + this.point[0].y;
        if (this.dy[0][0] > 0) this.formula += "+";
        this.formula += String.format("%.2f", this.dy[0][0]) + t;
        for (int i = 1; i < this.dy.length; i++) {
            t += "(t-" + i + ")";
            if (this.dy[i][0] > 0) this.formula += "+";
            this.formula += String.format("%.2f", this.dy[i][0]) + t + "/" + (i+1) + "!";
        }
    }
    private double eval(double x) {
        /*
        Вычисление значения функции на основе полинома по Первой формуле Ньютона
        P(x0+th) = y0 + dy[0][0]*t + ... + dy[n][0] * t(t-1)(t-2)...(t-n+1)/n!
        где t = (x - x0) / h, dy[n][0] - конечная разность n-ого порядка.
        z = t(t-1)(t-2)...(t-n+1)/n!, т.е. при i = 0, z = t/1!; при i = 1, z = t(t-1)/2!;
        */
        double t = (x - this.point[0].x) / this.h;
        double z = 1;
        double p = this.point[0].y;
        for (int i = 0; i < this.dy.length; i++) {
            z *= (t - i) / (i + 1);
            p += this.dy[i][0] * z;
        }
        return p;
    }

}
