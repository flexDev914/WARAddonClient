/*
 * Copyright (C) 2016 Björn Büttner
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.idrinth.waraddonclient.implementation.service;

public class FileWatcher implements java.lang.Runnable {

    protected java.nio.file.WatchService watcher = null;

    @Override
    public void run() {
        try {
            java.nio.file.Path path = java.nio.file.FileSystems.getDefault().getPath("logs");
            if (!path.toFile().exists()) {
                path.toFile().mkdirs();
            }
            watcher = path.getFileSystem().newWatchService();
            java.nio.file.WatchEvent.Kind[] modes = new java.nio.file.WatchEvent.Kind[2];
            modes[0] = java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
            modes[1] = java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
            path.register(
                    watcher,
                    modes,
                    com.sun.nio.file.ExtendedWatchEventModifier.FILE_TREE);
            handleEvents();
        } catch (InterruptedException | java.io.IOException exception) {
            de.idrinth.factory.Logger.build().log(exception.getMessage(), de.idrinth.Logger.levelError);
        }
    }

    protected void handleEvents() throws InterruptedException {
        while (true) {
            java.nio.file.WatchKey key = watcher.take();
            for (java.nio.file.WatchEvent event : key.pollEvents()) {
                if (event.kind() == java.nio.file.StandardWatchEventKinds.ENTRY_CREATE
                        || event.kind() == java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY) {
                    java.io.File file = new java.io.File("./logs/" + event.context().toString());
                    if (de.idrinth.waraddonclient.factory.AddonList.build().getWatchedFiles().containsKey(file.getName().toLowerCase())) {
                        de.idrinth.waraddonclient.factory.AddonList.build().getWatchedFiles().get(file.getName().toLowerCase()).setFileToProcess(file);
                    }
                }
            }
            key.reset();
        }
    }
}
