package Simulation;

import java.util.Objects;
import java.util.Vector;

import Simulation.Organisms.Abstract.Organism;

public abstract class World {
    public World(int width, int height, String type){
        this.width = width;
        this.height = height;
        this.currentType = type;
    }

    public static World getWorld(){
        return world;
    }

    public static World getWorld(int width, int height, String type){
        if(Objects.equals(type, "Rectangular"))
            world = new RectangularWorld(width, height);
        else if(Objects.equals(type, "Hexagonal"))
            world = null; //TODO: warunek dla heksagonalnego
        else
            world = null;

        return world;
    }

    public final int getWidth(){
        return width;
    }

    public final int getHeight(){
        return height;
    }

    public abstract int getMove(int direction, int axis);

    public final Organism getOrganism(int x, int y){
        for(Organism o : organisms){
            if(o.getX()==x && o.getY()==y)
                return o;
        }
        return null;
    }

    public final void addOrganism(Organism organism){
        this.organisms.add(organism);
    }

    private static World world = null;
    //EventListener
    protected int width;
    protected int height;
    private String currentType;
    protected boolean isEnd;

    Vector<Organism> organisms;
    //human

}
