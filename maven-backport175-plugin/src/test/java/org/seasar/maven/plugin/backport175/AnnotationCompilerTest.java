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

    protected MavenEmbedder maven;

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
        runProjectTest("simple");
    }

    public void testNoTest() throws Exception {
        runProjectTest("no-test");
    }

    public void testNoSource() throws Exception {
        runProjectTest("no-source");
    }

    public void testSkipTest() throws Exception {
        runProjectTest("skip-test");
    }

    public void testAsciiAnnotated() throws Exception {
        runProjectTest("ascii-annotated");
    }

    public void testProperties() throws Exception {
        runProjectTest("properties");
    }

    // TODO
    public void pending_testInherit() throws Exception {
        runProjectTest("inherit");
    }

    // TODO
    public void pending_testJapaneseAnnotated() throws Exception {
        runProjectTest("japanese-annotated");
    }

    protected void runProjectTest(final String projectName) throws IOException,
        ProjectBuildingException, ArtifactResolutionException,
        ArtifactNotFoundException, CycleDetectedException,
        LifecycleExecutionException, BuildFailureException,
        DuplicateProjectException {

        // ## Arrange ##
        // ## Act ##
        final long start = System.currentTimeMillis();
        File pom = getTestProjectFile(projectName + "/pom.xml");

        MavenProject mavenProject = maven.readProjectWithDependencies(pom);
        EventMonitor eventMonitor = new DefaultEventMonitor(
            new PlexusLoggerAdapter(new MavenEmbedderConsoleLogger()));

        final File executionRootDirectory = pom.getParentFile();
        System.out.println("executionRootDirectory=" + executionRootDirectory);
        maven.execute(mavenProject, Arrays.asList(new String[] { "clean",
            "package" }), eventMonitor, new ConsoleDownloadMonitor(),
            new Properties(), executionRootDirectory);

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

}
