package colunga;

import javax.swing.ImageIcon;
import java.util.LinkedList;
//inherits from GAmeObject class
public class Type_A_GameObject extends GameObject {
// set initial parameters
    public Type_A_GameObject(int x, int y) {
        super(x, y);
        setDirection(Direction.UP);  
        setVelocity(3);              
        imageList = new LinkedList<>();
        //images added to show animation f direction change
        imageList.add(new ImageIcon("images/Type_A_Up.png"));    
        imageList.add(new ImageIcon("images/Type_A_Down.png"));  
    }

    @Override
    //how object a will move
    public void move(Canvas c) {
    	//uses height to make sure object is in bounds
        int canvasHeight = c.getHeight();

//works with collisions to change directions andd velocity
        switch (getDirection()) {
            case Direction.UP -> {
                setY(getY() - getVelocity());
                if (getY() <= 0) { 

                    setY(0); 
                    setDirection(Direction.DOWN); 
                }
            }
            //increase y by the velocity
            case Direction.DOWN -> {
                setY(getY() + getVelocity());
                if (getY() + getCurrentImage().getIconHeight() >= canvasHeight) { 
                    setY(canvasHeight - getCurrentImage().getIconHeight());
                    setDirection(Direction.UP); 
                }
            }
        }
    }
//change images based off directon
    @Override
    public void setImage() {
       
        currentImage = switch (getDirection()) {
            case Direction.UP -> 0;
            case Direction.DOWN -> 1;
            default -> currentImage; 
        };
    }
}
