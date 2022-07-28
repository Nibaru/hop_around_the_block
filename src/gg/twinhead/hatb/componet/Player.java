package gg.twinhead.hatb.componet;

import gg.twinhead.hatb.KeyHandler;
import gg.twinhead.hatb.effect.Trail;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class Player extends PhysicsComponent {

    private final KeyHandler keyHandler;
    public static final float JUMP_STRENGTH = 10F;
    public static final float JUMP_HEIGHT = 100F;
    public static final float SPEED = 0.5F;

    public float coins;

    Color color = new Color(245, 40, 145, 255);
    Color defaultColor = new Color(245, 40, 145, 255);

    public Player(int x, int y, int size, KeyHandler handler) {
        super("player", x, y, size , size);
        setColor(Color.MAGENTA);

        this.keyHandler = handler;

        effects.add(new Trail(this, 12, 4));
    }

    public void jump() {jump(JUMP_STRENGTH);}

    public void runRight() {
        if(!isFalling())
            accelerate(SPEED, 0);
    }

    public void runLeft() {
        if(!isFalling())
            accelerate(-SPEED, 0);
    }

    public void crouch(){
        height = defaultHeight / 2;
        //posY -= defaultHeight / 2;
    }

    public void unCrouch(){
        height = defaultHeight;
        posY -= defaultHeight / 2;
    }

    public void keyHandler(){
        if(upPressed){
            jump();
        }

        if(rightPressed){
            runRight();
        }

        if(leftPressed){
            runLeft();
        }
    }

    @Override
    public void update() {
        super.update();
        keyHandler();
    }

    @Override
    public void draw(Graphics2D g){
        if(isFalling()){
            //setColor(Color.BLUE);
        } else if(isJumping()) {
           // setColor(Color.green);
        } else {
            setColor(defaultColor);
        }
        super.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if(e.getKeyCode() == KeyEvent.VK_S){
            crouch();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        if(e.getKeyCode() == KeyEvent.VK_S){
            unCrouch();
        }
    }

    @Override
    public List<String> getOutputList(){
        List<String> list = super.getOutputList();
        list.add("Coins: " + coins);

        return list;
    }
}
