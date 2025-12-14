
package ws_ca;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cmplx_respuesta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="cmplx_respuesta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numPedido" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cmplx_respuesta", propOrder = {
    "numPedido"
})
public class CmplxRespuesta {

    protected int numPedido;

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

}
