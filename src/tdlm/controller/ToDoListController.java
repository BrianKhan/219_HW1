package tdlm.controller;

import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import tdlm.data.DataManager;
import tdlm.gui.Workspace;
import saf.AppTemplate;
import saf.ui.AppMessageDialogSingleton;
import saf.ui.AppYesNoCancelDialogSingleton;
import tdlm.data.ToDoItem;
import tdlm.dialog.AddYesNoCancel;

/**
 * This class responds to interactions with todo list editing controls.
 * 
 * @author McKillaGorilla
 * @version 1.0
 */
public class ToDoListController {
    AppTemplate app;
    AddYesNoCancel myDiag;
    public ToDoListController(AppTemplate initApp) {
	app = initApp;
        
        
    }
    
    public void processAddItem() {	
	// ENABLE/DISABLE THE PROPER BUTTONS
	Workspace workspace = (Workspace)app.getWorkspaceComponent();
	workspace.reloadWorkspace();
        AddYesNoCancel myDiag = AddYesNoCancel.getSingleton();
        Stage newStage = new Stage();
        myDiag.init(newStage);
        myDiag.show("New ToDo Item");
        ToDoItem myToDo;
        DataManager manager = (DataManager)app.getDataComponent();
        if(myDiag.getSelection() == "Yes") {
            myToDo = myDiag.getToDo();
            manager.addItem(myToDo);
        }
        
    }
    
    public void processRemoveItem() {
        
    }
    
    public void processMoveUpItem() {
        
    }
    
    public void processMoveDownItem() {
        
    }
    
    public void processEditItem() {
        
    }
}
