public abstract class Shape {
    protected double x;
    protected double y;
    protected double sideLength;

    public Shape(double x, double y, double sideLength) {
        this.x = x;
        this.y = y;
        this.sideLength = sideLength;
    }

    public abstract double boundingBoxArea();
}
