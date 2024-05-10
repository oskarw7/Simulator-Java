package Simulation.Organisms.Animals;

import java.awt.Color;
import java.util.Objects;

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
        if(Objects.equals(world.getType(), "Rectangular")) {
            this.direction.assign(x, y);
        }
        else {
            if(x==-1 && y==-1){
                if(this.getY()%2==0)
                    this.direction.assign(-1, -1);
                else
                    this.direction.assign(0, -1);
            }
            else if(x==1 && y==-1){
                if(this.getY()%2==0)
                    this.direction.assign(0, -1);
                else
                    this.direction.assign(1, -1);
            }
            else if(x==-1 && y==1){
                if(this.getY()%2==0)
                    this.direction.assign(-1, 1);
                else
                    this.direction.assign(0, 1);
            }
            else if(x==1 && y==1){
                if(this.getY()%2==0)
                    this.direction.assign(0, 1);
                else
                    this.direction.assign(1, 1);
            }
            else
                this.direction.assign(x, y);
        }
    }

    public void setPower(int isActive, int timer){
        this.isPowerActive = isActive!=0;
        this.timer = timer;
    }

    @Override
    public void kill(){
        super.kill();
        // set end
    }

    @Override
    public Organism descendant(){
        return null;
    }

    @Override
    public String stringify(){
        String s = super.stringify();
        s += String.valueOf(this.isPowerActive ? 1 : 0) + '.';
        s += String.valueOf(this.timer) + '.';
        return s;
    }

    private Point direction;
    private int timer;
    private boolean isPowerActive;
}
