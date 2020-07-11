//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.11 at 12:31:57 PM CEST 
//


package com.zahtev.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bundleRequests complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bundleRequests">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bundleID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="podnosilac" type="{http://zahtev.com/soap}user"/>
 *         &lt;element name="zahtev" type="{http://zahtev.com/soap}zahtev" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bundleRequests", propOrder = {
    "bundleID",
    "podnosilac",
    "zahtev"
})
public class BundleRequests {

    protected long bundleID;
    @XmlElement(required = true)
    protected User podnosilac;
    @XmlElement(required = true)
    protected List<Zahtev> zahtev;

    /**
     * Gets the value of the bundleID property.
     * 
     */
    public long getBundleID() {
        return bundleID;
    }

    /**
     * Sets the value of the bundleID property.
     * 
     */
    public void setBundleID(long value) {
        this.bundleID = value;
    }

    /**
     * Gets the value of the podnosilac property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getPodnosilac() {
        return podnosilac;
    }

    /**
     * Sets the value of the podnosilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setPodnosilac(User value) {
        this.podnosilac = value;
    }

    /**
     * Gets the value of the zahtev property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zahtev property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZahtev().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Zahtev }
     * 
     * 
     */
    public List<Zahtev> getZahtev() {
        if (zahtev == null) {
            zahtev = new ArrayList<Zahtev>();
        }
        return this.zahtev;
    }

}
