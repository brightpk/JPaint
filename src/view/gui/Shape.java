package view.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import controller.Point;
import controller.interfaces.IShapeListObserver;
import model.ShapeInfo;
import model.persistence.ApplicationState;
import view.interfaces.IShape;

public class Shape implements IShape, IShapeListObserver {
	int x, y, w, h;
	Point startP, endP;
	ShapeInfo shapeInfo;
	ApplicationState appState;
		
	public Shape(Point startP, Point endP , ShapeInfo shapeInfo) {
		this.endP = endP;
		this.startP = startP;
		this.shapeInfo = shapeInfo;
	}

	@Override
	public void makeShape() {
		x = Math.min(startP.getX(), endP.getX());
		y = Math.min(startP.getY(), endP.getY()); 
		w = Math.abs(endP.getX() - startP.getX());
		h = Math.abs(endP.getY() - startP.getY());
		
		shapeInfo.setX(x);
		shapeInfo.setY(y);
		shapeInfo.setWidth(w);
		shapeInfo.setHeight(h);
		
		IShape s = null;
		
		switch (shapeInfo.shapeType.toString()) {
			case "ELLIPSE":
				s = ShapeFactory.createEllipse(startP, endP, shapeInfo);
			break;
			case "RECTANGLE":
				s = ShapeFactory.createRectangle(startP, endP, shapeInfo);
			break;
			case "TRIANGLE":
				s = ShapeFactory.createTriangle(startP, endP, shapeInfo);
			break;
		}
		
		s.makeShape();
		
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	@Override
	public int getWidth() {
		return w;
	}

	@Override
	public int getHeight() {
		return h;
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
	

	@Override
	public void update() {
		Graphics2D g = shapeInfo.g2D;
    	g.setColor(Color.WHITE);
    	g.fillRect(0, 0, 100000, 10000);
    	for (IShape s : shapeInfo.shapeList.getShapeList()) {
    		s.makeShape();
    	}
	}

	
}
