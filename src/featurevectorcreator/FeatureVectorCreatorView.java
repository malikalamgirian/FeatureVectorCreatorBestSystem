/*
 * FeatureVectorCreatorView.java
 */
package featurevectorcreator;

import com.malikalamgirian.qaw.fyp.*;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class FeatureVectorCreatorView extends FrameView {

    public FeatureVectorCreatorView(SingleFrameApplication app) {
        super(app);

        /*
         * initializations
         */
        chooser = new JFileChooser();
        /*
         * initialize properties
         */
        properties = new Properties();

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = FeatureVectorCreatorApp.getApplication().getMainFrame();
            aboutBox = new FeatureVectorCreatorAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        FeatureVectorCreatorApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBrowseProcessedXmlFile = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnExtractFeatures = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnSelectMonotonicAlignedFeatures = new javax.swing.JButton();
        btnSelectNonMonotonicAlignedFeatures = new javax.swing.JButton();
        btnBrowseUnProcessedXmlFile = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tarStatus = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        btnBrowseFeatureExtractedFile = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(featurevectorcreator.FeatureVectorCreatorApp.class).getContext().getResourceMap(FeatureVectorCreatorView.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        btnBrowseProcessedXmlFile.setText(resourceMap.getString("btnBrowseProcessedXmlFile.text")); // NOI18N
        btnBrowseProcessedXmlFile.setName("btnBrowseProcessedXmlFile"); // NOI18N
        btnBrowseProcessedXmlFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseProcessedXmlFileActionPerformed(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        btnExtractFeatures.setText(resourceMap.getString("btnExtractFeatures.text")); // NOI18N
        btnExtractFeatures.setName("btnExtractFeatures"); // NOI18N
        btnExtractFeatures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtractFeaturesActionPerformed(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        btnSelectMonotonicAlignedFeatures.setText(resourceMap.getString("btnSelectMonotonicAlignedFeatures.text")); // NOI18N
        btnSelectMonotonicAlignedFeatures.setEnabled(false);
        btnSelectMonotonicAlignedFeatures.setName("btnSelectMonotonicAlignedFeatures"); // NOI18N
        btnSelectMonotonicAlignedFeatures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectMonotonicAlignedFeaturesActionPerformed(evt);
            }
        });

        btnSelectNonMonotonicAlignedFeatures.setText(resourceMap.getString("btnSelectNonMonotonicAlignedFeatures.text")); // NOI18N
        btnSelectNonMonotonicAlignedFeatures.setName("btnSelectNonMonotonicAlignedFeatures"); // NOI18N
        btnSelectNonMonotonicAlignedFeatures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectNonMonotonicAlignedFeaturesActionPerformed(evt);
            }
        });

        btnBrowseUnProcessedXmlFile.setText(resourceMap.getString("btnBrowseUnProcessedXmlFile.text")); // NOI18N
        btnBrowseUnProcessedXmlFile.setName("btnBrowseUnProcessedXmlFile"); // NOI18N
        btnBrowseUnProcessedXmlFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseUnProcessedXmlFileActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tarStatus.setColumns(20);
        tarStatus.setLineWrap(true);
        tarStatus.setRows(5);
        tarStatus.setName("tarStatus"); // NOI18N
        jScrollPane1.setViewportView(tarStatus);

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        btnBrowseFeatureExtractedFile.setText(resourceMap.getString("btnBrowseFeatureExtractedFile.text")); // NOI18N
        btnBrowseFeatureExtractedFile.setName("btnBrowseFeatureExtractedFile"); // NOI18N
        btnBrowseFeatureExtractedFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseFeatureExtractedFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(64, 64, 64))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBrowseFeatureExtractedFile, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSelectMonotonicAlignedFeatures, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSelectNonMonotonicAlignedFeatures, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                            .addComponent(btnExtractFeatures, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnBrowseUnProcessedXmlFile, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBrowseProcessedXmlFile, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnBrowseProcessedXmlFile)
                    .addComponent(btnBrowseUnProcessedXmlFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnExtractFeatures))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBrowseFeatureExtractedFile)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelectNonMonotonicAlignedFeatures)
                    .addComponent(btnSelectMonotonicAlignedFeatures)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBrowseFeatureExtractedFile, btnExtractFeatures});

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(featurevectorcreator.FeatureVectorCreatorApp.class).getContext().getActionMap(FeatureVectorCreatorView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 531, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseUnProcessedXmlFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseUnProcessedXmlFileActionPerformed
        /* Here we just show JfileChooser and select the file */
        tarStatus.setText(tarStatus.getText() + "\nbtnBrowseUnProcessedXmlFile : Browse un-processed XML file pressed. \n");

        String chooser_Title = "Select Un-Processed XML File";

        try {
            chooser.setDialogTitle(chooser_Title);

            /* Open a file dialog. */
            int retval = chooser.showOpenDialog(mainPanel);

            if (retval == JFileChooser.APPROVE_OPTION) {
                //... The user selected a file, get it, use it.
                File file = chooser.getSelectedFile();

                // check if the selected file is .xml
                if (file.getName().toLowerCase().endsWith(".xml")) {

                    //... Update properties.input_XML_File_URL for process Button.
                    properties.setInput_XML_File_URL(file.getAbsolutePath());

                    tarStatus.setText(tarStatus.getText() + "File : '" + properties.getInput_XML_File_URL() + "' : Choosen Successfully. Now choose processed .xml file. \n");

                } else {
                    // ask user to select .xml file
                    tarStatus.setText(tarStatus.getText() + "Please choose proper unprocessed .xml file to process. \n");
                }
            }

        } catch (Exception ex) {
            tarStatus.setText(tarStatus.getText() + ex + "\n");
            tarStatus.setText(tarStatus.getText() + ex.getMessage() + "\n");
        }
    }//GEN-LAST:event_btnBrowseUnProcessedXmlFileActionPerformed

    private void btnBrowseProcessedXmlFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseProcessedXmlFileActionPerformed
        /* Here we just show JfileChooser and select the file */
        tarStatus.setText(tarStatus.getText() + "\nbtnBrowseProcessedXmlFile : Browse Processed XML file pressed. \n");

        String chooser_Title = "Select Processed XML File";

        try {
            chooser.setDialogTitle(chooser_Title);

            /* Open a file dialog. */
            int retval = chooser.showOpenDialog(mainPanel);

            if (retval == JFileChooser.APPROVE_OPTION) {
                //... The user selected a file, get it, use it.
                File file = chooser.getSelectedFile();

                // check if the selected file is .xml
                if (file.getName().toLowerCase().endsWith(".xml")) {

                    //... Update properties.input_XML_File_URL for process Button.
                    properties.setAntonym_Match_Detector_File_URL(file.getAbsolutePath());

                    tarStatus.setText(tarStatus.getText() + "File : '" + properties.getAntonym_Match_Detector_File_URL() + "' : Choosen Successfully. Now click \"Extract Features\". \n");

                } else {
                    // ask user to select .xml file
                    tarStatus.setText(tarStatus.getText() + "Please choose proper processed .xml file to process. \n");
                }
            }
        } catch (Exception ex) {
            tarStatus.setText(tarStatus.getText() + ex + "\n");
            tarStatus.setText(tarStatus.getText() + ex.getMessage() + "\n");
        }
    }//GEN-LAST:event_btnBrowseProcessedXmlFileActionPerformed

    private void btnExtractFeaturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtractFeaturesActionPerformed

        tarStatus.setText(tarStatus.getText() + "\nbtnExtractFeatures : Extract Features button pressed. \n");

        try {
            featureExtractor = new FeatureExtractor(properties);

            tarStatus.setText(tarStatus.getText() + "\nDone Feature Extraction.\n");

        } catch (Exception ex) {
            tarStatus.setText(tarStatus.getText() + ex + "\n");
            tarStatus.setText(tarStatus.getText() + ex.getMessage() + "\n");
        }
    }//GEN-LAST:event_btnExtractFeaturesActionPerformed

    private void btnSelectMonotonicAlignedFeaturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectMonotonicAlignedFeaturesActionPerformed

        tarStatus.setText(tarStatus.getText() + "\nbtnSelectMonotonicAlignedFeatures : Select Features button pressed. \n");

        try {
            monotonicFeatureSelector = new MonotonicFeatureSelector(properties);

            tarStatus.setText(tarStatus.getText() + "\nDone Monotonic Features Selection.\n");

        } catch (Exception ex) {
            tarStatus.setText(tarStatus.getText() + ex + "\n");
            tarStatus.setText(tarStatus.getText() + ex.getMessage() + "\n");
        }
    }//GEN-LAST:event_btnSelectMonotonicAlignedFeaturesActionPerformed

    private void btnSelectNonMonotonicAlignedFeaturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectNonMonotonicAlignedFeaturesActionPerformed

        tarStatus.setText(tarStatus.getText() + "\nbtnSelectNonMonotonicAlignedFeatures : Select Features button pressed. \n");

        try {

              nonMonotonicFeatureSelector = new NonMonotonicFeatureSelector(properties);

            tarStatus.setText(tarStatus.getText() + "\nDone Non-Monotonic Features Selection.\n");

        } catch (Exception ex) {
            tarStatus.setText(tarStatus.getText() + ex + "\n");
            tarStatus.setText(tarStatus.getText() + ex.getMessage() + "\n");
        }
    }//GEN-LAST:event_btnSelectNonMonotonicAlignedFeaturesActionPerformed

    private void btnBrowseFeatureExtractedFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseFeatureExtractedFileActionPerformed
       /* Here we just show JfileChooser and select the file */
        tarStatus.setText(tarStatus.getText() + "\nbtnBrowseFeatureExtractedFile : Browse Features Extracted XML file pressed. \n");

        String chooser_Title = "Select 'Features Extracted' XML File";

        try {
            chooser.setDialogTitle(chooser_Title);

            /* Open a file dialog. */
            int retval = chooser.showOpenDialog(mainPanel);

            if (retval == JFileChooser.APPROVE_OPTION) {
                //... The user selected a file, get it, use it.
                File file = chooser.getSelectedFile();

                // check if the selected file is .xml
                if (file.getName().toLowerCase().endsWith(".xml")) {

                    //... Update properties.feature_Extracted_File_URL for process Button.
                    properties.setFeature_Extracted_File_URL(file.getAbsolutePath());

                    tarStatus.setText(tarStatus.getText() + "File : '" + properties.getFeature_Extracted_File_URL() + "' : Choosen Successfully. Now click \"Proper Selection Related Button\". \n");

                } else {
                    // ask user to select .xml file
                    tarStatus.setText(tarStatus.getText() + "Please choose proper processed .xml file to process. \n");
                }
            }
        } catch (Exception ex) {
            tarStatus.setText(tarStatus.getText() + ex + "\n");
            tarStatus.setText(tarStatus.getText() + ex.getMessage() + "\n");
        }
    }//GEN-LAST:event_btnBrowseFeatureExtractedFileActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowseFeatureExtractedFile;
    private javax.swing.JButton btnBrowseProcessedXmlFile;
    private javax.swing.JButton btnBrowseUnProcessedXmlFile;
    private javax.swing.JButton btnExtractFeatures;
    private javax.swing.JButton btnSelectMonotonicAlignedFeatures;
    private javax.swing.JButton btnSelectNonMonotonicAlignedFeatures;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextArea tarStatus;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;

    /*
     * Manual declarations
     */
    private JFileChooser chooser;
    Properties properties;

    /*
     * Helper classes
     */
    FeatureExtractor featureExtractor;
    MonotonicFeatureSelector monotonicFeatureSelector;
    NonMonotonicFeatureSelector nonMonotonicFeatureSelector;
}
