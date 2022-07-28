package gg.twinhead.hatb;

import gg.twinhead.hatb.componet.Component;

public class Collision {

    public Collision(){

    }

    public boolean circleCollision(Component circleOne, Component circleTwo){
        return false;
    }

    public boolean rectPointCollision(double x, double y, Component rect){
        return inRange(x, (int) rect.getPosX(), (int) (rect.getPosX() + rect.getWidth())) &&
                inRange(y, (int) rect.getPosY(), (int) (rect.getPosY() + rect.getHeight()));

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
}
