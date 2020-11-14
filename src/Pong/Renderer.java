package Pong;

import java.awt.*;

/*Technically there's nothing wrong with having this class but it is a poor design choice - instead I have made
the PongGame class inherit from JPanel directly (it makes it a JPanel as such), and use the paintComponent()
that you defined below within PongGame to paint the panel
 */

/*import javax.swing.JPanel;

public class Renderer extends JPanel
{
    public void paintComponent(Graphics g)
    { // referencing to information read here: https://web.stanford.edu/class/archive/cs/cs108/cs108.1092/handouts/27PaintRepaint.pdf
        super.paintComponent(g);

        PongGame.pongGame.render(g);
    }

}*/