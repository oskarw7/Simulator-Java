package Simulation.Organisms.Abstract;

import java.awt.Color;

import Utils.Randomiser;

public abstract class Plant extends Organism{
    public Plant(int x, int y, int strength, int initiative, String name, Color color) {
        super(x, y, strength, initiative, name, color);
    }

    @Override
    public void action() {
        increaseAge();
        move(getX(), getY());
        if(getAge()>0 && Randomiser.randomInt(100)==0)
            spread();
    }

    @Override
    public void collision(Organism attacker) {
        if(this.getStrength() <= attacker.getStrength()){
            world.getEventListener().addEvent(attacker, this, "trample");
            this.kill();
        }
        else {
            world.getEventListener().addEvent(this, attacker, "kill");
            attacker.kill();
        }
    }

    protected final void spread(){
        Organism desc = descendant();

        if(!canBePlaced(desc)){
            world.getEventListener().addEvent(desc, null, "spread failed");
            desc.kill();
        }
        else {
            world.getEventListener().addEvent(desc, null, "spread");
        }
    }
}
