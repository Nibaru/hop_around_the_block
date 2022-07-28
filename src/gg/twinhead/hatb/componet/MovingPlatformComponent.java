package gg.twinhead.hatb.componet;

import gg.twinhead.hatb.Face;

import java.awt.*;

public class MovingPlatformComponent extends CollisionComponent{


    private final int startX;
    private final int maxX = 300;

    private final double speed = 2.5;

    private boolean returning = false;

    long lastMovement = System.currentTimeMillis();

    public MovingPlatformComponent(int x, int y, int sizeX, int sizeY) {
        super(x, y, sizeX, sizeY);
        startX = x;
    }


    @Override
    public void update(){
        //System.out.println(System.currentTimeMillis() - lastMovement);

        if (System.currentTimeMillis() - lastMovement <= 20) {
           return;
        }

        if(returning){
            this.color = Color.BLUE;
            move(-speed);
            if(this.posX <= startX) returning = false;
        }else {
            move(speed);
            this.color = Color.WHITE;
            if(this.posX >= startX + maxX) returning = true;
        }


        collision();

        for (CollisionComponent c: collidingWith){
            System.out.println("Touching player");
            if(c instanceof PlayerComponent player){

            }
        }

        lastMovement = System.currentTimeMillis();
    }


    public void move(double xDelta){
        if(collidingFaces.contains(Face.UP)){
            for (CollisionComponent c: collidingWith){
                System.out.println(collidingWith);
                c.posX += xDelta;
            }
        }
        posX += xDelta;
    }
}
