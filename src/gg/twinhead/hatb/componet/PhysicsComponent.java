package gg.twinhead.hatb.componet;

import gg.twinhead.hatb.CollisionFace;
import gg.twinhead.hatb.util.Vector;

import java.awt.*;
import java.util.List;

public class PhysicsComponent extends CollisionComponent{

    public static final float FRICTION = 0.9F;
    public static final float GRAVITY = 0.2F;
    public static final float SPEED = 2F;

    public Vector velocity = new Vector(0, 0);

    private boolean jumping;
    private float jumpStrength;

    private long timeSinceLastJump;

    public PhysicsComponent(String name, float x, float y, int sizeX, int sizeY) {
        super(name, x, y, sizeX, sizeY);
        timeSinceLastJump = System.currentTimeMillis();
    }


    public void accelerate(float accelerationX, float accelerationY) {
        velocity.x += accelerationX;
        velocity.y += accelerationY;
    }

    public void move() {
        posX += velocity.x;
        posY += velocity.y;
    }

    public void friction(){
        if(jumping || isFalling()){
            velocity.x *= 0.95;
            velocity.y *= 0.99;
        } else {
            velocity.x *= 0.9;;
            velocity.y *= 0.99;
        }
        //velocity.y *= 0.99;
    }

    @Override
    public void update() {
        super.update();

        friction();



        if (isGrounded() && velocity.y < 0){
            if(!jumping)
                velocity.y = 0.0f;
        } else {
            if(!jumping)
                accelerate(0, GRAVITY); // gravity accelerates the object downwards each tick
        }

        if(isFalling()){
            accelerate(0, GRAVITY);
        }

        if(collidingFaces.contains(CollisionFace.SOUTH) && velocity.y > 0 && collisionFaceCollision.get(CollisionFace.SOUTH) != null && collisionFaceCollision.get(CollisionFace.SOUTH).posY < posY + height - 1){
            if(!jumping){
                velocity.y = 0;
                posY = collisionFaceCollision.get(CollisionFace.SOUTH).posY - height + 1;
            }
        }

        if(collidingFaces.contains(CollisionFace.EAST) && velocity.x > 0){
            velocity.x = 0;
        }

        if(collidingFaces.contains(CollisionFace.WEST) && velocity.x < 0){
            velocity.x = 0;
        }



        pushOut();
        move();


        if(jumping && System.currentTimeMillis() - timeSinceLastJump < 20){
            jumping = false;
        }

    }

    public void jump (float jumpStrength){
        if(System.currentTimeMillis() - timeSinceLastJump > 100 && isGrounded()){
            jumping = true;
            velocity.y -= jumpStrength;
            timeSinceLastJump = System.currentTimeMillis();
        }
    }


    public boolean isGrounded () {
        return collidingFaces.contains(CollisionFace.SOUTH);
    }

    public boolean isJumping(){
        return !isGrounded() && velocity.y < 0;
    }

    public boolean isFalling(){
        return !isGrounded() && velocity.y > 0;
    }


    //Push out the object from another colliding object
    //Check a slightly smaller rectangle to not impact collision
    private void pushOut(){
        for (CollisionComponent c: collidingWith) {
            Rectangle rect = c.getRectangle().intersection(this.getRectangle());

            if(rect.width <= 1 || rect.height <= 1) return;

            if(rect.width > rect.height){
                if(collidingFaces.contains(CollisionFace.SOUTH)){
                    if(collisionFaceCollision.get(CollisionFace.SOUTH) != null && collisionFaceCollision.get(CollisionFace.SOUTH).posY < posY + height)
                        if(collisionFaceCollision.get(CollisionFace.SOUTH).posY < posY + height - 1){
                            posY = collisionFaceCollision.get(CollisionFace.SOUTH).posY - height + 1;
                        } else {
                            accelerate(0, -(SPEED) / 2);

                        }
                }
                if(collidingFaces.contains(CollisionFace.NORTH)){
                    velocity.y = 0.0f;
                    accelerate(0, GRAVITY);
                }
            } else {
                if(collidingFaces.contains(CollisionFace.WEST)){
                    velocity.x = 0.0f;
                    accelerate(SPEED / 2, 0);
                }
                if(collidingFaces.contains(CollisionFace.EAST)){
                    velocity.x = 0.0f;
                    accelerate(-SPEED / 2, 0);
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g){
        super.draw(g);
    }

    @Override
    public java.util.List<String> getOutputList(){
        List<String> list = super.getOutputList();
        list.add("Grounded: " + isGrounded());
        list.add("Jumping: " + isJumping());
        list.add("Falling: " + isFalling());
        list.add("Velocity: [X:" + String.format("%.2f", velocity.x)+ "Y: " + String.format("%.2f", velocity.y) +" ]");

        return list;
    }
}
