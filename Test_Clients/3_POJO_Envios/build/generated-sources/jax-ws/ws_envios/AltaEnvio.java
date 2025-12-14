
package ws_envios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para alta_envio complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="alta_envio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id_clte" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="num_pedido" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="num_dias" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "alta_envio", propOrder = {
    "idClte",
    "numPedido",
    "numDias"
})
public class AltaEnvio {

    @XmlElement(name = "id_clte")
    protected int idClte;
    @XmlElement(name = "num_pedido")
    protected int numPedido;
    @XmlElement(name = "num_dias")
    protected int numDias;

    /**
     * Obtiene el valor de la propiedad idClte.
     * 
     */
    public int getIdClte() {
        return idClte;
    }

    /**
     * Define el valor de la propiedad idClte.
     * 
     */
    public void setIdClte(int value) {
        this.idClte = value;
    }

    /**
     * Obtiene el valor de la propiedad numPedido.
     * 
     */
    public int getNumPedido() {
        return numPedido;
    }

    /**
     * Define el valor de la propiedad numPedido.
     * 
     */
    public void setNumPedido(int value) {
        this.numPedido = value;
    }

    /**
     * Obtiene el valor de la propiedad numDias.
     * 
     */
    public int getNumDias() {
        return numDias;
    }

    /**
     * Define el valor de la propiedad numDias.
     * 
     */
    public void setNumDias(int value) {
        this.numDias = value;
    }

}
