//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.09 at 11:31:53 AM CEST 
//


package com.agentApp.app.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for zahtev complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="zahtev"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="bundleID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="preuzimanje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="povratak" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="oglas" type="{http://zahtev.com/soap}oglas"/&gt;
 *         &lt;element name="izvestaj" type="{http://zahtev.com/soap}izvestaj"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zahtev", propOrder = {
    "id",
    "bundleID",
    "preuzimanje",
    "povratak",
    "status",
    "oglas",
    "izvestaj"
})
public class Zahtev {

    protected long id;
    protected long bundleID;
    @XmlElement(required = true)
    protected String preuzimanje;
    @XmlElement(required = true)
    protected String povratak;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    protected Oglas oglas;
    @XmlElement(required = true)
    protected Izvestaj izvestaj;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

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
     * Gets the value of the preuzimanje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreuzimanje() {
        return preuzimanje;
    }

    /**
     * Sets the value of the preuzimanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreuzimanje(String value) {
        this.preuzimanje = value;
    }

    /**
     * Gets the value of the povratak property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPovratak() {
        return povratak;
    }

    /**
     * Sets the value of the povratak property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPovratak(String value) {
        this.povratak = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the oglas property.
     * 
     * @return
     *     possible object is
     *     {@link Oglas }
     *     
     */
    public Oglas getOglas() {
        return oglas;
    }

    /**
     * Sets the value of the oglas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Oglas }
     *     
     */
    public void setOglas(Oglas value) {
        this.oglas = value;
    }

    /**
     * Gets the value of the izvestaj property.
     * 
     * @return
     *     possible object is
     *     {@link Izvestaj }
     *     
     */
    public Izvestaj getIzvestaj() {
        return izvestaj;
    }

    /**
     * Sets the value of the izvestaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link Izvestaj }
     *     
     */
    public void setIzvestaj(Izvestaj value) {
        this.izvestaj = value;
    }

}
