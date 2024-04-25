package Utils;

import java.util.Random;

public class Randomiser {

    public static int randomInt(int bound){
        return random.nextInt(bound);
    }

    private static Random random = new Random();
}
