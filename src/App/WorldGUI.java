package App;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Objects;

import Simulation.Organisms.Abstract.Organism;
import Simulation.Organisms.Animals.*;
import Simulation.Organisms.Plants.*;
import Simulation.World.HexagonalWorld;
import Simulation.World.World;
import Utils.Point;

public class WorldGUI extends JPanel implements KeyListener, MouseListener {
    public WorldGUI(int guiWidth, int guiHeight){
        this.guiWidth = guiWidth;
        this.guiHeight = guiHeight;

        this.setBounds(0,0,guiWidth,guiHeight);
        this.setBackground(Color.black);
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);

        initOrganismMenu();

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

        for (int i = 0; i < world.getHeight(); i++) {
            for (int j = 0; j < world.getWidth(); j++) {
                Organism o = world.getOrganism(j, i);
                if (o != null) {
                    g.setColor(o.getColor());
                    if(Objects.equals(world.getType(), "Rectangular")) {
                        g.fillRect(j * fieldSize, i * fieldSize, fieldSize, fieldSize);
                    }
                    else if(Objects.equals(world.getType(), "Hexagonal")){
                        HexagonalWorld hexWorld = (HexagonalWorld) world;
                        int[][] coords = hexWorld.coordsToHex(i, j, fieldSize);
                        g.fillPolygon(coords[0], coords[1], 6);
                    }
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
                if(Objects.equals(world.getType(), "Rectangular")) {
                    human.setDirection(0, -1);
                }
                else{
                    if(e.isShiftDown())
                        human.setDirection(-1, -1);
                    else
                        human.setDirection(1, -1);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(Objects.equals(world.getType(), "Rectangular")) {
                    human.setDirection(0, 1);
                }
                else{
                    if(e.isShiftDown())
                        human.setDirection(-1, 1);
                    else
                        human.setDirection(1, 1);
                }
                break;
            case KeyEvent.VK_LEFT:
                human.setDirection(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                human.setDirection(1,0);
                break;
            case KeyEvent.VK_SPACE:
                if(human.isPowerReady())
                    human.activatePower();
                break;
            default:
                human.setDirection(0,0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fieldSize = guiWidth/world.getWidth();
        organismMenu.show(this, e.getX(), e.getY());
        mousePosition = new Point(e.getX()/fieldSize, e.getY()/fieldSize);
        repaint();
        paint(this.getGraphics());
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

    private void initOrganismMenu(){
        organismMenu = new JPopupMenu();
        Organism[] organisms = {
            new Wolf(0,0),
            new Sheep(0,0),
            new Fox(0,0),
            new Turtle(0,0),
            new Antelope(0,0),
            new Grass(0,0),
            new SowThistle(0,0),
            new Guarana(0,0),
            new DeadlyNightshade(0,0),
            new SosnowskysHogweed(0,0)
        };
        for(Organism o : organisms){
            JMenuItem menuItem = new JMenuItem(o.getName());
            menuItem.setBackground(o.getColor());
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    placeOrganism(o);
                }
            });
            organismMenu.add(menuItem);
        }
    }

    private void placeOrganism(Organism o){
        o.setPosition(mousePosition);
        Organism copy = o.descendant();
        Organism other = world.getOrganism(mousePosition.getX(), mousePosition.getY());
        if(other!=null){
            other.kill();
        }
        world.addOrganism(copy);
        paint(this.getGraphics());
    }


    private World world;
    private int guiWidth;
    private int guiHeight;
    private JPopupMenu organismMenu;
    private Point mousePosition;
}
