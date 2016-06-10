package tdlm.data;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import tdlm.gui.Workspace;
import saf.components.AppDataComponent;
import saf.AppTemplate;

/**
 * This class serves as the data management component for this application.
 *
 * @author Richard McKenna
 * @version 1.0
 */
public class DataManager implements AppDataComponent {
    // FIRST THE THINGS THAT HAVE TO BE SAVED TO FILES
    
    // NAME OF THE TODO LIST
    StringProperty name;
    
    // LIST OWNER
    StringProperty owner;
    
    // THESE ARE THE ITEMS IN THE TODO LIST
    ObservableList<ToDoItem> items;
    
    // THIS IS A SHARED REFERENCE TO THE APPLICATION
    AppTemplate app;
    Workspace workspace;
    boolean changed;
    
    /**
     * THis constructor creates the data manager and sets up the
     *
     *
     * @param initApp The application within which this data manager is serving.
     */
    public DataManager(AppTemplate initApp) throws Exception {
	// KEEP THE APP FOR LATER
	app = initApp;
        items = FXCollections.observableArrayList();
        name = new SimpleStringProperty();
        owner = new SimpleStringProperty();
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        changed = false;
        
    }
    
    public ObservableList<ToDoItem> getItems() {
	return items;
    }
    
    public String getName() {
        return name.get();
    }
    
    public String getOwner() {
        return owner.get();
    }

    public void addItem(ToDoItem item) {
        items.add(item);
    }
    public void setName(String myName) {
        name.set(myName);
    }
    public void setOwner(String myOwner) {
        owner.set(myOwner);
    }
    public Workspace getWorkspace() {
        return workspace;
    }
    public void changed(boolean chang) {
        changed = chang;
    }
    public boolean getChanged(){
        return changed;
    }

    /**
     * 
     */
    @Override
    public void reset() {
        items.clear();
        setName(getName());
        setOwner(getOwner());
    }

   
}
