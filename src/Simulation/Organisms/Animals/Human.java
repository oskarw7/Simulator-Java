package Simulation.Organisms.Animals;

import java.awt.Color;

import Simulation.Organisms.Abstract.Animal;
import Simulation.Organisms.Abstract.Organism;
import Utils.Point;

public class Human extends Animal {
    public Human(int x, int y){
        super(x, y, 5, 4, "Human", Color.white);
        this.direction = new Point(0, 0);
        this.timer = 0;
        this.isPowerActive = false;
    }

    @Override
    public void action(){
        increaseAge();
        if(timer==0)
            isPowerActive = false;
        if(timer>-5)
            timer--;
        if(isPowerActive){
            world.getEventListener().addEvent(this, null, "power");
        }
        if(!move(getX()+direction.getX(), getY()+direction.getY()))
            move(getX(), getY());
        setDirection(0,0);
    }

    @Override
    public void collision(Organism attacker){
        if(isPowerActive){
            if(attacker instanceof Animal){
                world.getEventListener().addEvent(this, attacker, "push");
                attacker.pushBack();
            }
        }
        else{
            if(this.getStrength() <= attacker.getStrength()){
                world.getEventListener().addEvent(attacker, this, "kill");
                this.kill();
            }
            else {
                world.getEventListener().addEvent(this, attacker, "kill");
                attacker.kill();
            }
        }
    }

    public boolean isPowerReady(){
        return timer==-5;
    }

    public void activatePower(){
        if(timer==-5){
            timer = 5;
            isPowerActive = true;
        }
    }

    public void setDirection(int x, int y){
        this.direction.assign(x, y);
    }

    public void setPower(int isActive, int timer){
        this.isPowerActive = isActive!=0; // sprawdzic
        this.timer = timer;
    }

    @Override
    public void kill(){
        super.kill();
        // set end
    }

    @Override
    protected Organism descendant(){
        return null;
    }

    private Point direction;
    private int timer;
    private boolean isPowerActive;
}