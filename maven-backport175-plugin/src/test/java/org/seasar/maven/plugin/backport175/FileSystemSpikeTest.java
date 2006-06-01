package org.seasar.maven.plugin.backport175;

import java.io.File;
import java.net.URL;

import junit.framework.TestCase;

public class FileSystemSpikeTest extends TestCase {

    public void testLearningFile() throws Exception {
        final File file = new File(".");
        assertEquals(false, file.isAbsolute());
        assertEquals(".", file.getName());

        final File canonicalFile = file.getCanonicalFile();
        assertEquals(true, canonicalFile.isAbsolute());
        assertEquals(true, canonicalFile.isDirectory());
        assertEquals("maven-backport175-plugin", canonicalFile.getName());
    }

    public void testLearningResource() throws Exception {
        final URL url = Thread.currentThread().getContextClassLoader()
            .getResource(".");
        final String file = url.getFile();
        final String path = url.getPath();
        assertEquals(file, path);
        if (file.endsWith("/maven-backport175-plugin/target/test-classes/")) {
            // OK
        } else if (file
            .endsWith("/maven-backport175-plugin/target/clover/test-classes/")) {
            // OK (executed by "maven-clover-plugin")
        } else {
            fail(file);
        }
    }

}
