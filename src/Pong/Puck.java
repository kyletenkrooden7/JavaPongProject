package Pong;

import java.awt.*;

public class Puck {
    //All private for encapsulation purposes
    private int PuckWidth = 23;
    private int PuckHeight = 23;
    private int x , y; // x and y to know where the puck is
    private double PuckYDirection=2;
    private double PuckXDirection=-2;
    private PongGame pongGame;
    private Paddle LeftPaddle, RightPaddle; //we need the paddles for when a ball makes contact with the paddle
    private int PuckSpeed =4, hit = 0;
    private int LeftPaddleScore=0, RightPaddleScore=0;


    public Puck(PongGame pongGame, Paddle LeftPaddle, Paddle RightPaddle)
    {

        this.pongGame = pongGame;
        this.LeftPaddle = LeftPaddle;
        this.RightPaddle = RightPaddle;


        NewPuck();
    }

    public void update() {

        // get the puck moving at a certain speed in a certain direction
        this.x += PuckXDirection * PuckSpeed;
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
                hit++;
                System.out.println(hit);
                this.PuckXDirection *= -1;
                this.PuckYDirection *= ((Math.random()*-1) +0.5);

                if(hit==5)
                {
                    this.PuckYDirection = -2;
                }

                else if(hit == 8)
                {
                    this.PuckYDirection = -2;
                }
                PuckSpeed++;

                if(this.y == 0)
                {
                    this.PuckYDirection = 2;
                }

                else if(hit ==14)
                {
                    this.PuckYDirection=-1;
                    PuckSpeed--;
                }


            }

            else if (PaddleHit(LeftPaddle) == "No hit")
            {
                if(this.x < 0)
                {
                //System.out.println("Right Paddle Score");
                NewPuck();
                hit = 0;
                RightPaddleScore++;
                }
            }

            if (PaddleHit(RightPaddle) == "hit")
            {
                this.PuckXDirection *= -1;
                this.PuckYDirection *= ((Math.random()*-1) +0.5);
                hit++;
                PuckSpeed++;

                if(hit==5)
                {
                    this.PuckYDirection = -1;
                }
                else if(hit == 8)
                {
                    this.PuckYDirection*=-1.7;
                }


                else if(hit ==14)
                {
                    this.PuckYDirection = -1;
                    PuckSpeed--;
                }
            }
            else if (PaddleHit(RightPaddle) == "No hit")
            {

                if(this.x + this.PuckWidth > PongGame.WIDTH) //goes past width of game
                {
                    NewPuck();
                    LeftPaddleScore++;
                }

            }

    }

    public String PaddleHit(Paddle p)

    {
         String result ="";

         if(PuckXDirection < 0) //ball moving left
          {
              if(this.x <= this.LeftPaddle.getX() + this.LeftPaddle.getPaddleWidth() &&
                 this.x + this.PuckWidth + this.y >= this.LeftPaddle.getY() &&
                 this.y <= this.LeftPaddle.getY() +this.LeftPaddle.getPaddleHeight())

                 result = "hit";

              else
                 result = "No hit";

          }

       // return "";

        else if (PuckXDirection > 0) //ball moving to the right
        {
            if (this.x + this.PuckWidth >= this.RightPaddle.getX() && this.x <= this.RightPaddle.getX() &&
                this.y >= this.RightPaddle.getY() && this.y <= this.RightPaddle.getY() +
                this.RightPaddle.getPaddleHeight())

                result = "hit";

            else
                result = "No hit";
        }

      return result;

    }


    public void NewPuck()
    {
        this.x = pongGame.WIDTH/2 - this.PuckWidth/2; //PUT PUCK IN CENTRE(WIDTH WISE)
        this.y = pongGame.HEIGHT/2 - this.PuckWidth/2; //PUT PUCK IN CENTRE(HEIGHT WISE)

       PuckSpeed = 4;
       hit = 0;

    }


    public void render(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillOval(x,y,PuckWidth,PuckHeight);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman",Font.PLAIN,50));
        g.drawString(String.valueOf(LeftPaddleScore), 0 + (pongGame.WIDTH/10), 50);
        g.drawString(String.valueOf(RightPaddleScore), pongGame.WIDTH - (pongGame.WIDTH/8), 50);

      /*  if(LeftPaddleScore == 2)
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, PongGame.WIDTH, PongGame.HEIGHT);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman",Font.PLAIN,50));
            g.drawString("Left Player Wins!!", pongGame.WIDTH/4 + 20,pongGame.HEIGHT/2);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman",Font.PLAIN,35));
            g.drawString("Press Space To Play Another Game", pongGame.WIDTH/5,pongGame.HEIGHT/2 + 50);


            LeftPaddleScore=0;
            RightPaddleScore=0;

        }*/

        /*if(RightPaddleScore == 2)
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, PongGame.WIDTH, PongGame.HEIGHT);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman",Font.PLAIN,50));
            g.drawString("Right Player Wins!!", pongGame.WIDTH/4 + 20,pongGame.HEIGHT/2);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman",Font.PLAIN,35));
            g.drawString("Press Space To Play Another Game", pongGame.WIDTH/5,pongGame.HEIGHT/2 + 50);


            LeftPaddleScore=0;
            RightPaddleScore=0;

        }*/

    }

}