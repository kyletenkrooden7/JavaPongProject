package Pong;

import java.awt.*;

public class Puck {
    //JB - again make sure all these attributes ebd up private for encapsulation reasons

    private int PuckWidth = 23;
    private int PuckHeight = 23;
    private int x , y; // x and y to know where the puck is
    private double PuckYDirection=2;
    private double PuckXDirection=-2;
    private PongGame pongGame;
    private Paddle LeftPaddle, RightPaddle; //we need the paddles for when a ball makes contact with the paddle
    private int PuckSpeed =4;
   // private Score score;

    public Puck(PongGame pongGame, Paddle LeftPaddle, Paddle RightPaddle)
    {
        this.pongGame = pongGame;
        this.LeftPaddle = LeftPaddle;
        this.RightPaddle = RightPaddle;

        NewPuck();
    }

    public void update() {


        this.x += PuckXDirection * PuckSpeed; // get the puck moving at a certain speed in a certain direction
        this.y += PuckYDirection * PuckSpeed;


        if (this.y + PuckHeight - PuckYDirection > pongGame.HEIGHT || this.y + PuckYDirection < 0)
        {
            if (this.PuckYDirection < 0)
            {

                this.PuckYDirection *= -1; //reverse

            }

            else if (this.PuckYDirection > 0) //going up
            {

                this.PuckYDirection *= -1;
                this.y = pongGame.HEIGHT - PuckHeight;


            }

        }

            if (PaddleHit(LeftPaddle) == "hit")
            {

                this.PuckXDirection *= -1;
                this.PuckYDirection *=  -1;
                PuckSpeed++;
            }

            else if (PaddleHit(LeftPaddle) == "No hit")
            {
               // NewPuck();
            if(this.x < 0) {
                //System.out.println("Right Paddle Score");
                NewPuck();
            }
                //iterate left paddles score


            }

            if (PaddleHit(RightPaddle) == "hit")
            {
                this.PuckXDirection *= -1;
                this.PuckYDirection *= -1;
                PuckSpeed++;


            }
            else if (PaddleHit(RightPaddle) == "No hit")
            {

                if(this.x + this.PuckWidth > PongGame.WIDTH) //goes past width of game
                    NewPuck();

                //iterate left paddle score
            }



    }

    public String PaddleHit(Paddle p)

    {
         String result ="";


          if(PuckXDirection < 0) //ball moving left
          {
              if(this.x <= this.LeftPaddle.x + this.LeftPaddle.PaddleWidth &&
              this.x + this.PuckWidth + this.y >= this.LeftPaddle.y && this.y <= this.LeftPaddle.y +
                      this.LeftPaddle.PaddleHeight)
                 result = "hit";


              else
                result = "No hit";

          }

       // return "";

        else if (PuckXDirection > 0) //ball moving to the right
        {
            if (this.x + this.PuckWidth >= this.RightPaddle.x && this.x <= this.RightPaddle.x &&
                this.y >= this.RightPaddle.y && this.y <= this.RightPaddle.y +
                this.RightPaddle.PaddleHeight)

            result = "hit";

            else
                result = "No hit";
        }

      return result;

    }






    public void NewPuck() {
        this.x = pongGame.WIDTH/2 - this.PuckWidth/2; //PUT PUCK IN CENTRE(WIDTH WISE)
        this.y = pongGame.HEIGHT/2 - this.PuckWidth/2; //PUT PUCK IN CENTRE(HEIGHT WISE)

       PuckSpeed = 4;
    }


    public void render(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillOval(x,y,PuckWidth,PuckHeight);
    }

}