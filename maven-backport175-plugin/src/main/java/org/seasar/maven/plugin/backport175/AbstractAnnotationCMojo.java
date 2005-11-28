package org.seasar.maven.plugin.backport175;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * @author manhole
 */
public abstract class AbstractAnnotationCMojo extends AbstractMojo implements
    AnnotarionCParameter {

    /**
     * @parameter property="verbose"
     */
    private boolean _verbose;

    /**
     * @parameter property="ignoreUnknown"
     */
    private boolean _ignoreUnknown;

    /**
     * @parameter property="properties"
     */
    private List _properties = Collections.EMPTY_LIST;

    /**
     * @parameter property="copytodest"
     */
    private String _copytodest;

    public String getCopytodest() {
        return _copytodest;
    }

    public void setCopytodest(String copytodest) {
        _copytodest = copytodest;
    }

    public boolean isIgnoreUnknown() {
        return _ignoreUnknown;
    }

    public void setIgnoreUnknown(boolean ignoreUnknown) {
        _ignoreUnknown = ignoreUnknown;
    }

    public List getProperties() {
        return _properties;
    }

    public void setProperties(List properties) {
        _properties = properties;
    }

    public boolean isVerbose() {
        return _verbose;
    }

    public void setVerbose(boolean verbose) {
        _verbose = verbose;
    }

    public void execute() throws MojoExecutionException {
        AnnotationCWrapper compiler = new AnnotationCWrapper();
        try {
            compiler.compile(this);
        } catch (IOException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

}
