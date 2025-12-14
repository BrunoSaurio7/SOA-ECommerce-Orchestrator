/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo_ca;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class LanzadorEstres {

    private static final int NUM_HILOS = 20;
    private static final int REQ_POR_HILO = 10;

    static class Worker implements Runnable {
        private final int id;
        private final int num_solicitudes;
        
        public long totalTime = 0;
        public int hits = 0;
        public int misses = 0;

        public Worker(int id, int num_solicitudes) {
            this.id = id;
            this.num_solicitudes = num_solicitudes;
        }

        @Override
        public void run() {
            Pojo_CA cliente = new Pojo_CA();
            
            try {
                cliente.prepara(id);

                for (int i = 1; i <= num_solicitudes; i++) {
                    try {
                        
                        long dt = cliente.solicitaServicio(i);

                        if (dt >= 0) { 
                            totalTime += dt;
                            hits++;
                        } else {
                            misses++;
                        }
                    } catch (Exception e) {
                        misses++;
                        System.err.println("Hilo " + id + " fallo en req " + i);
                    }
                }
                
                cliente.cierra();
                
            } catch (Exception e) {
                System.err.println("Error fatal en Hilo " + id);
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        
        System.out.println("=============================================");
        System.out.println(" INICIANDO PRUEBA DE ESTRÉS");
        System.out.println("Usuarios: " + NUM_HILOS);
        System.out.println(" Req por Hilo: " + REQ_POR_HILO);
        System.out.println("=============================================\n");

        List<Thread> threads = new ArrayList<>();
        List<Worker> workers = new ArrayList<>();

        long globalStartTime = System.currentTimeMillis();

        for (int i = 0; i < NUM_HILOS; i++) {
            Worker w = new Worker(i + 1, REQ_POR_HILO);
            workers.add(w);
            
            Thread t = new Thread(w);
            threads.add(t);
            t.start();
            // System.out.println("Lanzando hilo " + (i+1));
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long globalEndTime = System.currentTimeMillis();
        long durationWallClock = globalEndTime - globalStartTime;

        long totalHits = 0;
        long totalMisses = 0;
        long totalLatencyAcum = 0;

        for (Worker w : workers) {
            totalHits += w.hits;
            totalMisses += w.misses;
            totalLatencyAcum += w.totalTime;
        }

        long totalRequests = totalHits + totalMisses;
        double avgLatency = (totalHits > 0) ? (double) totalLatencyAcum / totalHits : 0.0;
        
        double tps = 0;
        if (durationWallClock > 0) {
            tps = (double) totalRequests / (durationWallClock / 1000.0);
        }

        System.out.println("\n=============================================");
        System.out.println(" RESULTADOS FINALES");
        System.out.println("=============================================");
        System.out.printf(" Tiempo Total: %d ms\n", durationWallClock);
        System.out.printf(" Peticiones Totales: %d\n", totalRequests);
        System.out.printf(" Éxitos: %d\n", totalHits);
        System.out.printf(" Fallos: %d\n", totalMisses);
        System.out.println("---------------------------------------------");
        System.out.printf(" Latencia Promedio: %.2f ms\n", avgLatency);
        System.out.printf(" TPS: %.2f\n", tps);
        System.out.println("=============================================");
    }
}
