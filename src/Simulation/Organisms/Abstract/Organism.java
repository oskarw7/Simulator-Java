package Simulation.Organisms.Abstract;

import Simulation.World;
import Utils.Point;

public abstract class Organism {
    public Organism(int x, int y, int strength, int initiative, String name){
        this.position = new Point(x, y);
        this.strength = strength;
        this.initiative = initiative;
        this.name = name;
        this.age = 0;
        this.isDescendant = false;
        this.world = World.getWorld();
    }

    abstract public void action();
    abstract public void collision(Organism attacker);

    public final boolean move(int x, int y){
        if(x<0 || x>=world.getWidth() || y>0 || y>=world.getHeight())
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
        if(o!=null || x<0 || x>=world.getWidth() || y>0 || y>=world.getHeight())
            return false;

        this.previousPosition.assign(this.position);
        this.position.assign(x, y);

        return true;
    }

    public void kill(){
        this.age = -100;
        this.position.assign(-100,-100);
        //add event: die
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

    public boolean getDescendant(){
        return false;
    }

    public void setDescendant(){};

    protected World world;
    protected Point position;
    protected Point previousPosition;
    protected int age;
    protected int strength;
    protected int initiative;
    protected String name;
    protected boolean isDescendant;
}
