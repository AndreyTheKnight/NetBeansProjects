package vichmat.lab2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import javax.swing.*;

@SuppressWarnings("serial")
public class GraphDraw extends JPanel {
    private static final int PREF_W = 600;
    private static final int PREF_H = 450;
    private static final int BORDER_GAP = 30;
    private static final Color GRAPH_COLOR = Color.green;
    private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
    private static final int GRAPH_POINT_WIDTH = 6;
    private static final int X_MARKS_CNT = 5;
    private static final int Y_MARKS_CNT = 10;
    private final Poly.PolyPoint[] points;
    private final Poly.PolyPoint[] pointsInter;
    private final Poly.PolyPoint[] pointsGraph;

    public GraphDraw(Poly.PolyPoint[] points, Poly.PolyPoint[] pointsInter, 
            Poly.PolyPoint[] pointsGraph) {
        this.points = points; this.pointsInter = pointsInter;
        this.pointsGraph = pointsGraph;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (5);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (10);

        // create x and y axes 
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

        // create hatch marks for y axis. 
        for (int i = 0; i < Y_MARKS_CNT; i++) {
            int x0 = BORDER_GAP;
            int x1 = BORDER_GAP + GRAPH_POINT_WIDTH;
            int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_MARKS_CNT + BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < X_MARKS_CNT; i++) {
            int x0 = BORDER_GAP + (i + 1) * (getWidth() - BORDER_GAP * 2) / X_MARKS_CNT;
            int x1 = x0;
            int y0 = getHeight() - BORDER_GAP;
            int y1 = y0 - GRAPH_POINT_WIDTH;
            g2.drawLine(x0, y0, x1, y1);
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < this.pointsGraph.length - 1; i++) {
            int x1 = (int)(this.pointsGraph[i].x * xScale) + BORDER_GAP;
            int y1 = (int)(getHeight() - 2 * BORDER_GAP - 
                    this.pointsGraph[i].y * yScale) + BORDER_GAP;
            int x2 = (int)(this.pointsGraph[i+1].x * xScale) + BORDER_GAP;
            int y2 = (int)(getHeight() - 2 * BORDER_GAP - 
                    this.pointsGraph[i+1].y * yScale) + BORDER_GAP;
            g2.drawLine(x1, y1, x2, y2);         
        }

        g2.setStroke(oldStroke);
        g2.setColor(GRAPH_POINT_COLOR);
        for (Poly.PolyPoint point : this.points) {
            int x = (int) (point.x * xScale) - GRAPH_POINT_WIDTH / 2 + BORDER_GAP;
            int y = (int) (getHeight() - 2 * BORDER_GAP - 
                    point.y * yScale) - GRAPH_POINT_WIDTH / 2 + BORDER_GAP;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2.fillOval(x, y, ovalW, ovalH);
        }
        for (Poly.PolyPoint point : this.pointsInter) {
            int x = (int) (point.x * xScale) - GRAPH_POINT_WIDTH / 2 + BORDER_GAP;
            int y = (int) (getHeight() - 2 * BORDER_GAP - 
                    point.y * yScale) - GRAPH_POINT_WIDTH / 2 + BORDER_GAP;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    private static void createAndShowGui(Poly.PolyPoint[] points, 
            Poly.PolyPoint[] pointsInter, Poly.PolyPoint[] pointsGraph) {
        GraphDraw mainPanel = new GraphDraw(points, pointsInter, pointsGraph);

        JFrame frame = new JFrame("GraphDraw");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        
        Poly poly = new Poly(
                new double[] {1.28, 1.76, 2.24, 2.72, 3.20, 3.68, 4.16, 4.64},
                new double[] {2.10, 2.62, 3.21, 3.98, 4.98, 6.06, 7.47, 9.25});
        poly.setInter();
        poly.print();
        
        SwingUtilities.invokeLater(() -> {
            createAndShowGui(poly.getPoints(), poly.getPointsInter(), poly.getPointsGraph(100));
        });
    }

}
