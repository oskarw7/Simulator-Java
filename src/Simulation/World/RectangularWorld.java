package Simulation.World;

public class RectangularWorld extends World {
    public RectangularWorld(int width, int height){
        super(width, height, "Rectangular");
    }

    @Override
    public int getMove(int direction, int axis){
        return moves[direction][axis];
    }

    private final int[][] moves = {{1,0},{-1,0},{0,1},{0,-1}};
}
