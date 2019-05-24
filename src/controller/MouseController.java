package controller;
import java.awt.event.MouseEvent;
import controller.commands.CreateCommand;
import controller.commands.MoveCommand;
import controller.commands.SelectCommand;
import controller.interfaces.ICommand;
import controller.interfaces.IShapeListObserver;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;

import model.ShapeInfo;
import model.ShapeType;
import model.persistence.ApplicationState;
import view.gui.Shape;
import view.interfaces.PaintCanvasBase;

public class MouseController extends MouseAdapter { 
	Graphics2D g;
	ICommand command;
	Point startP, endP;
	ShapeList shapeList;
	ShapeType shapeType;
	ShapeInfo shapeInfo;
	ApplicationState appState;
	PaintCanvasBase paintCanvas;
	
	public MouseController(PaintCanvasBase paintCanvas, ApplicationState appState, ShapeList shapeList) {
		this.appState = appState;
		this.shapeList = shapeList;
		this.paintCanvas = paintCanvas;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		startP = new Point(e.getX(), e.getY());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		endP = new Point(e.getX(), e.getY());
		
		g = paintCanvas.getGraphics2D();
		shapeInfo = new ShapeInfo(g, appState, paintCanvas, shapeList);
        shapeInfo.setAllShapeInfo();
        
      IShapeListObserver observer = new Shape(startP, endP, shapeInfo);
      shapeList.registerObserver(observer);
        
		switch (appState.getActiveStartAndEndPointMode().toString()) {
			case "DRAW":
				command = new CreateCommand(startP, endP, shapeInfo);
				break;
		
			case "SELECT":
				command = new SelectCommand(startP, endP, shapeInfo);
				break;
				
			case "MOVE":
				command = new MoveCommand(startP, endP, shapeInfo);
				break;
		}
		
		command.run();

	}


}


