package treeml;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;

/**
 * A quick and dirty application that creates a dashboard
 *
 * @author nathan
 */
public class TreeML {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // make a local connection
            final RConnection c = new RConnection("localhost", 6311);

            REXP x0 = c.eval("R.version.string");
            System.out.println(x0.asString());
            
            // load the util functions
            c.eval("source('C:/Users/nathan/Google Drive/Data Science Boot Camp/Projects/Kaggle/MLUtil.R')");
            
            // lunch the gui
            /* Create and display the dialog */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    TreeMLFrame frame = new TreeMLFrame();
                    frame.setConnection(c);
                    frame.pack();
                    frame.setVisible(true);
                }
            });
            
            //System.out.println("Converted to String Array " + sadata.length + ", " + sadata[0].length);
        } catch (Exception ex) {
            Logger.getLogger(RserveTalk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
