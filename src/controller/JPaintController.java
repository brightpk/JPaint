package controller;

import controller.commands.CopyCommand;
import controller.commands.DeleteCommand;
import controller.commands.GroupCommand;
import controller.commands.PasteCommand;
import controller.commands.RedoCommand;
import controller.commands.UndoCommand;
import controller.commands.UngroupCommand;
import controller.interfaces.IJPaintController;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
	private final ShapeList shapeList;
    private final IUiModule uiModule;
    private final IApplicationState applicationState;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, ShapeList shapeList) {
        this.uiModule = uiModule;
        this.shapeList = shapeList;
        this.applicationState = applicationState;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.COPY, () -> new CopyCommand(shapeList).run());
        uiModule.addEvent(EventName.PASTE, () -> new PasteCommand(shapeList).run());
        uiModule.addEvent(EventName.DELETE, () -> new DeleteCommand(shapeList).run());
        uiModule.addEvent(EventName.UNDO, () -> new UndoCommand().run());
        uiModule.addEvent(EventName.REDO, () -> new RedoCommand().run());
        uiModule.addEvent(EventName.GROUP, () -> new GroupCommand(shapeList).run());
        uiModule.addEvent(EventName.UNGROUP, () -> new UngroupCommand(shapeList).run());
    }
}
