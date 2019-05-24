package controller.commands;

import controller.Point;
import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.ShapeInfo;
import view.gui.Shape;
import view.interfaces.IShape;

public class CreateCommand implements ICommand, IUndoable {
	IShape shape;
	Point startP, endP;
	ShapeInfo shapeInfo;;
	
	public CreateCommand(Point startP, Point endP, ShapeInfo shapeInfo) {
		this.endP = endP;
		this.startP = startP;
		this.shapeInfo = shapeInfo;
	}

	@Override
	public void run() {
		System.out.println("CREATE SHAPE...");
		
		shape = new Shape(startP, endP, shapeInfo);
		shape.makeShape();
		shapeInfo.shapeList.addShape(shape); 
//		shapeInfo.shapeList.update();
		
		CommandHistory.add(this);
		System.out.println("ShapeList size: " + shapeInfo.shapeList.getSize() + "\n");
	}

	@Override
	public void undo() {
		shapeInfo.shapeList.removeShape(shape);
//		shapeInfo.shapeList.update();
	}

	@Override
	public void redo() {
		shapeInfo.shapeList.addShape(shape);
//		shapeInfo.shapeList.update();
	}

}
