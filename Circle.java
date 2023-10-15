public class Circle extends Shape {
    public Circle(double x, double y, double radius) {
        super(x, y, radius);
    }

    @Override
    public double boundingBoxArea() {
        return 4 * sideLength * sideLength;
    }
}
