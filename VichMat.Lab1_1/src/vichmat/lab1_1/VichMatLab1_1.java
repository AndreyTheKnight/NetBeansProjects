/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vichmat.lab1_1;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class VichMatLab1_1 {

    /**
     * @param args the command line arguments
     */
    private static class Function {
	private float a, b, h, eps;
        private String format = "";
        private ArrayList<Float[]> OtdelennieKorni;
        private ArrayList<Float> UtochnennieKorni;
        public Function(float a, float b, float h, float eps) {
            this.a = a;	this.b = b; this.h = h; this.eps = eps;			
            for (float e = eps; Math.round(e) == 0 && e != 0; e *= 10)
                this.format += "#";
            OtdelennieKorni = new ArrayList<>();
            UtochnennieKorni = new ArrayList<>();
        };
        private float Evaluate(float x) {
            return (float)Math.sin(x-0.5f);
        };
        public void OtdelitKorni() {
            for (float i = a; i < b; i += h)
                if (Evaluate(i)*Evaluate(i+h) <= 0) {
                    Float[] x = {i, i+h};
                    OtdelennieKorni.add(x);						
                }
        }
        public void UtochnitKorni() {
            for (Float[] root : OtdelennieKorni) {
                float x0 = (root[0] + root[1])/2;
                float x = x0 - Evaluate(x0);
                while (Math.abs(x - (x - Evaluate(x))) > eps)
                    x = x - Evaluate(x);
                UtochnennieKorni.add(x);
            }
        }
        public void Print() {
            PrintStream cout = System.out;
            cout.println("(x^2 + 1)*sin(x - 0.5) = 0");
            cout.println("a = " + a + "; b = " + b + "; h = " + h + "; eps = " + eps);
            cout.print("Отделённые корни: ");
            for (Float[] root : OtdelennieKorni)
                cout.print("[" + root[0] + "; " + root[1] + "] ");
            cout.print("\nУточнённые корни: ");
            for (Float root : UtochnennieKorni)
                cout.print(new DecimalFormat("#."+this.format).format(root));
        }
    }

    public static void main(String[] args) {

        Function f = new Function(0.5f, 1.5f, 0.1f, 0.00001f);
        f.OtdelitKorni();
        f.UtochnitKorni();
        f.Print();
        System.out.println();

    }
    
}
