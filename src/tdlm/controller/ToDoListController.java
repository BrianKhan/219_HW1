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
import properties_manager.PropertiesManager;
import tdlm.data.DataManager;
import tdlm.gui.Workspace;
import saf.AppTemplate;
import saf.ui.AppMessageDialogSingleton;
import saf.ui.AppYesNoCancelDialogSingleton;
import tdlm.PropertyType;
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
    PropertiesManager props;
    public ToDoListController(AppTemplate initApp) {
	app = initApp;
        props = PropertiesManager.getPropertiesManager();
        
        
    }
    
    public void processAddItem() {	
	// ENABLE/DISABLE THE PROPER BUTTONS
        
	Workspace workspace = (Workspace)app.getWorkspaceComponent();
	workspace.reloadWorkspace();
        AddYesNoCancel myDiag = AddYesNoCancel.getSingleton();
        Stage newStage = new Stage();
        myDiag.init(newStage);
           
        myDiag.show(props.getProperty(PropertyType.ADD_TITLE));
        ToDoItem myToDo;
        DataManager manager = (DataManager)app.getDataComponent();
        if(myDiag.getSelection().compareToIgnoreCase(props.getProperty(PropertyType.YES)) ==0) {
            myToDo = myDiag.getToDo();
            manager.addItem(myToDo);
        } else {
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
