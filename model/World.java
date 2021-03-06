package model;

import java.awt.Rectangle;
import java.util.ArrayList;

import view.GraphicView;
import view.View;

/**
 * The world is our model. It saves the bare minimum of information required to
 * accurately reflect the state of the game. Note how this does not know
 * anything about graphics.
 */
public class World {

	public static final int DIR_RIGHT = 3;
	public static final int DIR_LEFT = 2;
	public static final int DIR_DOWN = 1;
	public static final int DIR_UP = 0;
	/** The world's width. */
	private final int width;
	/** The world's height. */
	private final int height;
	/** The player's x position in the world. */
	private int playerX = 0;
	/** The player's y position in the world. */
	private int playerY = 0;
	
	private static ArrayList<Rectangle> laby = new ArrayList<Rectangle>();
	
	/** Set of views registered to be notified of world updates. */
	private final ArrayList<View> views = new ArrayList<>();

	/**
	 * Creates a new world with the given size.t
	 */
	public World(int width, int height) {
		// Normally, we would check the arguments for proper values
		this.width = width;
		this.height = height;
	}
	
	public static ArrayList<Rectangle> labyrinth() {
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
		return laby;
	}
 
	///////////////////////////////////////////////////////////////////////////
	// Getters and Setters

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPlayerX() {
		return playerX;
	}

	public void setPlayerX(int playerX) {
		playerX = Math.max(0, playerX);
		playerX = Math.min(getWidth() - 1, playerX);
		this.playerX = playerX;
		
		updateViews();
	}

	public int getPlayerY() {
		return playerY;
	}

	public void setPlayerY(int playerY) {
		playerY = Math.max(0, playerY);
		playerY = Math.min(getHeight() - 1, playerY);
		this.playerY = playerY;
		
		updateViews();
	}

	///////////////////////////////////////////////////////////////////////////
	// Player Management
	
	/**
	 * Moves the player along the given direction.
	 * 
	 * @param direction where to move. 1 up, 2 down, 3, left, 4 right
	 */
	public void movePlayer(int direction) {	
		// The direction tells us exactly how much we need to move along
		// every direction
		
		
		setPlayerX(getPlayerX() + Direction.getDeltaX(direction));
		setPlayerY(getPlayerY() + Direction.getDeltaY(direction));
	}

	///////////////////////////////////////////////////////////////////////////
	// View Management

	/**
	 * Adds the given view of the world and updates it once. Once registered through
	 * this method, the view will receive updates whenever the world changes.
	 * 
	 * @param view the view to be registered.
	 */
	public void registerView(View view) {
		views.add(view);
		view.update(this);
	}

	/**
	 * Updates all views by calling their {@link View#update(World)} methods.
	 */
	private void updateViews() {
		for (int i = 0; i < views.size(); i++) {
			views.get(i).update(this);
		}
	}

}
