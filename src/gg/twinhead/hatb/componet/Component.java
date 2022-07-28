package gg.twinhead.hatb.componet;

import gg.twinhead.hatb.CollisionFace;
import gg.twinhead.hatb.Main;
import gg.twinhead.hatb.Util;
import gg.twinhead.hatb.effect.Effect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Component extends JComponent implements KeyListener, MouseListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, infoPressed, mouseOnePressed, mouseTwoPressed;
    public Point mouseOnePoint, mouseTwoPoint;

    List<Effect> effects;
    Color color;
    Util util = new Util();

    Color defaultColor = Color.GRAY;
    float posX;
    float posY;
    int width;
    int height;
    String name;

    final int defaultWidth, defaultHeight;

    public Component(String name, float x, float y, int sizeX, int sizeY){
        this.posX = x;
        this.posY = y;
        this.width = sizeX;
        this.defaultWidth = width;
        this.height = sizeY;
        this.defaultHeight = height;
        this.color = defaultColor;
        this.name = name;
        setEffects();
    }

    public void setEffects(){
        this.effects = new ArrayList<>();
    }

    public List<String> getOutputList(){
        List<String> list = new ArrayList<>();
        list.add("X: " + posX + " Y: " + posY);
        list.add("W: " + width + " H: "+ height);
        return list;
     }

    public List<Effect> getEffects(){
        return effects;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }
    public void update(){
        if(!effects.isEmpty())
            for (Effect e: effects) {
                e.tick((int) posX, (int) posY);
            }
    }

    public void draw(Graphics2D g){
        if(!effects.isEmpty())
            for (Effect e: effects) {
                e.display(g);
            }

        g.setColor(getColor());
        g.fillRect((int) getPosX(), (int) getPosY(), getWidth(), getHeight());

    }

    public Line2D getLine(CollisionFace face){
        return switch (face){
            case NORTH -> new Line2D.Float(posX + 2, posY - 1, posX + width -2, posY - 1);
            case SOUTH -> new Line2D.Float(posX + 2, posY + height + 1, posX + width -2, posY + height + 1);
            case EAST -> new Line2D.Float(posX + width, posY, posX + width, posY + height - 2);
            case WEST -> new Line2D.Float(posX - 1 , posY, posX -1, posY + height - 2);
        };
    }

    public Rectangle getRectangle(){
        return new Rectangle((int) posX, (int) posY, width,height);
    }

    public void remove(){
        Main.getComponentList().remove(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_F3){
            infoPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_F3){
            infoPressed = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getButton());

        if(e.getButton() == 1){
            mouseOnePressed = true;
            mouseOnePoint = e.getPoint();
        }

        if(e.getButton() == 2){
            mouseTwoPressed = true;
            mouseTwoPoint = e.getPoint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
