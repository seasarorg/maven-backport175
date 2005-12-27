package ascii;

public interface SomeMethodAnnotation {

    public String someValueA();

    /**
     * @org.codehaus.backport175.DefaultValue ("foo")
     */
    public String someValueB();

}
