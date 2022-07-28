package gg.twinhead.hatb;

import gg.twinhead.hatb.componet.CollisionComponent;

import java.awt.*;
import java.util.EnumSet;

public enum CollisionFace {
	NORTH,
	//NORTH_EAST,
	EAST,
	//SOUTH_EAST,
	SOUTH,
	//SOUTH_WEST,
	WEST;
	//NORTH_WEST;

	public EnumSet<CollisionFace> getBasic(){
		return EnumSet.of(NORTH, EAST, WEST, SOUTH);
	}

	public Point get(CollisionComponent component){
		return switch (this){
			case NORTH -> new Point((int) (component.getPosX() + component.getWidth() / 2 ), (int) (component.getPosY()));
			//case NORTH_EAST -> new Point((int) (component.getPosX() + component.getWidth()), (int) (component.getPosY()));
			case EAST -> new Point((int) (component.getPosX() + component.getWidth()), (int) (component.getPosY() + component.getHeight() / 2));
			//case SOUTH_EAST -> new Point((int) (component.getPosX() + component.getWidth()), (int) (component.getPosY() + component.getHeight()));
			case SOUTH -> new Point((int) (component.getPosX() + component.getWidth() / 2), (int) (component.getPosY() + component.getHeight()));
			//case SOUTH_WEST -> new Point((int) (component.getPosX()), (int) (component.getPosY() + component.getHeight()));
			case WEST -> new Point((int) (component.getPosX()), (int) (component.getPosY() + component.getHeight() / 2));
			//case NORTH_WEST -> new Point((int) (component.getPosX()), (int) (component.getPosY()));
		};
	}

}
