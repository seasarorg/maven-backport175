package org.seasar.maven.plugin.backport175;

import java.io.File;
import java.util.List;

/**
 * @author manhole
 */
public interface AnnotarionCParameter {

    public List getSourcepath();

    public List getClasspath();

    public File getDestdir();

    public String getCopytodest();

    public boolean isIgnoreUnknown();

    public List getProperties();

    public boolean isVerbose();

}
