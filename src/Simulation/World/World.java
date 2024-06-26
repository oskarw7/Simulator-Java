package Simulation.World;

import java.util.Objects;
import java.util.Vector;

import Simulation.Organisms.Abstract.*;
import Simulation.Organisms.Animals.*;
import Simulation.Organisms.Plants.*;
import Utils.Randomiser;
import Utils.EventListener;

public abstract class World {
    public World(int width, int height, String type, int moveCount){
        this.width = width;
        this.height = height;
        this.currentType = type;
        this.human = null;
        this.organisms = new Vector<>();
        this.eventListener = new EventListener();
    }

    public static World getWorld(){
        return world;
    }

    public static World getWorld(int width, int height, String type){
        if(Objects.equals(type, "Rectangular")) {
            world = new RectangularWorld(width, height);
            world.generateWorld();
        }
        else if(Objects.equals(type, "Hexagonal")) {
            world = new HexagonalWorld(width, height);
            world.generateWorld();
        }
        else
            world = null;

        return world;
    }

    public static void setWorld(World w){
        world = w;
    }

    public final void setCurrentType(String type){
        this.currentType = type;
    }

    public final void toggleWorld(String type){
        if(Objects.equals(type, "Rectangular")) {
            world = new RectangularWorld(width, height);
            this.currentType = "Rectangular";
            world.generateWorld();
        }
        else if(Objects.equals(type, "Hexagonal")) {
            world = new HexagonalWorld(width, height);
            this.currentType = "Hexagonal";
            world.generateWorld();
        }
    }

    public final int getWidth(){
        return width;
    }

    public final int getHeight(){
        return height;
    }

    public final String getType(){
        return currentType;
    }

    public abstract int getMove(int direction, int axis);

    public EventListener getEventListener(){
        return eventListener;
    }

    public final Human getHuman(){
        return human;
    }

    public final void setHuman(Human human){
        this.human = human;
    }

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

    public String stringify(){
        String s = "";
        s += String.valueOf(width) + '.' + String.valueOf(height) + '.' + currentType + '.' + '\n';
        s += eventListener.stringify() + '\n';
        for(Organism o : organisms){
            if(o.getAge()<0)
                continue;
            s += o.stringify();
            s+= '\n';
        }
        return s;
    }

    public final void newTurn(){
        sortTurn();

        for(Organism o : organisms){
            if(o instanceof Animal && o.getDescendant())
               o.setDescendant();
        }

        for(int i=0; i<organisms.size(); i++){
            if(organisms.get(i)!=null && organisms.get(i).getAge()>=0){
                if(organisms.get(i) instanceof Animal && organisms.get(i).getDescendant())
                    continue;
                organisms.get(i).action();
            }
        }
    }

    protected final void sortTurn(){
        int size = organisms.size();
        if(size==1)
            return;
        for(int i=0; i<size; i++){
            if(organisms.get(i)==null || organisms.get(i).getAge()<0){
                organisms.remove(i);
                i--;
                size--;
            }
        }
        organisms.sort((Organism o1, Organism o2) -> {
            if(o1.getInitiative() == o2.getInitiative()) {
                return o2.getAge() - o1.getAge();
            }
            else {
                return o2.getInitiative() - o1.getInitiative();
            }
        });
    }

    protected final void validateWorld(){
        for(Organism o1 : organisms){
            Organism o2 = getOrganism(o1.getX(), o1.getY());
            if(o2!=null && o1!=o2)
                o1.collision(o2);
        }
    }
    protected final void generateWorld(){
        this.human = new Human(Randomiser.randomInt(width), Randomiser.randomInt(height));
        addOrganism(human);

        addOrganism(new Wolf(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new Wolf(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new Sheep(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new Sheep(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new Fox(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new Turtle(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new Turtle(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new Antelope(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new Antelope(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new Grass(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new Grass(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new SowThistle(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new SowThistle(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new Guarana(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new Guarana(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new DeadlyNightshade(Randomiser.randomInt(width), Randomiser.randomInt(height)));
        addOrganism(new SosnowskysHogweed(Randomiser.randomInt(width), Randomiser.randomInt(height)));

        validateWorld();
    }


    protected static World world = null;
    protected int width;
    protected int height;

    protected EventListener eventListener;
    protected Vector<Organism> organisms;
    protected Human human;

    private String currentType;

}