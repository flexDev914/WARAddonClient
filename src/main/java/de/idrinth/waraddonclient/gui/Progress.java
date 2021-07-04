package de.idrinth.waraddonclient.gui;

import de.idrinth.waraddonclient.service.ProgressReporter;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Progress extends JFrame implements ProgressReporter {

    private int current=0;
    private int max=0;
    private Runnable callback;
    private boolean stopped;

    public Progress() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/logo.png")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("Title");

        progressBar.setValue(50);
        progressBar.setStringPainted(true);

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addGap(113, 113, 113))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeButton))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        EventQueue.invokeLater(() -> this.setVisible(false));
        callback.run();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void finish()
    {
        stopped = false;
        EventQueue.invokeLater(() -> closeButton.setEnabled(true));
    }

    @Override
    public synchronized void incrementCurrent() {
        current++;
        java.awt.EventQueue.invokeLater(() -> {
            progressBar.setValue(current);
            progressBar.setToolTipText(current + "/" + max);
            if (current == max && stopped) {
                finish();
            }
        });
    }
      
    @Override
    public synchronized void incrementMax(int amount) {
        max += amount;
        java.awt.EventQueue.invokeLater(() -> {
            if (max == 0) {
                return;
            }
            progressBar.setMaximum(max);
            progressBar.setToolTipText(current + "/" + max);
        });
    }

    @Override
    public synchronized void start(String title, Runnable callback)
    {
        max = 0;
        current = 0;
        setTitle(title);
        this.callback = callback;
        EventQueue.invokeLater(() -> {
            progressBar.setValue(0);
            closeButton.setEnabled(false);
            this.header.setText(title);
            this.setVisible(true);
        });
    }

    @Override
    public synchronized void stop()
    {
        stopped = true;
        if (current == max) {
            finish();
        } 
    }
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel header;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables
}
