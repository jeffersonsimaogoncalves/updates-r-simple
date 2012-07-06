package de.idos.updates;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FilesystemVersionStore_HomeTest {

    private static String currentValue;
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @BeforeClass
    public static void setUp() throws Exception {
        currentValue = System.getProperty("user.home");
    }

    @Test
    public void createsFoldersInUserHome() throws Exception {
        System.setProperty("user.home", folder.getRoot().getAbsolutePath());
        VersionStore store = FilesystemVersionStore.inUserHomeForApplication("Demo");
        store.addVersion(new NumericVersion(1, 1, 1));
        File file = new File(folder.getRoot(), ".Demo/versions/1.1.1");
        System.out.println(file);
        assertThat(file.exists(), is(true));
    }

    @AfterClass
    public static void restoreValue() throws Exception {
        System.setProperty("user.home", currentValue);
    }
}
