package insulinadjustmentcalc;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javax.swing.JOptionPane;

/**
 * This program implements a graphical user interface through various class
 * that use javaFx. It also implements a Node class that creates node objects, 
 * a queue class that assigns the nodes to the desired queue, and a file class
 * that handles I/O features that are needed for the program.
 * 
 * This class displays the main menu. It also works as the directory for the 
 * other display classes. Every class that displays GUI objects returns to this
 * page to be displayed.
 * @author trevor
 */
public class InsulinAdjustmentCalc extends Application{
    public static void main(String[]args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
       TextField instruction = new TextField("What would you like to do?");
       instruction.setEditable(false);
       //Stage primaryStage = (Stage) settings.getScene.getView();
       Button calBut = new Button("Calculate");
       Button retBut = new Button("Retrieve");
       Button exitBut = new Button("Exit");
       Button adBut = new Button("Add Item");
       Pane mPane = new Pane();
       
       // a handler setting on the action of each button
       //The calBut is set on action to call the calculateDis window
       calBut.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                //An instace of CalculateDis
                CalculateDis cDis = new CalculateDis();
                try{
                cDis.start(primaryStage);
                }
                catch(Exception e){
                    if(e instanceof NullPointerException){
                        JOptionPane.showMessageDialog(null, e);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "ERROR\n" + e);
                }
            }
        });
       
       // The retBut is set on action to call the retDis window
       retBut.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                //An instace of CalculateDis
                retDis rDis = new retDis();
                try{
                rDis.start(primaryStage);
                }
                catch(Exception e){
                    if(e instanceof NullPointerException){
                        JOptionPane.showMessageDialog(null, e);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "ERROR\n" + e);
                }
            }
        });
       
       // The adBut is set on action to call the addItemDis window
       adBut.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                //An instace of CalculateDis
                addItemDis aIDis = new addItemDis();
                try{
                aIDis.start(primaryStage);
                }
                catch(Exception e){
                    if(e instanceof NullPointerException){
                        JOptionPane.showMessageDialog(null, e);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "ERROR\n" + e);
                }
            }
        });
       
       // exitBut handler exits the program
       exitBut.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                System.exit(0);
            }
        });
        StackPane root = new StackPane();
        
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(instruction);
       
        // hBox is the box that holds all the information.  
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(calBut, retBut, adBut);
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(exitBut);
        
        root.getChildren().add(vBox);
        
        // Top level container for all view content
        Scene scene = new Scene(root, 800, 700);
        
        primaryStage.setTitle("Insulin Adjustment Calculator");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }
}
