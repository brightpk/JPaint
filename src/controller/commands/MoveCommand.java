package controller.commands;

import java.util.ArrayList;
import controller.Point;
import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.ShapeInfo;
import view.interfaces.IShape;

public class MoveCommand implements ICommand, IUndoable {
	Point startP, endP;
	int deltaX, deltaY;
	ShapeInfo shapeInfo;
	IShape prevShape, movedShape;
	ArrayList<IShape> movedList;
	ArrayList<IShape> prevList;

	public MoveCommand(Point startP, Point endP, ShapeInfo shapeInfo) {
		this.endP = endP;
		this.startP = startP;
		this.shapeInfo = shapeInfo;
		movedList = new ArrayList<IShape>();
		prevList = new ArrayList<IShape>();
	}

	/* Delete previous shapes in the master shape list
	 * then add new moved shape which are from selected list 
	 * by adding delta X and delta y to startPoint and endPoint of selected shapes 
	 */

	@Override
	public void run() {	
		System.out.println("MOVE SHAPE...");
		
		deltaX = endP.getX() - startP.getX();
		deltaY = endP.getY() - startP.getY();
		
		for (IShape s: shapeInfo.shapeList.getSelectList()) {
			shapeInfo.shapeList.removeShape(s);	
			movedShape = s;
			movedShape.setStartPoint(movedShape.getStartPoint().getX() + deltaX, movedShape.getStartPoint().getY() + deltaY);
			movedShape.setEndPoint(movedShape.getEndPoint().getX() + deltaX, movedShape.getEndPoint().getY() + deltaY);
			movedList.add(movedShape);
			shapeInfo.shapeList.addShape(movedShape);
		}
		
		shapeInfo.shapeList.dash(movedList);
		//movedList.clear();
		
		CommandHistory.add(this);
		System.out.println("Move # of shapes: " + movedList.size() + "\n");
	}

	@Override
	public void undo() {	
		for (IShape s: shapeInfo.shapeList.getSelectList()) {
			s.setStartPoint(s.getStartPoint().getX() - deltaX, s.getStartPoint().getY() - deltaY);
			s.setEndPoint(s.getEndPoint().getX() - deltaX, s.getEndPoint().getY() - deltaY);
			shapeInfo.shapeList.notifyObservers();
		}
	
		shapeInfo.shapeList.dash(movedList);
	}

	@Override
	public void redo() {
		for (IShape s: shapeInfo.shapeList.getSelectList()) {
			s.setStartPoint(s.getStartPoint().getX() + deltaX, s.getStartPoint().getY() + deltaY);
			s.setEndPoint(s.getEndPoint().getX() + deltaX, s.getEndPoint().getY() + deltaY);
			shapeInfo.shapeList.notifyObservers();
		}
		
		shapeInfo.shapeList.dash(movedList);
	}

}
