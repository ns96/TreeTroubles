/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeml;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 *
 * @author nathan
 */
public class SpeciesViewerDialog extends javax.swing.JDialog {
    
    private TreeInsightFrame treeInsightFrame = null;
    
    private HashMap<String, String[]> leafSnapData;
    
    private String[][] nycData;
            
    /**
     * Creates new form SpeciesViewerDialog
     */
    public SpeciesViewerDialog(TreeInsightFrame parent, HashMap<String, String[]> leafSnapData, String[][] nycData) {
        super(parent, false);
        treeInsightFrame = parent;
        
        this.leafSnapData = leafSnapData;
        this.nycData = nycData;
        
        initComponents();
        
        // activate hyperlink support
        htmlEditorPane.setEditable(false);
        htmlEditorPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    String url = e.getURL().toString();
                    url = url.replaceAll(" ", "%20");
                    treeInsightFrame.openInBrowser(url);
                }
            }
        });
        
        initHtmlString();
    }
    
    /**
     * This iterates through the data passed in and generates the html
     */
    private void initHtmlString() {
        htmlEditorPane.setText("<H1>LOADING IMAGES ...</H1>");
        
        StringBuilder sb = new StringBuilder();
        sb.append("<table style=\"width:100%\">");
        
        for(int i = 1; i < nycData.length; i++) {
            String[] info = nycData[i];
            
            String species = normalizeName(info[0]);
            String[] speciesInfo = leafSnapData.get(species);
            
            if(speciesInfo != null) {
                sb.append("<tr>");
                
                sb.append("<td>");
                sb.append("<a href=\"").append(speciesInfo[5]).append("\">");
                sb.append(speciesInfo[1]).append("</a><br>Count: ");
                
                sb.append(info[2]).append("<br>Max Size: ").append(info[6]);
                sb.append("</td>");
                
                sb.append("<td>");
                sb.append("<img src=\"").append(speciesInfo[2]).append("\" alt=\"Leaf\">");
                sb.append("</td>");
                
                sb.append("<td>");
                sb.append("<img src=\"").append(speciesInfo[3]).append("\" alt=\"Flower\">");
                sb.append("</td>");
                
                sb.append("<td>");
                sb.append("<img src=\"").append(speciesInfo[4]).append("\" alt=\"Fruit\">");
                sb.append("</td>");
                
                sb.append("</tr>");
            }
        }
        
        sb.append("</table>");
        
        htmlEditorPane.setText(sb.toString());
    }
    
    /**
     * Method to normalize the species names from NYC dataset to leafsnap dataset
     * @param species
     * @return 
     */
    private String normalizeName(String species) {
        if(species.equals("PLATANUS X ACERIFOLIA")) {
            species = "PLATANUS ACERIFOLIA";
        } else if(species.equals("STYPHNOLOBIUM JAPONICUM")) {
            species = "CERCIDIPHYLLUM JAPONICUM";
        } else if(species.equals("ACER PLATANOIDES 'CRIMSON KING'")) {
            species = "ACER PLATANOIDES";
        } else if(species.equals("FRAXINUS")) {
            species = "FRAXINUS NIGRA";
        } else if(species.equals("MALUS")) {
            species = "MALUS HUPEHENSIS";
        } else if(species.equals("CRATAEGUS")) {
            species = "CRATAEGUS LAEVIGATA";
        } else if(species.equals("MORUS")) {
            species = "MORUS RUBRA";
        } else if(species.equals("MAGNOLIA")) {
            species = "MAGNOLIA STELLATA";
        } else if(species.equals("CATALPA")) {
            species = "CATALPA SPECIOSA";
        } else if(species.equals("CLADRASTIS KENTUKEA")) {
            species = "CLADRASTIS LUTEA";
        } else if(species.equals("SYRINGA PEKINENSIS")) {
            species = "SYRINGA VULGARIS";
        } else if(species.equals("CARPINUS JAPONICA")) {
            species = "CARPINUS BETULUS";
        } else if(species.equals("CRATAEGUS CRUSGALLI")) {
            species = "CRATAEGUS CRUS-GALLI";
        } else if(species.equals("PINUS")) {
            species = "PINUS STROBUS";
        } else if(species.equals("CORNUS ALTERNIFOLIA")) {
            species = "CORNUS FLORIDA";
        } else if(species.equals("CERCIS RENIFORMIS")) {
            species = "CERCIS CANADENSIS";
        } else if(species.equals("HALESIA DIPTERA")) {
            species = "HALESIA TETRAPTERA";
        } else if(species.equals("TAXODIUM ASCENDENS")) {
            species = "TAXODIUM DISTICHUM";
        } else if(species.equals("PICEA")) {
            species = "PICEA ORIENTALIS";
        } else if(species.equals("CASTANEA MOLLISSIMA")) {
            species = "CASTANEA DENTATA";
        } else if(species.equals("AESCULUS X CARNEA")) {
            species = "AESCULUS HIPPOCASTANUM";
        } else if(species.equals("ILEX")) {
            species = "ILEX OPACA";
        } else if(species.equals("LARIX")) {
            species = "LARIX DECIDUA";
        } else if(species.equals("PRUNUS")) { // cherry
            species = "PRUNUS SERRULATA"; // oriental cherry
        } else if(species.equals("ACER")) { // cherry
            species = "ACER PLATANOIDES"; // oriental cherry
        }
        
        return species;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        htmlEditorPane = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Species Viewer");

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        htmlEditorPane.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(htmlEditorPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(closeButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeButton)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JEditorPane htmlEditorPane;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
