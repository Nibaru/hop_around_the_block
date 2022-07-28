package gg.twinhead.hatb.componet;

import gg.twinhead.hatb.KeyHandler;

import java.awt.*;

public class PlayerComponent extends PhysicsComponent {

    private final KeyHandler keyHandler;
    public static final double JUMP_STRENGTH = 0.8;
    public static final double SPEED = 0.5;

    public PlayerComponent(int x, int y, int size, KeyHandler handler) {
        super(x, y, size, size);
        setColor(Color.MAGENTA);

        this.keyHandler = handler;
    }

    public void jump() {jump(JUMP_STRENGTH);}

    public void runRight() {
        moveX(SPEED);
    }

    public void runLeft() {moveX(-SPEED);}

    @Override
    public void update() {
        super.update();
        if(keyHandler.upPressed){
            jump();
        }
        if(keyHandler.downPressed){
            //player.y += player.speed;
        }
        if(keyHandler.rightPressed){
            runRight();
        }
        if(keyHandler.leftPressed){
            runLeft();
        }
    }
}
