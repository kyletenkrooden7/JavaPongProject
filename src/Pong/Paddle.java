package Pong;

import java.awt.*;

/**
 * @author Kyle Tenkrooden
 * @version 1.0
 * This Paddle class resembles the instantiable
 * "Paddle" class for adding Paddle Objects
 * to the screen.
 *
 */

public class Paddle {
    private int x, y;
    private int PaddleWidth=35;
    private int PaddleHeight=120;
    private String PaddleLeftOrRight;
    private int distance = 15;
    //All variables defined as private for encapsulation purposes
    //private -> can be accessed only through methods of current Paddle Class

    /**
     * Multi-argument constructor for Paddle Class
     * that takes 6 arguments.
     * 6 Mutators called to initialise the 6 objects called via user input.
     * @param x defines the x coordinate of the Paddle of type int
     * @param y defines the y coordinate of the Paddle of type int
     * @param PaddleWidth defines the width of the Paddle of type int
     * @param PaddleHeight defines the height of the Paddle of type int
     * @param paddleLeftOrRight variable to distinguish between the left
     *                         and right paddle of type String.
     * @param distance defines the distance the paddle moves in a set direction
     *                 (either up or down) upon a key pressed.
     */

    public Paddle(int x, int y, int PaddleWidth, int PaddleHeight, String paddleLeftOrRight, int distance) {
        setX(x);
        setY(y);
        setPaddleWidth(PaddleWidth);
        setPaddleHeight(PaddleHeight);
        setPaddleLeftOrRight(paddleLeftOrRight);
        setDistance(distance);
    }

    /*****************************************************
     *    Code from lecturer
     *    Title:    Paddle.java, various lines as indicated
     *    Author: John Brosnan
     *    Site owner/sponsor:  NA
     *    Date: 14/11/2020
     *    Code version:  NA
     *    Availability:  NA

     *    Modified:  Code refactored
     *****************************************************/


    /**
     * Single argument constructor nested with if statements
     * to distinguish between the left and right paddles
     * and sets the X and Y coordinates of both.
     * @param PaddleLeftOrRight distinguishes between left and right paddles
     *
     */
    public Paddle(String PaddleLeftOrRight)
    {
        this.PaddleLeftOrRight = PaddleLeftOrRight;

        if(getPaddleLeftOrRight().equals("Right"))
        { //JB - removed the toLowerCase() to prevent logical error
            //this.x = PongGame.pongGame.width - PaddleWidth;
            setX(PongGame.WIDTH - PaddleWidth);  //JB - sorts out null-pointer excpetion that otherwise occurs
        }
        if(getPaddleLeftOrRight().equals("Left"))
        {  //JB - as above
            //this.x = PaddleWidth;
            setX(0); //sets x coordinate of left paddle to 0 (up against window)
        }
        setY(PongGame.HEIGHT/2 -  this.PaddleHeight/2);
            //PongGame.HEIGHT/2 -> places the paddle half way down the screen in relation to the top of the paddle
           // therefore top of paddle will be in middle, but we want the the middle coordinates of the paddle to be in the
          // center.
         //We now subtract the PaddleHeight/2 to get to the middle of the paddle
        //if we subtracted this.PaddleHeight for example, we would have the bottom of the Paddle in the centre of the screen

    }


//JB - End of [non-original or refactored] code

    /**
     * Render method that uses the graphics class
     * as an argument for use of .setColor() which sets the
     * color of the paddle, in this case red and the .fillRect()
     * that takes 4 parameters, to define the area of the
     * paddle.
     * @param g name of graphics object
     */
    public void render(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(x,y,PaddleWidth,PaddleHeight);
    }

    /**
     * Method that gets passed up a boolean
     * value from the PongGame driver and performs calculations to
     * allow the Left and Right paddles to either move up or down.
     * @param checkDirection either true or false
     */
    public void movePaddle(boolean checkDirection)
    {
        boolean up   = true; //paddle can only move up or down
        int moveUp   = y - getDistance();//y here is 130 (middle of screen) and you minus the distance (10) therefore will move up 10 pixels
        int moveDown = y + getDistance();

        if(checkDirection == up) //if you move the paddle up
        {
            if (moveUp > 0)  //top of screen
            {
                y = moveUp;

            }
            else
                y = 0; // if it is not positive it'll still be able to reach the top of the screen

        }
        else if (checkDirection != up)//if you move the paddle down
        {
            if(moveDown + PaddleHeight < PongGame.HEIGHT) //bottom of screen
            {
                y = moveDown;
            }
            else
            {
                y = PongGame.HEIGHT - PaddleHeight;
            }

        }

    }


    /**
     * Method to get the y coordinate of the paddle
     * @return an int value specifying the x coordinate of the paddle
     */
    public int getX() {
        return x;
    }

    /**
     * Method to set the value of the x coordinate of the paddle
     * @param x the x coordinate of the paddle
     */

    public void setX(int x) {
        this.x = x;
    }

    /**
     * Method to get the y coordinate of the paddle
     * @return an int value specifying the y coordinate of the paddle
     */
    public int getY() {
        return y;
    }

    /**
     * Method to set the value of the y coordinate of the paddle
     * @param y the y coordinate of the paddle
     */
    public void setY(int y) {
        this.y = y;
    }


    /**
     * Method to get the width of the paddle
     * @return an int value specifying the width of the paddle
     */
    public int getPaddleWidth() {
        return PaddleWidth;
    }

    /**
     * Method to set the width of the paddle
     * @param paddleWidth the width of the paddle
     */
    public void setPaddleWidth(int paddleWidth) {
        PaddleWidth = paddleWidth;
    }

    /**
     * Method to get the height of the paddle
     * @return an int value specifying the height of the paddle
     */
    public int getPaddleHeight() {
        return PaddleHeight;
    }

    /**
     * Method to set the height of the paddle
     * @param paddleHeight the height of the paddle
     */

    public void setPaddleHeight(int paddleHeight) {
        PaddleHeight = paddleHeight;
    }

    /**
     * Method to distinguish between the left and right paddle
     * @return a String value specifying whether the left or right paddle has been entered
     */
    public String getPaddleLeftOrRight() {
        return PaddleLeftOrRight;
    }

    /**
     * Method to set whether the paddle will be the left or the right
     * @param paddleLeftOrRight indicates whether the left or right paddle is being referred to
     */
    public void setPaddleLeftOrRight(String paddleLeftOrRight) {
        PaddleLeftOrRight = paddleLeftOrRight;
    }

    /**
     * Method to get the distance for the paddle to move in either
     * the upwards or downwards direction
     * @return an int value specifying the distance to move
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Method to set the distance for the paddle to move in either the upwards
     * or downwards direction
     * @param distance the distance the move in a set direction
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

}
