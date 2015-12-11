/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BankSoal;

import Database.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Dewangga P. Praja
 */
public class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    Database main;
    public MainMenu() {
        main = Database.getDatabase();
        main.setData(false);
        initComponents();
    }
    
    public void fail(){
        checkPortable.setState(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        buttonGenerate = new javax.swing.JButton();
        scNavPane = new javax.swing.JScrollPane();
        navPane = new javax.swing.JTree(Root());
        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TEST = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        itemImport = new javax.swing.JMenuItem();
        itemExport = new javax.swing.JMenuItem();
        separateFile = new javax.swing.JPopupMenu.Separator();
        itemExit = new javax.swing.JMenuItem();
        menuEdit = new javax.swing.JMenu();
        itemTambah = new javax.swing.JMenuItem();
        itemEdit = new javax.swing.JMenuItem();
        itemHapus = new javax.swing.JMenuItem();
        menuSetting = new javax.swing.JMenu();
        checkPortable = new javax.swing.JCheckBoxMenuItem();
        separateSetting = new javax.swing.JPopupMenu.Separator();
        itemProperties = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bank Soal");
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(1024, 600));

        toolBar.setFloatable(false);
        toolBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        buttonGenerate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/generate.png"))); // NOI18N
        buttonGenerate.setText("Generate");
        buttonGenerate.setFocusable(false);
        buttonGenerate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonGenerate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(buttonGenerate);

        navPane.setRootVisible(false);
        navPane.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                navPaneValueChanged(evt);
            }
        });
        scNavPane.setViewportView(navPane);

        mainPanel.setLayout(new java.awt.CardLayout());

        TEST.setColumns(20);
        TEST.setRows(5);
        jScrollPane1.setViewportView(TEST);

        mainPanel.add(jScrollPane1, "card2");

        menuFile.setText("File");

        itemImport.setText("Import File ...");
        menuFile.add(itemImport);

        itemExport.setText("Export File ...");
        itemExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExportActionPerformed(evt);
            }
        });
        menuFile.add(itemExport);
        menuFile.add(separateFile);

        itemExit.setText("Exit");
        itemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExitActionPerformed(evt);
            }
        });
        menuFile.add(itemExit);

        menuBar.add(menuFile);

        menuEdit.setText("Edit");

        itemTambah.setText("Tambah");
        menuEdit.add(itemTambah);

        itemEdit.setText("Edit ");
        menuEdit.add(itemEdit);

        itemHapus.setText("Hapus");
        menuEdit.add(itemHapus);

        menuBar.add(menuEdit);

        menuSetting.setText("Setting");

        checkPortable.setText("Portable");
        checkPortable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPortableActionPerformed(evt);
            }
        });
        menuSetting.add(checkPortable);
        menuSetting.add(separateSetting);

        itemProperties.setText("Properties");
        menuSetting.add(itemProperties);

        menuBar.add(menuSetting);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scNavPane, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scNavPane, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itemExitActionPerformed

    private void navPaneValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_navPaneValueChanged
        // TODO add your handling code here:
        Object[] nav = navPane.getSelectionPath().getPath();
        TEST.setText(nav[nav.length-1].toString());
    }//GEN-LAST:event_navPaneValueChanged

    private void itemExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExportActionPerformed
        try {
            // TODO add your handling code here:
            main.saveFile(main.get());
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itemExportActionPerformed

    private void checkPortableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPortableActionPerformed
        // TODO add your handling code here:
        main.setData(checkPortable.getState());
    }//GEN-LAST:event_checkPortableActionPerformed

    private DefaultMutableTreeNode Root(){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(main.getNama());
        DefaultMutableTreeNode mataKuliah, pokokBahasan, soal;
        String mK, pB;
        MataKuliah MK;
        PokokBahasan PB;
        if( ! main.isEmpty() )
        {
            for (int i = 0; i < main.length(); i++) {
                MK = main.get(i);
                mK = MK.getNama();
                mataKuliah = new DefaultMutableTreeNode(mK);
                
                // Generate all MataKuliah
                for (int j = 0; j < MK.length(); j++) {
                    PB = MK.get(j);
                    pB = PB.getNama();
                    pokokBahasan = new DefaultMutableTreeNode(pB);
                    
                    // Generate all PokokBahasan
                    for (int k = 0; k < PB.length(); k++) {
                        // Generate all Soal with number
                        soal = new DefaultMutableTreeNode(k + 1);
                        // Input Soal to PokokBahasan
                        pokokBahasan.add(soal);
                    }
                    // Input PokokBahasan to MataKuliah
                    mataKuliah.add(pokokBahasan);
                }
                // Input MataKuliah to Database
                root.add(mataKuliah);
            }
        }
        return root;
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
                if ("Steel".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TEST;
    private javax.swing.JButton buttonGenerate;
    protected javax.swing.JCheckBoxMenuItem checkPortable;
    private javax.swing.JMenuItem itemEdit;
    private javax.swing.JMenuItem itemExit;
    private javax.swing.JMenuItem itemExport;
    private javax.swing.JMenuItem itemHapus;
    private javax.swing.JMenuItem itemImport;
    private javax.swing.JMenuItem itemProperties;
    private javax.swing.JMenuItem itemTambah;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuEdit;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuSetting;
    private javax.swing.JTree navPane;
    private javax.swing.JScrollPane scNavPane;
    private javax.swing.JPopupMenu.Separator separateFile;
    private javax.swing.JPopupMenu.Separator separateSetting;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

    
}
