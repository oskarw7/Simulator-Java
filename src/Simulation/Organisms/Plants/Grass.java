package Simulation.Organisms.Plants;

import java.awt.Color;

import Simulation.Organisms.Abstract.Organism;
import Simulation.Organisms.Abstract.Plant;

public class Grass extends Plant {
    public Grass(int x, int y){
        super(x, y, 0, 0, "Grass", Color.green);
    }

    @Override
    protected Organism descendant() {
        return new Grass(getX(), getY());
    }
}