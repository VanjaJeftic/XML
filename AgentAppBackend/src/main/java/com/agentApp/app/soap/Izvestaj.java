//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.21 at 04:27:47 PM CEST 
//


package com.agentApp.app.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for izvestaj complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="izvestaj"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="predjeniKm" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="komentar" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="vozilo" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="zahtev" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "izvestaj", propOrder = {
    "predjeniKm",
    "komentar",
    "vozilo",
    "zahtev"
})
public class Izvestaj {

    @XmlElement(required = true)
    protected String predjeniKm;
    @XmlElement(required = true)
    protected String komentar;
    protected long vozilo;
    protected long zahtev;

    /**
     * Gets the value of the predjeniKm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPredjeniKm() {
        return predjeniKm;
    }

    /**
     * Sets the value of the predjeniKm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPredjeniKm(String value) {
        this.predjeniKm = value;
    }

    /**
     * Gets the value of the komentar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKomentar() {
        return komentar;
    }

    /**
     * Sets the value of the komentar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKomentar(String value) {
        this.komentar = value;
    }

    /**
     * Gets the value of the vozilo property.
     * 
     */
    public long getVozilo() {
        return vozilo;
    }

    /**
     * Sets the value of the vozilo property.
     * 
     */
    public void setVozilo(long value) {
        this.vozilo = value;
    }

    /**
     * Gets the value of the zahtev property.
     * 
     */
    public long getZahtev() {
        return zahtev;
    }

    /**
     * Sets the value of the zahtev property.
     * 
     */
    public void setZahtev(long value) {
        this.zahtev = value;
    }

}
