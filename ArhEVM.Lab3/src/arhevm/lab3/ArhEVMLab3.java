/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arhevm.lab3;

import java.io.PrintStream;

/**
 *
 * @author andre
 */
public class ArhEVMLab3 {

    /**
     * @param args the command line arguments
     */
    
    static private class SomeBox implements Box {
        
        protected int length;
        protected int width;
        protected int height;
        protected int weight;
        private BOX_COLOR color;
        
        {
            this.length = 1;
            this.width = 1;
            this.height = 1;
            this.weight = 1;
            this.color = BOX_COLOR.red;
        }
        
        public SomeBox() {
            super();
        }
        
        public SomeBox(int l, int wid, int h, int wei, BOX_COLOR c) {
            this.length = l;
            this.width = wid;
            this.height = h;
            this.weight = wei;
            this.color = c;
        }
        
        public SomeBox(SomeBox box) {
            this.length = box.length;
            this.width = box.width;
            this.height = box.height;
            this.weight = box.weight;
            this.color = box.color;
        }
        
        @Override
        public int getVolume() {
            return this.length * this.width * this.height;
        }
        
        @Override
        public int getWeight() {
            return this.weight;
        }
        
        @Override
        public BOX_COLOR getColor() {
            return this.color;
        }
        
    }
    
    static private class CompartmentBox extends SomeBox {
        
        private int compartmentsCount = 1;
        
        public CompartmentBox(int l, int wid, int h, int wei, BOX_COLOR c, int comp) {
            super(l, wid, h, wei, c);
            this.compartmentsCount = comp;
        }
        
        public CompartmentBox(CompartmentBox box) {
            super(box);
            this.compartmentsCount = box.compartmentsCount;
        }
        
        public int getCompartmentsCount() {
            return this.compartmentsCount;
        }
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        PrintStream cout = System.out;
        Box theBox = new SomeBox();
        cout.println(theBox.getColor());
        theBox = new CompartmentBox(10, 5, 3, 56, ColoredBox.BOX_COLOR.green, 2);
        cout.println(((CompartmentBox)theBox).getCompartmentsCount());
        
    }
    
}
