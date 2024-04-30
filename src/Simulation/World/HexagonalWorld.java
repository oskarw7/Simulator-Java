package Simulation.World;

public class HexagonalWorld extends World {
    public HexagonalWorld(int width, int height) {
        super(width, height, "Hexagonal");
    }

    @Override
    public int getMove(int direction, int axis) {
        return moves[direction][axis];
    }

    public int[][] coordsToHex(int i, int j, int fieldSize){
        int[][] coords = new int[2][6];
        double temp = j;
        if(i%2==1)
            temp = j+0.5;
        for (int k = 0; k < 6; k++) {
            int x = (int) (temp * fieldSize + fieldSize/2 * Math.sin(k * 2 * Math.PI / 6.0));
            int y = (int) (i * fieldSize + fieldSize/2 * Math.cos(k * 2 * Math.PI / 6.0));
            coords[0][k] = x;
            coords[1][k] = y+fieldSize/2;
        }
        return coords;
    }

    //private final int[][] moves = {{1,0}, {-1,0}, {-1,1}, {1,1}, {-1,-1}, {1,-1}};
    private final int[][] moves = {{1,0},{-1,0},{0,1},{0,-1}};

}