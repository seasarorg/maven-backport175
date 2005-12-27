package ascii;

import junit.framework.TestCase;

import org.codehaus.backport175.reader.Annotations;

public class AsciiAnnotatedTest extends TestCase {

    public void testReadClassAnnotation() throws Exception {
        SomeClassAnnotation ann = (SomeClassAnnotation) Annotations
            .getAnnotation(SomeClassAnnotation.class, AsciiAnnotated.class);
        System.out.println(ann);
        assertNotNull("アノテーションを取得できること", ann);
        assertEquals("アノテーション値[123abcDEF]を取得できること", "123abcDEF", ann
            .someValue());
    }

    public void testReadMethodAnnotation() throws Exception {
        SomeMethodAnnotation ann = (SomeMethodAnnotation) Annotations
            .getAnnotation(SomeMethodAnnotation.class, AsciiAnnotated.class
                .getMethod("doSomethingB", null));
        System.out.println(ann);
        assertNotNull("アノテーションを取得できること", ann);
        assertEquals("アノテーション値[null]を取得できること", null, ann.someValueA());
        assertEquals("アノテーション値[foo]を取得できること", "foo", ann.someValueB());
    }

    public void testMethodAnnotationNotExist() throws Exception {
        SomeMethodAnnotation ann = (SomeMethodAnnotation) Annotations
            .getAnnotation(SomeMethodAnnotation.class, AsciiAnnotated.class
                .getMethod("doSomethingA", null));
        System.out.println(ann);
        assertNull("アノテーションは存在しないこと", ann);
    }

}
