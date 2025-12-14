/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo_ca;

import ws_ca.CmplxRespuesta;
import ws_ca.SolicitarPedidoOperationFault;

/**
 *
 * @author rafael
 */
public class Pojo_CA implements interfazdesacoplada.InterfazDesacoplada
{
    public static java.util.List<wspedido.Customer> listaCltes = new java.util.ArrayList<>();
    public static java.util.List<wspedido.Product>  listaProds = new java.util.ArrayList<>();

    long quienSoy;
    String host = null;
    ws_ca.CmplxSolicitud solicitud = new ws_ca.CmplxSolicitud();
    ws_ca.CmplxRespuesta respuesta = new ws_ca.CmplxRespuesta();
    ws_ca.CmplxExepcion  excepcion;
    
    int num_cltes;
    int num_prods;
 
    @Override
    public void prepara(long quienSoy) 
    {
        this.quienSoy = quienSoy;
        this.host     = host; 
        
        if(listaCltes.isEmpty()) listaCltes  = catalogoCltes();
        if(listaProds.isEmpty()) listaProds  = catalogoProds();
        num_cltes = listaCltes.size();
        num_prods = listaProds.size();
    }

    @Override
    public long solicitaServicio(int vez) 
    {
        java.util.List<wspedido.ClsItem>  listaIt    = new java.util.ArrayList<>();
        long t0,t1,deltaT;
        int num_envio;
        int id_clte;
        int num_it;
        int id_prod,cantidad;

        int queClte,queProd = 0;
       
        wspedido.ClsItem item;
        
        queClte = (int) ( num_cltes * Math.random());
        id_clte = listaCltes.get(queClte).getId();

        num_it = (int) (1.0 + 10.0 * Math.random());
        num_it = num_it <= this.num_prods ? num_it : this.num_prods;
        
        int [] arr_id_prod = new int[num_it];
        boolean ya_esta;

        for( int k = 0; k <  num_it; k++)
        {
            ya_esta = true;
            while(ya_esta)
            {
              queProd = (int) ( num_prods * Math.random());
              if( k == 0)
                  ya_esta = false;
              else
              {   
                  ya_esta = false;
                  for( int j = 0; j < k; j++)
                      ya_esta = ya_esta | queProd == arr_id_prod[j]; 
              }
            }
            arr_id_prod[k] = queProd;
            
            id_prod  = listaProds.get(queProd).getId();
            cantidad = (int)( 5.0 + 100 * Math.random() );
            item = new wspedido.ClsItem();
            item.setIdProd(id_prod);
            item.setCantidad(cantidad);
            listaIt.add(item);
        }

        // System.out.println("-----------------------------------------------");
        // System.out.println("Estresador:" + this.quienSoy + ", vez:" + vez + ", Clte:" + id_clte);
        // System.out.println("-----------------------------------------------");
        // for(wspedido.ClsItem it : listaIt)
        //    System.out.println("Prod_id:" + it.getIdProd() + ", cantidad:" + it.getCantidad() );
        // System.out.println("-----------------------------------------------");

        solicitud = new ws_ca.CmplxSolicitud();
        solicitud.setIdCllte(id_clte);
        ws_ca.CmplxClsItem cmplxClsItem;
        
        java.util.List<ws_ca.CmplxClsItem> listaCmplxItems = solicitud.getListaItems();
        for(wspedido.ClsItem item_pedido : listaIt)
        {
          cmplxClsItem = new ws_ca.CmplxClsItem();
          cmplxClsItem.setIdProd(item_pedido.getIdProd());
          cmplxClsItem.setCantidad(item_pedido.getCantidad());
          listaCmplxItems.add(cmplxClsItem);
        }

        t0 = System.currentTimeMillis();
        try {
            respuesta = solicitarPedidoOperation(solicitud);
            num_envio = respuesta.getNumPedido();
            // System.out.println("El número de envío es:" + num_envio);
            // System.out.println("===============================================");
        } 
        catch (SolicitarPedidoOperationFault ex) 
        {
            // System.out.println(ex.getFaultInfo().getMessage());
        }
        t1 = System.currentTimeMillis();
        deltaT = t1 - t0;
      
        return deltaT;
    }

    @Override
    public void cierra() 
    {
         System.out.println("El thread de stress " + this.quienSoy + " ha terminado su trabajo"); 
    }

    public static void main(String[] args) 
    {
        java.util.List<wspedido.Customer> cltes = catalogoCltes();
        System.out.println("========== Clientes =================");
        for( wspedido.Customer clte: cltes )
        {
            System.out.println("clte:" + clte.getId() + " ... " + clte.getName());
        }
        System.out.println("=====================================");
        
        Pojo_CA objServ = new Pojo_CA();
        
        objServ.prepara(25);
        int n_veces = 20;
        for( int vez = 1; vez <= n_veces; vez++)
            objServ.solicitaServicio(vez);
        objServ.cierra();
    }

    private static java.util.List<wspedido.Customer> catalogoCltes() {
        wspedido.WSPedido_Service service = new wspedido.WSPedido_Service();
        wspedido.WSPedido port = service.getWSPedidoPort();
        return port.catalogoCltes();
    }

    private static java.util.List<wspedido.Product> catalogoProds() {
        wspedido.WSPedido_Service service = new wspedido.WSPedido_Service();
        wspedido.WSPedido port = service.getWSPedidoPort();
        return port.catalogoProds();
    }

    private static CmplxRespuesta solicitarPedidoOperation(ws_ca.CmplxSolicitud solicitudPedido) throws SolicitarPedidoOperationFault {
        ws_ca.IntegradoBpelTiendaCAService1 service = new ws_ca.IntegradoBpelTiendaCAService1();
        ws_ca.BpelIntegradoPortType port = service.getCasaPort1();
        return port.solicitarPedidoOperation(solicitudPedido);
    }
}