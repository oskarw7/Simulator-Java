package Simulation.Organisms.Plants;

import java.awt.Color;

import Simulation.Organisms.Abstract.Animal;
import Simulation.Organisms.Abstract.Organism;
import Simulation.Organisms.Abstract.Plant;

public class DeadlyNightshade extends Plant {
    public DeadlyNightshade(int x, int y){
        super(x, y, 99, 0, "Deadly nightshade", Color.decode("#251247"));
    }

    @Override
    public void collision(Organism attacker){
        if(attacker instanceof Animal){
            world.getEventListener().addEvent(attacker, this, "eat");
            this.kill();
        }
        attacker.kill();
    }

    @Override
    public Organism descendant() {
        return new DeadlyNightshade(getX(), getY());
    }
}
