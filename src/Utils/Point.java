package Utils;

public class Point {
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean equals(Point other){
        return this.x==other.x && this.y==other.y;
    }

    public void assign(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void assign(Point other){
        this.x = other.getX();
        this.y = other.getY();
    }

    private int x;
    private int y;
}
