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
import tdlm.dialog.EditYesNo;
import tdlm.dialog.RemoveYesNo;

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
    AppMessageDialogSingleton error;
    int position;
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
        if(myDiag.getSelection().compareToIgnoreCase(props.getProperty(PropertyType.YES)) ==0 && myDiag.getToDid()) {
            myToDo = myDiag.getToDo();
            manager.addItem(myToDo);
            workspace.enableRemove();
            if(manager.getName() != null || manager.getName() != "") {
            app.getGUI().updateToolbarControls(false);
            }
         /*   if(manager.getName() == null) {
                app.getGUI().updateToolbarControls(true);
            }*/
        }  if(!myDiag.getToDid()) {
            AppMessageDialogSingleton error = AppMessageDialogSingleton.getSingleton();
            Stage newerStage = new Stage();
            error.show(props.getProperty(PropertyType.USER_DENIED), props.getProperty(PropertyType.NO_WORK));
        }
        
    }
    
    public void processRemoveItem(ToDoItem editor) {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
	workspace.reloadWorkspace();
        DataManager manager = (DataManager)app.getDataComponent();
        if(manager.getName() != null || manager.getName() != "") {
            app.getGUI().updateToolbarControls(false);
            }
      /*  if(manager.getName() == null) {
                app.getGUI().updateToolbarControls(true);
            }*/
        AppYesNoCancelDialogSingleton myDiag = AppYesNoCancelDialogSingleton.getSingleton();
        Stage newStage = new Stage();
        
           
        myDiag.show(props.getProperty(PropertyType.REMOVE_ITEM),props.getProperty(PropertyType.REMOVE_ITEM) );
       // ToDoItem myToDo;
       // myToDo = manager.getItems().get(position);
        
        if(myDiag.getSelection().compareToIgnoreCase(props.getProperty(PropertyType.YES)) ==0) {
            manager.getItems().remove(editor);
           position = manager.getItems().lastIndexOf(editor);
            manager.getItems().remove(editor);
            if(manager.getName() != null || manager.getName() != "") {
            app.getGUI().updateToolbarControls(false);
            }
          /*  if(manager.getName() == null) {
                app.getGUI().updateToolbarControls(true);
            }*/
            if(manager.getItems().size() <1) {
                workspace.disableRemove();
            /*    if(manager.getName() != null || manager.getName() != "") {
            app.getGUI().updateToolbarControls(true);
            }*/
            }
            
        } /* if(position == -1) {
            AppMessageDialogSingleton error = AppMessageDialogSingleton.getSingleton();
            Stage newerStage = new Stage();
            error.show(props.getProperty(PropertyType.USER_DENIED), props.getProperty(PropertyType.NO_CHANGE));
        }*/
       // manager.getItems().set(position, editor);
    }
    
    public void processMoveUpItem(ToDoItem editor) {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
	workspace.reloadWorkspace();
        DataManager manager = (DataManager)app.getDataComponent();
        ToDoItem temp = manager.getItems().get(manager.getItems().indexOf(editor)-1); 
        int editorIndex = manager.getItems().indexOf(editor);
        int tempIndex = manager.getItems().indexOf(temp);
        manager.getItems().set(tempIndex, editor);
        manager.getItems().set(editorIndex, temp);
        if(manager.getName() != null || manager.getName() != "") {
            app.getGUI().updateToolbarControls(false);
            }
    /*    if(manager.getName() == null) {
                app.getGUI().updateToolbarControls(true);
            }*/
    }
    public void checkDisables(ToDoItem editor) {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
	
        DataManager manager = (DataManager)app.getDataComponent();
        
        if(manager.getItems().indexOf(editor) == 0 &&manager.getItems().indexOf(editor) <1) {
            
            workspace.disableUp();
        }
        if(manager.getItems().indexOf(editor)+1 == manager.getItems().size()) {
            
            workspace.disableDown();
        }
        if(manager.getItems().indexOf(editor) +1 != manager.getItems().size()) {
            
            workspace.enableDown();
        }
        if(manager.getItems().indexOf(editor) >0 && manager.getItems().indexOf(editor)  != 0) {
            
            workspace.enableUp();
        }
        if(manager.getItems().indexOf(editor) ==-1) {
            workspace.disableUp();
            workspace.disableDown();
        }
    }
    public void pressName(String name) {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        DataManager manager = (DataManager)app.getDataComponent();
        manager.setName(name);
    }
    public void pressOwner(String owner) {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        DataManager manager = (DataManager)app.getDataComponent();
        manager.setOwner(owner);
    }
    
    public void processMoveDownItem(ToDoItem editor) {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
	workspace.reloadWorkspace();
        DataManager manager = (DataManager)app.getDataComponent();
        ToDoItem temp = manager.getItems().get(manager.getItems().indexOf(editor)+1); 
        int editorIndex = manager.getItems().indexOf(editor);
        int tempIndex = manager.getItems().indexOf(temp);
        manager.getItems().set(tempIndex, editor);
        manager.getItems().set(editorIndex, temp);
        if(manager.getName() != null || manager.getName() != "") {
            app.getGUI().updateToolbarControls(false);
            }
   /*     if(manager.getName() == null) {
                app.getGUI().updateToolbarControls(true);
            } */
    }
    
    public void processEditItem(ToDoItem editor) {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
	workspace.reloadWorkspace();
        EditYesNo myDiag = EditYesNo.getSingleton();
        Stage newStage = new Stage();
        myDiag.init(newStage);
        myDiag.setOld(editor);
           
        myDiag.show(props.getProperty(PropertyType.EDIT_ITEM));
       // ToDoItem myToDo;
        DataManager manager = (DataManager)app.getDataComponent();
       // myToDo = manager.getItems().get(position);
        
        if(myDiag.getSelection().compareToIgnoreCase(props.getProperty(PropertyType.YES)) ==0 && myDiag.getToDid()) {
           position = manager.getItems().lastIndexOf(editor);
            editor = myDiag.getToDo();
            if(manager.getName() != null || manager.getName() != "") {
            app.getGUI().updateToolbarControls(false);
            }
         /*   if(manager.getName() == null) {
                app.getGUI().updateToolbarControls(true);
            }*/
            
        }  if(!myDiag.getToDid() || position == -1) {
            AppMessageDialogSingleton error = AppMessageDialogSingleton.getSingleton();
            Stage newerStage = new Stage();
            error.show(props.getProperty(PropertyType.USER_DENIED), props.getProperty(PropertyType.NO_WORK));
        }
        manager.getItems().set(position, editor);
    }
}
