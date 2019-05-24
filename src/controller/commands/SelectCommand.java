package controller.commands;

import controller.Point;
import controller.interfaces.ICommand;
import model.ShapeInfo;
import view.interfaces.IShape;

public class SelectCommand implements ICommand {
	int x, y, w, h;
	Point startP, endP;
	ShapeInfo shapeInfo;
	
	public SelectCommand(Point startP, Point endP, ShapeInfo shapeInfo) {
		this.endP = endP;
		this.startP = startP;
		this.shapeInfo = shapeInfo;
	}

	@Override
	public void run() {
		System.out.println("SELECT SHAPE...");
		
		// Clear the selected list before storing new selected shapes
		shapeInfo.shapeList.getSelectList().clear();
		shapeInfo.shapeList.notifyObservers();
//		shapeInfo.shapeList.update();
		
		// selected shapes
		 x = Math.min(startP.getX(), endP.getX());
		 y = Math.min(startP.getY(), endP.getY()); 
		 w = Math.abs(endP.getX() - startP.getX());
		 h = Math.abs(endP.getY() - startP.getY());
		
		// compare with master shapes
		for (IShape s: shapeInfo.shapeList.getShapeList()) { 
//			WHEN THERE IS NO GROUP COMMAND / UNGROUP COMMAND YET
//			if (x < s.getX() + s.getWidth() && x + w > s.getX() && 
//				y < s.getY() + s.getHeight() && y + h > s.getY()) {
//				shapeInfo.shapeList.addSelected(s);
//			} 
			
//			WHEN GROUP COMMAND BUT ONLY ONE GROUP (NEED GROUPLIST IN SHAPELIST THAT COMMENT OUT)
			if (x < s.getX() + s.getWidth() && x + w > s.getX() && 
					y < s.getY() + s.getHeight() && y + h > s.getY() &&
					shapeInfo.shapeList.getGroupList().contains(s)) {
					shapeInfo.shapeList.getSelectList().clear();
					
					for (IShape g: shapeInfo.shapeList.getGroupList()) {
						shapeInfo.shapeList.addSelected(g);
					}
	
			} 
			
			else if (x < s.getX() + s.getWidth() && x + w > s.getX() && 
					   y < s.getY() + s.getHeight() && y + h > s.getY()) {
					   shapeInfo.shapeList.addSelected(s);
			} 

		}
		
		
			
		System.out.println("Select # of shapes: " + shapeInfo.shapeList.getSelectSize() + "\n");
	}


}
