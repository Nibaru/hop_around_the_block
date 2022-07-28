package gg.twinhead.hatb;

import gg.twinhead.hatb.componet.CoinComponent;

import java.util.ArrayList;
import java.util.List;

public enum CoinGroup {
	SQUARE,
	TRIANGLE;

	public List<CoinComponent> getGroup(float x, float y){
		List<CoinComponent> list = new ArrayList<>();

		switch (this){
			case SQUARE -> {
				list.add(new CoinComponent(x, y));
				list.add(new CoinComponent(x + CoinComponent.DIAMETER + 5, y));
				list.add(new CoinComponent(x, y + CoinComponent.DIAMETER + 5));
				list.add(new CoinComponent(x + CoinComponent.DIAMETER + 5, y + CoinComponent.DIAMETER + 5));
			}
		}

		return list;
	}



}
