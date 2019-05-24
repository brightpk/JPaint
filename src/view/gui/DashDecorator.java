package view.gui;

import java.awt.BasicStroke;
import java.awt.Stroke;

import controller.Point;
import model.ShapeColor;
import model.ShapeInfo;
import view.interfaces.IShape;

public class DashDecorator implements IShape {
	IShape shape;
	int x, y, w, h;
	Point startP, endP;
	ShapeColor prevColor;
	Stroke stroke, prevStroke;

	public DashDecorator(IShape shape) {
		this.shape = shape;
		this.endP = shape.getEndPoint();
		this.startP = shape.getStartPoint();
	}
	
	@Override
	public void makeShape() {
		shape.makeShape();
		drawDash(shape);
	}
	
	public void drawDash(IShape shape) {
		// Adjust x, y, width and height by offset of 5 and 10 
		x = shape.getX() - 5;
		y = shape.getY() - 5;
		w = shape.getWidth() + 10; 
		h = shape.getHeight() + 10;
		
		// Store the previous color and stroke to change them back after finishing draw dash 
		prevStroke = shape.getShapeInfo().stroke;
		prevColor = shape.getShapeInfo().primaryColor;
		stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{10}, 0);

		// Adjust stroke and color of dash
		shape.getShapeInfo().stroke = stroke;
		shape.getShapeInfo().primaryColor = ShapeColor.BLACK;
		
		// Call drawOutline strategy 
		DrawShape drawType = new DrawShape();
		drawType.draw(x, y, w, h, shape.getShapeInfo(), new DrawOutlineStrategy());
		
		// Change back to previous color and stroke in ShapeInfo
		shape.getShapeInfo().primaryColor = prevColor;
		shape.getShapeInfo().stroke = prevStroke; 
	
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
		return shape.getShapeInfo();
	}

}
