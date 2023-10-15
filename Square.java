public class Square extends Shape {
    public Square(double x, double y, double sideLength) {
        super(x, y, sideLength);
    }

    @Override
    public double boundingBoxArea() {
        return sideLength * sideLength;
    }
}
