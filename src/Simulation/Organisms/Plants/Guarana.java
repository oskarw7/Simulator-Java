package Simulation.Organisms.Plants;

import java.awt.Color;

import Simulation.Organisms.Abstract.Organism;
import Simulation.Organisms.Abstract.Animal;
import Simulation.Organisms.Abstract.Plant;

public class Guarana extends Plant {
    public Guarana(int x, int y){
        super(x, y, 0, 0, "Guarana", Color.red);
    }

    @Override
    public void collision(Organism attacker){
        if(this.getStrength() <= attacker.getStrength()){
            if(attacker instanceof Animal animal){ // sprawdzic
                world.getEventListener().addEvent(attacker, this, "eat");
                animal.increaseStrength(3);
            }
            this.kill();
        }
        else {
            world.getEventListener().addEvent(this, attacker, "kill");
            attacker.kill();
        }
    }

    @Override
    public Organism descendant() {
        return new Guarana(getX(), getY());
    }
}
