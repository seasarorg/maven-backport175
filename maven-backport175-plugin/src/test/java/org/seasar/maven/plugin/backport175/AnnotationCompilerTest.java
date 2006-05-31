/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.maven.plugin.backport175;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.maven.BuildFailureException;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.cli.ConsoleDownloadMonitor;
import org.apache.maven.embedder.MavenEmbedder;
import org.apache.maven.embedder.MavenEmbedderConsoleLogger;
import org.apache.maven.embedder.PlexusLoggerAdapter;
import org.apache.maven.lifecycle.LifecycleExecutionException;
import org.apache.maven.monitor.event.DefaultEventMonitor;
import org.apache.maven.monitor.event.EventMonitor;
import org.apache.maven.project.DuplicateProjectException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuildingException;
import org.codehaus.plexus.util.dag.CycleDetectedException;

/**
 * @author manhole
 */
public class AnnotationCompilerTest extends TestCase {

    private MavenEmbedder maven;

    protected void setUp() throws Exception {
        super.setUp();
        maven = new MavenEmbedder();
        maven.setClassLoader(Thread.currentThread().getContextClassLoader());
        maven.setLogger(new MavenEmbedderConsoleLogger());
        maven.start();
    }

    protected void tearDown() throws Exception {
        maven.stop();
        super.tearDown();
    }

    public void testSimple() throws Exception {
        __testProject("simple");
    }

    public void testNoTest() throws Exception {
        __testProject("no-test");
    }

    public void testNoSource() throws Exception {
        __testProject("no-source");
    }

    public void testSkipTest() throws Exception {
        __testProject("skip-test");
    }

    public void testAsciiAnnotated() throws Exception {
        __testProject("ascii-annotated");
    }

    public void testProperties() throws Exception {
        __testProject("properties");
    }

    private void __testProject(final String projectName) throws IOException,
        ProjectBuildingException, ArtifactResolutionException,
        ArtifactNotFoundException, CycleDetectedException,
        LifecycleExecutionException, BuildFailureException, DuplicateProjectException {

        // ## Arrange ##
        // ## Act ##
        final long start = System.currentTimeMillis();
        File pom = getTestProjectFile(projectName + "/pom.xml");

        MavenProject mavenProject = maven.readProjectWithDependencies(pom);
        EventMonitor eventMonitor = new DefaultEventMonitor(
            new PlexusLoggerAdapter(new MavenEmbedderConsoleLogger()));

        maven.execute(mavenProject, Arrays.asList(new String[] { "clean",
            "package" }), eventMonitor, new ConsoleDownloadMonitor(),
            new Properties(), pom.getParentFile());

        // ## Assert ##
        File jarFile = getTestProjectFile(projectName + "/target/"
            + projectName + "-0.1.jar");
        assertEquals("testが成功しjarが作成されていること:" + jarFile, true, jarFile.exists());
        assertEquals("jarはこのtest内で作成されていること", true, start < jarFile
            .lastModified());
    }

    private File getTestProjectFile(String projectName) throws IOException {
        final File base = new File(".").getCanonicalFile();
        return new File(base, "src/test/projects/" + projectName);
    }

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
