package Simulation.Organisms.Animals;

import java.awt.Color;

import Simulation.Organisms.Abstract.Animal;
import Simulation.Organisms.Abstract.Organism;

public class Wolf extends Animal {
    public Wolf(int x, int y){
        super(x, y, 9, 5, "Wolf", Color.gray);
    }

    @Override
    public Organism descendant() {
        return new Wolf(getX(), getY());
    }
}
