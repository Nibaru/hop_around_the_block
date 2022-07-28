package gg.twinhead.hatb.game;

import gg.twinhead.hatb.componet.Component;
import gg.twinhead.hatb.Main;
import gg.twinhead.hatb.componet.InfoView;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final int screenWidth = 1000;
    final int screenHeight = 700;



    Thread gameThread;



    public int tickCount = 0;

    double FPS = 120.0;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        //this.setDoubleBuffered(true);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setBackground(Color.GREEN);
        panel.setFocusable(true);
        this.add(panel);

        Main.componentList.addAll(Level.ONE.getLevel());
        Main.getComponentList().add(new InfoView());

        for(Component c: Main.componentList){
            this.addKeyListener(c);
            this.addMouseListener(c);
        }


    }

    public void startGameThread(){
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }


    public void update(){
        for (int i = 0; i < Main.getComponentList().size(); i++) {
            Main.getComponentList().get(i).update();
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
                update();
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

        for (int i = 0; i < Main.getComponentList().size(); i++) {
            Main.getComponentList().get(i).draw(g2);
        }

        g2.dispose();
    }
}
