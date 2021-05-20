package de.idrinth.waraddonclient.gui;

import de.idrinth.waraddonclient.service.Config;
import de.idrinth.waraddonclient.service.Backup;
import de.idrinth.waraddonclient.model.Addon;
import de.idrinth.waraddonclient.model.GuiAddonList;
import java.io.IOException;
import java.net.URISyntaxException;
import net.lingala.zip4j.exception.ZipException;
import javax.swing.table.TableRowSorter;
import de.idrinth.waraddonclient.service.Version;
import de.idrinth.waraddonclient.service.logger.BaseLogger;
import de.idrinth.waraddonclient.service.Restarter;
import de.idrinth.waraddonclient.service.Shedule;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Window extends JFrame {

    private Addon activeAddon = new de.idrinth.waraddonclient.model.NoAddon();
    
    private final GuiAddonList addonList;

    private final BaseLogger logger;
    
    private final Config config;

    private final Backup backup;
    
    private final Restarter restarter;

    public Window(GuiAddonList addonList, Version version, ThemeManager manager, BaseLogger logger, Shedule schedule, Config config, Backup backup, Restarter restarter) {
        this.addonList = addonList;
        this.restarter = restarter;
        this.logger = logger;
        this.config = config;
        this.backup = backup;
        initComponents();
        manager.addTo(menuTheme);
        finishGuiBuilding(schedule);
        version.setVersion(remoteVersion);
        new Thread(version).start();
        changeLanguageTo(config.getLanguage());
    }

    private void finishGuiBuilding(Shedule schedule) {
        addonListTable.getSelectionModel().addListSelectionListener(new TableListener());
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/logo.png")));
        addonListTable.setRowSorter(new TableRowSorter<>(addonListTable.getModel()));
        addonList.setModel((DefaultTableModel) addonListTable.getModel());
        description.addHyperlinkListener(new HyperlinkListenerImpl());
        localVersion.setText(config.getVersion());
        addonList.setMenu(menuTags, (java.awt.event.ActionEvent evt) -> newFilter());
        schedule.register(300, addonList);
        (new TableListener()).updateUi();
    }

    private void newFilter() {
        try {
            TextCategory rf = new TextCategory(inputSearch.getText(), addonList);
            ((TableRowSorter) addonListTable.getRowSorter()).setRowFilter(rf);
        } catch (java.util.regex.PatternSyntaxException exception) {
            logger.error(exception);
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

        javax.swing.JSplitPane base = new javax.swing.JSplitPane();
        javax.swing.JPanel leftSide = new javax.swing.JPanel();
        inputSearch = new javax.swing.JTextField();
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane();
        addonListTable = new javax.swing.JTable();
        javax.swing.JButton buttonDeleteSearch = new javax.swing.JButton();
        javax.swing.JButton buttonUpdateAll = new javax.swing.JButton();
        rightSide = new javax.swing.JTabbedPane();
        javax.swing.JPanel tabMain = new javax.swing.JPanel();
        javax.swing.JScrollPane scrollDescription = new javax.swing.JScrollPane();
        description = new javax.swing.JEditorPane();
        installButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        addonTitle = new javax.swing.JLabel();
        localVersion = new javax.swing.JLabel();
        remoteVersion = new javax.swing.JLabel();
        javax.swing.JLabel slash = new javax.swing.JLabel();
        currentTags = new javax.swing.JLabel();
        javax.swing.JPanel tabSettings = new javax.swing.JPanel();
        javax.swing.JScrollPane scrollSettings = new javax.swing.JScrollPane();
        uploadReason = new javax.swing.JTextArea();
        uploadUrl = new javax.swing.JTextField();
        javax.swing.JLabel uploadLabel = new javax.swing.JLabel();
        uploadEnable = new javax.swing.JCheckBox();
        uploadFile = new javax.swing.JTextField();
        javax.swing.JLabel uploadFileLabel = new javax.swing.JLabel();
        javax.swing.JMenuBar mainMenu = new javax.swing.JMenuBar();
        javax.swing.JMenu menuFile = new javax.swing.JMenu();
        javax.swing.JMenuItem menuAbout = new javax.swing.JMenuItem();
        javax.swing.JMenuItem menuRestart = new javax.swing.JMenuItem();
        javax.swing.JMenuItem menuQuit = new javax.swing.JMenuItem();
        menuTags = new javax.swing.JMenu();
        javax.swing.JMenu menuTools = new javax.swing.JMenu();
        javax.swing.JMenuItem menuCreateBackup = new javax.swing.JMenuItem();
        javax.swing.JMenuItem menuRestoreBackup = new javax.swing.JMenuItem();
        javax.swing.JMenu menuSettings = new javax.swing.JMenu();
        javax.swing.JMenu menuLanguage = new javax.swing.JMenu();
        menuEnglish = new javax.swing.JRadioButtonMenuItem();
        menuFrancais = new javax.swing.JRadioButtonMenuItem();
        menuDeutsch = new javax.swing.JRadioButtonMenuItem();
        menuTheme = new javax.swing.JMenu();
        javax.swing.JMenu menuLinks = new javax.swing.JMenu();
        javax.swing.JMenuItem menuGuilded = new javax.swing.JMenuItem();
        javax.swing.JMenuItem menuBuyMeACoffee = new javax.swing.JMenuItem();
        javax.swing.JMenuItem menuSource = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        base.setDividerLocation(300);
        base.setToolTipText("");
        base.setMinimumSize(new java.awt.Dimension(300, 200));
        base.setName(""); // NOI18N

        inputSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputSearchActionPerformed(evt);
            }
        });
        inputSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputSearchKeyReleased(evt);
            }
        });

        scroll.setMaximumSize(null);

        addonListTable.setAutoCreateRowSorter(true);
        addonListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Name", "Version", "Installed"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        addonListTable.setColumnSelectionAllowed(true);
        addonListTable.setMaximumSize(null);
        addonListTable.setName(""); // NOI18N
        addonListTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scroll.setViewportView(addonListTable);
        addonListTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        buttonDeleteSearch.setText("X");
        buttonDeleteSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonDeleteSearchMouseClicked(evt);
            }
        });

        buttonUpdateAll.setText("Update All");
        buttonUpdateAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonUpdateAllMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout leftSideLayout = new javax.swing.GroupLayout(leftSide);
        leftSide.setLayout(leftSideLayout);
        leftSideLayout.setHorizontalGroup(
            leftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftSideLayout.createSequentialGroup()
                .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonDeleteSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonUpdateAll)
                .addGap(0, 30, Short.MAX_VALUE))
            .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        leftSideLayout.setVerticalGroup(
            leftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftSideLayout.createSequentialGroup()
                .addGroup(leftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDeleteSearch)
                    .addComponent(buttonUpdateAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                .addContainerGap())
        );

        base.setLeftComponent(leftSide);

        scrollDescription.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDescription.setMaximumSize(null);

        description.setEditable(false);
        description.setContentType("text/html"); // NOI18N
        scrollDescription.setViewportView(description);

        installButton.setText("(Re)Install");
        installButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                installButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        addonTitle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        localVersion.setText("0.0.0");
        localVersion.setToolTipText("Local Version");

        remoteVersion.setText("0.0.0");
        remoteVersion.setToolTipText("Remote Version");

        slash.setText("/");

        javax.swing.GroupLayout tabMainLayout = new javax.swing.GroupLayout(tabMain);
        tabMain.setLayout(tabMainLayout);
        tabMainLayout.setHorizontalGroup(
            tabMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMainLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(installButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addonTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(removeButton)
                .addGap(24, 24, 24))
            .addGroup(tabMainLayout.createSequentialGroup()
                .addComponent(localVersion)
                .addGap(1, 1, 1)
                .addComponent(slash)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remoteVersion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(currentTags, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(scrollDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tabMainLayout.setVerticalGroup(
            tabMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMainLayout.createSequentialGroup()
                .addGroup(tabMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeButton)
                    .addComponent(installButton)
                    .addComponent(addonTitle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(localVersion)
                    .addComponent(remoteVersion)
                    .addComponent(slash)
                    .addComponent(currentTags))
                .addGap(6, 6, 6))
        );

        rightSide.addTab("Main", tabMain);

        uploadReason.setEditable(false);
        uploadReason.setColumns(20);
        uploadReason.setRows(5);
        scrollSettings.setViewportView(uploadReason);

        uploadUrl.setEditable(false);
        uploadUrl.setToolTipText("");

        uploadLabel.setText("Upload URL");

        uploadEnable.setText("Allow Upload");
        uploadEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadEnableActionPerformed(evt);
            }
        });

        uploadFile.setEditable(false);
        uploadFile.setToolTipText("");

        uploadFileLabel.setText("Upload File");

        javax.swing.GroupLayout tabSettingsLayout = new javax.swing.GroupLayout(tabSettings);
        tabSettings.setLayout(tabSettingsLayout);
        tabSettingsLayout.setHorizontalGroup(
            tabSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollSettings)
            .addGroup(tabSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabSettingsLayout.createSequentialGroup()
                        .addComponent(uploadEnable)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabSettingsLayout.createSequentialGroup()
                        .addComponent(uploadLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(uploadUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabSettingsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(uploadFile, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(tabSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabSettingsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(uploadFileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(273, Short.MAX_VALUE)))
        );
        tabSettingsLayout.setVerticalGroup(
            tabSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabSettingsLayout.createSequentialGroup()
                .addComponent(scrollSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uploadUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uploadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(uploadFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uploadEnable)
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(tabSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabSettingsLayout.createSequentialGroup()
                    .addContainerGap(278, Short.MAX_VALUE)
                    .addComponent(uploadFileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(92, 92, 92)))
        );

        rightSide.addTab("Settings", tabSettings);

        base.setRightComponent(rightSide);

        menuFile.setText("File");

        menuAbout.setText("About");
        menuAbout.setToolTipText("");
        menuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAboutActionPerformed(evt);
            }
        });
        menuFile.add(menuAbout);

        menuRestart.setText("Restart");
        menuRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRestartActionPerformed(evt);
            }
        });
        menuFile.add(menuRestart);

        menuQuit.setText("Quit");
        menuQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQuitActionPerformed(evt);
            }
        });
        menuFile.add(menuQuit);

        mainMenu.add(menuFile);

        menuTags.setText("Tags");
        mainMenu.add(menuTags);

        menuTools.setText("Tools");

        menuCreateBackup.setText("Create Backup");
        menuCreateBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCreateBackupActionPerformed(evt);
            }
        });
        menuTools.add(menuCreateBackup);

        menuRestoreBackup.setText("Restore Backup");
        menuRestoreBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRestoreBackupActionPerformed(evt);
            }
        });
        menuTools.add(menuRestoreBackup);

        mainMenu.add(menuTools);

        menuSettings.setText("Settings");

        menuLanguage.setText("Language");

        menuEnglish.setSelected(true);
        menuEnglish.setText("English");
        menuEnglish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEnglishActionPerformed(evt);
            }
        });
        menuLanguage.add(menuEnglish);

        menuFrancais.setText("Francais");
        menuFrancais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFrancaisActionPerformed(evt);
            }
        });
        menuLanguage.add(menuFrancais);

        menuDeutsch.setText("Deutsch");
        menuDeutsch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeutschActionPerformed(evt);
            }
        });
        menuLanguage.add(menuDeutsch);

        menuSettings.add(menuLanguage);

        menuTheme.setText("Theme");
        menuSettings.add(menuTheme);

        mainMenu.add(menuSettings);

        menuLinks.setText("Links");

        menuGuilded.setText("Guilded: Idrinth's Addons");
        menuGuilded.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGuildedActionPerformed(evt);
            }
        });
        menuLinks.add(menuGuilded);

        menuBuyMeACoffee.setText("BuyMeACoffee");
        menuBuyMeACoffee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBuyMeACoffeeActionPerformed(evt);
            }
        });
        menuLinks.add(menuBuyMeACoffee);

        menuSource.setText("GitHub:WARAddonClient");
        menuSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSourceActionPerformed(evt);
            }
        });
        menuLinks.add(menuSource);

        mainMenu.add(menuLinks);

        setJMenuBar(mainMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(base, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * The button install was clicked
     *
     * @param evt
     */
    private void installButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_installButtonActionPerformed
        try {
            activeAddon.install();
            updateList();
            JOptionPane.showMessageDialog(this, "The requested Addon was installed.");
        } catch (IOException exception) {
            logger.error(exception);
            JOptionPane.showMessageDialog(this, "Sadly Installing failed, check if the folder is writeable.");
        }
    }//GEN-LAST:event_installButtonActionPerformed

    /**
     * the searchtext was changed
     *
     * @param evt
     */
    private void inputSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSearchActionPerformed
        newFilter();
    }//GEN-LAST:event_inputSearchActionPerformed

    /**
     * the searchtext was changed
     *
     * @param evt
     */
    private void inputSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputSearchKeyReleased
        newFilter();
    }//GEN-LAST:event_inputSearchKeyReleased

    /**
     * delete addon was clicked
     *
     * @param evt
     */
    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        try {
            activeAddon.uninstall();
            updateList();
            JOptionPane.showMessageDialog(this, "The requested Addon was removed.");
        } catch (IOException exception) {
            logger.error(exception);
            JOptionPane.showMessageDialog(this, "Sadly Removing failed, check if the folder is writeable.");
        }
    }//GEN-LAST:event_removeButtonActionPerformed

    /**
     *
     * @param evt
     */
    private void uploadEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadEnableActionPerformed
        config.setEnabled(activeAddon.getName(), uploadEnable.isSelected());
    }//GEN-LAST:event_uploadEnableActionPerformed

    /**
     * changes language to english
     *
     * @param evt
     */
    private void menuEnglishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEnglishActionPerformed
        changeLanguageTo("en");
    }//GEN-LAST:event_menuEnglishActionPerformed

    /**
     * @param evt
     */
    private void menuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAboutActionPerformed
        javax.swing.JOptionPane.showMessageDialog(this, "This software is provided for free by Björn Büttner.\n"
                + "If you have ideas or bugs please add them to the issues at GitHub:WARAddonClient.\n"
                + "If you want to buy me a coffee you can do so at Buy me a coffee.\n"
                + "If you need support, go to Guilded:Idrinth's Addons", "About", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_menuAboutActionPerformed

    /**
     * changes language to german
     *
     * @param evt
     */
    private void menuDeutschActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeutschActionPerformed
        changeLanguageTo("de");
    }//GEN-LAST:event_menuDeutschActionPerformed

    /**
     * changes language to french
     *
     * @param evt
     */
    private void menuFrancaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFrancaisActionPerformed
        changeLanguageTo("fr");
    }//GEN-LAST:event_menuFrancaisActionPerformed

    private void buttonDeleteSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDeleteSearchMouseClicked
        inputSearch.setText("");
        inputSearchActionPerformed(new java.awt.event.ActionEvent(evt.getSource(), 1001, "reset"));
    }//GEN-LAST:event_buttonDeleteSearchMouseClicked

    private void buttonUpdateAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonUpdateAllMouseClicked
        int errors = 0;
        int count = 0;
        for (int i = 0; i < addonList.size(); i++) {
            Addon addon = addonList.get(i);
            if (addon.getStatus().equals("X")) {
                count++;
                try {
                    addon.install();
                } catch (Exception ex) {
                    errors++;
                    logger.error(ex);
                }
            }
        }
        updateList();
        if (count == 0) {
            JOptionPane.showMessageDialog(this, "No Add-ons to update.");
        } else if (errors == 0) {
            JOptionPane.showMessageDialog(this, "All " + count + " Add-ons were updated.");
        } else {
            JOptionPane.showMessageDialog(this, "Updating " + errors + " out of " + count + " Add-ons failed.");
        }
    }//GEN-LAST:event_buttonUpdateAllMouseClicked

    private void menuCreateBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCreateBackupActionPerformed
        try {
            backup.create();
            JOptionPane.showMessageDialog(this, "Saved your profile and addons in backups.");
        } catch (ZipException ex) {
            logger.error(ex);
            JOptionPane.showMessageDialog(this, "Failed to save your profile and addons.");
        }
    }//GEN-LAST:event_menuCreateBackupActionPerformed

    private void menuSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSourceActionPerformed
        try {
            Desktop.getDesktop().browse(new java.net.URI("https://github.com/Idrinth/WARAddonClient/"));
        } catch (URISyntaxException | IOException ex) {
            logger.error(ex);
        }
    }//GEN-LAST:event_menuSourceActionPerformed

    private void menuBuyMeACoffeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBuyMeACoffeeActionPerformed
        try {
            Desktop.getDesktop().browse(new java.net.URI("https://buymeacoffee.com/idrinth"));
        } catch (URISyntaxException | IOException ex) {
            logger.error(ex);
        }
    }//GEN-LAST:event_menuBuyMeACoffeeActionPerformed

    private void menuGuildedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGuildedActionPerformed
        try {
            Desktop.getDesktop().browse(new java.net.URI("https://guilded.gg/Idrinths-Addons/"));
        } catch (URISyntaxException | IOException ex) {
            logger.error(ex);
        }
    }//GEN-LAST:event_menuGuildedActionPerformed

    private void menuRestoreBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRestoreBackupActionPerformed
        FileDialog dialog = new java.awt.FileDialog(this, "Select backup", java.awt.FileDialog.LOAD);
        dialog.setVisible(true);
        if (dialog.getFile() != null) {
            if (!dialog.getFile().endsWith(".zip")) {
                JOptionPane.showMessageDialog(this, "Backup has to be a zip-File.");
                return;
            }
            try {
                backup.restore(new java.io.File(dialog.getDirectory() + "/" + dialog.getFile()));
                JOptionPane.showMessageDialog(this, "Backup restored.");
            } catch (IOException ex) {
                logger.error(ex);
                JOptionPane.showMessageDialog(this, "Couldn't restore Backup.");
            }
        }
    }//GEN-LAST:event_menuRestoreBackupActionPerformed

    private void menuQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQuitActionPerformed
        this.dispose();
        Runtime.getRuntime().exit(0);
    }//GEN-LAST:event_menuQuitActionPerformed

    private void menuRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRestartActionPerformed
        try {
            restarter.restart();
        } catch (IOException ex) {
            logger.error(ex);
            JOptionPane.showMessageDialog(this, "Couldn't restart app.");
        }
    }//GEN-LAST:event_menuRestartActionPerformed

    /**
     * handles actual changing of languages
     *
     * @param lang
     */
    private void changeLanguageTo(String lang) {
        menuEnglish.setSelected("en".equals(lang));
        menuDeutsch.setSelected("de".equals(lang));
        menuFrancais.setSelected("fr".equals(lang));
        config.setLanguage(lang);
        description.setText(activeAddon.getDescription(lang));
    }

    /**
     * updates addon list
     */
    private void updateList() {
        for (int position = 0; position < addonListTable.getRowCount(); position++) {
            Addon addon = addonList.get(addonListTable.convertRowIndexToModel(position));
            addonListTable.setValueAt(addon.getStatus(), position, 0);
            addonListTable.setValueAt(addon.getVersion(), position, 2);
            addonListTable.setValueAt(addon.getInstalled(), position, 3);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable addonListTable;
    private javax.swing.JLabel addonTitle;
    private javax.swing.JLabel currentTags;
    private javax.swing.JEditorPane description;
    private javax.swing.JTextField inputSearch;
    private javax.swing.JButton installButton;
    private javax.swing.JLabel localVersion;
    private javax.swing.JRadioButtonMenuItem menuDeutsch;
    private javax.swing.JRadioButtonMenuItem menuEnglish;
    private javax.swing.JRadioButtonMenuItem menuFrancais;
    private javax.swing.JMenu menuTags;
    private javax.swing.JMenu menuTheme;
    private javax.swing.JLabel remoteVersion;
    private javax.swing.JButton removeButton;
    private javax.swing.JTabbedPane rightSide;
    private javax.swing.JCheckBox uploadEnable;
    private javax.swing.JTextField uploadFile;
    private javax.swing.JTextArea uploadReason;
    private javax.swing.JTextField uploadUrl;
    // End of variables declaration//GEN-END:variables

    private class HyperlinkListenerImpl implements HyperlinkListener {

        @Override
        public void hyperlinkUpdate(HyperlinkEvent event) {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(event.getEventType())) {
                try {
                    Desktop.getDesktop().browse(event.getURL().toURI());
                } catch (java.net.URISyntaxException | java.io.IOException exception) {
                    logger.error(exception);
                }
            }

        }
    }

    private class TableListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent event) {
            try {
                activeAddon = addonList.get(addonListTable.convertRowIndexToModel(addonListTable.getSelectedRow()));
            } catch (java.lang.ArrayIndexOutOfBoundsException exception) {
                logger.error(exception);
                return;
            }
            if (activeAddon == null) {
                return;
            }
            updateUi();
        }

        /**
         * Updates the ui to show the current addon's data
         */
        public void updateUi() {
            description.setText(activeAddon.getDescription(config.getLanguage()));
            addonTitle.setText(activeAddon.getName());
            installButton.setEnabled(true);
            removeButton.setEnabled(true);
            rightSide.setEnabledAt(1, false);
            setTitle(activeAddon.getName() + " - Idrinth's WAR Addon Client " + config.getVersion());
            rightSide.setEnabledAt(1, activeAddon.showSettings());
            uploadReason.setText(activeAddon.getReason());
            uploadUrl.setText(activeAddon.getUrl());
            uploadFile.setText(activeAddon.getFile());
            uploadEnable.setSelected(config.isEnabled(activeAddon.getName()));
            String taglist = "Tagged: ";
            taglist = activeAddon.getTags().stream().map(tagname -> tagname + ", ").reduce(taglist, String::concat);
            currentTags.setText(taglist.substring(0, taglist.length() - 2));
        }
    }
}
