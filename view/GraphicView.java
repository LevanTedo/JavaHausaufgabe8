package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.World;

/**
 * A graphical view of the world.
 */
public class GraphicView extends JPanel implements View {
	
	/** The view's width. */
	private final int WIDTH;
	/** The view's height. */
	private final int HEIGHT;
	
	private Dimension fieldDimension;
	
	public GraphicView(int width, int height, Dimension fieldDimension) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.fieldDimension = fieldDimension;
		this.bg = new Rectangle(WIDTH, HEIGHT);
	}
	
	/** The background rectangle. */
	private final Rectangle bg;
	/** The rectangle we're moving. */
	private final Rectangle player = new Rectangle(1, 1);
	
	
	public static ArrayList<Rectangle> laby = new ArrayList<Rectangle>();
	public void labyrinth() {
		laby.add(new Rectangle(50, 50, 50, 650));
		laby.add(new Rectangle(50, 100, 600, 50));
		laby.add(new Rectangle(150, 150, 50, 600));
		laby.add(new Rectangle(50, 250, 50, 650));
		laby.add(new Rectangle(150, 350, 50, 450));
		laby.add(new Rectangle(650, 250, 350, 50));
		laby.add(new Rectangle(150, 400, 350, 50));
		laby.add(new Rectangle(550, 400, 100, 50));
		laby.add(new Rectangle(350, 400, 150, 50));
		laby.add(new Rectangle(250, 450, 250, 50));
		laby.add(new Rectangle(450, 450, 250, 50));
		laby.add(new Rectangle(450, 650, 50, 300));
		laby.add(new Rectangle(300, 600, 100, 150));
	}
	
	
	
	/**
	 * Creates a new instance.
	 */
	@Override
	public void paint(Graphics g) {
		labyrinth();
		g.setColor(Color.GREEN);
		g.fillRect(bg.x, bg.y, bg.width, bg.height);
		// Paint player
		g.setColor(Color.BLUE);
		g.fillRect(player.x, player.y, player.width, player.height);
		
		// beyond this point is the map coloring
		g.setColor(Color.RED);
		g.fillRect(700, 700, 50, 50);
		
		g.drawString("FINISH", 700, 700);
		
		for(int i = 0;i<laby.size();i++) {
			g.setColor(Color.BLACK);
			g.fillRect(laby.get(i).x,laby.get(i).y, laby.get(i).height, laby.get(i).width);
		}
	}

	@Override
	public void update(World world) {
		
		// Update players size and location
		player.setSize(fieldDimension);
		player.setLocation((int)
				(world.getPlayerX() * fieldDimension.width),
				(int) (world.getPlayerY() * fieldDimension.height));
		repaint();
	}
	
}
