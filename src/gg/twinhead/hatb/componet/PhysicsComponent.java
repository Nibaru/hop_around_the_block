package gg.twinhead.hatb.componet;

import gg.twinhead.hatb.Face;

import java.awt.*;

public class PhysicsComponent extends CollisionComponent{

    public static final double FRICTION = 0.6;
    public static final double GRAVITY = 0.02;

    private double speedX = 0;
    private double speedY = 0;

    private boolean jumping;
    private Long lastJump;

    private double jumpStrength;

    public PhysicsComponent(int x, int y, int sizeX, int sizeY) {
        super(x, y, sizeX, sizeY);
        lastJump = System.currentTimeMillis();
    }


    public void accelerate(double accelerationX, double accelerationY) {
        speedX += accelerationX;
        speedY += accelerationY;
    }

    public void moveX(double xDelta) {
        if(this.collidingFaces.contains(Face.RIGHT) && xDelta > 0 || collidingFaces.contains(Face.LEFT) && xDelta < 0){
            return;
        } else {
            posX += xDelta;
        }
    }

    public void moveY(double yDelta) {
        if(collidingFaces.contains(Face.DOWN) && yDelta > 0){
            //posY -= yDelta;
        } else{
            posY += yDelta;
        }
    }

    public void update() {
        //System.out.println("");
        //System.out.printf("Player: jumping: %b X:%f:  Y: %f, xV: %f:, yV: %f faces: %s" , jumping, posY, posX,  speedX, speedY, collidingFaces.toString());

        moveX(speedX);
        moveY(speedY);

        collision();

        speedX *= FRICTION;
        speedY *= 0.995;

        for (Component c: getCollidingWith()) {
            c.setColor(Color.CYAN);
        }


        if (collidingFaces.contains(Face.DOWN)){
            speedY = 0.0;
        }if(collidingFaces.contains(Face.UP)){
            speedY = 0.0;
            accelerate(0, GRAVITY);
        } else {
            accelerate(0, GRAVITY); // gravity accelerates the object downwards each tick
        }

        if(jumping){
            accelerate(0, -jumpStrength);
            if(System.currentTimeMillis() - lastJump > 1){
                jumping = false;
            }
        }
    }

    public void jump (double jumpStrength){
        this.jumpStrength = jumpStrength;
        if (isGrounded()) {
            lastJump = System.currentTimeMillis();
            jumping = true;
            if(collidingFaces.contains(Face.UP)){
               speedY =0;
            }
        }
    }

    public boolean isGrounded () {
        return collidingFaces.contains(Face.DOWN);
    }

}
