package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Александр on 04.06.2017.
 */


public class DekartSystem extends JPanel {

    private int xLength;
    private int yLength;
    private int ZeroX;
    private int ZeroY;
    private int LastX;
    private int LastY;
    private BasicStroke basicStroke;
    private int scalingPercentage;
    private boolean bool;
    private int kolMass = 1;
    private final int delta = 50;
    private int maxSortTime;
    private final int kolSegmentY = 11;
    private ArrayList<Long> arraySort;
    private int stepY;
    private int stepX;

    public DekartSystem() {
        this.setSize(850, 620);
        this.setPreferredSize(new Dimension(850, 620));
        this.setBorder(BorderFactory.createTitledBorder("dfg"));
        xLength = getWidth();
        yLength = getHeight();
        ZeroX = 50;
        ZeroY = yLength;
        LastX = xLength;
        LastY = ZeroY - yLength + 25;
        scalingPercentage = 100;
        arraySort = new ArrayList<>();

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        paintSystem(g2);
        if (bool == false)
            paintGoordinate(g2);
        else
            paintGraphLine(g2);
    }


    private void paintSystem(Graphics2D g2) {
        basicStroke = new BasicStroke(2.7f + scalingPercentage / 100.0f);
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.red);
        g2.setStroke(basicStroke);


        g2.drawLine(ZeroX * scalingPercentage / 100, ZeroY * scalingPercentage / 100, ZeroX * scalingPercentage / 100, LastY * scalingPercentage / 100);//Oy
        g2.drawLine(ZeroX * scalingPercentage / 100, LastY * scalingPercentage / 100, (ZeroX + 5) * scalingPercentage / 100, (LastY + 10) * scalingPercentage / 100);//Шапка для Оу
        g2.drawLine(ZeroX * scalingPercentage / 100, LastY * scalingPercentage / 100, (ZeroX - 5) * scalingPercentage / 100, (LastY + 10) * scalingPercentage / 100);//Шапка для Оу

        g2.drawLine(ZeroX * scalingPercentage / 100, ZeroY * scalingPercentage / 100, LastX * scalingPercentage / 100, ZeroY * scalingPercentage / 100);//Ox
        g2.drawLine(LastX * scalingPercentage / 100, ZeroY * scalingPercentage / 100, (LastX - 10) * scalingPercentage / 100, (ZeroY + 5) * scalingPercentage / 100);//Шапка для Оx
        g2.drawLine(LastX * scalingPercentage / 100, ZeroY * scalingPercentage / 100, (LastX - 10) * scalingPercentage / 100, (ZeroY - 5) * scalingPercentage / 100);//Шапка для Оx


        g2.setColor(Color.black);

        g2.drawString("O", (ZeroX + 5) * scalingPercentage / 100, (ZeroY - 7) * scalingPercentage / 100);

        basicStroke = new BasicStroke(1.9f);
        g2.setStroke(basicStroke);

