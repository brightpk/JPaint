package controller.commands;

import java.util.ArrayList;

import controller.ShapeList;
import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import view.interfaces.IShape;

public class DeleteCommand implements ICommand, IUndoable {
	ShapeList shapeList;
	IShape deleteShape;
	ArrayList<IShape> deleteList;
	
	public DeleteCommand(ShapeList shapeList) {
		this.shapeList = shapeList;
		deleteList = new ArrayList<IShape>();
	}
	
	/* Delete shape in the selectList in the ShapeList class */
	@Override
	public void run() {
		System.out.println("DELETE SHAPE...");
		
		for (IShape s: shapeList.getSelectList()) {
			shapeList.removeShape(s);
			deleteList.add(s);
		}
		
		CommandHistory.add(this);
		
		System.out.println("Delete # of shapes: " + shapeList.getSelectSize() + "\n");
		
	}

	@Override
	public void undo() {
		for (IShape s: deleteList) {
			shapeList.addShape(s);
		}

	}

	@Override
	public void redo() {
		for (IShape s: deleteList) {
			shapeList.removeShape(s);
		}
		
	}

}
