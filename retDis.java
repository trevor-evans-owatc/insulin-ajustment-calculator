package insulinadjustmentcalc;
import java.util.*;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javax.swing.JOptionPane;

/**
 * The retDis class gets the data from the records file and displays it in an
 * interactive GUI.
 * @author trevo
 */
public class retDis extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
       Stage stage = new Stage();
       stage.setTitle("Entry Logs");
       // Create a vBox to hold each list with in it.
       VBox dateBox = new VBox();
       VBox BGbox = new VBox();
       VBox CHObox = new VBox();
       VBox BGdoseBox = new VBox();
       VBox CHOdoseBox = new VBox();
       VBox totalDoseBox = new VBox();
        
       
       // Add the lables to each box
       dateBox.setPadding(new Insets(15));
       dateBox.getChildren().add(new Label("Date"));
       
       BGbox.setPadding(new Insets(15));
       BGbox.getChildren().add(new Label("BGL"));
       
       CHObox.setPadding(new Insets(15));
       CHObox.getChildren().add(new Label("CHO"));
       
       BGdoseBox.setPadding(new Insets(15));
       BGdoseBox.getChildren().add(new Label("BGL dose"));
       
       CHOdoseBox.setPadding(new Insets(15));
       CHOdoseBox.getChildren().add(new Label("CHO dose"));
       
       totalDoseBox.setPadding(new Insets(15));
       totalDoseBox.getChildren().add(new Label("Total"));
       
       // add a new text field to each box to hold each value.
       // After a new text field is initialized add it to the proper vbox.
       
       
       // open the reocrds file by creating an instance of Filehandler and 
       // calling its getstack class. Build a stack object to hold data.
       FileHandler records = new FileHandler();
       linkedStack recStack = 
               records.getStack("nbproject/txtFile/bglRecords.txt");
       
       LinkedList<String> ln = new LinkedList<String>();
       
       while(!recStack.isNextEmpty())
           ln.add(recStack.pop());
       // The fallowing are multiple textfield arrays to sort each piece of 
       // information into indevigual txt fields
       TextField[] dtField = new TextField[ln.size()];
       TextField[] BGField = new TextField[ln.size()];
       TextField[] CHOField = new TextField[ln.size()];
       TextField[] BGDsField = new TextField[ln.size()];
       TextField[] CHDsField = new TextField[ln.size()];
       TextField[] TltField = new TextField[ln.size()];
       
       // read each node from the stack and tokenize. Each token is assinged to
       // a new text field node. Add each node to its corrisponding vbox.
       
       for (int c = 0; c < ln.size(); c++){
           // Tokenize the line
           if(ln.get(c).contains(" ")){
               String[] tks = ln.get(c).split(" ");
               // each tks gets put into their own text field
               if (tks.length == 6){ //assures the correct number of tokens
                   dtField[c] = new TextField(tks[0]);
                   dtField[c].setEditable(false);
                   dateBox.getChildren().add(dtField[c]);

                   BGField[c] = new TextField(tks[1]);
                   BGField[c].setEditable(false);
                   BGbox.getChildren().add(BGField[c]);

                   CHOField[c] = new TextField(tks[2]);
                   CHOField[c].setEditable(false);
                   CHObox.getChildren().add(CHOField[c]);

                   BGDsField[c] = new TextField(tks[3]);
                   BGDsField[c].setEditable(false);
                   BGdoseBox.getChildren().add(BGDsField[c]);

                   CHDsField[c] = new TextField(tks[4]);
                   CHDsField[c].setEditable(false);
                   CHOdoseBox.getChildren().add(CHDsField[c]);

                   TltField[c] = new TextField(tks[5]);
                   TltField[c].setEditable(false);
                   totalDoseBox.getChildren().add(TltField[c]);
               }
               else{ // If the numbers of tokens to not match an error occured
                   JOptionPane.showMessageDialog(null, 
                           "The records file sent a line back that had "
                           + "value who did not match the expected format.");
                   System.exit(1);
               }
           }
       }
       // Add the VBoxses to an HBox in the order they are to be presented
       HBox disBox = new HBox();
       disBox.getChildren().addAll(dateBox, BGbox, CHObox, BGdoseBox,
               CHOdoseBox, totalDoseBox);
       
       // Add disBox to a scroll pane
       ScrollPane sBox = new ScrollPane();
       sBox.setContent(disBox);
       
       // Build a done button with a button handler that clears this display
       Button doneBut = new Button("Done");
       
       // Add sBox and doneBut to a vBox then seed that in disPane
       VBox contBox = new VBox();
       contBox.setAlignment(Pos.CENTER);
       contBox.getChildren().addAll(sBox, doneBut);
       
        doneBut.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                // If the donBut is clicked simply clear the calc pane
                // and return
                stage.close();
            }
        });
       
        // Set a scene and show the stage
       Scene scene = new Scene(contBox, 800, 700);
       stage.setScene(scene);
       stage.show();
    }
}
