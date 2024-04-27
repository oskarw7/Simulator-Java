package App;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Simulation.Organisms.Abstract.Organism;
import Simulation.Organisms.Animals.Human;
import Simulation.World;

public class WorldGUI extends JPanel implements KeyListener, MouseListener {
    public WorldGUI(int guiWidth, int guiHeight){
        this.guiWidth = guiWidth;
        this.guiHeight = guiHeight;

        this.setBounds(0,0,guiWidth,guiHeight);
        this.setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        this.world = World.getWorld();
    }

    public void newTurn(){
        world.newTurn();
        paint(this.getGraphics());
        requestFocus();
    }

    @Override
    public void paint(Graphics g){
        int fieldSize = guiWidth/world.getWidth();
        g.setColor(Color.black);
        g.fillRect(0,0, guiWidth, guiHeight);
        for(int i=0; i<world.getHeight(); i++){
            for(int j=0; j<world.getWidth(); j++){
                Organism o = world.getOrganism(j, i);
                if(o!=null){
                    g.setColor(o.getColor());
                    g.fillRect(j*fieldSize, i*fieldSize, fieldSize, fieldSize);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Human human = world.getHuman();

        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                human.setDirection(0,-1);
                break;
            case KeyEvent.VK_DOWN:
                human.setDirection(0, 1);
                break;
            case KeyEvent.VK_LEFT:
                human.setDirection(-1,0);
                break;
            case KeyEvent.VK_RIGHT:
                human.setDirection(1,0);
                break;
            default:
                human.setDirection(0,0);
                break;
                // obsluga umiejetnosci
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private World world;
    private int guiWidth;
    private int guiHeight;
}
