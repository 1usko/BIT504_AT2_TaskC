import java.awt.Color;
import java.awt.Rectangle;

public class Sprite {

	private int initialXPosition, initialYPosition;
	private int xPosition, yPosition;
	private int xVelocity, yVelocity;
	private int width, height;
	private Color colour;
	
	 // Getter methods
	 public int getXPosition() {return xPosition;}
	 public int getYPosition() {return yPosition;}
	 public int getXVelocity() {return xVelocity;}
	 public int getYVelocity() {return yVelocity;}
	 public int getWidth() {return width;}
	 public int getHeight() {return height;}
	 public Color getColour() {return colour;}
	 
	 // Setter methods
	 public void setInitialPosition(int initialX, int initialY) {
		 	initialXPosition = initialX;
		 	initialYPosition = initialY;
	 	}
	 public void resetToInitialPosition() {
		 	setXPosition(initialXPosition);
		 	setYPosition(initialYPosition);
	 	}
	 public void setXPosition(int xPosition) {
	         this.xPosition = xPosition;
	     }
	 public void setYPosition(int yPosition) {
	         this.yPosition = yPosition;
	     }
	 public void setXVelocity(int xVelocity) {
	         this.xVelocity = xVelocity;
	     }
	 public void setYVelocity(int yVelocity) {
	         this.yVelocity = yVelocity;
	     }
	 public void setWidth(int width) {
	         this.width = width;
	     }
	 public void setHeight(int height) {
	         this.height = height;
	     }
	 public void setColour(Color colour) {
		 	this.colour = colour;
	 	}
	 
	 
	 public void setXPosition(int newX, int panelWidth) {
	     xPosition = newX;
	     
	     if(xPosition < 0)
	     {
	    	 xPosition = 0;
	     }
	     else if(xPosition + width > panelWidth)
	     {
	    	 xPosition = panelWidth - width;
	     }
	 }
	 public void setYPosition(int newY, int panelHeight) {
	     yPosition = newY;
	     
	     if(yPosition < 0)
	     {
	    	 yPosition = 0;
	     }
	     else if(yPosition + height > panelHeight)
	     {
	    	 yPosition = panelHeight - height;
	     }
	 }
	 
	 public Rectangle getRectangle() {
         return new Rectangle(getXPosition(), getYPosition(), getWidth(), getHeight());
     }
}