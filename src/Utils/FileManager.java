package Utils;

import java.io.File;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

import Simulation.Organisms.Abstract.Organism;
import Simulation.Organisms.Animals.*;
import Simulation.Organisms.Plants.*;
import Simulation.World.HexagonalWorld;
import Simulation.World.RectangularWorld;
import Simulation.World.World;


public class FileManager {
    public FileManager(){
        world = World.getWorld();
    }

    public void saveWorld() throws IOException {
        try {
            int filename = Randomiser.randomInt(1000000);
            File file = new File("./Saves/" + String.valueOf(filename) + ".txt");

            if (file.createNewFile()) {
                try {
                    FileWriter writer = new FileWriter(file);
                    world = World.getWorld();
                    writer.write(world.stringify());
                    writer.close();

                    file = new File("./Saves/saves_list.txt");
                    writer = new FileWriter(file, true);
                    writer.write(String.valueOf(filename) + ".txt\n");
                    writer.close();

                    world.getEventListener().addInfo("World saved to file " + String.valueOf(filename) + ".txt");
                }
                catch (IOException e) {
                    world.getEventListener().addInfo("An error occurred while writing to file.");
                    e.printStackTrace();
                }

            } else {
                world.getEventListener().addInfo("File already exists.");
            }
        }
        catch (IOException e) {
            world.getEventListener().addInfo("An error occurred while creating file.");
            e.printStackTrace();
        }
    }

    public void loadWorld(String filename) {
        File file = new File("./Saves/" + filename);
        try {
            Scanner scanner = new Scanner(file);
            String buffer;
            String[] splitted;
            buffer = scanner.nextLine();
            splitted = buffer.split("\\.");
            int width = Integer.parseInt(splitted[0]);
            int height = Integer.parseInt(splitted[1]);
            String type = splitted[2];
            if(type.equals("Rectangular")) {
                world = new RectangularWorld(width, height);
                World.setWorld(world);
                world = World.getWorld();
            }
            else if(type.equals("Hexagonal")) {
                world = new HexagonalWorld(width, height);
                World.setWorld(world);
                world = World.getWorld();
            }
            world.setCurrentType(type);
            world.getEventListener().addInfo("World loaded from file " + filename);
            buffer = scanner.nextLine();
            splitted = buffer.split("\\.");
            for(String s : splitted){
                if(s.equals("") || s.equals("\n"))
                    break;
                world.getEventListener().addEvent(null, null, s);
            }
            while(scanner.hasNextLine()) {
                Organism o = null;
                buffer = scanner.nextLine();
                splitted = buffer.split("\\.");
                String name = splitted[0];
                int x = Integer.parseInt(splitted[1]);
                int y = Integer.parseInt(splitted[2]);
                int strength = Integer.parseInt(splitted[3]);
                int age = Integer.parseInt(splitted[4]);
                if(Objects.equals(name, "Human")) {
                    o = new Human(x, y);
                    int isActive = Integer.parseInt(splitted[5]);
                    int timer = Integer.parseInt(splitted[6]);
                    Human human = (Human)o;
                    human.setPower(isActive, timer);
                    world.setHuman(human);
                }
                else if(Objects.equals(name, "Antelope")) o = new Antelope(x, y);
                else if(Objects.equals(name, "Deadly nightshade")) o = new DeadlyNightshade(x, y);
                else if(Objects.equals(name, "Fox")) o = new Fox(x, y);
                else if(Objects.equals(name, "Grass")) o = new Grass(x, y);
                else if(Objects.equals(name, "Guarana")) o = new Guarana(x, y);
                else if(Objects.equals(name, "Sheep")) o = new Sheep(x, y);
                else if(Objects.equals(name, "Sosnowsky's Hogweed")) o = new SosnowskysHogweed(x, y);
                else if(Objects.equals(name, "Sow Thistle")) o = new SowThistle(x, y);
                else if(Objects.equals(name, "Turtle")) o = new Turtle(x, y);
                else if(Objects.equals(name, "Wolf")) o = new Wolf(x, y);

                o.setStrength(strength);
                o.increaseAge(age);
                world.addOrganism(o);
            }
            scanner.close();
        } catch (IOException e) {
            world.getEventListener().addInfo("An error occurred while reading file.");
            e.printStackTrace();
        }
    }

    public ArrayList<String> getSavesList() {
        File file = new File("./Saves/saves_list.txt");
        ArrayList<String> saves = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                saves.add(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            world.getEventListener().addInfo("An error occurred while reading file.");
            e.printStackTrace();
        }

        return saves;
    }

    private World world;
}
