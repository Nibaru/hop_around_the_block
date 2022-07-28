package gg.twinhead.hatb.game;

import gg.twinhead.hatb.componet.Component;

import java.util.ArrayList;
import java.util.List;

public class GameHolder {


    private List<Component> componentList = new ArrayList<>();

    public List<Component> getComponentList(){
        return componentList;
    }

    public void updateList(List<Component> list){
        this.componentList = list;
    }
}
