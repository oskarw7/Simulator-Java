package Simulation.Organisms.Animals;

import java.awt.Color;

import Simulation.Organisms.Abstract.Animal;
import Simulation.Organisms.Abstract.Organism;
import Utils.Randomiser;

public class Turtle extends Animal {
    public Turtle(int x, int y){
        super(x, y, 2, 1, "Turtle", Color.green);
    }

    @Override
    public void action(){
        increaseAge();
        int direction = Randomiser.randomInt(16);
        if(direction<4){
            while(!move(getX()+world.getMove(direction, 0), getY()+world.getMove(direction, 1))){
                direction++;
                direction %= 4;
            }
        }
        else{
            move(getX(), getY());
        }
    }

    @Override
    public void collision(Organism attacker){
        if(this.getClass().equals(attacker.getClass())){
            multiply(attacker);
        }
        else{
            if(attacker instanceof Animal && attacker.getStrength()<5){
                // event tpush
                attacker.pushBack();
            }
            else {
                if(this.getStrength() <= attacker.getStrength()){
                    // event kill
                    this.kill();
                }
                else {
                    // event kill
                    attacker.kill();
                }
            }
        }
    }

    @Override
    protected Organism descendant() {
        return new Turtle(getX(), getY());
    }
}
