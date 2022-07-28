package gg.twinhead.hatb.game;

import gg.twinhead.hatb.componet.CollisionComponent;
import gg.twinhead.hatb.componet.Component;
import gg.twinhead.hatb.KeyHandler;
import gg.twinhead.hatb.Main;
import gg.twinhead.hatb.componet.MovingPlatformComponent;
import gg.twinhead.hatb.componet.PlayerComponent;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final int screenWidth = 1000;
    final int screenHeight = 700;



    Thread gameThread;

    PlayerComponent player = new PlayerComponent(30, 30, 25, Main.getKeyHandler());

    public int tickCount = 0;

    double FPS = 120.0;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.addKeyListener(Main.getKeyHandler());
        this.setFocusable(true);
        //this.setDoubleBuffered(true);



        Main.componentList.addAll(Level.ONE.getLevel());
    }

    public void startGameThread(){
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }


    public void update(){


        player.update();
        for (Component c: Main.getComponentList()) {
            c.update();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / FPS;

        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        while (gameThread.isAlive()) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;

            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                shouldRender = true;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            if (shouldRender) {
                frames++;
                update();
                repaint();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println(ticks + " ticks, " + frames + " frames");
                frames = 0;
                ticks = 0;
            }
        }
    }

    public void tick() {
        tickCount++;
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);

        g2.setColor(Color.GREEN);

        //g2.fillRect((int) player.getPosX(), (int) player.getPosY(), player.getWidth(), player.getHeight());

        player.draw(g2);

        for (Component c: Main.componentList) {
            c.draw(g2);
        }

        g2.dispose();
    }
}
