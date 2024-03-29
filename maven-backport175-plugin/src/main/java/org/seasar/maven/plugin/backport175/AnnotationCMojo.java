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
import java.util.List;

/**
 * Compile backport175 annotations.
 * 
 * @author manhole
 * 
 * @goal annotationc
 * @phase compile
 * @requiresDependencyResolution compile
 */
public class AnnotationCMojo extends AbstractAnnotationCMojo {

    /**
     * The source directories containing the sources to be compiled.
     * 
     * @parameter expression="${project.compileSourceRoots}"
     * @required
     * @readonly
     */
    private List compileSourceRoots;

    /**
     * Project classpath.
     *
     * @parameter expression="${project.compileClasspathElements}"
     * @required
     * @readonly
     */
    private List compileClasspathElements;

    /**
     * The directory for compiled classes.
     * 
     * @parameter expression="${project.build.outputDirectory}"
     * @required
     * @readonly
     */
    private File outputDirectory;

    public List getSourcepath() {
        return compileSourceRoots;
    }

    public List getClasspath() {
        return compileClasspathElements;
    }

    public File getDestdir() {
        return outputDirectory;
    }

}
