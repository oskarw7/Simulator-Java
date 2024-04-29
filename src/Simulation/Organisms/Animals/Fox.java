package Simulation.Organisms.Animals;

import java.awt.Color;

import Simulation.Organisms.Abstract.Animal;
import Simulation.Organisms.Abstract.Organism;
import Utils.Randomiser;

public class Fox extends Animal {
    public Fox(int x, int y){
        super(x, y, 3, 7, "Fox", Color.decode("#f58d42"));
    }

    @Override
    public void action(){
        increaseAge();
        int direction = Randomiser.randomInt(4);
        int iterations = 0;
        while(!sense(getX()+world.getMove(direction, 0), getY()+world.getMove(direction, 1)) && iterations<4){
            direction++;
            direction %= 4;
            iterations++;
        }
        if(iterations==4)
            move(getX(), getY());
    }

    @Override
    public Organism descendant() {
        return new Fox(getX(), getY());
    }

    private boolean sense(int x, int y){
        if(x<0 || x>=world.getWidth() || y<0 || y>=world.getHeight())
            return false;

        Organism o = world.getOrganism(x, y);
        if(o!=null && o.getStrength()>this.getStrength())
            return false;

        this.previousPosition = this.position;
        this.position.assign(x, y);
        if(o!=null && o.getStrength()<=this.getStrength())
            o.collision(this);

        return true;
    }
}
