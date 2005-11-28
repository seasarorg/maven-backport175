package org.seasar.maven.plugin.backport175;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * @author manhole
 * 
 * @goal testAnnotationc
 * @phase test-compile
 * @requiresDependencyResolution test
 */
public class TestAnnotationCMojo extends AbstractAnnotationCMojo {

    /**
     * @parameter expression="${project.testCompileSourceRoots}"
     * @required
     * @readonly
     */
    private List _testCompileSourceRoots;

    /**
     * Project test classpath.
     * 
     * @parameter expression="${project.testClasspathElements}"
     * @required
     * @readonly
     */
    private List _testClasspathElements;

    /**
     * @parameter expression="${project.build.testOutputDirectory}"
     * @required
     * @readonly
     */
    private File _testOutputDirectory;

    /**
     * @parameter expression="${maven.test.skip}"
     */
    private boolean _testSkip;

    public void execute() throws MojoExecutionException {
        if (_testSkip) {
            getLog().info("Not backport175 compiling test sources");
            return;
        }
        super.execute();
    }

    public List getSourcepath() {
        return _testCompileSourceRoots;
    }

    public List getClasspath() {
        return _testClasspathElements;
    }

    public File getDestdir() {
        return _testOutputDirectory;
    }

}
