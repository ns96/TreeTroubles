/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeml;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author nathan
 */
public class DataImagePanel extends JPanel {
    private BufferedImage image;
    private String sortBy = "sidewalk";
    
    public DataImagePanel(BufferedImage image) {
        this.image = image;
        
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

    public BufferedImage getImage() {
        return image;
    }
    
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
    
    public String getSortBy() {
        return sortBy;
    }
}
