package gg.twinhead.hatb.componet;

import gg.twinhead.hatb.Collision;
import gg.twinhead.hatb.Face;
import gg.twinhead.hatb.Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollisionComponent extends Component{


    Collision collision = new Collision();

    public final Set<Face> collidingFaces = new HashSet<>();
    public final List<CollisionComponent> collidingWith = new ArrayList<>();

    public boolean gravity = true;

    public CollisionComponent(int x, int y, int sizeX, int sizeY) {
        super(x, y, sizeX, sizeY);
    }

    public CollisionComponent(int x, int y, int sizeX, int sizeY, boolean gravity) {
        super(x, y, sizeX, sizeY);
    }


    public boolean isCollidingWith(CollisionComponent comp){
        return collision.rectIntersect(this, comp);
    }

    public HashSet<Face> getCollidingFaces(CollisionComponent comp){
        HashSet<Face> list = new HashSet<>();
        if(collision.rectPointCollision(getPosX() + getWidth() / 2, getPosY() + getHeight() + 1, comp)){
            list.add(Face.DOWN);
        }
        if(collision.rectPointCollision(getPosX() + getWidth() / 2, getPosY() - 1, comp)){
            list.add( Face.UP);
        }
        if(collision.rectPointCollision(getPosX() + getWidth() + 1, getPosY() + getHeight()/2, comp)){
            list.add( Face.RIGHT);
        }
        if(collision.rectPointCollision(getPosX() - 1, getPosY() + getHeight()/2, comp)){
            list.add( Face.LEFT);
        }
        return list;
    }

    public List<CollisionComponent> getCollidingWith(){
        List<CollisionComponent> list = new ArrayList<>();
        for (Component c : Main.componentList) {
            if(c instanceof CollisionComponent cc)
                if(this.isCollidingWith(cc)){
                    list.add(cc);
                }
        }
        return list;
    }

    public void collision(){
        collidingFaces.clear();
        for(CollisionComponent c: getCollidingWith()){
            collidingFaces.addAll(getCollidingFaces(c));
        }
    }

    @Override
    public void update(){
    }
}
