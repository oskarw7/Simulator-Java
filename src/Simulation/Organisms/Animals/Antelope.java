package Simulation.Organisms.Animals;

import java.awt.Color;

import Simulation.Organisms.Abstract.Animal;
import Simulation.Organisms.Abstract.Organism;
import Utils.Randomiser;

public class Antelope extends Animal {
    public Antelope(int x, int y){
        super(x, y, 4, 4, "Antelope", Color.decode("#f7eb81"));
    }

    @Override
    public void action(){
        increaseAge();
        int direction = Randomiser.randomInt(8);
        if(direction<4){
            while(!move(getX()+world.getMove(direction, 0), getY()+world.getMove(direction, 1))){
                direction++;
                direction %= 4;
            }
        }
        else{
            direction %= 4;
            while(!move(getX()+2*world.getMove(direction, 0), getY()+2*world.getMove(direction, 1))){
                direction++;
                direction %= 4;
            }
        }
    }

    @Override
    public void collision(Organism attacker) {
        if(this.getClass().equals(attacker.getClass())){
            multiply(attacker);
        }
        else{
            if(this.getStrength() <= attacker.getStrength()){
                int willEscape = Randomiser.randomInt(2);
                if(willEscape!=0){
                    int direction = Randomiser.randomInt(4);
                    int iterations = 0;
                    while(!this.moveOnEmpty(getX()+world.getMove(direction, 0), getY()+ world.getMove(direction, 1)) && iterations<4){
                        direction++;
                        direction %= 4;
                        iterations++;
                    }
                    if(iterations==4){
                        world.getEventListener().addEvent(attacker, this, "kill");
                        this.kill();
                    }
                    else{
                        world.getEventListener().addEvent(this, attacker, "escaped from");
                    }
                }
                else{
                    world.getEventListener().addEvent(attacker, this, "kill");
                    this.kill();
                }
            }
            else {
                world.getEventListener().addEvent(this, attacker, "kill");
                attacker.kill();
            }
        }
    }

    @Override
    protected Organism descendant() {
        return new Antelope(getX(), getY());
    }
}
