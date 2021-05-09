package de.idrinth.waraddonclient.service;

import com.sun.nio.file.ExtendedWatchEventModifier;
import de.idrinth.waraddonclient.Config;
import de.idrinth.waraddonclient.list.AddonList;
import java.io.File;
import java.io.IOException;

public class FileWatcher implements java.lang.Runnable {

    private java.nio.file.WatchService watcher;
    
    private final AddonList addonList;
    
    private final FileLogger logger;

    public FileWatcher(AddonList addonList, FileLogger logger) {
        this.addonList = addonList;
        this.logger = logger;
    }
    
    @Override
    public void run() {
        try {
            File path = new File(Config.getWARPath() + "/logs");
            if (!path.exists()) {
                path.mkdirs();
            }
            watcher = path.toPath().getFileSystem().newWatchService();
            java.nio.file.WatchEvent.Kind[] modes = new java.nio.file.WatchEvent.Kind[2];
            modes[0] = java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
            modes[1] = java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
            path.toPath().register(
                    watcher,
                    modes,
                    ExtendedWatchEventModifier.FILE_TREE
            );
            handleEvents();
        } catch (InterruptedException | IOException exception) {
            logger.error(exception);
        }
    }

    /**
     * lets filesbe uploaded if so desired
     *
     * @throws InterruptedException
     */
    private void handleEvents() throws InterruptedException {
        while (true) {
            java.nio.file.WatchKey key = watcher.take();
            key.pollEvents().stream().filter((event) -> (isValidEvent(event))).map((event) -> new java.io.File(Config.getWARPath() + "/logs/" + event.context().toString())).filter((file) -> (isValidFile(file))).forEach((file) -> {
                addonList.getWatchedFiles().get(file.getName().toLowerCase()).setFileToProcess(file);
            });
            key.reset();
        }
    }

    /**
     * is this an event to process?
     *
     * @param event
     * @return
     */
    private boolean isValidEvent(java.nio.file.WatchEvent event) {
        return event.kind() == java.nio.file.StandardWatchEventKinds.ENTRY_CREATE || event.kind() == java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
    }

    /**
     * is this a file to process?
     *
     * @param file
     * @return
     */
    private boolean isValidFile(java.io.File file) {
        return addonList.getWatchedFiles().containsKey(file.getName().toLowerCase());
    }
}
