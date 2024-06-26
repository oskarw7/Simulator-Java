package Simulation.Organisms.Abstract;

import java.awt.Color;

import Simulation.World.World;
import Utils.Point;
import Utils.Randomiser;

public abstract class Organism {
    public Organism(int x, int y, int strength, int initiative, String name, Color color){
        this.position = new Point(x, y);
        this.previousPosition = new Point(x, y);
        this.strength = strength;
        this.initiative = initiative;
        this.name = name;
        this.color = color;
        this.age = 0;
        this.isDescendant = false;
        this.world = World.getWorld();
    }

    abstract public void action();
    abstract public void collision(Organism attacker);

    public final boolean move(int x, int y){
        if(x<0 || x>=world.getWidth() || y<0 || y>=world.getHeight())
            return false;
        Organism o = world.getOrganism(x, y);

        this.previousPosition.assign(this.position);
        this.position.assign(x, y);

        if(o!=null && o!=this)
            o.collision(this);

        return true;
    }

    public final boolean moveOnEmpty(int x, int y){
        Organism o = world.getOrganism(x, y);
        if(o!=null || x<0 || x>=world.getWidth() || y<0 || y>=world.getHeight())
            return false;

        this.previousPosition.assign(this.position);
        this.position.assign(x, y);

        return true;
    }

    public void kill(){
        this.age = -100;
        this.position.assign(-100,-100);
        world.getEventListener().addEvent(this, null, "die");
    }

    public String stringify(){
        String s="";
        s += this.name + '.';
        s += String.valueOf(this.position.getX()) + '.' + String.valueOf(this.position.getY()) + '.';
        s += String.valueOf(this.strength) + '.';
        s += String.valueOf(this.age) + '.';
        return s;
    }

    public final void pushBack(){
        position.assign(previousPosition);
    }

    public final void increaseStrength(int difference){
        this.strength+=difference;
    }

    public final void increaseAge(int value){
        this.age+=value;
    }

    public final void increaseAge(){
        this.age++;
    }

    public final int getX(){
        return position.getX();
    }

    public final int getY(){
        return position.getY();
    }

    public final int getAge(){
        return age;
    }

    public final int getStrength(){
        return strength;
    }

    public final int getInitiative(){
        return initiative;
    }

    public final String getName(){
        return name;
    }

    public final Color getColor(){
        return color;
    }

    public boolean getDescendant(){
        return false;
    }

    public void setDescendant(){};

    public final void setStrength(int strength){
        this.strength = strength;
    }

    public final void setPosition(Point p){
        this.position.assign(p);
    }

    public abstract Organism descendant();

    protected final boolean canBePlaced(Organism desc){
        world.addOrganism(desc);

        int direction = Randomiser.randomInt(4);
        int iterations = 0;
        while(!desc.moveOnEmpty(getX()+world.getMove(direction, 0), getY()+world.getMove(direction, 1)) && iterations<4){
            direction++;
            direction %= 4;
            iterations++;
        }
        return iterations!=4;
    }

    protected World world;
    protected Point position;
    protected Point previousPosition;
    protected int age;
    protected int strength;
    protected int initiative;
    protected String name;
    protected boolean isDescendant;
    protected Color color;
}
