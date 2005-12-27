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
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuildingException;
import org.codehaus.plexus.util.dag.CycleDetectedException;

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

    public void testAsciiAnnotated() throws Exception {
        __testProject("ascii-annotated");
    }

    private void __testProject(final String projectName) throws IOException,
        ProjectBuildingException, ArtifactResolutionException,
        ArtifactNotFoundException, CycleDetectedException,
        LifecycleExecutionException, BuildFailureException {

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
        assertEquals("testが成功しjarが作成されていること", true, jarFile.exists());
        assertEquals("jarはこのtest内で作成されていること", true, start < jarFile
            .lastModified());
    }

    private File getTestProjectFile(String projectName) throws IOException {
        final File base = new File(".").getCanonicalFile();
        return new File(base, "src/test/projects/" + projectName);
    }

}