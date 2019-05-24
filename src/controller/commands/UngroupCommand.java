package controller.commands;

import controller.ShapeList;
import controller.interfaces.ICommand;
import view.interfaces.IShape;

public class UngroupCommand implements ICommand {
	ShapeList shapeList;
	public UngroupCommand(ShapeList shapeList) {
		this.shapeList = shapeList;
	}

	@Override
	public void run() {
		System.out.println("UNGROUP SHAPE...");
		for (IShape s: shapeList.getSelectList()) {
			if (shapeList.getGroupList().contains(s)) {
				shapeList.removeGroup(s);
			} 
		}
		
		System.out.println("Ungroup # of shapes: " + shapeList.getGroupSize() + "\n");
	}
	
	

}
