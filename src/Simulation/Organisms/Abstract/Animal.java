package Simulation.Organisms.Abstract;

import Utils.Randomiser;

public abstract class Animal extends Organism{
    public Animal(int x, int y, int strength, int initiative, String name){
        super(x, y, strength, initiative, name);
    }

    @Override
    public void action(){
        increaseAge();
        int direction = Randomiser.randomInt(4);
        while(!move(getX()+world.getMove(direction, 0), getY()+ world.getMove(direction, 1))){
            direction++;
            direction%=4;
        }
    }
}
