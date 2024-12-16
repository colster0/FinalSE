package colunga;

import javax.swing.ImageIcon;
import java.util.LinkedList;

public class Type_D_GameObject extends GameObject {

    public Type_D_GameObject(int x, int y) {
        super(x, y);
        setDirection(Direction.NONE); 
        setVelocity(3);               
        //initalize images for animation
        imageList = new LinkedList<>();
        imageList.add(new ImageIcon("images/Type_D_Up.png"));
        imageList.add(new ImageIcon("images/Type_D_Down.png"));
        imageList.add(new ImageIcon("images/Type_D_Left.png"));
        imageList.add(new ImageIcon("images/Type_D_Right.png"));
    }

    @Override
    public void move(Canvas c) {
      //switch statement to work with direction and velocity
        switch (getDirection()) {
            case Direction.UP -> setY(getY() - getVelocity());
            case Direction.DOWN -> setY(getY() + getVelocity());
            case Direction.LEFT -> setX(getX() - getVelocity());
            case Direction.RIGHT -> setX(getX() + getVelocity());
            case Direction.NONE -> {
                //if not direction then it is none
            }
        }
    }

    @Override
    public void setImage() {
        //images change base off direction
        currentImage = switch (getDirection()) {
            case Direction.UP -> 0;
            case Direction.DOWN -> 1;
            case Direction.LEFT -> 2;
            case Direction.RIGHT -> 3;
            default -> currentImage; 
            //if none then it is none
        };
    }
}
