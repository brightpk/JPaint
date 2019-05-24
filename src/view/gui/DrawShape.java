package view.gui;

import model.ShapeInfo;
import view.interfaces.IDraw;

public class DrawShape {
	public void draw(int x, int y, int w, int h, ShapeInfo shapeInfo, IDraw drawType) {
		drawType.draw(x, y, w, h, shapeInfo);
	}
}
