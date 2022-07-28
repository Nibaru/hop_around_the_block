package gg.twinhead.hatb.componet;

import gg.twinhead.hatb.CollisionFace;
import gg.twinhead.hatb.Face;

import java.awt.*;

public class MovingPlatformComponent extends CollisionComponent{


    private final float startX;
    private final int maxX = 300;
    private final float speed = 2.5f;

    private boolean returning = false;

    long lastMovement = System.currentTimeMillis();

    public MovingPlatformComponent(String name, float x, float y, int sizeX, int sizeY) {
        super(name, x, y, sizeX, sizeY);
        startX = x;
    }


    @Override
    public void update(){
        super.update();
        //System.out.println(System.currentTimeMillis() - lastMovement);

        if (System.currentTimeMillis() - lastMovement <= 10) {
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

        lastMovement = System.currentTimeMillis();
    }


    public void move(float xDelta){
        if(collidingFaces.contains(CollisionFace.NORTH)){
            for (CollisionComponent c: collidingWith){
                if(c instanceof PhysicsComponent)
                    c.posX += xDelta;
            }
        }
        if(collidingFaces.contains(CollisionFace.EAST) && collisionFaceCollision.get(CollisionFace.EAST) instanceof PhysicsComponent){
            for (CollisionComponent c: collidingWith){
                if(c instanceof PhysicsComponent){
                    if(posX + width + xDelta > c.posX)
                        c.posX += xDelta;

                }
            }
        } if(collidingFaces.contains(CollisionFace.WEST) && collisionFaceCollision.get(CollisionFace.WEST) instanceof PhysicsComponent){
            if(returning)
                for (CollisionComponent c: collidingWith){
                    if(c instanceof PhysicsComponent){
                        c.posX += xDelta;

                    }
                }
        }

        posX += xDelta;
    }
}
