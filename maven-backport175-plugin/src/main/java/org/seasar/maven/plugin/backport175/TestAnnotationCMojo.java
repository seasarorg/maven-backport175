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

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * Compile backport175 annotations. (test-source)
 * 
 * @author manhole
 * 
 * @goal testAnnotationc
 * @phase test-compile
 * @requiresDependencyResolution test
 */
public class TestAnnotationCMojo extends AbstractAnnotationCMojo {

    /**
     * The source directories containing the test-source to be compiled.
     * 
     * @parameter expression="${project.testCompileSourceRoots}"
     * @required
     * @readonly
     */
    private List testCompileSourceRoots;

    /**
     * Project test classpath.
     * 
     * @parameter expression="${project.testClasspathElements}"
     * @required
     * @readonly
     */
    private List testClasspathElements;

    /**
     * The directory where compiled test classes go.
     * 
     * @parameter expression="${project.build.testOutputDirectory}"
     * @required
     * @readonly
     */
    private File testOutputDirectory;

    /**
     * Set this to 'true' to bypass unit tests entirely.
     * Its use is NOT RECOMMENDED, but quite convenient on occasion.
     * 
     * @parameter expression="${maven.test.skip}"
     */
    private boolean testSkip;

    public void execute() throws MojoExecutionException {
        if (testSkip) {
            getLog().info("Not backport175 compiling test sources");
            return;
        }
        super.execute();
    }

    public List getSourcepath() {
        return testCompileSourceRoots;
    }

    public List getClasspath() {
        return testClasspathElements;
    }

    public File getDestdir() {
        return testOutputDirectory;
    }

}
