import java.io.*;
import java.util.*;

public class ShapeProcessor {
    private List<Shape> shapes = new ArrayList<>();

    public void loadShapesFromFile(String filepath) throws IOException, InvalidShapeDataException 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            int declaredNumShapes = Integer.parseInt(br.readLine().trim());
            int actualNumShapes = 0;
            String line;
            while ((line = br.readLine()) != null) {
                createAndAddShape(line.trim());
                actualNumShapes++;
            }
            if (actualNumShapes != declaredNumShapes) {
                throw new InvalidShapeDataException("The number of shapes declared (" + declaredNumShapes +
                                                    ") does not match the actual number of shapes provided (" +
                                                    actualNumShapes + ").");
            }
        }
    }


    private void createAndAddShape(String shapeData) throws InvalidShapeDataException 
    {
        String[] parts = shapeData.split(" ");
        if (parts.length != 4) {
            throw new InvalidShapeDataException("Invalid data format: " + shapeData);
        }

        String type = parts[0];
        double x = Double.parseDouble(parts[1]);
        double y = Double.parseDouble(parts[2]);
        double sideLength = Double.parseDouble(parts[3]);

        if (sideLength <= 0) {
            throw new InvalidShapeDataException("Invalid radius/side length: " + sideLength);
        }

        Shape shape = null;
        switch (type) {
            case "C":
                shape = new Circle(x, y, sideLength);
                break;
            case "T":
                shape = new Triangle(x, y, sideLength);
                break;
            case "S":
                shape = new Square(x, y, sideLength);
                break;
            case "H":
                shape = new Hexagon(x, y, sideLength);
                break;
            default:
                throw new InvalidShapeDataException("Unknown shape type: " + type);
        }
        shapes.add(shape);
    }


    public Shape findShapeWithLargestBoundingBox() {
        Shape maxShape = null;
        double maxArea = 0;
        for (Shape shape : shapes) {
            double currentArea = shape.boundingBoxArea();
            if (currentArea > maxArea) {
                maxArea = currentArea;
                maxShape = shape;
            }
        }
        return maxShape;
    }

    public static void main(String[] args) 
    {
        ShapeProcessor sp = new ShapeProcessor();
        try {
            sp.loadShapesFromFile("shapes.txt");
            Shape maxShape = sp.findShapeWithLargestBoundingBox();
            System.out.println("Shape with the largest bounding box: " + maxShape.getClass().getSimpleName());
            System.out.println("Bounding box area: " + maxShape.boundingBoxArea());
        } catch (InvalidShapeDataException e) {
            System.err.println("Error: Invalid shape data. " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error: An I/O error occurred. " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: An unexpected error occurred. " + e.getMessage());
        }
    }
}
