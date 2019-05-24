package controller;

import java.util.ArrayList;

import controller.interfaces.IShapeListObserver;
import controller.interfaces.IShapeListSubject;
import view.gui.DashDecorator;
import view.gui.Shape;
import view.interfaces.IShape;

public class ShapeList implements IShapeListSubject/*, IShapeListObserver*/ {
	ArrayList<IShape> shapeList;
	ArrayList<IShape> selectList;
	ArrayList<IShape> copyList;
	ArrayList<IShapeListObserver> observers;
	ArrayList<IShape> groupList;
	
	public ShapeList() {
		copyList = new ArrayList<IShape>();
		shapeList = new ArrayList<IShape>();
		selectList = new ArrayList<IShape>();
		observers = new ArrayList<IShapeListObserver>();
		groupList = new ArrayList<IShape>();
	}
	
	/* MASTER SHAPE LIST*/
	public void addShape(IShape s) {
		this.shapeList.add(s);
		notifyObservers();	
	}
	
	public void removeShape(IShape s) {
		this.shapeList.remove(s);
		notifyObservers();
	}
	
	public ArrayList<IShape> getShapeList() {
		return shapeList;
	}

	public int getSize() {
		return shapeList.size();
	}
	
	/* SELECT LIST */
	public void addSelected(IShape s) {
		this.selectList.add(s);
		notifyObservers();
		dash(selectList);
	}
	
	public void removeSelected(IShape s) {
		this.selectList.remove(s);
		notifyObservers();
	}
	
	public ArrayList<IShape> getSelectList() {
		return selectList;
	}
	
	public int getSelectSize() {
		return selectList.size();
	}
	
	public void dash(ArrayList<IShape> lst) {		
		for (IShape s: lst) {	
			IShape dashShape = new DashDecorator(new Shape (s.getStartPoint(), s.getEndPoint(), s.getShapeInfo()));
			dashShape.makeShape();
		}
	}
	
	/* COPY LIST */
	public void addCopy(IShape s) {
		this.copyList.add(s);
		dash(copyList);
	}
	
	public void removeCopy(IShape s) {
		this.copyList.remove(s);
	}
	
	public ArrayList<IShape> getCopyList() {
		return copyList;
	}
	
	public int getCopySize() {
		return copyList.size();
	}
	
	@Override
	public void registerObserver(IShapeListObserver observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(IShapeListObserver observer) {
		observers.remove(observer);
	}
	
	@Override
	public void notifyObservers() {
		System.out.println("notify...");
		for(IShapeListObserver observer : observers) {
			observer.update();
		}
	}
	
	/* GROUP LIST */
	public void addGroup(IShape s) {
		this.groupList.add(s);
		dash(groupList);
	}
	
	public void removeGroup(IShape s) {
		this.groupList.remove(s);
	}
	
	public ArrayList<IShape> getGroupList() {
		return groupList;
	}
	
	public int getGroupSize() {
		return groupList.size();
	}
	

}
