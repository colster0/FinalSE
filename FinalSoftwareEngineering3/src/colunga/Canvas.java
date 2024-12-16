package colunga;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;


//Make a canvas for the shapes 
public class Canvas extends JComponent implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;
//Jframe for GUI and timer 
    private JFrame frame;
    private Timer gameLoopTimer;
    //list for the objects
    private List<GameObject> gameObjectList;
    private int currentControlledIndex;

    public Canvas() {
       //linked list
        gameObjectList = new LinkedList<>();
        //-1 so that no object is controlled 
        currentControlledIndex = -1;

//design for canvas
        frame = new JFrame("Game Canvas");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);

//25 miliseconds to update game 
        gameLoopTimer = new Timer(25, this);
        gameLoopTimer.start();

        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
        setFocusable(true);
    }
//new game
    public synchronized void addGameObject(GameObject gameObject) {
        gameObjectList.add(gameObject);

    

        if (currentControlledIndex == -1 && gameObject instanceof Type_D_GameObject) {
            currentControlledIndex = gameObjectList.size() - 1;
        }
    }
//will render cnavas
    @Override
    public synchronized void paint(Graphics g) {
    	
    	//loop to go through all objects to renderr
        for (int i = 0; i < gameObjectList.size(); i++) {
            GameObject gameObject = gameObjectList.get(i);

            gameObject.draw(this, g);

            if (i == currentControlledIndex) {
                int x = gameObject.getX();
                int y = gameObject.getY();
                int width = gameObject.getCurrentImage().getIconWidth();
                int height = gameObject.getCurrentImage().getIconHeight();

                g.setColor(java.awt.Color.RED);
                g.drawRect(x - 1, y - 1, width + 2, height + 2); 
            }
        }
    }


    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        for (int i = 0; i < gameObjectList.size(); i++) {
            GameObject gameObject = gameObjectList.get(i);
//move objects
            if (i == currentControlledIndex) {
                gameObject.move(this);
                gameObject.setImage();
            } else {

                gameObject.move(this);
                gameObject.setImage();
            }
//calls collison
            handleCollision(gameObject);
        }
        //redraw
        repaint();
    }

//collision logic, nothing bounce out
    private void handleCollision(GameObject gameObject) {
        int x = gameObject.getX();
        int y = gameObject.getY();
        int direction = gameObject.getDirection();
        int canvasWidth = getWidth();
        int canvasHeight = getHeight();

        if (direction == Direction.LEFT && x <= 0) {
            gameObject.setDirection(Direction.RIGHT);
        } else if (direction == Direction.RIGHT && x + gameObject.getCurrentImage().getIconWidth() >= canvasWidth) {
            gameObject.setDirection(Direction.LEFT);
        } else if (direction == Direction.UP && y <= 0) {
            gameObject.setDirection(Direction.DOWN);
        } else if (direction == Direction.DOWN && y + gameObject.getCurrentImage().getIconHeight() >= canvasHeight) {
            gameObject.setDirection(Direction.UP);
        }
    }

//tab button allows for the list of objetcs to be cycled
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_TAB) {

            if (!gameObjectList.isEmpty()) {
                currentControlledIndex = (currentControlledIndex + 1) % gameObjectList.size();
                System.out.println("Switched control to object at index: " + currentControlledIndex);


                for (int i = 0; i < gameObjectList.size(); i++) {
                    if (i != currentControlledIndex) {
                        GameObject nonControlledObject = gameObjectList.get(i);
                        //dirctionNone is responsible for moving the objects when they are no longer being controlled
                        if (nonControlledObject.getDirection() == Direction.NONE) {
                            nonControlledObject.setDirection(getDefaultDirection(nonControlledObject));
                        }
                    }
                }
            }
            //when its not at -1, so 0,1,2,3 are the different objects.
        } else if (currentControlledIndex != -1) {

            GameObject controlledObject = gameObjectList.get(currentControlledIndex);
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                controlledObject.setDirection(Direction.UP);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                controlledObject.setDirection(Direction.DOWN);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                controlledObject.setDirection(Direction.LEFT);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                controlledObject.setDirection(Direction.RIGHT);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	//when its not at -1, so 0,1,2,3 are the different objects.
        if (currentControlledIndex != -1) {

            GameObject controlledObject = gameObjectList.get(currentControlledIndex);
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN ||
                e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                controlledObject.setDirection(Direction.NONE);
                //when released it will mak the object go back to the default direction of directionNONE
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}


    private int getDefaultDirection(GameObject gameObject) {
        if (gameObject instanceof Type_A_GameObject) {
            return Direction.UP;
        } else if (gameObject instanceof Type_B_GameObject) {
            return Direction.UP; 
            //defaults to up after not being moved
        } else if (gameObject instanceof Type_C_GameObject) {
            return Direction.LEFT;//automatically moves left
        } else if (gameObject instanceof Type_D_GameObject) {
            return Direction.NONE; //does not move on its own
        }
        return Direction.NONE; 
    }
}

