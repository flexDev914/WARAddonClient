package de.idrinth.waraddonclient.gui;

import darrylbu.util.MenuScroller;
import de.idrinth.waraddonclient.service.Config;
import de.idrinth.waraddonclient.service.Backup;
import de.idrinth.waraddonclient.model.addon.Addon;
import de.idrinth.waraddonclient.model.GuiAddonList;
import de.idrinth.waraddonclient.model.addon.ActualAddon;
import de.idrinth.waraddonclient.model.addon.NoAddon;
import de.idrinth.waraddonclient.service.ProgressReporter;
import java.io.IOException;
import java.net.URISyntaxException;
import net.lingala.zip4j.exception.ZipException;
import javax.swing.table.TableRowSorter;
import de.idrinth.waraddonclient.service.Version;
import de.idrinth.waraddonclient.service.logger.BaseLogger;
import de.idrinth.waraddonclient.service.Restarter;
import de.idrinth.waraddonclient.service.Shedule;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Window extends BaseFrame implements MainWindow {

    private Addon activeAddon = new NoAddon();
    
    private final GuiAddonList addonList;

    private final BaseLogger logger;
    
    private final Config config;

    private final Backup backup;
    
    private final Restarter restarter;
    
    private final ProgressReporter reporter;

    public Window(GuiAddonList addonList, Version version, ThemeManager manager, BaseLogger logger, Shedule schedule, Config config, Backup backup, Restarter restarter, ProgressReporter reporter) {
        super(config);
        this.addonList = addonList;
        this.restarter = restarter;
        this.logger = logger;
        this.config = config;
        this.backup = backup;
        this.reporter = reporter;
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
        MenuScroller.setScrollerFor(menuTags);
        (new TableListener()).updateUi();
        autoClose1.setSelected(config.getAutoClose() == 0);
        autoClose10.setSelected(config.getAutoClose() == 10);
        autoClose60.setSelected(config.getAutoClose() == 60);
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

        JSplitPane base = new JSplitPane();
        JPanel leftSide = new JPanel();
        inputSearch = new JTextField();
        JScrollPane scroll = new JScrollPane();
        addonListTable = new JTable();
        JButton buttonDeleteSearch = new JButton();
        JButton buttonUpdateAll = new JButton();
        rightSide = new JTabbedPane();
        JPanel tabMain = new JPanel();
        JScrollPane scrollDescription = new JScrollPane();
        description = new JEditorPane();
        installButton = new JButton();
        removeButton = new JButton();
        addonTitle = new JLabel();
        localVersion = new JLabel();
        remoteVersion = new JLabel();
        JLabel slash = new JLabel();
        currentTags = new JLabel();
        JPanel tabSettings = new JPanel();
        JScrollPane scrollSettings = new JScrollPane();
        uploadReason = new JTextArea();
        uploadUrl = new JTextField();
        JLabel uploadLabel = new JLabel();
        uploadEnable = new JCheckBox();
        uploadFile = new JTextField();
        JLabel uploadFileLabel = new JLabel();
        JMenuBar mainMenu = new JMenuBar();
        JMenu menuFile = new JMenu();
        JMenuItem menuAbout = new JMenuItem();
        JMenuItem menuRestart = new JMenuItem();
        JMenuItem menuQuit = new JMenuItem();
        menuTags = new JMenu();
        JMenu menuTools = new JMenu();
        JMenuItem menuCreateBackup = new JMenuItem();
        JMenuItem menuRestoreBackup = new JMenuItem();
        JMenu menuSettings = new JMenu();
        JMenu menuLanguage = new JMenu();
        menuEnglish = new JRadioButtonMenuItem();
        menuFrancais = new JRadioButtonMenuItem();
        menuDeutsch = new JRadioButtonMenuItem();
        menuTheme = new JMenu();
        JMenu autoCloseMenu = new JMenu();
        autoClose1 = new JRadioButtonMenuItem();
        autoClose10 = new JRadioButtonMenuItem();
        autoClose60 = new JRadioButtonMenuItem();
        JMenu menuLinks = new JMenu();
        JMenuItem menuGuilded = new JMenuItem();
        JMenuItem menuBuyMeACoffee = new JMenuItem();
        JMenuItem menuSource = new JMenuItem();
        JMenuItem menuWebpage = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        base.setDividerLocation(350);
        base.setToolTipText("");
        base.setMinimumSize(new Dimension(300, 200));
        base.setName(""); // NOI18N

        inputSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                inputSearchActionPerformed(evt);
            }
        });
        inputSearch.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                inputSearchKeyReleased(evt);
            }
        });

        scroll.setMaximumSize(null);

        addonListTable.setAutoCreateRowSorter(true);
        addonListTable.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Name", "Version", "Installed", "Endorsements", "Downloads"
            }
        ) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, String.class, Integer.class, Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        addonListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll.setViewportView(addonListTable);
        addonListTable.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        buttonDeleteSearch.setText("X");
        buttonDeleteSearch.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                buttonDeleteSearchMouseClicked(evt);
            }
        });

        buttonUpdateAll.setText("Update All");
        buttonUpdateAll.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                buttonUpdateAllMouseClicked(evt);
            }
        });

        GroupLayout leftSideLayout = new GroupLayout(leftSide);
        leftSide.setLayout(leftSideLayout);
        leftSideLayout.setHorizontalGroup(leftSideLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(leftSideLayout.createSequentialGroup()
                .addGroup(leftSideLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(leftSideLayout.createSequentialGroup()
                        .addComponent(inputSearch, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDeleteSearch)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonUpdateAll))
                    .addComponent(scroll, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        leftSideLayout.setVerticalGroup(leftSideLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(leftSideLayout.createSequentialGroup()
                .addGroup(leftSideLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(inputSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDeleteSearch)
                    .addComponent(buttonUpdateAll))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                .addContainerGap())
        );

        base.setLeftComponent(leftSide);

        scrollDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDescription.setMaximumSize(null);

        description.setEditable(false);
        description.setContentType("text/html"); // NOI18N
        scrollDescription.setViewportView(description);

        installButton.setText("(Re)Install");
        installButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                installButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        addonTitle.setFont(new Font("Tahoma", 1, 11)); // NOI18N

        localVersion.setText("0.0.0");
        localVersion.setToolTipText("Local Version");

        remoteVersion.setText("0.0.0");
        remoteVersion.setToolTipText("Remote Version");

        slash.setText("/");

        GroupLayout tabMainLayout = new GroupLayout(tabMain);
        tabMain.setLayout(tabMainLayout);
        tabMainLayout.setHorizontalGroup(tabMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tabMainLayout.createSequentialGroup()
                .addComponent(localVersion)
                .addGap(1, 1, 1)
                .addComponent(slash)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remoteVersion)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(currentTags, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(scrollDescription, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(tabMainLayout.createSequentialGroup()
                .addComponent(installButton)
                .addGap(42, 42, 42)
                .addComponent(addonTitle, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(36, 36, 36)
                .addComponent(removeButton)
                .addContainerGap())
        );
        tabMainLayout.setVerticalGroup(tabMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tabMainLayout.createSequentialGroup()
                .addGroup(tabMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(removeButton)
                    .addComponent(installButton)
                    .addComponent(addonTitle))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollDescription, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
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
        uploadEnable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                uploadEnableActionPerformed(evt);
            }
        });

        uploadFile.setEditable(false);
        uploadFile.setToolTipText("");

        uploadFileLabel.setText("Upload File");

        GroupLayout tabSettingsLayout = new GroupLayout(tabSettings);
        tabSettings.setLayout(tabSettingsLayout);
        tabSettingsLayout.setHorizontalGroup(tabSettingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tabSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabSettingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(tabSettingsLayout.createSequentialGroup()
                        .addComponent(uploadEnable)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabSettingsLayout.createSequentialGroup()
                        .addComponent(uploadLabel, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(uploadUrl, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabSettingsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(uploadFile, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollSettings, GroupLayout.Alignment.TRAILING)))
            .addGroup(tabSettingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(tabSettingsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(uploadFileLabel, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(344, Short.MAX_VALUE)))
        );
        tabSettingsLayout.setVerticalGroup(tabSettingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tabSettingsLayout.createSequentialGroup()
                .addComponent(scrollSettings, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabSettingsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(uploadUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(uploadLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(uploadFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uploadEnable)
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(tabSettingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, tabSettingsLayout.createSequentialGroup()
                    .addContainerGap(278, Short.MAX_VALUE)
                    .addComponent(uploadFileLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addGap(92, 92, 92)))
        );

        rightSide.addTab("Settings", tabSettings);

        base.setRightComponent(rightSide);

        menuFile.setText("File");

        menuAbout.setText("About");
        menuAbout.setToolTipText("");
        menuAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuAboutActionPerformed(evt);
            }
        });
        menuFile.add(menuAbout);

        menuRestart.setText("Restart");
        menuRestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuRestartActionPerformed(evt);
            }
        });
        menuFile.add(menuRestart);

        menuQuit.setText("Quit");
        menuQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuQuitActionPerformed(evt);
            }
        });
        menuFile.add(menuQuit);

        mainMenu.add(menuFile);

        menuTags.setText("Tags");
        menuTags.setMaximumSize(new Dimension(40, 22));
        mainMenu.add(menuTags);

        menuTools.setText("Tools");

        menuCreateBackup.setText("Create Backup");
        menuCreateBackup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuCreateBackupActionPerformed(evt);
            }
        });
        menuTools.add(menuCreateBackup);

        menuRestoreBackup.setText("Restore Backup");
        menuRestoreBackup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuRestoreBackupActionPerformed(evt);
            }
        });
        menuTools.add(menuRestoreBackup);

        mainMenu.add(menuTools);

        menuSettings.setText("Settings");

        menuLanguage.setText("Language");

        menuEnglish.setSelected(true);
        menuEnglish.setText("English");
        menuEnglish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuEnglishActionPerformed(evt);
            }
        });
        menuLanguage.add(menuEnglish);

        menuFrancais.setText("Francais");
        menuFrancais.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuFrancaisActionPerformed(evt);
            }
        });
        menuLanguage.add(menuFrancais);

        menuDeutsch.setText("Deutsch");
        menuDeutsch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuDeutschActionPerformed(evt);
            }
        });
        menuLanguage.add(menuDeutsch);

        menuSettings.add(menuLanguage);

        menuTheme.setText("Theme");
        menuSettings.add(menuTheme);

        autoCloseMenu.setText("Auto-Close");

        autoClose1.setSelected(true);
        autoClose1.setText("0 second");
        autoClose1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                autoClose1ActionPerformed(evt);
            }
        });
        autoCloseMenu.add(autoClose1);

        autoClose10.setSelected(true);
        autoClose10.setText("10 seconds");
        autoClose10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                autoClose10ActionPerformed(evt);
            }
        });
        autoCloseMenu.add(autoClose10);

        autoClose60.setSelected(true);
        autoClose60.setText("60 seconds");
        autoClose60.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                autoClose60ActionPerformed(evt);
            }
        });
        autoCloseMenu.add(autoClose60);

        menuSettings.add(autoCloseMenu);

        mainMenu.add(menuSettings);

        menuLinks.setText("Links");

        menuGuilded.setText("Guilded: Idrinth's Addons");
        menuGuilded.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuGuildedActionPerformed(evt);
            }
        });
        menuLinks.add(menuGuilded);

        menuBuyMeACoffee.setText("BuyMeACoffee");
        menuBuyMeACoffee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuBuyMeACoffeeActionPerformed(evt);
            }
        });
        menuLinks.add(menuBuyMeACoffee);

        menuSource.setText("GitHub:WARAddonClient");
        menuSource.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuSourceActionPerformed(evt);
            }
        });
        menuLinks.add(menuSource);

        menuWebpage.setText("Webpage");
        menuWebpage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuWebpageActionPerformed(evt);
            }
        });
        menuLinks.add(menuWebpage);

        mainMenu.add(menuLinks);

        setJMenuBar(mainMenu);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(base, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(base, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * The button install was clicked
     *
     * @param evt
     */
    private void installButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_installButtonActionPerformed
        reporter.start("Installing " + activeAddon.getName(), () -> {
            this.setEnabled(true);
            this.updateList();
        });
        this.setEnabled(false);
        activeAddon.install(reporter);
        reporter.stop();
    }//GEN-LAST:event_installButtonActionPerformed

    /**
     * the searchtext was changed
     *
     * @param evt
     */
    private void inputSearchActionPerformed(ActionEvent evt) {//GEN-FIRST:event_inputSearchActionPerformed
        newFilter();
    }//GEN-LAST:event_inputSearchActionPerformed

    /**
     * the searchtext was changed
     *
     * @param evt
     */
    private void inputSearchKeyReleased(KeyEvent evt) {//GEN-FIRST:event_inputSearchKeyReleased
        newFilter();
    }//GEN-LAST:event_inputSearchKeyReleased

    /**
     * delete addon was clicked
     *
     * @param evt
     */
    private void removeButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        reporter.start("Removing " + activeAddon.getName(), () -> {
            this.setEnabled(true);
            this.updateList();
        });
        this.setEnabled(false);
        activeAddon.uninstall(reporter);
        reporter.stop();
    }//GEN-LAST:event_removeButtonActionPerformed

    /**
     *
     * @param evt
     */
    private void uploadEnableActionPerformed(ActionEvent evt) {//GEN-FIRST:event_uploadEnableActionPerformed
        config.setEnabled(activeAddon.getName(), uploadEnable.isSelected());
    }//GEN-LAST:event_uploadEnableActionPerformed

    /**
     * changes language to english
     *
     * @param evt
     */
    private void menuEnglishActionPerformed(ActionEvent evt) {//GEN-FIRST:event_menuEnglishActionPerformed
        changeLanguageTo("en");
    }//GEN-LAST:event_menuEnglishActionPerformed

    /**
     * @param evt
     */
    private void menuAboutActionPerformed(ActionEvent evt) {//GEN-FIRST:event_menuAboutActionPerformed
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
    private void menuDeutschActionPerformed(ActionEvent evt) {//GEN-FIRST:event_menuDeutschActionPerformed
        changeLanguageTo("de");
    }//GEN-LAST:event_menuDeutschActionPerformed

    /**
     * changes language to french
     *
     * @param evt
     */
    private void menuFrancaisActionPerformed(ActionEvent evt) {//GEN-FIRST:event_menuFrancaisActionPerformed
        changeLanguageTo("fr");
    }//GEN-LAST:event_menuFrancaisActionPerformed

    private void buttonDeleteSearchMouseClicked(MouseEvent evt) {//GEN-FIRST:event_buttonDeleteSearchMouseClicked
        inputSearch.setText("");
        inputSearchActionPerformed(new java.awt.event.ActionEvent(evt.getSource(), 1001, "reset"));
    }//GEN-LAST:event_buttonDeleteSearchMouseClicked

    private void buttonUpdateAllMouseClicked(MouseEvent evt) {//GEN-FIRST:event_buttonUpdateAllMouseClicked
        reporter.start("Updating All", () -> {
            this.setEnabled(true);
            this.updateList();
        });
        new Thread(() -> {
            this.setEnabled(false);
            reporter.incrementMax(addonList.size());
            for (int i = 0; i < addonList.size(); i++) {
                Addon addon = addonList.get(i);
                if (addon.getStatus().equals("X")) {
                    addon.install(reporter);
                }
                reporter.incrementCurrent();
            }
            reporter.stop();
        }).start();
    }//GEN-LAST:event_buttonUpdateAllMouseClicked

    private void menuCreateBackupActionPerformed(ActionEvent evt) {//GEN-FIRST:event_menuCreateBackupActionPerformed
        try {
            backup.create();
            JOptionPane.showMessageDialog(this, "Saved your profile and addons in backups.");
        } catch (ZipException ex) {
            logger.error(ex);
            JOptionPane.showMessageDialog(this, "Failed to save your profile and addons.");
        }
    }//GEN-LAST:event_menuCreateBackupActionPerformed

    private void menuSourceActionPerformed(ActionEvent evt) {//GEN-FIRST:event_menuSourceActionPerformed
        try {
            Desktop.getDesktop().browse(new java.net.URI("https://github.com/Idrinth/WARAddonClient/"));
        } catch (URISyntaxException | IOException ex) {
            logger.error(ex);
        }
    }//GEN-LAST:event_menuSourceActionPerformed

    private void menuBuyMeACoffeeActionPerformed(ActionEvent evt) {//GEN-FIRST:event_menuBuyMeACoffeeActionPerformed
        try {
            Desktop.getDesktop().browse(new java.net.URI("https://buymeacoffee.com/idrinth"));
        } catch (URISyntaxException | IOException ex) {
            logger.error(ex);
        }
    }//GEN-LAST:event_menuBuyMeACoffeeActionPerformed

    private void menuGuildedActionPerformed(ActionEvent evt) {//GEN-FIRST:event_menuGuildedActionPerformed
        try {
            Desktop.getDesktop().browse(new java.net.URI("https://guilded.gg/Idrinths-Addons/"));
        } catch (URISyntaxException | IOException ex) {
            logger.error(ex);
        }
    }//GEN-LAST:event_menuGuildedActionPerformed

    private void menuRestoreBackupActionPerformed(ActionEvent evt) {//GEN-FIRST:event_menuRestoreBackupActionPerformed
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

    private void menuQuitActionPerformed(ActionEvent evt) {//GEN-FIRST:event_menuQuitActionPerformed
        this.dispose();
        Runtime.getRuntime().exit(0);
    }//GEN-LAST:event_menuQuitActionPerformed

    private void menuRestartActionPerformed(ActionEvent evt) {//GEN-FIRST:event_menuRestartActionPerformed
        try {
            restarter.restart();
        } catch (IOException ex) {
            logger.error(ex);
            JOptionPane.showMessageDialog(this, "Couldn't restart app.");
        }
    }//GEN-LAST:event_menuRestartActionPerformed

    private void menuWebpageActionPerformed(ActionEvent evt) {//GEN-FIRST:event_menuWebpageActionPerformed
        try {
            Desktop.getDesktop().browse(new java.net.URI("https://tools.idrinth.de/"));
        } catch (URISyntaxException | IOException ex) {
            logger.error(ex);
        }
    }//GEN-LAST:event_menuWebpageActionPerformed

    private void autoClose1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_autoClose1ActionPerformed
        autoClose1.setSelected(true);
        autoClose10.setSelected(false);
        autoClose60.setSelected(false);
        config.setAutoClose(0);
    }//GEN-LAST:event_autoClose1ActionPerformed

    private void autoClose10ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_autoClose10ActionPerformed
        autoClose1.setSelected(true);
        autoClose10.setSelected(false);
        autoClose60.setSelected(false);
        config.setAutoClose(10);
    }//GEN-LAST:event_autoClose10ActionPerformed

    private void autoClose60ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_autoClose60ActionPerformed
        autoClose1.setSelected(true);
        autoClose10.setSelected(false);
        autoClose60.setSelected(false);
        config.setAutoClose(60);
    }//GEN-LAST:event_autoClose60ActionPerformed

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
            addonListTable.setValueAt(addon.getName(), position, 1);
            addonListTable.setValueAt(addon.getVersion(), position, 2);
            addonListTable.setValueAt(addon.getInstalled(), position, 3);
            addonListTable.setValueAt(addon.getEndorsements(), position, 4);
            addonListTable.setValueAt(addon.getDownloads(), position, 5);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTable addonListTable;
    private JLabel addonTitle;
    private JRadioButtonMenuItem autoClose1;
    private JRadioButtonMenuItem autoClose10;
    private JRadioButtonMenuItem autoClose60;
    private JLabel currentTags;
    private JEditorPane description;
    private JTextField inputSearch;
    private JButton installButton;
    private JLabel localVersion;
    private JRadioButtonMenuItem menuDeutsch;
    private JRadioButtonMenuItem menuEnglish;
    private JRadioButtonMenuItem menuFrancais;
    private JMenu menuTags;
    private JMenu menuTheme;
    private JLabel remoteVersion;
    private JButton removeButton;
    private JTabbedPane rightSide;
    private JCheckBox uploadEnable;
    private JTextField uploadFile;
    private JTextArea uploadReason;
    private JTextField uploadUrl;
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
                int row = addonListTable.getSelectedRow();
                if (row != -1) {
                    activeAddon = addonList.get(addonListTable.convertRowIndexToModel(row));
                }
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
            if (ActualAddon.class.isInstance(activeAddon)) {
                new Thread(((ActualAddon) activeAddon).loadDescription(description, config.getLanguage())).start();
            }
            addonTitle.setText(activeAddon.getName());
            installButton.setEnabled(true);
            removeButton.setEnabled(true);
            rightSide.setEnabledAt(1, false);
            setTitle(activeAddon.getName());
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
