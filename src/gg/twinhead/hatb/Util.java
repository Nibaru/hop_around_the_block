package gg.twinhead.hatb;

import gg.twinhead.hatb.componet.Component;

import java.awt.*;
import java.awt.geom.Line2D;

public class Util {

    public Util(){

    }

    public boolean circleCollision(Component circleOne, Component circleTwo){
        return false;
    }

    public boolean rectPointCollision(double x, double y, Component rect){
        return inRange(x, (int) rect.getPosX(), (int) (rect.getPosX() + rect.getWidth())) &&
                inRange(y, (int) rect.getPosY(), (int) (rect.getPosY() + rect.getHeight()));

    }

    public boolean rectPointCollision(Point d, Component rect){
        return inRange(d.x, (int) rect.getPosX(), (int) (rect.getPosX() + rect.getWidth())) &&
                inRange(d.y, (int) rect.getPosY(), (int) (rect.getPosY() + rect.getHeight()));

    }

    private boolean inRange(double value, int min, int max){
        return value >= min && value <= max;
    }


    private boolean rangeIntersect(int min0, int max0, int min1, int max1) {
        return Math.max(min0, max0) >= Math.min(min1, max1) &&
                Math.min(min0, max0) <= Math.max(min1, max1);
    }

    public boolean rectIntersect(Component r0, Component r1) {
        return rangeIntersect((int) r0.getPosX(), (int) (r0.getPosX() + r0.getWidth()), (int) r1.getPosX(), (int) (r1.getPosX() + r1.getWidth())) &&
                rangeIntersect((int) r0.getPosY(), (int) (r0.getPosY() + r0.getHeight()), (int) r1.getPosY(), (int) (r1.getPosY() + r1.getHeight()));
    }

    public double distance(int x1, int y1, int x2, int y2){
        return Math.sqrt( Math.pow((y2 - y1), 2) + Math.pow((x2 - x1),  2));
    }


    public boolean rectangleLineIntersect(Component rect, int x1, int y1, int x2, int y2){
        Line2D line = new Line2D.Float(x1, y1, x2, y2);

        //TOP LINE
        if(line.intersectsLine(rect.getPosX(), rect.getPosY(), rect.getPosX() + rect.getWidth(), rect.getPosY())) return true;
        System.out.println("Top");

        //LEFT LINE
        if(line.intersectsLine(rect.getPosX(), rect.getPosY(), rect.getPosX(), rect.getPosY() + rect.getHeight())) return true;
        System.out.println("Left");

        //RIGHT LINE
        if(line.intersectsLine(rect.getPosX() + rect.getWidth(), rect.getPosY(), rect.getPosX() + rect.getWidth(), rect.getPosY() + rect.getHeight())) return true;
        System.out.println("Right");

        //BOTTOM LINE
        if(line.intersectsLine(rect.getPosX(), rect.getPosY() + rect.getHeight(), rect.getPosX() + rect.getWidth(), rect.getPosY() + rect.getHeight())) return true;
        System.out.println("Bottom");


        return false;
    }
}
