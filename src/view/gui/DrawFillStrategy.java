package view.gui;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import model.ShapeColor;
import model.ShapeInfo;
import model.ShapeType;
import view.interfaces.IDraw;

public class DrawFillStrategy implements IDraw {
	Graphics2D g;
	ShapeType shapeType;
	ShapeColor primaryColor;
	ShapeColor secondColor;
	ShapeInfo shapeInfo;

	@Override
	public void draw(int x, int y, int w, int h, ShapeInfo shapeInfo) {
		this.g = shapeInfo.g2D;
		shapeType = shapeInfo.shapeType;
		primaryColor = shapeInfo.primaryColor;
		secondColor = shapeInfo.secondColor;
		
		switch (shapeType.toString()) {
			case "ELLIPSE":
				g.setColor(primaryColor.getColor());
				g.fillOval(x, y, w, h);
				break;
			
			case "RECTANGLE":
				g.setColor(primaryColor.getColor());
				g.fillRect(x, y, w, h);
				break;
			
			case "TRIANGLE":
				int[] listX = {x, x + w, x + (w/2)};
				int[] listY = {y + h, y + h, y};
				g.setColor(primaryColor.getColor());
				g.setStroke(new BasicStroke(3));
				g.fillPolygon(listX, listY, listX.length);
				break;
		}
		
	}

}
