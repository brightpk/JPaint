package controller.commands;

import controller.ShapeList;
import controller.interfaces.ICommand;
import view.interfaces.IShape;

public class CopyCommand implements ICommand {
	ShapeList shapeList;
	
	public CopyCommand(ShapeList shapeList) {
		this.shapeList = shapeList;
	}
	
	@Override
	public void run() {
		System.out.println("COPY SHAPE...");
		
		/* Clear the copy list before storing new selected shapes 
		 * that want to be copied and paste 
		 */
		shapeList.getCopyList().clear();
		
		for (IShape s: shapeList.getSelectList()) { 
			shapeList.addCopy(s);
		}
		
		System.out.println("Copy # of shapes: " + shapeList.getCopySize() + "\n");
		
	}
}

