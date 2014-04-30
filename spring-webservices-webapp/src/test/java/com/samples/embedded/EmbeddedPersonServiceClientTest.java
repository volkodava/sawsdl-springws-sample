package com.samples.embedded;

import com.samples.util.OWLHelper;
import edu.uga.cs.lsdis.sawsdl.Definition;
import edu.uga.cs.lsdis.sawsdl.ModelReference;
import edu.uga.cs.lsdis.sawsdl.PortType;
import edu.uga.cs.lsdis.sawsdl.extensions.schema.Schema;
import edu.uga.cs.lsdis.sawsdl.impl.util.SchemaUtils;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.wsdl.Types;
import javax.wsdl.WSDLException;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;
import org.junit.Before;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-config-test.xml")
public class EmbeddedPersonServiceClientTest {

    @Autowired
    @Qualifier("wsProps")
    private Properties properties;

    protected final String TARGET_NS = "http://com/samples/webservices/purchaseorderservice";
    protected final String PORTTYPE_NAME = "PurchaseOrderService";
    protected final String OPERATION_NAME = "PurchaseOrder";

    private String wsdlURI;

    /**
     * Start Web Services in an embedded Jetty instance with the main
     * configuration context as the parent of the embedded Web Service context.
     */
    @BeforeClass
    public static void init() throws Exception {
        AbstractApplicationContext ctx
            = new ClassPathXmlApplicationContext("/embedded-jetty.xml");
        ctx.registerShutdownHook();

        Server server = (Server) ctx.getBean("jettyServer");

        ServletContext servletContext = null;

        for (Handler handler : server.getHandlers()) {
            if (handler instanceof Context) {
                Context context = (Context) handler;

                servletContext = context.getServletContext();
            }
        }

        XmlWebApplicationContext wctx = new XmlWebApplicationContext();
        wctx.setParent(ctx);
        wctx.setConfigLocation("");
        wctx.setServletContext(servletContext);
        wctx.refresh();

        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, wctx);

        server.start();
    }

    @Before
    public void preInit() {
        wsdlURI = getWsdlURI();
    }

    private String getWsdlURI() {
        String host = properties.getProperty("ws.host");
        String port = properties.getProperty("ws.port");
        String context = properties.getProperty("ws.context.path");
        String wsdl = properties.getProperty("ws.wsdl");
        String url = "http://" + host + ":" + port + "/" + context + "/" + wsdl;

        return url;
    }

    /**
     * Tests SAWSDL attribute extensions model reference.
     *
     * @throws java.lang.Exception
     */
    @Test
    @DirtiesContext
    public void testAttributeExtensionsModelReference() throws Exception {
        Definition sawsdlDefinition = getDefinition();
        PortType portType = sawsdlDefinition.getSemanticPortType(new QName(TARGET_NS, PORTTYPE_NAME));
        edu.uga.cs.lsdis.sawsdl.Operation sawsdOperation
            = portType.getSemanticOperation(OPERATION_NAME, null, null);
        ModelReference modelReference = sawsdOperation.getModelReference();

        String modelReferenceNamespace = getNamespace(modelReference);
        System.out.println("Model reference namespace: " + modelReferenceNamespace);

        String concept = getConcept(modelReference);
        assertThat("RequestPurchaseOrder", equalTo(concept));

        printOwlInfo(modelReference.toString());
    }

    /**
     * Tests SAWSDL attribute annotation model reference.
     *
     * @throws java.lang.Exception
     */
    @Test
    @DirtiesContext
    public void testAttributeAnnotationModelReference() throws Exception {
        Definition sawsdlDefinition = getDefinition();
        Types types = sawsdlDefinition.getTypes();
        List<Schema> schemaList = SchemaUtils.getSchemas(types);

        assertThat(schemaList, is(notNullValue()));
        assertThat(schemaList.size(), greaterThan(0));

        Schema s = schemaList.get(0);
        Set<ModelReference> modelReferences;
        ModelReference modelReference;
        String concept;

        modelReferences = s.getModelReferences(s.getElement(), "//xsd:complexType[1]/xsd:sequence/xsd:element[@name=\"orderQuantity\"]", sawsdlDefinition);

        assertThat(modelReferences, is(notNullValue()));
        assertThat(modelReferences.size(), greaterThan(0));

        modelReference = getSingleModelReference(modelReferences);
        concept = getConcept(modelReference);
        assertThat("OrderQuantity", equalTo(concept));

        modelReferences = s.getModelReferences(s.getElement(), "//xsd:complexType[1]/xsd:sequence/xsd:element[@name=\"productCost\"]", sawsdlDefinition);

        assertThat(modelReferences, is(notNullValue()));
        assertThat(modelReferences.size(), greaterThan(0));

        modelReference = getSingleModelReference(modelReferences);
        concept = getConcept(modelReference);
        assertThat("UnitPrice", equalTo(concept));
    }

    /**
     * Get the SAWSDL definition.
     *
     * @return the SAWSDL definition component
     */
    private Definition getDefinition() {
        try {
            System.setProperty("javax.wsdl.factory.WSDLFactory",
                "edu.uga.cs.lsdis.sawsdl.impl.factory.WSDLFactoryImpl");

            WSDLReader wsdlReader = WSDLFactory.newInstance().newWSDLReader();
            return (Definition) wsdlReader.readWSDL(wsdlURI);
        } catch (WSDLException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        return null;
    }

    /**
     * Get the SAWSDL concept.
     *
     * @return the SAWSDL concept
     */
    private String getConcept(ModelReference ref) {
        if (ref.toString() == null) {
            return null;
        } else {
            return ref.toString().split("#")[1];
        }
    }

    private ModelReference getSingleModelReference(Set<ModelReference> modelRef) {
        return modelRef.toArray(new ModelReference[modelRef.size()])[0];
    }

    private String getNamespace(ModelReference ref) {
        if (ref.toString() == null) {
            return null;
        } else {
            return ref.toString().split("#")[0];
        }
    }

    private void printOwlInfo(String uri) throws Exception {
        // Print the hierarchy below thing
        OWLHelper helper = new OWLHelper(uri);
//        helper.printHierarchy();
        helper.printSuperclasses();
    }
}
