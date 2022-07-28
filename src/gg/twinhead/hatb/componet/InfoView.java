package gg.twinhead.hatb.componet;

import gg.twinhead.hatb.KeyHandler;
import gg.twinhead.hatb.Main;
import gg.twinhead.hatb.Util;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class InfoView extends Component{

	private boolean display;
	private long lastPress = 0;
	private final Color BACKGROUND_COLOR = new Color(66, 74, 152, 200);
	private final List<String> outputList = new ArrayList();

	private Component infoComponent;

	public InfoView() {
		super("info_view", 10, 10, 300, 200);
		display = false;
	}




	@Override
	public void update(){
		if(infoPressed){
			if(System.currentTimeMillis() - lastPress > 400){
				display = !display;
				lastPress = System.currentTimeMillis();
			}
		}

		if(display)
			if(getClickedComponent() != null && getClickedComponent() != this)
				infoComponent = getClickedComponent();
	}

	public @Nullable Component getClickedComponent(){
		for (Component c: Main.getComponentList()) {
			if(mouseOnePoint != null && util.rectPointCollision(mouseOnePoint, c)){
				return c;
			}
		}
		return null;
	}


	@Override
	public void draw(Graphics2D g){
		if(display){
			if(infoComponent != null){
				g.setColor(Color.RED);
				g.drawRect((int) infoComponent.posX, (int) infoComponent.posY, infoComponent.width, infoComponent.height);
			}

			g.setColor(BACKGROUND_COLOR);
			g.fillRect((int) posX, (int) posY, width, height);
			if(infoComponent != null){
				g.setColor(Color.WHITE);
				int i = 1;
				for (String str: infoComponent.getOutputList()) {
					g.drawString(str, (int) posX + 5, (int) posY + 12 * i);
					i++;
				}
			}
		}
	}
}
