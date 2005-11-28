package org.seasar.maven.plugin.backport175;

import java.io.File;
import java.util.List;

/**
 * @author manhole
 * 
 * @goal annotationc
 * @phase compile
 * @requiresDependencyResolution compile
 */
public class AnnotationCMojo extends AbstractAnnotationCMojo {

    /**
     * @parameter expression="${project.compileSourceRoots}"
     * @required
     * @readonly
     */
    private List _compileSourceRoots;

    /**
     * Project classpath.
     *
     * @parameter expression="${project.compileClasspathElements}"
     * @required
     * @readonly
     */
    private List _compileClasspathElements;

    /**
     * @parameter expression="${project.build.outputDirectory}"
     * @required
     * @readonly
     */
    private File _outputDirectory;

    public List getSourcepath() {
        return _compileSourceRoots;
    }

    public List getClasspath() {
        return _compileClasspathElements;
    }

    public File getDestdir() {
        return _outputDirectory;
    }

}
