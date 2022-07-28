package gg.twinhead.hatb.game;

import gg.twinhead.hatb.CoinGroup;
import gg.twinhead.hatb.Main;
import gg.twinhead.hatb.componet.*;

import java.util.ArrayList;
import java.util.List;

public enum Level {
    ONE,
    TWO;



    public List<Component> getLevel(){
        List<Component> list = new ArrayList<>(getBorder());

        switch (this){
            case ONE -> {
                list.add(new CollisionComponent("main_platform", 75, 600, 800, 50));
                list.add(new MovingPlatformComponent("moving_platform", 200, 570, 100, 10));
                //list.add(new MovingPlatformComponent("moving_platform", 100, 570, 100, 10));

                list.addAll(CoinGroup.SQUARE.getGroup(200, 500));

                list.add(new Player(30, 30, 40, Main.getKeyHandler()));
            }

            case TWO -> {


            }

        }


        return list;
    }


    private List<Component> getBorder(){
        List<Component> list = new ArrayList<>();

        list.add(new CollisionComponent("border_top", 0, 0, 1000, 20));
        list.add(new CollisionComponent("border_bottom", 0, 700-20, 1000, 20));
        list.add(new CollisionComponent("border_right", 1000-20, 0, 20, 700));
        list.add(new CollisionComponent("border_left", 0, 0, 20, 700));

        return list;
    }

}
