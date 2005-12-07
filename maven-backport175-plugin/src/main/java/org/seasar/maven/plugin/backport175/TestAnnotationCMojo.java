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