        g2.drawString("n", (LastX + 5) * scalingPercentage / 100, ZeroY * scalingPercentage / 100);
        g2.drawString("t,нс", ZeroX * scalingPercentage / 100, (LastY - 5) * scalingPercentage / 100);

    }


    private void paintGoordinate(Graphics2D g2) {


        for (int currentSegmentX = ZeroX + delta; currentSegmentX < LastX; currentSegmentX += delta) {
            g2.drawLine(currentSegmentX * scalingPercentage / 100, ZeroY * scalingPercentage / 100, currentSegmentX * scalingPercentage / 100, (ZeroY + 5) * scalingPercentage / 100);
            g2.drawLine(currentSegmentX * scalingPercentage / 100, ZeroY * scalingPercentage / 100, currentSegmentX * scalingPercentage / 100, (ZeroY - 5) * scalingPercentage / 100);
        }

        for (int currentSegmentY = ZeroY - delta; currentSegmentY > LastY; currentSegmentY -= delta) {
            g2.drawLine(ZeroX * scalingPercentage / 100, currentSegmentY * scalingPercentage / 100, (ZeroX + 5) * scalingPercentage / 100, currentSegmentY * scalingPercentage / 100);
            g2.drawLine(ZeroX * scalingPercentage / 100, currentSegmentY * scalingPercentage / 100, (ZeroX - 5) * scalingPercentage / 100, currentSegmentY * scalingPercentage / 100);

        }
    }

    private void paintGraphLine(Graphics2D g2) {
        paintSegmentX(g2);
        paintSegmentY(g2);
        paintGraph(g2);

    }


    private void paintSegmentY(Graphics2D g2) {
        if (maxSortTime > kolSegmentY)
            stepY = maxSortTime / kolSegmentY;
        else
            stepY = 1;
        int currentY = maxSortTime;
        System.out.println(stepY);
        for (int currentSegmentY = LastY + delta; currentSegmentY <= ZeroY && currentY > 0; currentSegmentY += delta) {
            g2.drawLine(ZeroX * scalingPercentage / 100, currentSegmentY * scalingPercentage / 100, (ZeroX + 5) * scalingPercentage / 100, currentSegmentY * scalingPercentage / 100);
            g2.drawLine(ZeroX * scalingPercentage / 100, currentSegmentY * scalingPercentage / 100, (ZeroX - 5) * scalingPercentage / 100, currentSegmentY * scalingPercentage / 100);
            g2.drawString(String.valueOf(currentY), (ZeroX - 45) * scalingPercentage / 100, currentSegmentY * scalingPercentage / 100);
            currentY -= stepY;
        }


    }

    private void paintSegmentX(Graphics2D g2) {

        int kolSegmentX = (LastX - (ZeroX + delta)) / delta;
        stepX = (int) Math.round((kolMass - 1) / kolSegmentX + 0.5);
        int currentValue = 2;
        for (int currentSegmentX = ZeroX + delta; currentSegmentX < LastX; currentSegmentX += delta) {
            g2.drawLine(currentSegmentX * scalingPercentage / 100, ZeroY * scalingPercentage / 100, currentSegmentX * scalingPercentage / 100, (ZeroY + 5) * scalingPercentage / 100);
            g2.drawLine(currentSegmentX * scalingPercentage / 100, ZeroY * scalingPercentage / 100, currentSegmentX * scalingPercentage / 100, (ZeroY - 5) * scalingPercentage / 100);
            g2.drawString(String.valueOf(currentValue), (currentSegmentX - 3) * scalingPercentage / 100, (ZeroY + 25) * scalingPercentage / 100);

            currentValue += stepX;

        }


    }


    private void paintGraph(Graphics2D g2) {

        g2.setColor(Color.black);
        basicStroke = new BasicStroke(2.9f + scalingPercentage / 100.0f);
        g2.setStroke(basicStroke);


        int startPointX = ZeroX + delta;
        for (int numberCurrentPoint = 0; numberCurrentPoint < arraySort.size() - 1; numberCurrentPoint++) {
            g2.drawLine((startPointX + delta * (numberCurrentPoint) / stepX) * scalingPercentage / 100, (int) (ZeroY - (delta * (arraySort.get(numberCurrentPoint))) / stepY) * scalingPercentage / 100, (startPointX + delta * (numberCurrentPoint + 1) / stepX) * scalingPercentage / 100, (int) (ZeroY - (delta * (arraySort.get(numberCurrentPoint + 1))) / stepY) * scalingPercentage / 100);
        }

    }

    public void buildGraph(int kol, boolean bool, int maxSortTime, ArrayList<Long> arraySort) {
        this.bool = bool;
        this.kolMass = kol;
        this.maxSortTime = maxSortTime;
        this.arraySort = arraySort;
        System.out.println(arraySort);

    }


    public int getScalingPercentage() {
        return scalingPercentage;
    }

    public void setScalingPercentage(int scalingPercentage) {
        this.scalingPercentage = scalingPercentage;
    }

}

