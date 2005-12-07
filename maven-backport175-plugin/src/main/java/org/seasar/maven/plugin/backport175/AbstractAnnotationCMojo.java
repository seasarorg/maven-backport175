/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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
        compiler.setLog(getLog());
        try {
            compiler.compile(this);
        } catch (IOException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

}
