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
            direction %= 4;
        }
    }

    @Override
    public void collision(Organism attacker){
        if(this.getClass().equals(attacker.getClass())){
            //multiply
        }
        else {
            if(this.getStrength() <= attacker.getStrength()){
                //add event kill
                this.kill();
            }
            else {
                // add event kill
                attacker.kill();
            }
        }
    }

    @Override
    public final boolean getDescendant(){
        return isDescendant;
    }

    public final void setDescendant(){
        isDescendant = !isDescendant;
    }

    protected final void multiply(Organism partner){
        if(partner!=this && this.getAge()>=7 && partner.getAge()>=7){
            Organism desc = descendant();
            desc.setDescendant();
            world.addOrganism(desc);

            int direction = Randomiser.randomInt(4);
            int iterations = 0;
            while(!desc.moveOnEmpty(getX()+world.getMove(direction, 0), getY()+ world.getMove(direction, 1)) && iterations<4){
                direction++;
                direction %= 4;
                iterations++;
            }
            if(iterations==4){
                //add event multiplication failed
                desc.kill();
            }
            else{
                //add event multiplied
            }
        }
    }
}
