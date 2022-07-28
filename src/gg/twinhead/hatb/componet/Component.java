package gg.twinhead.hatb.componet;

import javax.swing.*;
import java.awt.*;

public class Component extends JComponent {

    Color color = Color.WHITE;
    double posX;
    double posY;
    int width;
    int height;

    public Component(int x, int y, int sizeX, int sizeY){
        this.posX = x;
        this.posY = y;
        this.width = sizeX;
        this.height = sizeY;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
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

    }

    public void draw(Graphics2D g){
        g.setColor(getColor());
        g.fillRect((int) getPosX(), (int) getPosY(), getWidth(), getHeight());
    }
}
