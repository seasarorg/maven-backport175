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
package japanese;

import junit.framework.TestCase;

import org.codehaus.backport175.reader.Annotations;

public class JapaneseAnnotatedTest extends TestCase {

    public void testReadClassAnnotation() throws Exception {
        SomeClassAnnotation ann = (SomeClassAnnotation) Annotations
            .getAnnotation(SomeClassAnnotation.class, JapaneseAnnotated.class);
        System.out.println(ann);
        assertNotNull("get annotation", ann);
        assertEquals("get annotated value [あいう]", "あいう", ann
            .someValue());
    }

    public void testReadMethodAnnotation() throws Exception {
        SomeMethodAnnotation ann = (SomeMethodAnnotation) Annotations
            .getAnnotation(SomeMethodAnnotation.class, JapaneseAnnotated.class
                .getMethod("doSomethingB", null));
        System.out.println(ann);
        assertNotNull("get annotation", ann);
        assertEquals("get annotated value [null]", null, ann.someValueA());
        assertEquals("get annotated value [か]", "か", ann.someValueB());
    }

    public void testMethodAnnotationNotExist() throws Exception {
        SomeMethodAnnotation ann = (SomeMethodAnnotation) Annotations
            .getAnnotation(SomeMethodAnnotation.class, JapaneseAnnotated.class
                .getMethod("doSomethingA", null));
        System.out.println(ann);
        assertEquals(null, ann);
    }

}
