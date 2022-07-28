package gg.twinhead.hatb.effect;

import gg.twinhead.hatb.componet.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Trail extends Effect{
	private List<Dimension> lastLocations;

	int count = 0;
	private int trailLength, timer;
	private Player player;

	public Trail(Player player, int trailLength, int delay){
		this.player = player;
		this.trailLength = trailLength;
		this.timer = delay;
		this.lastLocations = new ArrayList<>();
	}

	@Override
	public void tick(int x, int y){
		if(count >= timer) {
			lastLocations.add(0, new Dimension(x, y));
			if (lastLocations.size() > trailLength) lastLocations.remove(lastLocations.size() - 1);
		}
		count++;
		if(count > timer) count = 0;
	}

	@Override
	public void display(Graphics2D g){
		for (int i = 0; i < lastLocations.size()-1; i++) {
			Color trailColor = new Color(245, 40, 145, Math.max((200 - (i * 20)), 0));
			g.setColor(trailColor);
			g.fillRect((int) lastLocations.get(i).getWidth() + (i + 1), (int) lastLocations.get(i).getHeight() + (i + 1), player.getWidth() - (3 * i) - 1, player.getHeight() - (3 * i) - 1);
		}
	}
}
