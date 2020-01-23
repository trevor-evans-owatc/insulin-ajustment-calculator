package insulinadjustmentcalc;
import java.io.*;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.geometry.Insets;
import javax.swing.JOptionPane;
/**
 * The addItemDis class handles the display of, and the event handler to, add
 * new menu items to the storage files that are used in the function of the 
 * insulinAdjustmentCalc.
 * @author trevo
 */
public class addItemDis extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Stage stage = new Stage();
        stage.setTitle("Add an Item to the menu");
        // Create the objects for the GUI
        VBox disPane = new VBox();
        Label typLab = new Label("Item type");
        Label nmLab = new Label("Food Name");
        Label crbLab = new Label("Carohydrates");
        TextField nmFld = new TextField();
        nmFld.setEditable(true);
        TextField crbFld = new TextField();
        crbFld.setEditable(true);
        
        // Create a toggle group and radio buttons. The handler for these
        // buttons sets the instance of the fileHandler class to write to.
        ToggleGroup toggle = new ToggleGroup();
        
        RadioButton mainBut = new RadioButton("Main Dish");
        mainBut.setToggleGroup(toggle);
        RadioButton sideBut = new RadioButton("Sides");
        sideBut.setToggleGroup(toggle);
        RadioButton bevBut = new RadioButton("Beverages");
        bevBut.setToggleGroup(toggle);
        mainBut.setSelected(false);
        sideBut.setSelected(false);
        bevBut.setSelected(false);
        
        VBox radBox = new VBox();
        radBox.getChildren().addAll(mainBut, sideBut, bevBut);
        VBox entryBox = new VBox();
        
        // A HBox to hold name entry text field and lable nodes
        HBox nmBx = new HBox();
        nmBx.getChildren().addAll(nmLab, nmFld);
        nmBx.setPadding(new Insets(15));
        
        // A HBox to hold carb entry and text field nodes 
        HBox crbBx = new HBox();
        crbBx.getChildren().addAll(crbLab, crbFld);
        crbBx.setPadding(new Insets(15));
        
        // entrybox holds crbBx and nmBx
        entryBox.getChildren().addAll(nmBx, crbBx);
        // Create two non-toggle buttons one to submit the information,
        // the other for redirction when the suer has finished on this page.
        Button subBut = new Button("Submit");
        Button donBut = new Button("done");
        
        // topBox holds entryBox and radBox
        HBox topBox = new HBox();
        topBox.getChildren().addAll(radBox, entryBox);
        topBox.setAlignment(Pos.CENTER);
        
        // butBx holds the non-radio buttons
        HBox butBx = new HBox();
        butBx.getChildren().addAll(subBut,donBut);
        butBx.setAlignment(Pos.CENTER);
        
        disPane.getChildren().addAll(topBox, butBx);
        
        // Create event handlers for the non toggled buttons
       donBut.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                stage.close();
            }
        });
        
        // This hadles the evnet that subBut is clicked
        subBut.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                boolean isInt = true; // turns false if non digit in crbFld
               // Enusre that a radio button has been selected
               if(mainBut.isSelected() || sideBut.isSelected() || 
                       bevBut.isSelected()){
                    String carbSt = crbFld.getText();
                    String nameSt = nmFld.getText();
              
                    // Ensure that both strings are the correct values
                    if(!nameSt.isEmpty()){
                        if(!carbSt.isEmpty()){
                            carbSt = carbSt.trim();
                            char[] carbStArr = new char[carbSt.length()];
                            
                            // make sure all characters are integers
                            for(int v = 0; v < carbStArr.length; v++){
                                carbStArr[v] = carbSt.charAt(v);
                                if(!Character.isDigit(carbStArr[v])){
                                   isInt = false;
                                   break;
                                }
                            }
                            if(isInt == true){
                               // If the texts pass all the tests append the
                               // information to the appropreate text file
                               //Scanner input = new Scanner(nameSt);
                               String appendSt = ""; //input.next();
                               appendSt += nameSt.toString();
                               if (appendSt.contains(" ")){
                                   appendSt.replace(" ", "_");
                               }
                                   
                               
                               // Append a space then the carbSt object
                               appendSt += (" " + carbSt.toString());
                               
                               // Use an instance of the FileHandler class
                               // to append the appendSt to the selected file.
                               FileHandler handel = new FileHandler();
                               // Identify the selected file 
                               if(mainBut.isSelected()){
                                   try{
                                    handel.putFile("nbproject/txtFile/MenuMain.txt",
                                            appendSt 
                                            );
                                   }
                                   catch(Exception e){
                                        if(e instanceof IOException){
                                        JOptionPane.showMessageDialog(null,
                                                "Whout oh something went wrong with the "
                                                        + "file\nDon't worry it's not you"
                                                                + " its me.");
                                        }
                                    }
                               }
                               else if(sideBut.isSelected()){
                                   try{
                                    handel.putFile(
                                            "nbproject/txtFile/MenuSides.txt",
                                            appendSt);
                                   }
                                   catch(Exception e){
                                        if(e instanceof IOException){
                                        JOptionPane.showMessageDialog(null,
                                            "Whout oh something went wrong with the "
                                               + "file\nDon't worry it's not you"
                                               + " its me.");
                                            }
                                       }
                               }
                               else if(bevBut.isSelected()){
                                    try{
                                    handel.putFile( 
                                            "nbproject/txtFile/beverage.txt",
                                            appendSt);
                                   }
                                   catch(Exception e){
                                        if(e instanceof IOException){
                                        JOptionPane.showMessageDialog(null,
                                            "Whout oh something went wrong with the "
                                                + "file\nDon't worry it's not you"
                                                + " its me.");
                                        }
                                    }
                               }
                            }
                            else{
                                //inform the user that they have an integer 
                                // value for the number of carbs and rebuild
                                JOptionPane.showMessageDialog(null, "You must "
                                        + "enter an integer value in the "
                                        + "field for carbohydrates.");
                                Rebuild();
                            }
                        }
                        else {
                            //inform the user that a value must be entered in
                            // the text field
                            JOptionPane.showMessageDialog(null, "There wasn't "
                                    + " a value enterd in for the number of "
                                    + "carbohydrates.\nPlease be sure to enter"
                                    + "a integer value for the number of carb.");
                            Rebuild();
                        }
                    }
                    else {
                        // inform the user the food item needs to be named
                        JOptionPane.showMessageDialog(null, "The item needs "
                                + "to be named.\nPlease ensure you enter a name"
                                + " for the food item.");
                        Rebuild();
                    }
                }
                else{
                    //Inform the user a radiio button must be 
                    // selected.
                    JOptionPane.showMessageDialog(null, "A menu"
                           + "Item was not selected\n"
                           + "Please ensure you select a menu"
                           + " item");

                    // Redisplay the the pain is its original 
                    // state 
                    Rebuild();
                }
            }
            
            // An inner class that rebuilds the GUI
            public void Rebuild() {
                 // Redisplay the the pain is its original 
                // state 
                crbFld.clear();
                nmFld.clear();
               
                radBox.getChildren().addAll(mainBut, sideBut, bevBut);

                nmBx.getChildren().addAll(nmLab, nmFld);

                crbBx.getChildren().addAll(crbLab, crbFld);

                entryBox.getChildren().addAll(nmBx, crbBx);

                topBox.getChildren().addAll(radBox, entryBox);

                
                butBx.getChildren().addAll(subBut,donBut);
                
                disPane.getChildren().clear();
                disPane.getChildren().addAll(topBox, butBx);
            }
        });
        
        disPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(disPane, 800, 700);
        stage.setScene(scene);
        stage.show();
    }   
}
