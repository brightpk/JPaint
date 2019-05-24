package controller.commands;

import java.util.ArrayList;

import controller.ShapeList;
import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import view.interfaces.IShape;

public class GroupCommand implements ICommand, IUndoable {
	ShapeList shapeList;
	ArrayList<IShape> groupShape;
	
	public GroupCommand(ShapeList shapeList) {
		this.shapeList = shapeList;
		groupShape = new ArrayList<IShape>();
	}
	
	@Override
	public void run() {
		System.out.println("GROUP SHAPE...");
		
		for (IShape s: shapeList.getSelectList()) {
			if (shapeList.getGroupList().contains(s)) {
				System.out.println("Already in the group");
			} else {
				groupShape.add(s);
				shapeList.addGroup(s);
				
			}
		}
		
		System.out.println("Group # of shapes: " + shapeList.getGroupSize() + "\n");
		
	}

	@Override
	public void undo() {
		for (IShape s: groupShape) {
			shapeList.removeShape(s);
		}
		shapeList.dash(groupShape);
	}

	@Override
	public void redo() {
		for (IShape s: groupShape) {
			shapeList.addShape(s);
		}
		
		shapeList.dash(groupShape);
		
	}

}
