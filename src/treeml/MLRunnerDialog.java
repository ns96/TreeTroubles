/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeml;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

/**
 *
 * @author nathan
 */
public class MLRunnerDialog extends javax.swing.JDialog {
    private int mlrunTimer = 0;
    private String dataframe;
    
    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;
    
    private RConnection c;

    /**
     * Creates new form MLRunnerDialog
     */
    public MLRunnerDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
    }
    
    public void setConnection(RConnection c) {
        this.c = c;
    }
    
    /**
     * Set the column name
     */
    public void setColumns(String dataframe, Set<String> featureSet, String[][] mainData, boolean invert) {
        this.dataframe = dataframe;
        String[] featureNames = mainData[0];
        
        if(!invert) {
            for(String feature: featureSet) {
                if(!feature.equals("loss")) {
                    featuresTextArea.append(feature + "\n");
                }
            }
        } else {
            for(String feature: featureNames) {
                if(!featureSet.contains(feature)) {
                    if(!feature.equals("loss") && !feature.equals("id")) {
                        featuresTextArea.append(feature + "\n");
                    }
                }
            }
        }
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        featuresTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        consoleTextArea = new javax.swing.JTextArea();
        mlrunProgressBar = new javax.swing.JProgressBar();
        timerLabel = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        okButton.setText("Run");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Close");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        featuresTextArea.setColumns(20);
        featuresTextArea.setRows(5);
        jScrollPane1.setViewportView(featuresTextArea);

        consoleTextArea.setColumns(20);
        consoleTextArea.setRows(4);
        jScrollPane2.setViewportView(consoleTextArea);

        timerLabel.setText("Timer: 0000");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mlrunProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap())
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane2)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cancelButton)
                        .addComponent(okButton)
                        .addComponent(timerLabel))
                    .addComponent(mlrunProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getRootPane().setDefaultButton(okButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        consoleTextArea.append("Running model ...\n\n");
        mlrunProgressBar.setIndeterminate(true);
        okButton.setEnabled(false);
        
        // start the timer
        mlrunTimer = 0;
        final Timer SimpleTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerLabel.setText("Timer: " + mlrunTimer++ + " secs");
            }
        });
        SimpleTimer.start();
        
        Thread t = new Thread() {
            public void run() {
                try {
                    /*** 11/22/2016 HARD CODE MUST CHANGE ***/
                    
                    // delete the old variables
                    c.eval("rm(df_train)");
                    c.eval("rm(dm_train)");
                    c.eval("rm(lmFit)");
                    c.eval("rm(gbmFit)");
                    
                    // first generate the data matrix
                    consoleTextArea.append("Filtering features ...\n");
                    
                    // filter rows in the data
                    String selectStatement = getSelectStatement();
                    if(dataframe.equals("train_e")) {
                        c.eval("df_train <- select("+ dataframe +",-id,-cat112); df_train <-select(df_train" + selectStatement + ")");
                    } else {
                        c.eval("df_train <- select("+ dataframe +",-id); df_train <-select(df_train" + selectStatement + ")");
                    }
                    
                    consoleTextArea.append("Creating data matrix ...\n");
                    c.eval("dm_train <- createTrainDM(df_train)");
                    
                    consoleTextArea.append("Training model ...\n");
                    c.eval("createSubTrainAndTest(dm_train, loss_e)");
                    c.eval("fitDataLM()");
                    //c.eval("tuneGBM()");
                    //c.eval("fitDataGBM()");
                    
                    
                    consoleTextArea.append("Getting train performance ...\n");
                    //RList rlist = c.eval("getTrainPerf(gbmFit)").asList();
                    RList rlist = c.eval("getTrainPerf(lmFit)").asList();
                    double trainRMSE = ((REXP)rlist.get(0)).asDouble();
                    double trainRSQR = ((REXP)rlist.get(1)).asDouble();
                    consoleTextArea.append("Train RMSE: " + trainRMSE + " / R^2: " + trainRSQR + "\n\n");
                    
                    consoleTextArea.append("Testing model ...\n");
                    double[] values = c.eval("testFit()").asDoubles();
                    double testRMSE = values[0];
                    double testRSQR = values[1];
                    consoleTextArea.append("Test RMSE: " + testRMSE + " / R^2: " + testRSQR + "\n\n");
                    
                    // we finished modeling                    
                    consoleTextArea.append("****Modeling Done****\n");
                } catch (RserveException | REXPMismatchException ex) {
                    consoleTextArea.append("****Fatal Error****\n");
                    Logger.getLogger(MLRunnerDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                SimpleTimer.stop();
                mlrunTimer = 0;
                
                mlrunProgressBar.setIndeterminate(false);
                okButton.setEnabled(true);
                timerLabel.setText("Timer: 000");
            }
        };
        
        t.start();
    }//GEN-LAST:event_okButtonActionPerformed

    private String getSelectStatement() {
        StringBuilder sb = new StringBuilder();
        String[] features = featuresTextArea.getText().trim().split("\n");
        
        for(String feature: features) {
            sb.append(",").append(feature);
        }
        
        // make sure to add loss column
        sb.append(",loss");
        
        String selectStatement = sb.toString();
        System.out.println("Select Statement: " + selectStatement);
        
        return selectStatement;
    }
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog
    
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextArea consoleTextArea;
    private javax.swing.JTextArea featuresTextArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JProgressBar mlrunProgressBar;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel timerLabel;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;
}
