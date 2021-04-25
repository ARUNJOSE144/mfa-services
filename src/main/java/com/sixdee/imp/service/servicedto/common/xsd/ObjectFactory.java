
package com.sixdee.imp.service.servicedto.common.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sixdee.imp.service.servicedto.common.xsd package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DataName_QNAME = new QName("http://common.serviceDTO.service.imp.sixdee.com/xsd", "name");
    private final static QName _DataValue_QNAME = new QName("http://common.serviceDTO.service.imp.sixdee.com/xsd", "value");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sixdee.imp.service.servicedto.common.xsd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Data }
     * 
     */
    public Data createData() {
        return new Data();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.serviceDTO.service.imp.sixdee.com/xsd", name = "name", scope = Data.class)
    public JAXBElement<String> createDataName(String value) {
        return new JAXBElement<String>(_DataName_QNAME, String.class, Data.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.serviceDTO.service.imp.sixdee.com/xsd", name = "value", scope = Data.class)
    public JAXBElement<String> createDataValue(String value) {
        return new JAXBElement<String>(_DataValue_QNAME, String.class, Data.class, value);
    }

}
