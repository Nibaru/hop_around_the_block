package gg.twinhead.hatb.game;

import gg.twinhead.hatb.Main;
import gg.twinhead.hatb.componet.CollisionComponent;
import gg.twinhead.hatb.componet.Component;
import gg.twinhead.hatb.componet.MovingPlatformComponent;

import java.util.ArrayList;
import java.util.List;

public enum Level {
    ONE,
    TWO;



    public List<Component> getLevel(){
        List<Component> list = new ArrayList<>(getBorder());

        switch (this){
            case ONE -> {
                list.add(new CollisionComponent(50, 500, 800, 50));
                list.add(new CollisionComponent(500, 650, 100, 50));
                list.add(new CollisionComponent(200, 450, 200, 50));
                list.add(new CollisionComponent(500, 200, 100, 25));
                list.add(new CollisionComponent(500, 475, 100, 25));
                list.add(new MovingPlatformComponent(100, 300, 100, 15));
            }

            case TWO -> {


            }

        }


        return list;
    }


    private List<Component> getBorder(){
        List<Component> list = new ArrayList<>();

        list.add(new CollisionComponent(0, 0, 1000, 20));
        list.add(new CollisionComponent(0, 700-20, 1000, 20));
        list.add(new CollisionComponent(1000-20, 0, 20, 700));
        list.add(new CollisionComponent(0, 0, 20, 700));

        return list;
    }

}
