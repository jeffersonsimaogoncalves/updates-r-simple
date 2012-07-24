package de.idos.updates.store;

import de.idos.updates.Version;

public class NullReport implements ProgressReport {
    @Override
    public void startingInstallationOf(Version version) {
        //nothing to do
    }

    @Override
    public void assemblingFileList() {
        //nothing to do
    }

    @Override
    public void foundElementsToInstall(int numberOfElements) {
        //nothing to do
    }

    @Override
    public void installingFile(String name) {
        //nothing to do
    }

    @Override
    public void expectedSize(long size) {
        //nothing to do
    }

    @Override
    public void progress(long progress) {
        //nothing to do
    }

    @Override
    public void finishedFile() {
        //nothing to do
    }

    @Override
    public void finishedInstallation() {
        //nothing to do
    }
}
