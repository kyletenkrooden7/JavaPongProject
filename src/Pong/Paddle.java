package Pong;

import java.awt.*;

public class Paddle {
    private int x;
    private int y;
    private int PaddleWidth=35;
    private int PaddleHeight=120;
    private String PaddleLeftOrRight;

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
        this.y = PongGame.HEIGHT/2 - this.PaddleHeight/2;

    }

    //End of [non-original or refactored] code

    public void render(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(x,y,PaddleWidth,PaddleHeight);
    }
}

