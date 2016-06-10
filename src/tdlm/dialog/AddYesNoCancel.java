/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdlm.dialog;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import properties_manager.PropertiesManager;
import tdlm.PropertyType;
import tdlm.data.ToDoItem;

/**
 * This class is heavily based on AppYesNoCancelDialogSingleton 
 * with changes made to it to allow for a custom add function
 * 
 * @author Richard McKenna
 * @author Brian Khaneyan
 * @version 1.0
 */
public class AddYesNoCancel extends Stage {
    // HERE'S THE SINGLETON
    static AddYesNoCancel singleton;
    
    // GUI CONTROLS FOR OUR DIALOG
    VBox messagePane;
    
    HBox catBox;
    HBox descriptionBox;
    HBox startBox;
    HBox endBox;
    HBox completedBox;
    
    CheckBox checkField;
    
    TextField catField;
    TextField descriptionField;
   // TextField startField;
   // TextField endField;
    DatePicker startDate;
    DatePicker endDate;
    
    ToDoItem myItem;
    
    Scene messageScene;
    
    
    Label categoryLabel;
    Label descriptionLabel;
    Label startLabel;
    Label endLabel;
    Label completedLabel;
    
    Button yesButton;
    Button noButton;
  //  Button cancelButton;
    String selection;
    PropertiesManager props;
    String YES;
    String NO;
    Boolean toDid;
    // CONSTANT CHOICES

    /**
     *
     */
    

    /**
     *
     */
    public static final String CANCEL = "Cancel";
    
    /**
     * Note that the constructor is private since it follows
     * the singleton design pattern.
     * 
     * @param primaryStage The owner of this modal dialog.
     */
    private AddYesNoCancel() {
        props = PropertiesManager.getPropertiesManager();
        YES = props.getProperty(PropertyType.YES);
        NO = props.getProperty(PropertyType.NO);
        toDid = null;
    }
    
    /**
     * The static accessor method for this singleton.
     * 
     * @return The singleton object for this type.
     */
    public static AddYesNoCancel getSingleton() {
	if (singleton == null)
	    singleton = new AddYesNoCancel();
	return singleton;
    }
	
    /**
     * This method initializes the singleton for use.
     * 
     * @param primaryStage The window above which this
     * dialog will be centered.
     */
    public void init(Stage primaryStage) {
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        // CHANGED SO THAT WE ONLY SET THIS WINDOW MODAL ONCE, AS WE 
        // CAN ONLY INITIALIZE IN OUR WORKSPACE CLASS EACH TIME WE HIT THE BUTTON
        toDid = false;
        if(primaryStage.getModality().toString() != "NONE") {
            
        initModality(Modality.WINDOW_MODAL);
        
        initOwner(primaryStage);
        }
        // LABELS AND TEXT FIELDS
        categoryLabel = new Label(props.getProperty(PropertyType.CATEGORY));        
        descriptionLabel = new Label(props.getProperty(PropertyType.DESCRIPTION));
        startLabel = new Label(props.getProperty(PropertyType.START_DATE));
        endLabel = new Label(props.getProperty(PropertyType.END_DATE));
        completedLabel = new Label(props.getProperty(PropertyType.COMPLETED));
        
        catField = new TextField();
        descriptionField = new TextField();
        
       // startField = new TextField();
       // endField = new TextField();
        startDate = new DatePicker();
        endDate = new DatePicker();
        checkField  = new  CheckBox();
        
        
        // YES, NO, AND CANCEL BUTTONS
        yesButton = new Button(YES);
        noButton = new Button(NO);
        //cancelButton = new Button(CANCEL);
	
	// MAKE THE EVENT HANDLER FOR THESE BUTTONS
        EventHandler yesNoCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            AddYesNoCancel.this.selection = sourceButton.getText();
            AddYesNoCancel.this.hide();
        };
        
	// AND THEN REGISTER THEM TO RESPOND TO INTERACTIONS
        yesButton.setOnAction(yesNoCancelHandler);
        noButton.setOnAction(yesNoCancelHandler);
      //  cancelButton.setOnAction(yesNoCancelHandler);
        
        // CATEGORY HBOX
        catBox = new HBox();
        catBox.getChildren().add(categoryLabel);
        catBox.getChildren().add(catField = new TextField());
        
        // DESCRIPTION HBOX
        descriptionBox = new HBox();
        descriptionBox.getChildren().add(descriptionLabel);
        descriptionBox.getChildren().add(descriptionField);
        
        // START HBOX
        startBox = new HBox();
        startBox.getChildren().add(startLabel);
       // startBox.getChildren().add(startField);
       startBox.getChildren().add(startDate);
        
        // END HBOX
        endBox = new HBox();
        endBox.getChildren().add(endLabel);
       // endBox.getChildren().add(endField);
       endBox.getChildren().add(endDate);
        
        // COMPLETED HBOX
        completedBox = new HBox();
        completedBox.getChildren().add(completedLabel);
        completedBox.getChildren().add(checkField);
        
        
        // NOW ORGANIZE OUR BUTTONS
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(yesButton);
        buttonBox.getChildren().add(noButton);
       // buttonBox.getChildren().add(cancelButton);
        
        // WE'LL PUT EVERYTHING HERE
        messagePane = new VBox();
        messagePane.setAlignment(Pos.CENTER);
        messagePane.getChildren().add(catBox);
        messagePane.getChildren().add(descriptionBox);
        messagePane.getChildren().add(startBox);
        messagePane.getChildren().add(endBox);
        messagePane.getChildren().add(completedBox);
        
        messagePane.getChildren().add(buttonBox);
        
        // MAKE IT LOOK NICE
        messagePane.setPadding(new Insets(10, 20, 20, 20));
        messagePane.setSpacing(10);
        

        // AND PUT IT IN THE WINDOW
        messageScene = new Scene(messagePane);
        this.setWidth(350);
        this.setHeight(250);
        this.setScene(messageScene);
    }

    /**
     * Accessor method for getting the selection the user made.
     * 
     * @return Either YES, NO, or CANCEL, depending on which
     * button the user selected when this dialog was presented.
     */
    public String getSelection() {
        return selection;
    }
 
    /**
     * This method loads a custom message into the label
     * then pops open the dialog.
     * 
     * @param title The title to appear in the dialog window bar.
     * 
     * 
     */
    public ToDoItem getToDo() {
        return myItem;
    }
    public boolean getToDid() {
        return toDid;
    }
    
    public void show(String title) {
	// SET THE DIALOG TITLE BAR TITLE
	setTitle(title);
	
	// SET THE MESSAGE TO DISPLAY TO THE USER
        
	
	// AND OPEN UP THIS DIALOG, MAKING SURE THE APPLICATION
	// WAITS FOR IT TO BE RESOLVED BEFORE LETTING THE USER
	// DO MORE WORK.
        showAndWait();
        
        // CHECK TO SEE IF OUR FIELDS ARE EMPTY
        // CREATE TODO ITEM IF USER CLICKS YES
        if(getSelection() == YES &&catField.getText() != null && !catField.getText().isEmpty() && descriptionField.getText() != null && !descriptionField.getText().isEmpty() && startDate.getValue() != null && endDate.getValue() != null) {
           // System.out.println("Category value = " +catField.getText());
           // System.out.println("description value = " +descriptionField.getText());
           // System.out.println("start value = " +startDate.getValue().toString());
            // System.out.println("end value = " +endDate.getValue().toString());
            myItem = new ToDoItem(catField.getText(),descriptionField.getText(),startDate.getValue(),endDate.getValue(), checkField.isSelected());
            toDid = true;
        }
        else {
            toDid = false;
        }
    }
}