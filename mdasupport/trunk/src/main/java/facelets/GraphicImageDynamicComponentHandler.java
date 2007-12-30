package facelets;

import com.sun.facelets.tag.MetaRuleset;
import com.sun.facelets.tag.MethodRule;
import com.sun.facelets.tag.jsf.ComponentConfig;
import com.sun.facelets.tag.jsf.html.HtmlComponentHandler;

public class GraphicImageDynamicComponentHandler extends HtmlComponentHandler
{
    protected final static Class[] GET_BYTES_METHOD_SIG = new Class[0];
    protected final static Class[] GET_CONTENT_TYPE_METHOD_SIG = new Class[0];
    protected final static MethodRule getBytesMethodTagRule
       = new MethodRule("getBytesMethod", byte[].class, GET_BYTES_METHOD_SIG);
    protected final static MethodRule getContentTypeMethodTagRule
       = new MethodRule("getContentTypeMethod", String.class, GET_CONTENT_TYPE_METHOD_SIG);
    
    public GraphicImageDynamicComponentHandler(ComponentConfig tagConfig)
    {
        super(tagConfig);
    }

    protected MetaRuleset createMetaRuleset(Class type)
    {
        MetaRuleset m = super.createMetaRuleset(type);

        m.addRule(getBytesMethodTagRule);
        m.addRule(getContentTypeMethodTagRule);

        return m;
    }
}