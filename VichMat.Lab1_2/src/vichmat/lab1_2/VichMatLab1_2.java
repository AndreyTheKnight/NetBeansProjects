/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vichmat.lab1_2;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 *
 * @author andre
 */
public class VichMatLab1_2 {

    /**
     * @param args the command line arguments
     */
    private static class SystLinAlgUrav {

        private double[][] a;
        private double[] b;
        private double eps;
        private String format = "";
        private int n;
        private double[] x;
        private double p;
        private int iterationsCount;

        public SystLinAlgUrav (double[][] a, double[] b, double eps) {
            this.a = a; this.b = b; this.eps = eps;	this.n = a.length;
            for (double e = eps; Math.round(e) == 0 && e != 0; e *= 10)
                this.format += "#";
        }
        public void ReshitSlau() {
            this.MakeExtendedMatrix();
            this.CheckMetric();
            if (this.p >= 1) {
                System.out.println("Ни одно из условий сходимости не выполнено");
                return;
            }
            this.x = Arrays.copyOf(this.b, this.n);
            double s;
            do {
                s = 0;
                double[] y = Arrays.copyOf(this.b, this.n);
                for (int i = 0; i < this.n; ++i) {
                    for (int j = 0; j < this.n; ++j)
                        y[i] += this.a[i][j]*this.x[j];
                    s += Math.pow(this.x[i] - y[i], 2);
                }
                this.x = Arrays.copyOf(y, this.n);
                ++(this.iterationsCount);
            } while (Math.sqrt(s) > this.eps * (1 - this.p)/this.p);
            System.out.println("Количество итераций = " + this.iterationsCount);
            System.out.print("Корни системы: ");
            for (double xi: this.x)
                System.out.print(new DecimalFormat("#."+this.format).format(xi) + " ");
        }
        private void MakeExtendedMatrix() {
            double[] glavDiag = new double[this.n];
            for (int i = 0; i < this.n; ++i) {
                glavDiag[i] = this.a[i][i];
                this.a[i][i] = 0;
                this.b[i] /= glavDiag[i];
            }
            for (int i = 0; i < this.n; ++i)
                for (int j = 0; j < this.n; ++j)
                    this.a[i][j] /= -1 * glavDiag[i];
        }
        private void CheckMetric() {
            for (double[] ai: this.a)
                for (double aj: ai)
                    this.p += Math.pow(aj, 2);
            this.p = Math.sqrt(this.p);
        }

    }

    public static void main(String[] args) {

        double[][] a = {
            {4.81, 0.4, -0.57},
            {1.06, 7.28, 1.75},
            {-0.05, -0.1, 2.59}
        };
        double[] b = {-1.82, -8.09, -8.88};
        double eps = 0.0001;
        SystLinAlgUrav slau = new SystLinAlgUrav(a, b, eps);
        slau.ReshitSlau();
        System.out.println();

    }
    
}
