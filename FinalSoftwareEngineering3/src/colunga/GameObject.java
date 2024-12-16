package colunga;

import java.awt.Component;
import java.awt.Graphics;
import java.util.List;
import javax.swing.Icon;

public abstract class GameObject {
  
// x and y
	//velocity and direction
    private int x;
    private int y;
    private int velocity;
    private int direction;

//image lists, ill be seperate for each image
    protected List<Icon> imageList;
    protected int currentImage;
//initialize gameobject and make inital x,y, veloc, and image 0
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        velocity = 0;
        currentImage = 0;
    }
//draws canvas
    public void draw(Component c, Graphics g) {
        imageList.get(currentImage).paintIcon(c, g, x, y);
    }

   //setters and getters

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Icon getCurrentImage() {
        return imageList.get(currentImage);
    }

  
    public abstract void move(Canvas c);

    public abstract void setImage();
}
