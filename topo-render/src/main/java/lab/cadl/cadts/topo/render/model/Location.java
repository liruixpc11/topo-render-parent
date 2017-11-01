package lab.cadl.cadts.topo.render.model;

/**
 * Created by lirui on 2017/11/1.
 */
public class Location {
    private double x;
    private double y;
    private double width;
    private double height;

    public Location(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
