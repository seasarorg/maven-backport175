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
package simple;

import java.lang.reflect.Method;

import junit.framework.TestCase;

/**
 * @author manhole
 */
public class AnnotationCTaskBugTest extends TestCase {

    /*
     * 通常、サブクラスでメソッドを実装しない場合は、親のMethodオブジェクトを取得できる。
     * 
     * しかし、backport175-1.0のAnnotationCTaskではサブクラスのバイトコードに
     * メソッド情報を埋め込むようで、このassertはfailしてしまう。
     * 
     * AnnotationCTaskを行わない場合は、assertはsuccessする。
     */
    // TODO backport175の修正待ち
    // https://www.seasar.org/issues/browse/MAVENBACKPORT-2
    public void pending_testGetMethod() throws Exception {
        final Method ifsMethod = Foo.class.getMethod("baaaar", null);
        final Method abstractClassMethod = FooImpl.class.getMethod("baaaar",
            null);
        assertEquals(ifsMethod, abstractClassMethod);
    }

    public static interface Foo {
        void baaaar();
    }

    public static abstract class FooImpl implements Foo {
    }

    public void testTodo() throws Exception {
        System.out.println("TODO: backport175の修正待ち。https://www.seasar.org/issues/browse/MAVENBACKPORT-2");
    }

}
