package colunga;

import javax.swing.ImageIcon;
import java.util.LinkedList;


//inherits from game object just lkike object A
public class Type_B_GameObject extends GameObject {

    public Type_B_GameObject(int x, int y) {
        super(x, y);
        imageList = new LinkedList<>();
        //initializes images for the animation
        imageList.add(new ImageIcon("images/Type_B_Up.png"));    
        imageList.add(new ImageIcon("images/Type_B_Down.png"));  
        imageList.add(new ImageIcon("images/Type_B_Left.png"));  
        imageList.add(new ImageIcon("images/Type_B_Right.png")); 
        setDirection(Direction.UP); //will initially go up
    }

    @Override
    //gets canvas boundaries
    public void move(Canvas c) {
        int canvasWidth = c.getWidth();
        int canvasHeight = c.getHeight();

        //get current position
        int x = getX();
        int y = getY();
        int velocity = getVelocity();
//move up by decreasing y by velocity
        //disapears going up
        switch (getDirection()) {
            case Direction.UP -> {
                setY(y - velocity);
                if (getY() <= 0) { 
                    setY(0); 
                    setDirection(Direction.RIGHT);
                }
            }
            //move right by doing x+ velocity
            case Direction.RIGHT -> {
                setX(x + velocity);
                if (getX() + getCurrentImage().getIconWidth() >= canvasWidth) { 
                    setX(canvasWidth - getCurrentImage().getIconWidth()); 
                    setDirection(Direction.DOWN);
                }
            }
            //move down by doing y+ velocity
            case Direction.DOWN -> {
                setY(y + velocity);
                if (getY() + getCurrentImage().getIconHeight() >= canvasHeight) { 
                    setY(canvasHeight - getCurrentImage().getIconHeight()); 
                    setDirection(Direction.LEFT);
                }
            }
            //move left by doing x-velocity
            case Direction.LEFT -> {
                setX(x - velocity);
                if (getX() <= 0) { 
                    setX(0); 
                    setDirection(Direction.UP);
                }
            }
        }
    }
//images by direction
    @Override
    public void setImage() {
        
        currentImage = switch (getDirection()) {
            case Direction.UP -> 0;
            case Direction.DOWN -> 1;
            case Direction.LEFT -> 2;
            case Direction.RIGHT -> 3;
            default -> currentImage;
        };
    }
}

