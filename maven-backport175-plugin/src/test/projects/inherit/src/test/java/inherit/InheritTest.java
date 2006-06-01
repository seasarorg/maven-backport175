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
package inherit;

import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.codehaus.backport175.reader.Annotations;

public class InheritTest extends TestCase {

    public void testFromInterface() throws Exception {
        A a = (A) Annotations.getAnnotation(A.class, Bar.class);
        assertNotNull(a);
        assertEquals("aaa", a.value());

        final Method method = Bar.class.getMethod("baaaar", null);
        B b = (B) Annotations.getAnnotation(B.class, method);
        assertNotNull(b);
        assertEquals("bbb", b.value());
    }

    public void testFromAbstractClass() throws Exception {
        A a = (A) Annotations.getAnnotation(A.class, BarImpl.class);
        assertNull(a);

        final Method method = BarImpl.class.getMethod("baaaar", null);
        B b = (B) Annotations.getAnnotation(B.class, method);
        assertNotNull(b);
        assertEquals("bbb", b.value());
    }

}
