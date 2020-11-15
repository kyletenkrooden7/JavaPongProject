package Pong;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle {
    private int x;
    private int y;
    private int PaddleWidth=35;
    private int PaddleHeight=120;
    private String PaddleLeftOrRight;
    private int distance = 10;

//all private for encapsulation purposes (can be accessed only through methods of current Paddle Class)
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

    public Paddle(String PaddleLeftOrRight)
    {
        this.PaddleLeftOrRight = PaddleLeftOrRight;

        if(PaddleLeftOrRight.equals("Right")){ //JB - removed the toLowerCase() to prevent logical error
            //this.x = PongGame.pongGame.width - PaddleWidth;
            this.x = PongGame.WIDTH - PaddleWidth; //JB - sorts out null-pointer excpetion that otherwise occurs
        }
        if(PaddleLeftOrRight.equals("Left")){  //JB - as above
            //this.x = PaddleWidth;
            this.x = 0; //want this paddle to be up again the edge of the window
        }
        this.y = PongGame.HEIGHT/2 -  this.PaddleHeight/2;
      //PongGame.HEIGHT/2 -> places the paddle half way down the screen in relation to the top of the paddle
      // therefore top of paddle will be in middle, but we want the the middle coordinates of the paddle to be in the
      // center.
      // We now subtract the PaddleHeight/2 to get to the middle of the paddle
        // if we subtracted this.PaddleHeight for example, we would have the bottom of the Paddle in the centre of the screen

    }


    //End of [non-original or refactored] code

    public void render(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(x,y,PaddleWidth,PaddleHeight);
    }


    public void movePaddle(boolean checkDirection)
    {
        boolean up = true; //paddle can only move up or down
        int moveUp = y - distance;
        int moveDown = y + distance;

        if(checkDirection == up) //if you move the paddle up
        {
            if (moveUp > 0) //y here is 130 (middle of screen) and you minus the distance (10) therefore will move up 10 pixels
            {
                y = moveUp;

            }
               else
                {
                y = 0; // if it is not positive it'll still be able to reach the top of the screen
                }
        }
        else if (checkDirection != up)//if you move the paddle down
        {
            if(moveDown + PaddleHeight < PongGame.HEIGHT)
            {                                //bottom of screen
                y = moveDown;
            }
                else
                {
                y = PongGame.HEIGHT - PaddleHeight;
                }

        }

    }
}
