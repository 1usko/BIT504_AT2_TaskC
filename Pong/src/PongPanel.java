import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;

public class PongPanel extends JPanel implements ActionListener, KeyListener {

	 private final static Color BACKGROUND_COLOUR = Color.BLACK;
	 private final static int TIMER_DELAY = 5;
	 private final static int BALL_MOVEMENT_SPEED = 2;
	 
	 Ball ball;
	 Paddle paddle1, paddle2;
	 
	 GameState gameState = GameState.INITIALISING;
	
	public PongPanel() {
        setBackground(BACKGROUND_COLOUR);
        Timer timer = new Timer(TIMER_DELAY, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }
	
	public void createObjects() {
	       ball = new Ball(getWidth(), getHeight());
	       paddle1 = new Paddle(Player.One, getWidth(), getHeight());
	       paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
	}
	
	private void moveObject(Sprite obj) {
		obj.setXPosition(obj.getXPosition() + obj.getXVelocity(), getWidth());
		obj.setYPosition(obj.getYPosition() + obj.getYVelocity(), getHeight());
	}
	
	private void checkWallBounce() {
       if(ball.getXPosition() <= 0) {
           // Hit left side of screen
           ball.setXVelocity(-ball.getXVelocity());
    	   resetBall();
       } else if(ball.getXPosition() >= getWidth() - ball.getWidth()) {
           // Hit right side of screen
           ball.setXVelocity(-ball.getXVelocity());
    	   resetBall();
       }
       if(ball.getYPosition() <= 0 || ball.getYPosition() >= getHeight() - ball.getHeight()) {
           // Hit top or bottom of screen
           ball.setYVelocity(-ball.getYVelocity());
       }
	}
	
	private void resetBall() {
		ball.resetToInitialPosition();
	}
 	
	private void update() {
	      switch(gameState) {
          case INITIALISING: {
              createObjects();
              ball.setXVelocity(BALL_MOVEMENT_SPEED);
              ball.setYVelocity(BALL_MOVEMENT_SPEED);
              gameState = GameState.PLAYING;
              break;
           }
          case PLAYING: {
        	  moveObject(paddle1);
        	  moveObject(paddle2);
        	  moveObject(ball);
              checkWallBounce();
        	  break;
          }
          case GAMEOVER: {
        	  break;
          }
      }
	}
	
	private void paintDottedLine(Graphics g) {
	      Graphics2D g2d = (Graphics2D) g.create();
	         Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	         g2d.setStroke(dashed);
	         g2d.setPaint(Color.WHITE);
	         g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
	         g2d.dispose();
	}
	
	private void paintSprite(Graphics g, Sprite sprite) {
	      g.setColor(sprite.getColour());
	      g.fillRect(sprite.getXPosition(), sprite.getYPosition(), sprite.getWidth(), sprite.getHeight());
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    paintDottedLine(g);
	    if(gameState != GameState.INITIALISING) {
	    	paintSprite(g, ball);
	    	paintSprite(g, paddle1);
	    	paintSprite(g, paddle2);
	    }
	}
	
	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_UP) {
            paddle2.setYVelocity(-1);
       } else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
            paddle2.setYVelocity(1);
       }
       if(event.getKeyCode() == KeyEvent.VK_W) {
            paddle1.setYVelocity(-1);
       } else if(event.getKeyCode() == KeyEvent.VK_S) {
            paddle1.setYVelocity(1);
       }
	}

	@Override
	public void keyReleased(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
            paddle2.setYVelocity(0);
        }
        if(event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) {
            paddle1.setYVelocity(0);
        }
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		update();
		repaint();
		
	}
}
