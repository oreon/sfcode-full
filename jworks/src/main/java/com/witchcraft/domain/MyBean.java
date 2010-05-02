package com.witchcraft.domain;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * @author joaquin. Mock class.
 */
@Name("myBean")
@Scope(ScopeType.CONVERSATION)
public class MyBean {

    /**
     * campo de prueba.
     */
    private String text = "domain";

    /**
     * @return text field.
     */
    public final String getText() {
        return text;
    }

    /**
     * @param textToSet
     *            text to set field.
     */
    public final void setText(final String textToSet) {
        this.text = textToSet;
    }

}
