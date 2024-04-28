package Simulation.Organisms.Plants;

import java.awt.Color;

import Simulation.Organisms.Abstract.Organism;
import Simulation.Organisms.Abstract.Plant;
import Utils.Randomiser;

public class SowThistle extends Plant {
    public SowThistle(int x, int y){
        super(x, y, 0, 0, "Sow Thistle", Color.yellow);
    }

    @Override
    public void action(){
        increaseAge();
        move(getX(), getY());
        for(int i=0; i<3; i++){
            if(Randomiser.randomInt(100)==0 && getAge()>1)
                spread();
        }
    }

    @Override
    protected Organism descendant(){
        return new SowThistle(getX(), getY());
    }
}
