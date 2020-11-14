package Pong;

import java.awt.*;

public class Puck {
    //JB - again make sure all these attributes ebd up private for encapsulation reasons

    private int PuckWidth = 23;
    private int PuckHeight = 23;
    private int x, y;
    public int PuckYDirection;
    public int PuckXDirection;
    private PongGame pongGame;


    public Puck(PongGame pongGame)
    {
        this.pongGame = pongGame;
        this.x = pongGame.WIDTH/2 - this.PuckWidth/2; //PUT PUCK IN CENTRE(WIDTH WISE)
        this.y = pongGame.HEIGHT/2 - this.PuckWidth/2; //PUT PUCK IN CENTRE(HEIGHT WISE)

    }

    public void render(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillOval(x,y,PuckWidth,PuckHeight);
    }




}
