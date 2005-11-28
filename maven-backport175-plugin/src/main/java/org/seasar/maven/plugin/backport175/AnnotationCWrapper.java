package org.seasar.maven.plugin.backport175;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.tools.ant.ComponentHelper;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;
import org.codehaus.backport175.compiler.task.AnnotationCTask;

/**
 * @author manhole
 */
public class AnnotationCWrapper {

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
            classpath.setPath(path);
        }

        Path sourcepath = task.createSourcepath();
        for (Iterator it = parameter.getSourcepath().iterator(); it.hasNext();) {
            String path = (String) it.next();
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

}
