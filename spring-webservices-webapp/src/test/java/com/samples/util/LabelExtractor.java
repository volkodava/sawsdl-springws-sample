package com.samples.util;

import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationObjectVisitorEx;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.util.OWLObjectVisitorExAdapter;

public class LabelExtractor extends OWLObjectVisitorExAdapter<String> implements
    OWLAnnotationObjectVisitorEx<String> {

    @Override
    public String visit(OWLAnnotation annotation) {
        /*
         * If it's a label, grab it as the result. Note that if there are
         * multiple labels, the last one will be used.
         */
        if (annotation.getProperty().isLabel()) {
            OWLLiteral c = (OWLLiteral) annotation.getValue();
            return c.getLiteral();
        }
        return null;
    }
}
