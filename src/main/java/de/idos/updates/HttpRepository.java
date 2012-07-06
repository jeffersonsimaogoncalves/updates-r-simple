package de.idos.updates;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class HttpRepository implements Repository {
    private URL baseUrl;

    public HttpRepository(String url) {
        try {
            this.baseUrl = new URL(url);
        } catch (IOException e) {
            throw new IllegalArgumentException("Please specify a valid URL as your repository base.", e);
        }
    }

    @Override
    public Version getLatestVersion() {
        try {
            return readVersionsFromRepository();
        } catch (IOException e) {
            return VersionFinder.BASE_VERSION;
        }
    }

    @Override
    public void transferVersionTo(Version version, VersionStore store) {
        store.addVersion(version);
        try {
            URL contentList = new URL(baseUrl, "updates/" + version.asString() + "/content");
            InputStream input = contentList.openStream();
            List<String> filesToLoad = IOUtils.readLines(input);
            for (String file : filesToLoad) {
                URL fileUrl = new URL(baseUrl, "updates/" + version.asString() + "/" + file);
                store.addContent(version, file, fileUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private Version readVersionsFromRepository() throws IOException {
        URL versionList = new URL(baseUrl, "updates/availableVersions");
        InputStream input = versionList.openStream();
        List<String> strings = IOUtils.readLines(input);
        List<Version> versions = new VersionFactory().createVersionsFromStrings(strings);
        return new VersionFinder().findLatestVersion(versions);
    }
}
