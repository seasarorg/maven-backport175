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
package property;

import junit.framework.TestCase;

import org.codehaus.backport175.reader.Annotations;

public class BarTest extends TestCase {

    public void test1() throws Exception {
        Bbb1Annotation ann = (Bbb1Annotation) Annotations.getAnnotation(
            Bbb1Annotation.class, Bar.class);
        System.out.println(ann);
        assertEquals("get annotated value", "bbbbb1", ann.value());
    }

    public void test2() throws Exception {
        Bbb2Annotation ann = (Bbb2Annotation) Annotations.getAnnotation(
            Bbb2Annotation.class, Bar.class.getMethod("baaaar", null));
        System.out.println(ann);
        assertEquals("get annotated value", "bbbbb2", ann.value());
    }

}
