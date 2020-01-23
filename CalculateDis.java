package insulinadjustmentcalc;
import java.util.*;
import java.io.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.DatePicker;
import javafx.application.Application;

import javax.swing.JOptionPane;
import static javafx.application.Application.launch;
/**
 * This class handles the GUI of the calculation display.
 * @author trevor
 */
public class CalculateDis extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Stage stage = new Stage();
        stage.setTitle("Calculate");
        
        // Create the top section of the pane this is where the user will
        // enter information that does not pertain to the menu
        VBox adjBox = new VBox();
        TextField BSL = new TextField();
        BSL.setEditable(true);
        adjBox.getChildren().add(new Label("Enter BGL"));
        adjBox.getChildren().add(BSL);
        
        VBox dateBox = new VBox();
        DatePicker dp = new DatePicker();
        dateBox.getChildren().add(new Label("Date:"));
        dateBox.getChildren().add(dp);
        
        // a HBox to hold the adjBox and date entery information
        HBox topBox = new HBox();
        
        topBox.getChildren().addAll(adjBox, dateBox);

        // The bottom panel Contains a lable and a textbox to display the 
        // calculated insulin dose.
        HBox bottomBox = new HBox();
        TextField result = new TextField();
        result.setEditable(false);
        bottomBox.getChildren().add(new Label("Insulin Dose"));
        bottomBox.getChildren().add(result);
        
        
        FileHandler mMenuItems = new FileHandler();
        linkedStack mStack = 
                mMenuItems.getStack("nbproject/txtFile/MenuMain.txt");
        mStack = mMenuItems.alphbetized();
        
        // Create lists to hold strings and to hold integers
        List<String> mL = new LinkedList<>();
        List<Integer> mCarb = new LinkedList<>();
        HBox menuBox = new HBox();
        
        // Initualizes lists with the values with in mStack
        while (!mStack.isNextEmpty()){
            if(mStack.peek().contains(" ")){
                String[] tokens = mStack.pop().split(" ");
                mL.add(tokens[0]);
                mCarb.add(Integer.parseInt(tokens[1]));
            }
            else{ mStack.pop();}
        }
        
        // A textBox array holds each string value. They are all labled 
        // to by the string that is within the linked list.
        CheckBox[] mMCheck = new CheckBox[mL.size()];
        VBox mMBox = new VBox();
        
        // Initialize the check box array and and use each object to set 
        // contenet in the scrollPane.
        ScrollPane mnCourse = new ScrollPane();
        for (int e = 0; e < mL.size(); e++){
            mMCheck[e] = new CheckBox(mL.get(e));
            mMBox.getChildren().add(mMCheck[e]);
        }
         
        mnCourse.setContent(mMBox);
        mnCourse.setMaxSize(230, 230);
        // mnCourse is added to MenuBox
        menuBox.getChildren().addAll(mnCourse, mMBox);
        
        // Repeat the previouse process to create a sides menu and a beverage
        // menu.
        FileHandler sMenuItems = new FileHandler();
        linkedStack sStack = new linkedStack();
        sStack = sMenuItems.getStack("nbproject/txtFile/MenuSides.txt");
        sStack = sMenuItems.alphbetized();
        LinkedList<String> list = new LinkedList<String>();
        List<Integer>sCarb = new LinkedList<>();
        List<String>sL = new LinkedList<>();
        
        while (!sStack.isNextEmpty()){
            if(sStack.peek().contains(" "))
                list.add(sStack.pop());
            else{sStack.pop();}// If the value is off pop it off the stack
        }
        for (int id = 0; id < list.size(); id++){
            // The values have been checked.
            // Splitting the string and adding the values to the proper array
            String line = list.get(id);
            String[] tokens = line.split(" ");
            sL.add(tokens[0]);
            sCarb.add(Integer.parseInt(tokens[1])); 
        }
        
        // Putting the sL list values to a checkbox for each value
        VBox sidesBox = new VBox();
        CheckBox[] sMCheck = new CheckBox[sL.size()];
        for(int e = 0; e<sL.size();e++){
            sMCheck[e] = new CheckBox(sL.get(e));
            sidesBox.getChildren().add(sMCheck[e]);
        }
        
        // The checkboxes being put into a scroll pane
        ScrollPane Sides = new ScrollPane();
        Sides.setContent(sidesBox);
        Sides.setMaxSize(230, 230);
        // Sides are added to menuBox
        menuBox.getChildren().addAll(sidesBox, Sides);
        
        // The last Scrollpane will hold the beverage menu
        FileHandler BeverageItems = new FileHandler();
        linkedStack bStack = 
                BeverageItems.getStack("nbproject/txtFile/beverage.txt");
        bStack = BeverageItems.alphbetized();
        List<String> bL = new LinkedList<>();
        List<Integer> bCarb = new LinkedList<>();
        
        // Evaluates each string. The strings that are accepted are split
        // and added to one of two lists.
        while (!bStack.isNextEmpty()){
            if(bStack.peek().contains(" ")){
                String[] tokens = bStack.pop().split(" ");
                bL.add(tokens[0]);
                bCarb.add(Integer.parseInt(tokens[1]));
            }
            else{bStack.pop();}//if no space pops it off.
        }
        
        // A textBox array holds each string value. They are all labled 
        // to by the string that is within the linked list.
        CheckBox[] bCheck = new CheckBox[bL.size()];
        VBox bBox = new VBox();
        
        // Initialize the check box array and and use each object to set 
        // contenet in the scrollPane.
        ScrollPane beverages = new ScrollPane();
        
        for (int e = 0; e<bL.size(); e++){
            bCheck[e] = new CheckBox(bL.get(e));
            bBox.getChildren().add(bCheck[e]);
        }
         
        beverages.setContent(bBox);
        beverages.setMaxSize(230, 230);
        
        // mnCourse is added to MenuBox
        menuBox.getChildren().addAll(beverages, bBox);
        
        // Add a label to instruct the user
        Label instruct = new Label("Check all items to be consumed, then click"
                + " calculate.");
        
        // Add a button for calculating the total.
        Button calcBut = new Button("Calculate");
        Button donBut = new Button("Done");
        HBox butBx = new HBox();
        butBx.getChildren().addAll(calcBut, donBut);
        VBox calcBx = new VBox();
        
        calcBx.getChildren().addAll(topBox,instruct,menuBox,butBx,bottomBox);
        
        calcBut.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                // Ensure all fields have been filled out
                if (!BSL.getText().isEmpty()){
                    if(dp.getValue() != null){
                        int bldSgr;
                        int carbohydrates = 0;
                        
                        // Cycle through all the check boxes get the boxes  
                        // that are checked by calling the getCarbs method
                        carbohydrates += getCarbs(mMCheck,  mCarb);
                        carbohydrates += getCarbs(sMCheck, sCarb);
                        carbohydrates += getCarbs(bCheck, bCarb);
                        bldSgr = Integer.parseInt(BSL.getText());
                        
                        // get the calculated insulin needed
                        int tot = getDose(carbohydrates, bldSgr);
                        
                        // set text of the result text field with tot
                        result.setText(Integer.toString(tot));
                        
                        // The gathered information is added to a string builder
                        // then appended to the records file.
                        StringBuilder rec = new StringBuilder();
                        rec.append(dp.getValue().toString())
                                .append(" " +Integer.toString(carbohydrates))
                                .append(" " +Integer.toString(bldSgr))
                    /*Get the dose for only carbhydrates*/
                                .append(" " +Integer.toString(getDose(carbohydrates, 0)))
                    /*Get the dose for only bldSugar*/             
                                .append(" " +Integer.toString(getDose(0, bldSgr)))
                                .append(" " +Integer.toString(tot));
                        
                        // rec is the argument to call the a instance of
                        // Filehandler calling the put method
                        FileHandler appFile = new FileHandler();
                        try{
                        appFile.putFile("nbproject/txtFile/bglRecords.txt", 
                                rec.toString());
                        }
                        catch(Exception e){
                            if(e instanceof IOException){
                            JOptionPane.showMessageDialog(null,
                                    "Whoops something went wrong with the "
                                            + "file\nDon't worry it's not you"
                                            + " its me.");
                            }
                        }
                        
                        // Add the Dose to result text field and redisplay
                        result.setText(Integer.toString(tot));
                        
                        // Clear and rebox the bottom box items
                        bottomBox.getChildren().clear();
                        bottomBox.getChildren().add(new Label("Insulin Dose"));
                        bottomBox.getChildren().add(result);
                        
                        // Rebox tthe entire fram with the newly seeded bottom 
                        // box and return the results.
                        calcBx.getChildren().clear();
                        calcBx.getChildren().addAll(topBox,instruct,menuBox,butBx,bottomBox);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,
                            "Please enter an integer "
                                    + "value for Blood glucose lavel");
                }
                 
            }
        });
        
        donBut.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                // If the donBut is clicked simply clear the calc pane
                // and return
                stage.close();
            }
        });        
       
      
       Scene scene = new Scene(calcBx, 800, 700);
       stage.setScene(scene);
       stage.show();
    }
    
    /**
     * The getCarbs method goes through an array of check boxes and adds the 
     * coinciding carbohydrates to a single value.
     * @param cb an array of checkBox nodes
     * @param crbs A list containing integers
     * @return An integer holding the total calculated in this method.
     */
    public static int getCarbs (CheckBox[] cb, List<Integer> crbs){
        // Cycle through all the check boxes and get the values that are
        // parallel to a box that has been checked
        int carbs = 0;
        for(int i = 0; i<cb.length; i++){
            if(cb[i].isSelected()){
                carbs += crbs.get(i);
            }
        }
        return carbs;
    }
    
    /**
     * The getDose takes the integers in its parameters and calculates 
     * The dosage of insulin that is needed.
     * @param car An integer value that represents the total total number of 
     *      carbohydrates that will be ingested
     * @param bShug An integer value that represents the the blood glucose
     *      level
     * @return the calculated insulin dosage
     */
    public static int getDose(int car, int bShug){
        int dosage;         // Too hold the dosage
        int carbAdj = 0;    // Holds the carb adjustment dosage
        int bglAdj;         // Holds the BGL adjustment dosage
        
        // To calculate the bglAdj the perscribed method is 1 unit : 25mg/dL
        // over 150mgdL
        if (bShug > 150){
            int ovr = ((bShug - 150));
            
            // ovr is the mg/dL over 150. with in every 25mg/dL will add one to
            // the bglAdj value
            bglAdj = (ovr/25);
        }
        else {bglAdj = 0;} // if it 150 or lower bglAdj is zero
        
        // To calculate carbAdj the perscribed method is 1:5 ratio ie 1 unit 
        // of insulin for every five grams of carbohydrates 
        while (car > 5){
            carbAdj += 1;
            car -= 5;
        }
        
        // The count starts at three... if there is more than three in car add
        // one more to carbAdj
        if(car > 3)
            carbAdj +=1;
        
        // The dosage is the two adjustment values added togethter
        dosage = bglAdj + carbAdj;
        
        return dosage;
    }
}
