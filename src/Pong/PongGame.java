package Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/*****************************************************
 *    Code from lecturer
 *    Title:    PongGame.java, various lines as indicated
 *    Author: John Brosnan
 *    Site owner/sponsor:  NA
 *    Date: 14/11/2020
 *    Code version:  NA
 *    Availability:  NA

 *    Modified:  Code refactored, design altered
 *****************************************************/

public class PongGame extends JPanel implements ActionListener, KeyListener {
    //JB - you won't need a pongGame attribute here now given the way I've re-arranged things
    //public static PongGame pongGame; //JB - all attributes here should be private too (can leave the constants public if you wish so they can be accessed directly within Paddle class)

    public final static int WIDTH=900, HEIGHT=500; //JB - make these constants here (sorts out a null-pointer exception that arises in Paddle class)

    public Paddle LeftPaddle; //creating variable called LeftPaddle from instantiated Paddle Class
    public Paddle RightPaddle;
    public Puck puck;
    public boolean a,z,k,m; //this represents the controls for the game
                            //a and z allow you to move LeftPaddle up and down
                            // k and m allow you to move RightPaddle up and down


    public PongGame()
    {

        Timer timer= new Timer(30,this); // adding a timer to make objects move on screen (The more modern way to the thread class)

        JFrame Window = new JFrame(); //makes a new JFrame called window (Acts as container)

        Window.setTitle("Pong Game"); // sets the title of the Window

        Window.setSize(WIDTH, HEIGHT); // sets width and height of that defined above

        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows window to be closed


        Window.setResizable(false); //enforces the same size window

        //Renderer renderer = new Renderer(); //creates a new renderer object from the Renderer class - JB, this needs to be removed so that the actionPerformed() references the "global"
                                              //renderer reference (otherwise an exception gets thrown)

        //renderer = new Renderer();

        setPreferredSize(new Dimension(WIDTH,HEIGHT)); //JB - need to do this to ensure the panel that will display the game is the dimensions you are looking for
                                                       //because otherwise some of the panel will be missing due to the fact that the JFrame borders "eats up" some
                                                       //of the pixels allocated
        Window.addKeyListener(this);
        Window.add(this); //Adds the game panel to the window


        Window.setVisible(true);//sets everything to be visible, if not you wont see anything (JB moved this code as call to setVisible() should come after everything else in GUI construction)

        BeginGame();

        timer.start();

        Window.pack(); //needed to mark sure that the GUI will appear as desired with the panels "preferred size"
                       //otherwise some of the right paddle will be missing and also the bottom of the panel
    }

    public static void main(String[] args) //main method
    {
        PongGame pongGame = new PongGame();//creates a new instance of the pong game

    }

    public void render(Graphics g)
    {
        //JB - just ensure you use the "How to Cite Source Code" document to put in this reference in the standard way (as I did above)

         /*****************************************************
         *    Obtained knowledge of graphics classes here: https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html and https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html
         *    Title: lines 97 and 98 [.setColor keyword and .fillRect]
         *    Author: Oracle
         *    Site owner/sponsor:  NA
         *    Date: 13/11/2020
         *    Code version:  NA
         *    Availability:  NA
          *        Modified:  obtained understanding of class API, code implemented from there
        // *****************************************************/

        g.setColor(Color.BLACK); //predefined setColor function to set colour of input argument g to black
        g.fillRect(0, 0, PongGame.WIDTH, PongGame.HEIGHT); //x and y are the coordinates of the point of the rectangle, where as the width and height are that defined in


        RightPaddle.render(g);
        LeftPaddle.render(g);
        puck.render(g);


    }

    public void BeginGame()
    {
        LeftPaddle = new Paddle("Left");
        RightPaddle = new Paddle("Right");
        puck = new Puck(this);
    }


    public void actionPerformed(ActionEvent e) // needed for when a user interacts with the game
    {
        repaint();
       refresh();
    }


    // public void checkCollisions{}


    //JB - copied from the original Renderer class and modified slightly

    public void paintComponent(Graphics g)
    { // referencing to information read here: https://web.stanford.edu/class/archive/cs/cs108/cs108.1092/handouts/27PaintRepaint.pdf
        super.paintComponent(g);

        render(g);
    }
    //End of [non-original or refactored] code

    public void refresh()
    {

        if(k) //PaddlePaddle up
          {
        RightPaddle.movePaddle(true); //passes movePaddle in Paddle.java a boolean value of true, meaning "UP"
          }
        if(m)//RightPaddle down
        {
            RightPaddle.movePaddle(false);//passes movePaddle in Paddle.java a boolean value of false, meaning "NOT UP", therefore "DOWN"
        }
        if(a) //LeftPaddle up
        {
            LeftPaddle.movePaddle(true);
        }
        if(z) //LeftPaddle down
        {
            LeftPaddle.movePaddle(false);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { //key listener for when a user uses the virtual keyboard


        if (e.getKeyCode() == KeyEvent.VK_K) //When a user "Presses" the key "K"
        {
            k = true; // passes true to if statement and therefore the if statement executes
        }
        else if (e.getKeyCode() == KeyEvent.VK_M)
        {
            m = true;
        }
            else if (e.getKeyCode() == KeyEvent.VK_A)
            {
                a = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_Z)
            {
                z = true;
            }

    }

    @Override
    public void keyReleased(KeyEvent e) { //keylistener for releasing a key

        if (e.getKeyCode() == KeyEvent.VK_K)
        {
            k = false; //passes "if statement" a false boolean
        }
        else if (e.getKeyCode() == KeyEvent.VK_M)
        {
            m = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_A)
        {
            a = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_Z)
        {
            z = false;
        }


    }



}
