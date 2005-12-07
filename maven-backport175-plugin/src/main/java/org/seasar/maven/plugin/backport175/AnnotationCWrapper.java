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
import java.io.IOException;
import java.util.Iterator;

import org.apache.maven.plugin.logging.Log;
import org.apache.tools.ant.ComponentHelper;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;
import org.codehaus.backport175.compiler.task.AnnotationCTask;

/**
 * @author manhole
 */
public class AnnotationCWrapper {

    private Log _log;

    private Project createProject() {
        Project project = new Project();
        project.setName("maven-backport175");
        ComponentHelper componentHelper = ComponentHelper
            .getComponentHelper(project);
        componentHelper.initDefaultDefinitions();
        return project;
    }

    public void compile(AnnotarionCParameter parameter) throws IOException {
        AnnotationCTask task = new AnnotationCTask();

        task.setProject(createProject());
        task.setVerbose(parameter.isVerbose());
        task.setIgnoreUnknown(parameter.isIgnoreUnknown());
        task.setCopytodest(parameter.getCopytodest());

        File destdir = parameter.getDestdir();

        Path classpath = task.createClasspath();
        classpath.setPath(destdir.getCanonicalPath());
        for (Iterator it = parameter.getClasspath().iterator(); it.hasNext();) {
            String path = (String) it.next();
            if (!new File(path).exists()) {
                _log.info("classpath does not exist: " + path);
                continue;
            }
            classpath.setPath(path);
        }

        Path sourcepath = task.createSourcepath();
        for (Iterator it = parameter.getSourcepath().iterator(); it.hasNext();) {
            String path = (String) it.next();
            if (!new File(path).exists()) {
                _log.info("source directory does not exist: " + path);
                return;
            }
            sourcepath.setPath(path);
        }

        if (!destdir.exists()) {
            if (!destdir.mkdirs()) {
                throw new IOException("Could not create destDir: "
                    + destdir.getAbsolutePath());
            }
        }
        task.setDestdir(destdir);

        Path properties = task.createProperties();
        for (Iterator it = parameter.getProperties().iterator(); it.hasNext();) {
            String property = (String) it.next();
            properties.setPath(property);
        }

        task.execute();
    }

    public void setLog(Log log) {
        _log = log;
    }

}
