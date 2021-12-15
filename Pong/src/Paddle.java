import java.awt.Color;

public class Paddle extends Sprite {
	
	private static final int PADDLE_WIDTH = 10;
    private static final int PADDLE_HEIGHT = 100;
    private static final int DISTANCE_FROM_EDGE = 40;
    private static final Color PADDLE_COLOUR = Color.BLACK;
    
    public Paddle(Player player, int panelWidth, int panelHeight) {
        setWidth(PADDLE_WIDTH);
        setHeight(PADDLE_HEIGHT);
        setColour(PADDLE_COLOUR);
        
        int xPos = 0;
        
        if(player == Player.One) {
        	xPos = DISTANCE_FROM_EDGE;
        }
        else if(player == Player.Two) {
        	xPos = panelWidth - DISTANCE_FROM_EDGE - getWidth();
        }
        setInitialPosition(xPos, panelHeight / 2 - (getHeight() / 2));
        resetToInitialPosition();
    }
}
