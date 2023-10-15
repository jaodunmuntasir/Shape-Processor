public class Triangle extends Shape {
    public Triangle(double x, double y, double sideLength) {
        super(x, y, sideLength);
    }

    @Override
    public double boundingBoxArea() {
        return sideLength * (Math.sqrt(3) / 2 * sideLength);
    }
}
