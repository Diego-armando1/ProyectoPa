/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import Clases.Cocinero;
import Clases.Plato;
import Logica.LogCocinero;
import Logica.LogPlato;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Presentar {

    // Cola compartida
    static Queue<Plato> colaPlatos =
            new LinkedList<>();

    public static void main(String[] args) {

        try {

            LogCocinero objLogCocinero =
                    new LogCocinero();

            LogPlato objLogPlato =
                    new LogPlato();

            // Cargar TODOS los platos
            ArrayList<Plato> listaPlatos = new ArrayList<>();

            listaPlatos = objLogPlato.BuscarPlato(listaPlatos);

            // Meter platos a la cola
            for (Plato p : listaPlatos) {

                colaPlatos.add(p);
            }

            System.out.println(
                    "Platos en cola: "
                    + colaPlatos.size());

            // Cargar cocineros
            ArrayList<Cocinero> listaCocineros =
                    new ArrayList<>();

            listaCocineros =
                    objLogCocinero
                    .BuscarCocinero(
                            listaCocineros);

            // Crear hilos
            for (Cocinero c : listaCocineros) {
                Hilos h = new Hilos(c);
                h.start();
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    static class Hilos extends Thread {

        private Cocinero cocinero;

        public Hilos(Cocinero cocinero) {

            this.cocinero = cocinero;
        }

        @Override
        public void run() {
            int contador = 0;
            while (true) {
                Plato plato;
                synchronized (colaPlatos) {
                    if (colaPlatos.isEmpty()) {
                        break;
                    }
                    plato = colaPlatos.poll();
                    System.out.println(
                            cocinero.getNombre()
                            + " agarró "
                            + plato.getNombre());
                }
                try {
                    System.out.println(
                            cocinero.getNombre()
                            + " preparando "
                            + plato.getNombre());
                    Thread.sleep(
                            plato.getTiempoPreparacion()
                            * 1000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                System.out.println(
                        cocinero.getNombre()
                        + " terminó "
                        + plato.getNombre());

                contador++;
            }

            System.out.println(
                    cocinero.getNombre()
                    + " cocinó "
                    + contador
                    + " platos");
        }
    }
}