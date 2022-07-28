package gg.twinhead.hatb.componet;

import gg.twinhead.hatb.CollisionFace;
import gg.twinhead.hatb.Main;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CollisionComponent extends Component{

    public final Set<CollisionFace> collidingFaces = new HashSet<>();
    public final List<CollisionComponent> collidingWith = new ArrayList<>();
    public final HashMap<CollisionFace, CollisionComponent> collisionFaceCollision = new HashMap<>();

    public CollisionComponent(String name, float x, float y, int sizeX, int sizeY) {
        super(name, x, y, sizeX, sizeY);
    }

    public void getCollidingWith(){
        collidingWith.clear();
        for (Component c : Main.componentList) {
            if(c instanceof CollisionComponent cc)
                if(util.rectIntersect(this, cc)){
                    if(cc != this) collidingWith.add(cc);
                }
        }
    }

    public boolean isCollidingWith(Component c){
        return getRectangle().intersects(c.getRectangle());
    }



    @Override
    public void update(){
        super.update();
        getCollidingWith();
        getCollidingFaces();
    }

    private void getCollidingFaces() {
        collidingFaces.clear();
        collisionFaceCollision.clear();
        for (CollisionComponent c : collidingWith) {
            for(CollisionFace cf: CollisionFace.values())
                if(getLine(cf).intersects(c.getRectangle())){
                    collidingFaces.add(cf);
                    collisionFaceCollision.put(cf, c);
                }

        }
    }

    @Override
    public void draw(Graphics2D g){
        super.draw(g);
    }

    @Override
    public List<String> getOutputList(){
        List<String> list = super.getOutputList();
        list.add("Colliding #: " + collidingWith.size());

        list.add("Colliding-Faces: ");
        for (int i = 0; i < CollisionFace.values().length; i++) {
            list.add("   -" + CollisionFace.values()[i] +": " + (collisionFaceCollision.get(CollisionFace.values()[i]) == null ? "NONE" : collisionFaceCollision.get(CollisionFace.values()[i]).name));
        }
        return list;
    }
}
