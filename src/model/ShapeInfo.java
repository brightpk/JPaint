package model;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

import controller.Point;
import controller.ShapeList;
import model.interfaces.IShapeInfo;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class ShapeInfo implements IShapeInfo {
	public Stroke stroke;
	public Graphics2D g2D;
	public int x, y, w, h;
	public Point startP, endP;
	public ShapeList shapeList;
	public ShapeType shapeType;
	public ShapeColor primaryColor;
	public ShapeColor secondColor;
	public ShapeShadingType shadingType;
	public PaintCanvasBase paintCanvas;
	public ApplicationState appState;

	public ShapeInfo(Graphics2D g2D, ApplicationState appState, PaintCanvasBase paintCanvas, ShapeList shapeList) {
		this.g2D = g2D;
		this.appState = appState;
		this.shapeList = shapeList;
		this.paintCanvas = paintCanvas;
		this.stroke = new BasicStroke(3);
	}

	@Override
	public void setAllShapeInfo() {
		//this.stroke = new BasicStroke(3);
		this.shapeType = appState.getActiveShapeType();
		this.primaryColor = appState.getActivePrimaryColor();
		this.secondColor = appState.getActiveSecondaryColor();
		this.shadingType = appState.getActiveShapeShadingType();
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setWidth(int w) {
		this.w = w;
	}
	
	public void setHeight(int h) {
		this.h = h;
	}
	
}
