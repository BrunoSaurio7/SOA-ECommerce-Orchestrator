/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo_envios;

/**
 *
 * @author rafael/rgamb
 */
public class POJO_Envios implements interfazdesacoplada.InterfazDesacoplada
{
    long quienSoy;
    
    int NUM_CLTES;
    int DIAS_MIN;
    int DIAS_MAX;

    @Override
    public void prepara(long quienSoy)
    {
        this.quienSoy = quienSoy;
        this.NUM_CLTES = 100; // Valor referencia
        this.DIAS_MIN = 5;
        this.DIAS_MAX = 10;
    }

    @Override
    public long solicitaServicio(int vez) 
    {
       long t0,t1,dt  = 0;
       int num_envio;
       int idClte     = (int)(1 + this.NUM_CLTES * Math.random());
       int num_dias   = (int)(this.DIAS_MIN + (this.DIAS_MAX - this.DIAS_MIN) * Math.random());
       int num_pedido = (int)(100.0 + 1000.0 * Math.random());
       
       t0 = System.currentTimeMillis();
       
       num_envio = altaEnvio(idClte,num_pedido,num_dias);
       
       t1 = System.currentTimeMillis();
           
       dt = t1 - t0;
           
       System.out.println("ObjetoDeServicio " + this.quienSoy + " vez:" + vez +
                          " idClte:" + idClte + ", num_pedido:" + num_pedido + 
                          " num_dias:" + num_dias + ", num_envio:" + num_envio + 
                          " deltaT:" + dt);
       
       return dt;
    }

    @Override
    public void cierra() 
    {
        System.out.println("Objeto de servicio " + this.quienSoy + " terminando hilo de carga."); 
    }

    public static void main(String[] args) 
    {
      long quienEres = 25;
      int VECES = 10;
      
      interfazdesacoplada.InterfazDesacoplada objServ = new POJO_Envios();
              
      objServ.prepara(quienEres);
      
      for(int vez = 1; vez <= VECES; vez++)
      {
          objServ.solicitaServicio(vez);
      }
      
      objServ.cierra();
    }

    // UTILERÍA ORIGINAL
    private static int altaEnvio(int idClte, int numPedido, int numDias) {
        ws_envios.WSEnvios_Service service = new ws_envios.WSEnvios_Service();
        ws_envios.WSEnvios port = service.getWSEnviosPort();
        return port.altaEnvio(idClte, numPedido, numDias);
    }
}