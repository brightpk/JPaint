package view.gui;

import controller.Point;
import model.ShapeInfo;
import view.interfaces.IShape;

public class ShapeFactory {
	
	public static IShape createEllipse(Point startP, Point endP, ShapeInfo shapeInfo) {
		return new Ellipse(startP, endP, shapeInfo);
	}
	
	public static IShape createRectangle(Point startP, Point endP, ShapeInfo shapeInfo) {
		return new Rectangle(startP, endP, shapeInfo);
	}
	
	public static IShape createTriangle(Point startP, Point endP, ShapeInfo shapeInfo) {
		return new Triangle(startP, endP, shapeInfo);
	}

}
