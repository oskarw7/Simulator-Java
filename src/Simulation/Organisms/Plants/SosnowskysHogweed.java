package Simulation.Organisms.Plants;

import java.awt.Color;

import Simulation.Organisms.Abstract.Organism;
import Simulation.Organisms.Abstract.Animal;
import Simulation.Organisms.Abstract.Plant;

public class SosnowskysHogweed extends Plant {
    public SosnowskysHogweed(int x, int y){
        super(x, y, 10, 0, "Sosnowsky's Hogweed", Color.decode("#baeb34"));
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
        return new SosnowskysHogweed(getX(), getY());
    }
}
