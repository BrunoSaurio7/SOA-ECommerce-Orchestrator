
package wspedido;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para montoCO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="montoCO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id_CO" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "montoCO", propOrder = {
    "idCO"
})
public class MontoCO {

    @XmlElement(name = "id_CO")
    protected int idCO;

    /**
     * Obtiene el valor de la propiedad idCO.
     * 
     */
    public int getIdCO() {
        return idCO;
    }

    /**
     * Define el valor de la propiedad idCO.
     * 
     */
    public void setIdCO(int value) {
        this.idCO = value;
    }

}
