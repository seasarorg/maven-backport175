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
     * flag marking the task verbosity.
     * 
     * @parameter property="verbose"
     */
    private boolean verbose;

    /**
     * flag marking if the task should ignore unknown annotations
     * (like f.e. @author if not associated to an annotation interface)
     * 
     * @parameter property="ignoreUnknown"
     */
    private boolean ignoreUnknown;

    /**
     * path to a properties file when user-defined annoations are to be used.
     * 
     * @parameter property="properties"
     */
    private List properties = Collections.EMPTY_LIST;

    public boolean isIgnoreUnknown() {
        return ignoreUnknown;
    }

    public void setIgnoreUnknown(boolean ignoreUnknown) {
        this.ignoreUnknown = ignoreUnknown;
    }

    public List getProperties() {
        return properties;
    }

    public void setProperties(List properties) {
        this.properties = properties;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void execute() throws MojoExecutionException {
        AnnotationCWrapper compiler = new AnnotationCWrapper();
        compiler.setLog(getLog());
        try {
            compiler.compile(this);
        } catch (IOException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

}
