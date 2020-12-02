package Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;


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
    public final static int WIDTH=900, HEIGHT=500; //JB - make these constants here (sorts out a null-pointer exception that arises in Paddle class)
    private Paddle LeftPaddle; //creating variable called LeftPaddle from instantiated Paddle Class
    private Paddle RightPaddle;
    private Puck puck;
    private boolean showMainMenu=true;
    private boolean a,z,k,m; //this represents the controls for the game
    //a and z allow you to move LeftPaddle up and down
    // k and m allow you to move RightPaddle up and down
    private String Player1="", Player2="";
    private ArrayList<String> ListOfScores = new ArrayList<String>(); //arraylist with generics(only capable of storing String type)
    private boolean showScores;
    String text = "";



    public PongGame()
    {

        /**
         * Knowledge of timer class found here: https://www.educative.io/edpresso/what-is-a-timer-class-in-java
         *  Title:    PongGame.java Timer class
         *  Author: NA
         *  Site owner/sponsor:  NA
         *  Date: 13/11/2020
         *  Code version:  NA
         *  Availability:  NA
         * Modified:  Understood syntax, code implemented from there
         */


        Timer timer= new Timer(30,this); // adding a timer to make objects move on screen (The more modern way to the thread class)
        // without the timer, objects will not move on the screen!
        JFrame Window = new JFrame(); //makes a new JFrame called window (Acts as container)

        Window.setTitle("Pong Game"); // sets the title of the Window

        Window.setSize(WIDTH, HEIGHT); // sets width and height of that defined above

        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows window to be closed

        Window.setResizable(false); //enforces the same size window

        Window.setIconImage(new ImageIcon("C:\\Users\\Kyle Tenkrooden\\IdeaProjects\\PongProject\\PongProject\\src\\Pong\\PongIconLogo.png").getImage()); //image found here: data:image/png;base64,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

        setPreferredSize(new Dimension(WIDTH,HEIGHT)); //JB - need to do this to ensure the panel that will display the game is the dimensions you are looking for
        //because otherwise some of the panel will be missing due to the fact that the JFrame borders "eats up" some
        //of the pixels allocated
        Window.addKeyListener(this);
        Window.add(this); //Adds the game panel to the window

        Window.setVisible(true);//sets everything to be visible, if not you wont see anything (JB moved this code as call to setVisible() should come after everything else in GUI construction)

        inputNames();

        BeginGame();

        timer.start();

        Window.pack(); //needed to mark sure that the GUI will appear as desired with the panels "preferred size"
        //otherwise some of the right paddle will be missing and also the bottom of the panel
    }




    public static void main(String[] args) //main method
    {
        PongGame pongGame = new PongGame();//creates a new instance of the pong game

    }

    public void render(Graphics g) {

        //JB - just ensure you use the "How to Cite Source Code" document to put in this reference in the standard way (as I did above)

        /*****************************************************
         *    Obtained knowledge of graphics classes here: https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html and https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html
         *    Title: [.setColor keyword and .fillRect]
         *    Author: Oracle
         *    Site owner/sponsor:  NA
         *    Date: 13/11/2020
         *    Code version:  NA
         *    Availability:  NA
         *    Modified:  obtained understanding of class API, code implemented from there
         // *****************************************************/

        g.setColor(Color.BLACK); //predefined setColor function to set colour of input argument g to black
        g.fillRect(0, 0, PongGame.WIDTH, PongGame.HEIGHT); //x and y are the coordinates of the point of the rectangle


        if (showMainMenu == true) {
            MainMenu(g);

        }
        if (showMainMenu == false && showScores==false) {

            RightPaddle.render(g);
            LeftPaddle.render(g);
            puck.render(g);
            puck.update();

            if(puck.isShowWinningScore())
            {
                showScores=true;

            }
        }
        if(showScores == true)
        {
            showWinningScore(g);

        }

    }

    public void BeginGame()
    {
        LeftPaddle = new Paddle("Left");
        RightPaddle = new Paddle("Right");
        puck = new Puck(this, LeftPaddle, RightPaddle,0,0);

    }

    public void actionPerformed(ActionEvent e) // needed for when a user interacts with the game
    {
        if(showMainMenu == false)
        {
            refresh();
        }

        repaint();
    }
    //JB - copied from the original Renderer class and modified slightly

    public void paintComponent(Graphics g)
    {
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
    private void inputNames()
    {
        Player1 = JOptionPane.showInputDialog("Player 1 Name: ");

        if(Player1==null)
            Player1="";

        while(Player1.length() > 6)
            Player1 = JOptionPane.showInputDialog("Name too long, please re-enter: ");

        Player2 = JOptionPane.showInputDialog("Player 2 Name: ");

        if(Player2==null)
            Player2="";

        while(Player2.length() > 6)
            Player2 = JOptionPane.showInputDialog("Name too long, please re-enter: ");

    }

    public void MainMenu(Graphics g)
    {
        //image found here: https://us.123rf.com/450wm/maralingstad/maralingstad1509/maralingstad150900079/44860773-vector-flat-white-ping-pong-icon-on-dark-background.jpg?ver=6
        ImageIcon pongImage = new ImageIcon("C:\\Users\\Kyle Tenkrooden\\IdeaProjects\\PongProject\\PongProject\\src\\Pong\\MainMenuPicture.jpg");
        pongImage.paintIcon(this,g,WIDTH/4,HEIGHT/20); //watched short youtube tutorial on this: https://www.youtube.com/watch?v=UXLOZshtC3I

        g.setColor(Color.WHITE);
        g.setFont(new Font("Calibri", 1, 40));
        g.drawString("Welcome To Pong",WIDTH/2 - 150,80);

        if(Player1 == null || Player2 == null)
        {
            Player1="";
            Player2="";
        }

        g.setColor(Color.RED);
        g.setFont(new Font("Calibri", 1, 20));
        g.drawString(Player1,WIDTH/4 - WIDTH/8,80);

        g.setColor(Color.RED);
        g.setFont(new Font("Calibri", 1, 20));
        g.drawString(Player2,(WIDTH - WIDTH/4) + WIDTH/8,80);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Calibri", 1, 30));
        g.drawString("Press Space To Play",WIDTH/2 - 120,430);

    }

    public void showWinningScore(Graphics g)
    {
        if(puck.getLeftPaddleScore() == 7)
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, PongGame.WIDTH, PongGame.HEIGHT);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman",Font.PLAIN,50));
            g.drawString("Left Player Wins!!", WIDTH/4 + 20,HEIGHT/2);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman",Font.PLAIN,35));
            g.drawString("Press Space To Play Another Game", WIDTH/5,HEIGHT/2 + 50);

        }

        else if(puck.getRightPaddleScore() == 7)
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, PongGame.WIDTH, PongGame.HEIGHT);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman",Font.PLAIN,50));
            g.drawString("Right Player Wins!!", WIDTH/4 + 20,HEIGHT/2);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman",Font.PLAIN,35));
            g.drawString("Press Space To Play Another Game", WIDTH/5,HEIGHT/2 + 50);

        }

    }

    private void DisplayListOfScores()
    {
        AddScore();

        if(puck.getRightPaddleScore() ==7 || puck.getLeftPaddleScore() == 7)
        {
            File inFile	= new File("scores_list.dat");

            try {

                if(inFile.exists())
                {
                    FileInputStream inStream = new FileInputStream(inFile);

                    ObjectInputStream objectInStream = new ObjectInputStream(inStream);

                    ArrayList<String> ListOfScores = (ArrayList<String>) objectInStream.readObject();

                    for (String str : ListOfScores)
                        text += str + "\n";

                    JOptionPane.showMessageDialog(null, "Scores: \n" + text, "List Of Scores", JOptionPane.INFORMATION_MESSAGE);

                    inStream.close();
                }

                else
                    inFile.createNewFile();

            }
            catch(FileNotFoundException fnfe){
                fnfe.printStackTrace();
                JOptionPane.showMessageDialog(null,"The File could not be found!",
                        "Problem Finding File",JOptionPane.ERROR_MESSAGE);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
                JOptionPane.showMessageDialog(null,"The File could not be read!",
                        "Problem Reading From File",JOptionPane.ERROR_MESSAGE);
            }
            catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
                JOptionPane.showMessageDialog(null,"Could not find the appropriate class!",
                        "Problem Finding the Class!",JOptionPane.ERROR_MESSAGE);
            }
            catch (ClassCastException cce) {
                cce.printStackTrace();
                JOptionPane.showMessageDialog(null,"Could not convert the object to the appropriate class!",
                        "Problem Converting Object!",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void AddScore()
    {
        File outFile = new File("scores_list.dat");

        try {
            FileOutputStream outStream = new FileOutputStream(outFile);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            if (puck.getRightPaddleScore() == 7 || puck.getLeftPaddleScore() == 7) {
                ArrayList<String> ListOfScores = new ArrayList<>();

                ListOfScores.add(Player1 + " : " + puck.getLeftPaddleScore() + " - " + Player2 + " : " + puck.getRightPaddleScore());
                objectOutStream.writeObject(ListOfScores);
                outStream.close();
            }
        }

        catch(FileNotFoundException fnfe){
            System.out.println(fnfe.getStackTrace());
            JOptionPane.showMessageDialog(null,"The File could not be found!",
                    "Problem Finding File!",JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe){
            System.out.println(ioe.getStackTrace());
            JOptionPane.showMessageDialog(null," The File could not be written!",
                    "Problem Writing to File!",JOptionPane.ERROR_MESSAGE);
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

        else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            if(showMainMenu == false || showScores == true)
                showMainMenu = true;
                showScores=false;
                inputNames();
        }

        else if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            if(showMainMenu == true || showScores == true) {
                DisplayListOfScores();
                showScores = false;
                showMainMenu = false;
            }
            BeginGame();
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

        else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            showMainMenu = true;
        }
    }

}




