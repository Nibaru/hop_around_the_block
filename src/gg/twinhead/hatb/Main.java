package gg.twinhead.hatb;

import gg.twinhead.hatb.componet.Component;
import gg.twinhead.hatb.componet.GameHolder;
import gg.twinhead.hatb.game.GamePanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Component> componentList = new ArrayList<>();
    public static List<Component> getComponentList(){
        return componentList;
    }

    public static KeyHandler keyHandler = new KeyHandler();
    public static KeyHandler getKeyHandler(){return keyHandler;}

    public static GameHolder holder = new GameHolder();

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Hop Around the Block");

        GamePanel game = new GamePanel();


        window.add(game);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        game.startGameThread();

    }
}