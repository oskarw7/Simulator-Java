package Simulation.Organisms.Animals;

import java.awt.Color;

import Simulation.Organisms.Abstract.Animal;
import Simulation.Organisms.Abstract.Organism;

public class Sheep extends Animal {
    public Sheep(int x, int y){
        super(x, y, 4, 4, "Sheep", Color.pink);
    }

    @Override
    public Organism descendant() {
        return new Sheep(getX(), getY());
    }
}
