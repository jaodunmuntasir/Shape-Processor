public class Hexagon extends Shape {
    public Hexagon(double x, double y, double sideLength) {
        super(x, y, sideLength);
    }

    @Override
    public double boundingBoxArea() {
        return sideLength * (3 * Math.sqrt(3) / 2 * sideLength);
    }
}
