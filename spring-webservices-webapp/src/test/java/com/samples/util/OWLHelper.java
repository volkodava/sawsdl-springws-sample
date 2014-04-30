package com.samples.util;

import java.util.HashSet;
import java.util.Set;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;

public class OWLHelper {

    private final String uri;
    private final OWLOntologyManager manager;
    private final IRI documentIRI;
    private final OWLOntology ontology;
    private final OWLDataFactory dataFactory;
    // a visitor to extract label annotations
    private final LabelExtractor labelExtractor;
    private final OWLReasonerFactory reasonerFactory;
    private final OWLClass owlThingClass;

    public OWLHelper(String uri) throws OWLOntologyCreationException {
        this.uri = uri;
        // We first need to obtain a copy of an
        // OWLOntologyManager, which manages a set of ontologies.
        manager = OWLManager.createOWLOntologyManager();
        // We load an ontology from the URI specified
        // on the command line
        documentIRI = IRI.create(uri);
        // Now load the ontology.
        ontology = manager.loadOntologyFromOntologyDocument(documentIRI);
        dataFactory = OWLManager.getOWLDataFactory();
        reasonerFactory = new StructuralReasonerFactory();
        // Get Thing
        owlThingClass = dataFactory.getOWLThing();
        // a visitor to extract label annotations
        labelExtractor = new LabelExtractor();
    }

    private String labelFor(OWLEntity clazz, OWLOntology o) {
        Set<OWLAnnotation> annotations = clazz.getAnnotations(o);
        for (OWLAnnotation anno : annotations) {
            String result = anno.accept(labelExtractor);
            if (result != null) {
                return result;
            }
        }
        return clazz.getIRI().toString();
    }

    public void printSuperclasses() throws Exception {
        OWLClass documentClass = dataFactory.getOWLClass(documentIRI);
        // for each superclass there will be a corresponding axiom
        // the ontology indexes axioms in a variety of ways
        Set<OWLSubClassOfAxiom> superClasses = ontology.getSubClassAxiomsForSubClass(documentClass);
        System.out.println(superClasses);
    }

    /**
     * Print the class hierarchy for the given ontology from this class down,
     * assuming this class is at the given level. Makes no attempt to deal
     * sensibly with multiple inheritance.
     *
     * @throws org.semanticweb.owlapi.model.OWLException
     */
    public void printHierarchy() throws OWLException {
        OWLReasoner reasoner = reasonerFactory.createNonBufferingReasoner(ontology);
        printHierarchy(reasoner, owlThingClass, 0, new HashSet<OWLClass>());
        /* Now print out any unsatisfiable classes */
        for (OWLClass cl : ontology.getClassesInSignature()) {
            if (!reasoner.isSatisfiable(cl)) {
                // System.out.println("XXX: " + labelFor(cl, ontology));
            }
        }
        reasoner.dispose();
    }

    public void printHierarchy(OWLReasoner reasoner, OWLClass clazz, int level,
        Set<OWLClass> visited) throws OWLException {
        // Only print satisfiable classes to skip Nothing
        if (!visited.contains(clazz) && reasoner.isSatisfiable(clazz)) {
            visited.add(clazz);
            for (int i = 0; i < level * 4; i++) {
                System.out.print(" ");
            }
            System.out.println(labelFor(clazz, reasoner.getRootOntology()));
            /* Find the children and recurse */
            NodeSet<OWLClass> subClasses = reasoner.getSubClasses(clazz, true);
            for (OWLClass child : subClasses.getFlattened()) {
                printHierarchy(reasoner, child, level + 1, visited);
            }
        }
    }
}
