package Utils;

import Simulation.World;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
                    writer.write(world.stringify());
                    writer.close();
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

    private World world;
}
