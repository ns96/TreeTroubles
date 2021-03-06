/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeml;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

/**
 *
 * @author nathan
 */
public class TreeInsightFrame extends javax.swing.JFrame {

    private RConnection c = null;
    private RserveTalk rserveTalk = null;
    private String modelInformation = "Unknown";

    private HashMap<String, String[]> leafSnapData = new HashMap<>();

    /**
     * Creates new form TreeInsightFrame
     */
    public TreeInsightFrame() {
        initComponents();
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
        jLabel1 = new javax.swing.JLabel();
        boroComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        zipcodeComboBox = new javax.swing.JComboBox();
        loadTreesButton = new javax.swing.JButton();
        viewMapButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        treesTable = new javax.swing.JTable();
        treesCountLabel = new javax.swing.JLabel();
        gotoButton = new javax.swing.JButton();
        speciesInfoButton = new javax.swing.JButton();
        speciesButton = new javax.swing.JButton();
        analyzeButton = new javax.swing.JButton();
        sidewalkButton = new javax.swing.JButton();
        viewSpeciesButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NYC Tree Insight 1.0");

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Select Boro");

        boroComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bronx", "Brooklyn", "Manhattan", "Queens", "Staten Island" }));
        boroComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boroComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Select Zipcode");

        zipcodeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00000" }));

        loadTreesButton.setText("Dead Trees");
        loadTreesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadTreesButtonActionPerformed(evt);
            }
        });

        viewMapButton.setText("View Area");
        viewMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewMapButtonActionPerformed(evt);
            }
        });

        treesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(treesTable);

        treesCountLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        treesCountLabel.setForeground(java.awt.Color.red);
        treesCountLabel.setText("0 loaded");
        treesCountLabel.setToolTipText("");

        gotoButton.setText("Go To");
        gotoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotoButtonActionPerformed(evt);
            }
        });

        speciesInfoButton.setText("NYC Species");
        speciesInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speciesInfoButtonActionPerformed(evt);
            }
        });

        speciesButton.setText("Species");
        speciesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speciesButtonActionPerformed(evt);
            }
        });

        analyzeButton.setText("Analyze");
        analyzeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyzeButtonActionPerformed(evt);
            }
        });

        sidewalkButton.setText("Damage Sidewalks");
        sidewalkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sidewalkButtonActionPerformed(evt);
            }
        });

        viewSpeciesButton.setText("View Species Images");
        viewSpeciesButton.setToolTipText("");
        viewSpeciesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewSpeciesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boroComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zipcodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewMapButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speciesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadTreesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addComponent(treesCountLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(gotoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speciesInfoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewSpeciesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sidewalkButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(analyzeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(boroComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(zipcodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadTreesButton)
                    .addComponent(viewMapButton)
                    .addComponent(treesCountLabel)
                    .addComponent(speciesButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(gotoButton)
                    .addComponent(speciesInfoButton)
                    .addComponent(analyzeButton)
                    .addComponent(sidewalkButton)
                    .addComponent(viewSpeciesButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boroComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boroComboBoxActionPerformed
        try {
            String boro = boroComboBox.getSelectedItem().toString();
            REXP x0 = c.eval("getZipCodes('" + boro + "')");
            String[] zipcodes = x0.asStrings();

            // add these zip codes to the zipcode combo box now
            zipcodeComboBox.removeAllItems();
            for (String zipcode : zipcodes) {
                zipcodeComboBox.addItem(zipcode);
            }
        } catch (RserveException | REXPMismatchException ex) {
            Logger.getLogger(TreeInsightFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_boroComboBoxActionPerformed

    private void viewMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMapButtonActionPerformed
        String zipcode = zipcodeComboBox.getSelectedItem().toString();
        String boro = boroComboBox.getSelectedItem().toString().replaceAll(" ", "+");
        String url = "https://www.google.com/maps/place/" + boro + ",+NY+" + zipcode;
        openInBrowser(url);
    }//GEN-LAST:event_viewMapButtonActionPerformed

    /**
     * Method to open url in system browser
     *
     * @param url
     */
    public void openInBrowser(String url) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception ex) {
                Logger.getLogger(TreeInsightFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Close the program
     *
     * @param evt
     */
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        c.close();
        System.exit(0);
    }//GEN-LAST:event_closeButtonActionPerformed

    /**
     * Load the dead trees data for this location
     *
     * @param evt
     */
    private void loadTreesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadTreesButtonActionPerformed
        try {
            String zipcode = zipcodeComboBox.getSelectedItem().toString();
            c.eval("df <- filterByZipCode(" + zipcode + ")");
            String[][] treeData = rserveTalk.getDataFrameAsStringArray("df");

            DefaultTableModel tableModel = getTableModel(treeData);
            treesTable.setModel(tableModel);

            treesCountLabel.setText(treeData.length - 1 + " found");
        } catch (Exception ex) {
            Logger.getLogger(TreeInsightFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_loadTreesButtonActionPerformed

    /**
     * Go to location specified by tree in the browser
     *
     * @param evt
     */
    private void gotoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotoButtonActionPerformed
        int row = treesTable.getSelectedRow();
        String url = "";

        if (treesCountLabel.getText().contains("zipcodes")) {
            String zipcode = treesTable.getValueAt(row, 0).toString();
            String boro = treesTable.getValueAt(row, 1).toString().replaceAll(" ", "+");
            url = "https://www.google.com/maps/place/" + boro + ",+NY+" + zipcode;
        } else if (treesCountLabel.getText().contains("species")) {
            url = "http://leafsnap.com/species/";
        } else {
            // get the selected row
            String lat = treesTable.getValueAt(row, 14).toString();
            String lng = treesTable.getValueAt(row, 13).toString();
            url = "https://www.google.com/maps/place/" + lat + "," + lng + "/@" + lat + "," + lng + ",21z";
        }

        openInBrowser(url);
    }//GEN-LAST:event_gotoButtonActionPerformed

    /**
     * View the species information on the leafsnap site
     *
     * @param evt
     */
    private void speciesInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speciesInfoButtonActionPerformed
        try {
            String[][] speciesData = rserveTalk.getDataFrameAsStringArray("species_info");

            DefaultTableModel tableModel = getTableModel(speciesData);

            treesTable.setModel(tableModel);

            treesCountLabel.setText(tableModel.getRowCount() + " species found");
        } catch (Exception ex) {
            Logger.getLogger(TreeInsightFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_speciesInfoButtonActionPerformed

    /**
     * Load a table with all the species in this particular zipcode
     *
     * @param evt
     */
    private void speciesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speciesButtonActionPerformed
        try {
            String boro = boroComboBox.getSelectedItem().toString();
            String zipcode = zipcodeComboBox.getSelectedItem().toString();

            c.eval("df <- getSpeciesByZipCode(" + zipcode + ")");
            String[][] speciesData = rserveTalk.getDataFrameAsStringArray("df");

            DefaultTableModel tableModel = getTableModel(speciesData);

            // display the species information dialog
            SpeciesByLocationDialog dialog = new SpeciesByLocationDialog(this, speciesData, tableModel);
            dialog.setTitle(boro + ", " + zipcode + " | " + (speciesData.length - 1) + " Tree Species");
            dialog.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(TreeInsightFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_speciesButtonActionPerformed

    /**
     * Button which takes the dead trees and perform analysis to see if any of
     * the common species in the are will cause tree damage
     *
     * @param evt
     */
    private void analyzeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyzeButtonActionPerformed
        int[] selectedTrees = treesTable.getSelectedRows();
        ArrayList<String> treeList = new ArrayList<>();

        if (selectedTrees.length != 0) {
            for (int i = 0; i < selectedTrees.length; i++) {
                int index = selectedTrees[i];
                String treeId = treesTable.getValueAt(index, 0).toString();
                treeList.add(treeId);
            }
        } else {
            for (int i = 0; i < treesTable.getModel().getRowCount(); i++) {
                String treeId = treesTable.getValueAt(i, 0).toString();
                treeList.add(treeId);
            }
        }

        // open the gui now
        String boro = boroComboBox.getSelectedItem().toString();
        String zipcode = zipcodeComboBox.getSelectedItem().toString();

        // display the species information dialog
        AnalyzeDialog dialog = new AnalyzeDialog(this, treeList, zipcode);
        dialog.setTitle(boro + ", " + zipcode + " | Analyzing " + treeList.size() + " Trees | (" + modelInformation + ")");
        dialog.setVisible(true);
    }//GEN-LAST:event_analyzeButtonActionPerformed

    /**
     * Displays table of damage sidewalks
     *
     * @param evt
     */
    private void sidewalkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sidewalkButtonActionPerformed
        try {
            c.eval("df <- getSidewalkDamage()");
            String[][] treeData = rserveTalk.getDataFrameAsStringArray("df");

            DefaultTableModel tableModel = getTableModel(treeData);
            treesTable.setModel(tableModel);

            treesCountLabel.setText(treeData.length - 1 + " zipcodes");
        } catch (Exception ex) {
            Logger.getLogger(TreeInsightFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sidewalkButtonActionPerformed

    /**
     * View images of the species in NYC
     *
     * @param evt
     */
    private void viewSpeciesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewSpeciesButtonActionPerformed
        try {
            String[][] speciesData = rserveTalk.getDataFrameAsStringArray("species_info");
            
             // display the species information dialog
            SpeciesViewerDialog dialog = new SpeciesViewerDialog(this, leafSnapData, speciesData);
            dialog.setTitle("NYC Species | " + (speciesData.length - 1) + " Found");
            dialog.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(TreeInsightFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_viewSpeciesButtonActionPerformed

    /**
     * Set the R connection object for connecting to the R backend
     *
     * @param c
     */
    public void setConnection(RConnection c) {
        this.c = c;
        rserveTalk = new RserveTalk(c);

        loadInitialData();
    }

    public RConnection getConnection() {
        return c;
    }

    public RserveTalk getRserveTalk() {
        return rserveTalk;
    }

    /**
     * load any initial data
     */
    public void loadInitialData() {
        boroComboBoxActionPerformed(null);
        loadModelInformation();
        loadLeafSnapData();
    }

    private void loadModelInformation() {
        try {
            modelInformation = c.eval("modelFit$modelInfo$label").asString();
        } catch (Exception ex) {
            Logger.getLogger(TreeInsightFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadLeafSnapData() {
        try {
            String[][] data = rserveTalk.getDataFrameAsStringArray("leafsnap_info");

            for (String[] info : data) {
                leafSnapData.put(info[0].trim(), info);
            }
        } catch (Exception ex) {
            Logger.getLogger(TreeInsightFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public HashMap<String,String[]> getLeafSnapData() {
        return leafSnapData;
    }
    
    /**
     * Return a table model given the string array return from the R backend
     *
     * @param treeData
     * @return
     */
    public DefaultTableModel getTableModel(String[][] treeData) {
        String[] colnames = treeData[0];

        // remove the first row from the data array now. Java really needs built method to do this???
        List<String[]> l = new ArrayList<String[]>(Arrays.asList(treeData));
        l.remove(0);
        treeData = l.toArray(new String[][]{});

        DefaultTableModel model = new DefaultTableModel(treeData, colnames);

        return model;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TreeInsightFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TreeInsightFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TreeInsightFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TreeInsightFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        // paranters below is for running in local mode
        //"C:/Users/nathan/Google Drive/Data Science Boot Camp/Projects/Capstone/RCode/MLUtilPred.R" localhost 6311
        
        // try connecting to the R backend
        try {
            // make a local connection
            String host = args[1];
            int port = Integer.parseInt(args[2]);
            final RConnection c = new RConnection(host, port);

            REXP x0 = c.eval("R.version.string");
            System.out.println(x0.asString());

            // load the util script
            String MLPredScript = args[0];
            c.eval("source('" + MLPredScript + "')");

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    TreeInsightFrame treeInsightFrame = new TreeInsightFrame();
                    treeInsightFrame.setConnection(c);
                    treeInsightFrame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analyzeButton;
    private javax.swing.JComboBox boroComboBox;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton gotoButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadTreesButton;
    private javax.swing.JButton sidewalkButton;
    private javax.swing.JButton speciesButton;
    private javax.swing.JButton speciesInfoButton;
    private javax.swing.JLabel treesCountLabel;
    private javax.swing.JTable treesTable;
    private javax.swing.JButton viewMapButton;
    private javax.swing.JButton viewSpeciesButton;
    private javax.swing.JComboBox zipcodeComboBox;
    // End of variables declaration//GEN-END:variables
}
