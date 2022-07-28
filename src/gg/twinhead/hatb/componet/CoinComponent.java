package gg.twinhead.hatb.componet;

import gg.twinhead.hatb.Main;
import gg.twinhead.hatb.effect.Effect;

import java.awt.*;

public class CoinComponent extends Component {

	public static final float DIAMETER = 30;

	private float displayWidth, displayHeight;
	private boolean expanding;

	Color defaultColor = Color.YELLOW;
	Color color = Color.YELLOW;

	long lastUpdate;

	public CoinComponent(float x, float y) {
		super("coin", x, y, (int) DIAMETER, (int) DIAMETER);
		displayWidth = width;
		lastUpdate = System.currentTimeMillis();
	}


	@Override
	public void update(){
		super.update();

		for (Component c: Main.getComponentList()) {
			if(c instanceof Player player){
				if(player.isCollidingWith(this)){
					player.coins++;
					this.remove();
				}
			}
		}



		if (System.currentTimeMillis() - lastUpdate <= 20) {
			return;
		}

		if(!expanding){
			displayWidth--;
			if(displayWidth <= 1){
				expanding = true;
			}
		} else {
			displayWidth++;
			if(displayWidth == width)
				expanding = false;
		}
		lastUpdate = System.currentTimeMillis();


	}


	public void draw(Graphics2D g){
		if(!effects.isEmpty())
			for (Effect e: effects) {
				e.display(g);
			}

		g.setColor(color);
		//g.fillOval((int) (getPosX() - displayWidth / 2), (int) getPosY(), (int) displayWidth, getHeight());
		g.fillArc((int) (getPosX() - displayWidth / 2) + width / 2 , (int) getPosY(), (int) displayWidth, getHeight(), 270, 180);
		g.setColor(color.darker());
		g.fillArc((int) (getPosX() - displayWidth / 2)  + width / 2, (int) getPosY(), (int) displayWidth, getHeight(), 270, -180);


		if(displayWidth > width - 10){
			g.setColor(Color.lightGray);
			g.fillOval((int) (posX + 19), (int) (posY + 5), 6, 6);



			g.setColor(Color.white);
			g.fillOval((int) (posX + 19), (int) (posY + 5), 5, 5);
		}

		if(displayWidth > width - 5){
			g.setColor(Color.lightGray);
			g.fillOval((int) (posX + 3), (int) (posY + 17), 6, 6);

			g.setColor(Color.white);
			g.fillOval((int) (posX + 3), (int) (posY + 17), 5, 5);

		}


	}
}
