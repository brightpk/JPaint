package controller.commands;

import java.util.ArrayList;

import controller.ShapeList;
import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import view.gui.Shape;
import view.interfaces.IShape;

public class PasteCommand implements ICommand, IUndoable{
	ShapeList shapeList;
	ArrayList<IShape> pasteList;
	IShape currShape, prevShape, pasteShape;
	
	public PasteCommand(ShapeList shapeList) {
		this.shapeList = shapeList;
		pasteList = new ArrayList<IShape>();
	}
	
	/* Make a new shape and add it into the shapeList 
	 * if you just assign new IShape to the shape in copylist 
	 * it points to the its address 
	 */
	
	@Override
	public void run() {
		System.out.println("PASTE SHAPE...");
		
		for (IShape s: shapeList.getCopyList()) {
			int offset = 50;
			pasteShape = new Shape(s.getStartPoint(), s.getEndPoint(), s.getShapeInfo());
			pasteShape.setStartPoint(pasteShape.getStartPoint().getX() + offset, pasteShape.getStartPoint().getY() + offset);
			pasteShape.setEndPoint(pasteShape.getEndPoint().getX() + offset, pasteShape.getEndPoint().getY() + offset);
			pasteList.add(pasteShape);	
			shapeList.addShape(pasteShape);
		}
			
		shapeList.dash(shapeList.getCopyList());		
		CommandHistory.add(this);
		
		System.out.println("Paste # of shapes: " + shapeList.getCopySize() + "\n");
	}

	@Override
	public void undo() {
		for (IShape s: pasteList) {
			shapeList.removeShape(s);
		}
		
		shapeList.dash(shapeList.getCopyList());
		
	}

	@Override
	public void redo() {
		for (IShape s: pasteList) {
			shapeList.addShape(s);
		}
		
		shapeList.dash(shapeList.getCopyList());
	}
	
	

}
