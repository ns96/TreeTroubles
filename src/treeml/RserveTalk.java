package treeml;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

/**
 * A simple test class for "talking" to Rserve server
 *
 * @author nathan
 */
public class RserveTalk {
    
    private RConnection c = null;
    private HashMap<String, ColumnInfo> columnInfoMap = new HashMap<String, ColumnInfo>();
    
    /**
     * The constructor
     *
     * @param c
     */
    public RserveTalk(RConnection c) {
        this.c = c;
    }

    /**
     * Method to return a dataframe as string array
     *
     * @param dataframe
     * @return
     */
    public String[][] getDataFrameAsStringArray(String dataframe) throws RserveException, REXPMismatchException {
        int[] dim = c.eval("dim(" + dataframe + ")").asIntegers();
        int rows = dim[0];
        int cols = dim[1];
        
        // initialize the string array
        String[][] dataframeArray = new String[rows + 1][cols];
        
        // test recreating
        RList list = c.eval(dataframe).asList();
        
        for (int i = 0; i < cols; i++) {
            String colname = list.names.get(i).toString();
            //System.out.println("Column name: " + colname);
            
            dataframeArray[0][i] = colname;
            
            // get summary for each columns
            ColumnInfo columnInfo = null;
            if(i != 0) {
                columnInfo  = storeColumnInfo(dataframe, colname);
            }
            
            String[] data = list.at(colname).asStrings();
            for(int j = 0; j < data.length; j++) {
                dataframeArray[j+1][i] = data[j];
            }
        }
        
        return dataframeArray;
    }
    
    /**
     * Store the summary for each variable
     * 
     * @param dataframe 
     */
    private ColumnInfo storeColumnInfo(String dataframe, String colname) throws RserveException, REXPMismatchException {
        REXP rexp = c.eval("summary(" + dataframe + "$" + colname + ", maxsum = 500)");
        
        String[] keys = rexp._attr().asList().at(0).asStrings();
        String[] values = rexp.asStrings();
        ColumnInfo columnInfo = new ColumnInfo(colname, keys, values);
        columnInfoMap.put(colname, columnInfo);
        
        //System.out.println("Feature: " + colname + ", number levels: " + keys.length);
        
        return columnInfo;
    }
    
    /**
     * Attempt to generate a gray scale image
     * @param grayScaleArray 
     */
    public BufferedImage generateImage(String[][] sadata, int obsToSkip, int expander) {
        // create an int which just uses every 100 data pount and is 10* the width
        int height = (sadata.length - 1)/obsToSkip; // remove one for the header
        int width = (sadata[0].length - 1) * expander; // remove 1 for id column and multiple by 10 to get more info
        
        // buffer image
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        // skip the id column        
        for(int i = 1; i < sadata[0].length; i++) {
            String colname = sadata[0][i];
            ColumnInfo columnInfo = columnInfoMap.get(colname);
            
            int m = 0;
            for(int j = 1; j < sadata.length; j += obsToSkip) {
                int[] colorValue = columnInfo.getColorValue(sadata[j][i]);
                Color color = new Color(colorValue[0], colorValue[1], colorValue[2]);
                
                // need to fill this in another loop
                for(int k = 0; k < expander; k++) {
                    if(m < height) {
                        bufferedImage.setRGB((i - 1)*expander + k, m, color.getRGB());
                    }
                }
                
                m++;
                //System.out.println("Gray " + columnInfo + ", " + gray + " : " + m);
            }
        }
        
        return bufferedImage;
    }

    /**
     * Main method for testing purposes
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            // make a local connection
            RConnection c = new RConnection("localhost", 6311);

            REXP x0 = c.eval("R.version.string");
            System.out.println(x0.asString());
            
            String datamatrix = "dm_trees";
            
            // load the dataframe if needed
            int loaded = c.eval("exists('" + datamatrix +"')").asInteger();
            
            // load the util functions
            String EDAScript = "C:/Users/nathan/Google Drive/Data Science Boot Camp/Projects/Capstone/RCode/EDAUtil.R";
            c.eval("source('"+ EDAScript +"')");
            
            loaded = 0; // DEBUG
            if(loaded == 0) {
                REXP x1 = c.eval("loadTreeData()");
                System.out.println(x1.asString());
                
                // create the sparce matrix
                c.eval("createDM(trees_2015)");
                
                System.out.println("Generated data matrix ...");
            }
            
            RserveTalk rserveTalk = new RserveTalk(c);
            
            String[][] sadata = rserveTalk.getDataFrameAsStringArray("dm_trees");
            
            // number of data to skip
            int skipFrequency = 15;
            
            // test saving the image
            BufferedImage image = rserveTalk.generateImage(sadata, skipFrequency, 60);
            File f = new File("C:/temp/trees_sidewalk.png");
            ImageIO.write(image, "PNG", f);
            
            String[] sortBy = {"tree_dbh", "healthGood"};
            
            // do an example sorting by one of the continuos variable
            for(String feature: sortBy) {
                c.eval("df <- dm_trees[order(dm_trees$" + feature + "), ]");
                sadata = rserveTalk.getDataFrameAsStringArray("df");
                image = rserveTalk.generateImage(sadata, skipFrequency, 50);
                f = new File("C:/temp/trees_"+ feature + ".png");
                ImageIO.write(image, "PNG", f);
                
                System.out.println("Generated Image for " + feature);
            }
        } catch (REngineException | REXPMismatchException | IOException ex) {
            Logger.getLogger(RserveTalk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
