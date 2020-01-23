package insulinadjustmentcalc;
import java.io.File;
import java.io.*;
import java.util.Scanner;
import java.util.*;

/**
 * The FileHandler class handles changes made to a file and retrieves data, 
 * from a file, that is needed to run the program
 * @author trevor
 */
public class FileHandler {
    private String sLine;
    private File file;
    private List<String> line = new LinkedList<String>();
    public linkedStack stack = new linkedStack();
    
    
    /**
     * constructor
     */
    public FileHandler(){}
    
    /**
     * The getStack method creates an instance of the linkedStack and 
     * populates it with each line that is within the specified file.
     * @param fmn The name of the file being used
     * @return a reference to the linkedStack class object
     */
    public linkedStack getStack(String fmn) throws Exception{
        file = new File(fmn);
       
         // Open file to be scanned through.
        if(file.exists()){
            // Create a scanner to read lines from file
            Scanner fScan = new Scanner(file);
            
            while (fScan.hasNextLine()){
                if (fScan.hasNextLine()){
                    sLine = fScan.nextLine();
                    stack.push(sLine);   
                }
                else {
                    System.out.println("Whoops system stepped out of bounds "
                            + "of the files leangth.");
                    break;
                } 
            }
            // Close the file it is no longer needed
            fScan.close();
        }
        return stack;
    }
    
    /**
     * The putFile method appends an entry to the end of a file.
     * @param fmn The name of a the file that is being changed
     * @param nLine The line of data being appended to the file.
     */
    public void putFile(String fmn, String nLine) throws Exception{
        FileWriter fwrite = new FileWriter(fmn, true);
        PrintWriter outPut = new PrintWriter(fwrite);
        
        // Write nline to the file
        outPut.println(nLine);

        // Close the file
        outPut.close();
    }
    
    public linkedStack alphbetized (){
        List<String> restack = new LinkedList<>();
        String frstVal, nxtVal, valHolder;
        
        while (!stack.isNextEmpty()){
            restack.add(stack.pop());
        }

        // restack goes through 2 for loops to reorganize the information in
        // ascending order
        for (int i = 0; i < restack.size(); i++){
            for (int x = (i+1); x < restack.size(); x++){
                nxtVal = restack.get(x);
                // compare values
                if(restack.get(i).compareTo(restack.get(x)) < 0){
                   valHolder = restack.get(i);
                   restack.set(i, restack.get(x));
                   restack.set(x, valHolder);
                }
            }
        }
        
        // Re-populate stack with restack variables
        for (int y = 0; y < restack.size(); y++){
            stack.push(restack.get(y));
        }
        return stack;
    }
}
