package de.idrinth.waraddonclient.gui;

import de.idrinth.waraddonclient.service.Config;
import de.idrinth.waraddonclient.service.Backup;
import de.idrinth.waraddonclient.service.ProgressReporter;
import de.idrinth.waraddonclient.service.Shedule;
import java.io.IOException;
import net.lingala.zip4j.exception.ZipException;
import de.idrinth.waraddonclient.service.logger.BaseLogger;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class Backups extends BaseFrame implements MainWindow
{
    private final BaseLogger logger;

    private final Backup backup;
    
    private final ProgressReporter reporter;
    
    private final MainWindowMap map;
    
    private final Config config;
    
    private final ArrayList<File> contained = new ArrayList<>();

    public Backups(MainWindowMap map, BaseLogger logger, Config config, Backup backup, ProgressReporter reporter, Shedule scheduler) {
        super(config);
        this.map = map;
        this.logger = logger;
        this.backup = backup;
        this.reporter = reporter;
        this.config = config;
        initComponents();
        this.backupFolder.setText(config.getWARPath() + "/backups");
        setTitle("Backups");
        loadFromFolder();
        scheduler.register(2, () -> loadFromFolder());
    }
    private void loadFromFolder()
    {
        File folder = new File(config.getWARPath() + "/backups");
        if (!folder.isDirectory()) {
            for (int i=this.restoreTable.getModel().getRowCount();i > 0; i--) {
                ((DefaultTableModel) this.restoreTable.getModel()).removeRow(i - 1);
            }
            return;
        }
        for (File file : contained) {
            if (!file.exists()) {
                ((DefaultTableModel) this.restoreTable.getModel()).removeRow(contained.indexOf(file));
                contained.remove(file);
            }
        }
        for (File file : folder.listFiles()) {
            if (file.getName().endsWith(".zip") && !contained.contains(file)) {
                String[] row = new String[2];
                row[0] = file.getName();
                row[1] = new Date(file.lastModified()).toString();
                ((DefaultTableModel) this.restoreTable.getModel()).addRow(row);
                contained.add(file);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel restorePane = new JPanel();
        JScrollPane tablePane = new JScrollPane();
        restoreTable = new JTable();
        JButton restoreButton = new JButton();
        JPanel createPane = new JPanel();
        JLabel backupFolderLabel = new JLabel();
        backupFolder = new JTextField();
        JButton backupCreateButton = new JButton();
        JMenuBar mainMenu = new JMenuBar();

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        restoreTable.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Filename", "Last Modified"
            }
        ) {
            Class[] types = new Class [] {
                String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablePane.setViewportView(restoreTable);

        restoreButton.setText("Restore Backup");
        restoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                restoreButtonActionPerformed(evt);
            }
        });

        GroupLayout restorePaneLayout = new GroupLayout(restorePane);
        restorePane.setLayout(restorePaneLayout);
        restorePaneLayout.setHorizontalGroup(restorePaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(restorePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tablePane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(restoreButton)
                .addContainerGap(319, Short.MAX_VALUE))
        );
        restorePaneLayout.setVerticalGroup(restorePaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(restorePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(restorePaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(restoreButton)
                    .addComponent(tablePane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Restore", restorePane);

        backupFolderLabel.setText("Backup-Folder:");

        backupFolder.setEditable(false);
        backupFolder.setText("jTextField1");

        backupCreateButton.setText("Create Backup");
        backupCreateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backupCreateButtonActionPerformed(evt);
            }
        });

        GroupLayout createPaneLayout = new GroupLayout(createPane);
        createPane.setLayout(createPaneLayout);
        createPaneLayout.setHorizontalGroup(createPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(createPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(createPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(createPaneLayout.createSequentialGroup()
                        .addComponent(backupFolderLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(backupFolder, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE))
                    .addComponent(backupCreateButton))
                .addContainerGap(429, Short.MAX_VALUE))
        );
        createPaneLayout.setVerticalGroup(createPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(createPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(createPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(backupFolderLabel)
                    .addComponent(backupFolder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(backupCreateButton)
                .addContainerGap(393, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Create", createPane);

        setJMenuBar(mainMenu);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        map.exchange(MainWindowMap.BACKUPS, MainWindowMap.START);
    }//GEN-LAST:event_formWindowClosing

    private void backupCreateButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_backupCreateButtonActionPerformed
        reporter.start("Create Backup", () -> {
            this.setEnabled(true);
        });
        this.setEnabled(false);
        new Thread(() -> {
            try {
                backup.create(reporter);
                JOptionPane.showMessageDialog(this, "Saved your profile and addons in backups.");
            } catch (ZipException ex) {
                logger.error(ex);
                JOptionPane.showMessageDialog(this, "Failed to save your profile and addons.");
            }
            reporter.stop();
        }).start();
    }//GEN-LAST:event_backupCreateButtonActionPerformed

    private void restoreButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_restoreButtonActionPerformed
        File backupFile;
        if (restoreTable.getSelectedRow() != -1) {
            backupFile = new File(config.getWARPath() + "/backups/" + restoreTable.getModel().getValueAt(restoreTable.getSelectedRow(), 1));
        } else {
            FileDialog dialog = new java.awt.FileDialog(this, "Select backup", java.awt.FileDialog.LOAD);
            dialog.setVisible(true);
            if (dialog.getFile() != null) {
                return;
            }
            if (!dialog.getFile().endsWith(".zip")) {
                JOptionPane.showMessageDialog(this, "Backup has to be a zip-File.");
                return;
            }
            backupFile = new File(dialog.getDirectory() + "/" + dialog.getFile());
        }
        reporter.start("Restore Backup", () -> {
            this.setEnabled(true);
        });
        this.setEnabled(false);
        new Thread(() -> {
            try {
                backup.restore(backupFile, reporter);
                JOptionPane.showMessageDialog(this, "Backup restored.");
            } catch (IOException ex) {
                logger.error(ex);
                JOptionPane.showMessageDialog(this, "Couldn't restore Backup.");
            }
            reporter.stop();
        }).start();
    }//GEN-LAST:event_restoreButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextField backupFolder;
    private JTable restoreTable;
    // End of variables declaration//GEN-END:variables
}