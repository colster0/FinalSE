package colunga;

import javax.swing.ImageIcon;
import java.util.LinkedList;

public class Type_C_GameObject extends GameObject {
//initialize images
    public Type_C_GameObject(int x, int y) {
        super(x, y);
        imageList = new LinkedList<>();
        //list with images 
        imageList.add(new ImageIcon("images/Type_C_Left.png"));
        imageList.add(new ImageIcon("images/Type_C_Right.png"));
    }

    @Override
    //check if going left/right move it if not
    public void move(Canvas c) {
        if (getDirection() == Direction.LEFT) {
            setX(getX() - getVelocity());
        } else if (getDirection() == Direction.RIGHT) {
            setX(getX() + getVelocity());
        }
    }

    @Override
    // change image based on direction
    public void setImage() {
        if (getDirection() == Direction.LEFT) {
            currentImage = 0;
        } else if (getDirection() == Direction.RIGHT) {
            currentImage = 1;
        }
    }
}
