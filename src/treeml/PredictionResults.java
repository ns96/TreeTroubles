/*
 * This class stores results from making a prediction
 */
package treeml;

import java.awt.Image;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nathan
 */
public class PredictionResults {
    private String tree_id;
    private DefaultTableModel tableModel;
    private Image plotImage = null;
    private int damageCount = 0;
    
    /**
     * Constructor taking everything needed
     * @param tree_id
     * @param tableModel
     * @param plotImage 
     */
    public PredictionResults(String tree_id, DefaultTableModel tableModel, Image plotImage, int damageCount) {
        this.tree_id = tree_id;
        this.tableModel = tableModel;
        this.plotImage = plotImage;
        this.damageCount = damageCount;
    }

    public String getTree_id() {
        return tree_id;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public Image getPlotImage() {
        return plotImage;
    }
    
    @Override
    public String toString() {
        if(damageCount == 0) {
            return "Results for Tree: " + tree_id;
        } else {
            return "Results for Tree: " + tree_id + ", Damage: " + damageCount;
        }
    }
}
