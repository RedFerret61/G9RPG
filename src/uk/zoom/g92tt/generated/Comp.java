//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.11.23 at 05:56:36 PM GMT 
//


package uk.zoom.g92tt.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}State"/>
 *         &lt;element ref="{}Type"/>
 *         &lt;element ref="{}Parm1"/>
 *         &lt;element ref="{}Parm2"/>
 *         &lt;element ref="{}Parm3"/>
 *         &lt;element ref="{}Parm4"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "state",
    "type",
    "parm1",
    "parm2",
    "parm3",
    "parm4"
})
@XmlRootElement(name = "Comp")
public class Comp {

    @XmlElement(name = "State", required = true)
    protected State state;
    @XmlElement(name = "Type", required = true)
    protected Type type;
    @XmlElement(name = "Parm1", required = true)
    protected Parm1 parm1;
    @XmlElement(name = "Parm2", required = true)
    protected Parm2 parm2;
    @XmlElement(name = "Parm3", required = true)
    protected Parm3 parm3;
    @XmlElement(name = "Parm4", required = true)
    protected Parm4 parm4;

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link State }
     *     
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link State }
     *     
     */
    public void setState(State value) {
        this.state = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link Type }
     *     
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link Type }
     *     
     */
    public void setType(Type value) {
        this.type = value;
    }

    /**
     * Gets the value of the parm1 property.
     * 
     * @return
     *     possible object is
     *     {@link Parm1 }
     *     
     */
    public Parm1 getParm1() {
        return parm1;
    }

    /**
     * Sets the value of the parm1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parm1 }
     *     
     */
    public void setParm1(Parm1 value) {
        this.parm1 = value;
    }

    /**
     * Gets the value of the parm2 property.
     * 
     * @return
     *     possible object is
     *     {@link Parm2 }
     *     
     */
    public Parm2 getParm2() {
        return parm2;
    }

    /**
     * Sets the value of the parm2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parm2 }
     *     
     */
    public void setParm2(Parm2 value) {
        this.parm2 = value;
    }

    /**
     * Gets the value of the parm3 property.
     * 
     * @return
     *     possible object is
     *     {@link Parm3 }
     *     
     */
    public Parm3 getParm3() {
        return parm3;
    }

    /**
     * Sets the value of the parm3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parm3 }
     *     
     */
    public void setParm3(Parm3 value) {
        this.parm3 = value;
    }

    /**
     * Gets the value of the parm4 property.
     * 
     * @return
     *     possible object is
     *     {@link Parm4 }
     *     
     */
    public Parm4 getParm4() {
        return parm4;
    }

    /**
     * Sets the value of the parm4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parm4 }
     *     
     */
    public void setParm4(Parm4 value) {
        this.parm4 = value;
    }

}
