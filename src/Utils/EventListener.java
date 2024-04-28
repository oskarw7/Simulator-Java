package Utils;

import java.util.ArrayList;
import java.util.Objects;

import Simulation.Organisms.Abstract.Organism;

public class EventListener {
    public EventListener(){
        this.events = new ArrayList<>();
    }

    public void addEvent(Organism o1, Organism o2, String event){
        if(Objects.equals(event, "multiplication")){
            events.add("Multiplication of " + o1.getName() + " on position (" + String.valueOf(o1.getX()) + "," + String.valueOf(o1.getY()) + ")");
        }
        else if(Objects.equals(event, "multiplication failed")){
            events.add(o1.getName() + " failed to multiply, there is no space around");
        }
        else if(Objects.equals(event, "spread")){
            events.add(o1.getName() + " spreads on position (" + String.valueOf(o1.getX()) + "," + String.valueOf(o1.getY()) + ")");
        }
        else if(Objects.equals(event, "spread failed")){
            events.add(o1.getName() + " failed to spread, there is no space around");
        }
        else if(Objects.equals(event, "kill")){
            events.add(o1.getName() + " killed " + o2.getName() + " on position (" + String.valueOf(o2.getX()) + "," + String.valueOf(o2.getY()) + ")");
        }
        else if(Objects.equals(event, "trample")){
            events.add(o1.getName() + " trampled " + o2.getName() + " on position (" + String.valueOf(o2.getX()) + "," + String.valueOf(o2.getY()) + ")");
        }
        else if(Objects.equals(event, "die")){
            events.add(o1.getName() + " died");
        }
        else if(Objects.equals(event, "eat")){
            events.add(o1.getName() + " ate " + o2.getName() + " on position (" + String.valueOf(o2.getX()) + "," + String.valueOf(o2.getY()) + ")");
        }
        else if(Objects.equals(event, "power")){
            events.add(o1.getName() + " is using Alzur's Shields");
        }
        else if(Objects.equals(event, "push")){
            events.add(o1.getName() + " pushed back " + o2.getName() + " using Alzur's Shields");
        }
        else if(Objects.equals(event, "tpush")){
            events.add(o1.getName() + " pushed back " + o2.getName() + " using his shell");
        }
        else if(o1==null && o2==null){
            events.add(event);
        }
        else {
            events.add(o1.getName() + " " + event + " " + o2.getName());
        }
    }

    public void addInfo(String info){
        this.info = info;
    }

    public void clearEvents(){
        events.clear();
    }

    public ArrayList<String> getEvents(){
        return events;
    }

    public String stringify() {
        String s = ".";
        for (String event : events) {
            s += event + ".";
        }
        return s;
    }

    private ArrayList<String> events;
    String info;
}
