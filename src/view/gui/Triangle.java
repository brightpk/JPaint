package view.gui;

import controller.Point;
import model.ShapeInfo;
import model.ShapeShadingType;
import model.persistence.ApplicationState;
import view.interfaces.IShape;
import view.interfaces.IDraw;

public class Triangle implements IShape {
	int x, y, w, h;
	Point startP, endP;
	ShapeInfo shapeInfo;
	ApplicationState appState;
	ShapeShadingType shadingType;
	
	public Triangle(Point startP, Point endP, ShapeInfo shapeInfo) {
		this.endP = endP;
		this.startP = startP;
		this.shapeInfo = shapeInfo;
		this.shadingType = shapeInfo.shadingType;
		this.x = shapeInfo.x;
		this.y = shapeInfo.y;
		this.w = shapeInfo.w;
		this.h = shapeInfo.h;
	}

	@Override
	public void makeShape() {				
		IDraw d = null;
		DrawShape drawType = new DrawShape();
		
		switch (shadingType.toString()) {
			case "OUTLINE":
				d = new DrawOutlineStrategy();
				break;
			case "FILLED_IN":
				d = new DrawFillStrategy();
				break;
			case "OUTLINE_AND_FILLED_IN":
				d = new DrawOutlineAndFillStrategy();
				break;
		}
		
		drawType.draw(x, y, w, h, shapeInfo, d);	
		

	}
	
	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}
	
	@Override
	public int getWidth() {
		return this.w;
	}

	@Override
	public int getHeight() {
		return this.h;
	}

	@Override
	public Point getStartPoint() {
		return startP;
	}

	@Override
	public Point getEndPoint() {
		return endP;
	}

	@Override
	public void setStartPoint(int x, int y) {
		startP = new Point(x, y);
		
	}

	@Override
	public void setEndPoint(int x, int y) {
		endP = new Point(x, y);
		
	}

	@Override
	public ShapeInfo getShapeInfo() {
		return shapeInfo;
	}
}
