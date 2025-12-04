/*
 * Logic.java
 * Autor: Marely Jacome
 * Fecha: 2025-12-02
 * Versión: 2.0
 * Descripción: Gestiona la entrada de datos del usuario y coordina
 *              el refinamiento de la integración usando
 *              SimpsonIntegration.
 */

/*
 * Listing Contents:
 *  - Reuse Instructions
 *  - Clase Logic con método logic1a()
 */

/*
 * Reuse Instructions:
 *  - Llamada desde App.
 *  - Solicita x, dof y epsilon por consola.
 *  - Controla el refinamiento: num_seg = 10, 20, 40, ...
 *  - Imprime el valor p en cada iteración y el resultado final.
 */

import java.util.Scanner;

public class Logic {

    private int intNumSeg;   /* Número actual de segmentos */
    private double dblE;     /* Error permitido (epsilon) */
    private int intDOF;      /* Grados de libertad de la t-Student */
    private double dblX;     /* Límite superior de integración */

    /*
     * logic1a
     * Propósito: leer los datos del usuario, ejecutar el refinamiento
     *            de Simpson y mostrar los resultados.
     */
    public void logic1a() {

        /* === Entrada del usuario === */
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresa el valor de x: ");
        dblX = sc.nextDouble();

        System.out.print("Ingresa los grados de libertad (dof): ");
        intDOF = sc.nextInt();

        System.out.print("Ingresa epsilon (error permitido): ");
        dblE = sc.nextDouble();

        sc.close();

        /* Encabezado del proceso en consola */
        System.out.println("=== Proceso de Integración ===");
        System.out.printf("error permitido = %.1E%n", dblE);

        /* Inicializar integración con Simpson */
        SimpsonIntegration simpson = new SimpsonIntegration();
        intNumSeg = 10;    /* Primer número de segmentos */

        double pAnterior = 0.0;                       /* Valor anterior de p */
        double pActual = simpson.integrate(
                intNumSeg,
                dblX,
                intDOF
        );                                            /* Primer valor de p */

        System.out.println("num_seg = " + intNumSeg);
        System.out.println("p = " + pActual);

        /* === Ciclo de refinamiento: 10, 20, 40, 80, ... === */
        while (Math.abs(pActual - pAnterior) > dblE) {
            pAnterior = pActual;          /* Guardar valor previo */
            intNumSeg = intNumSeg * 2;    /* Duplicar segmentos */

            pActual = simpson.integrate(
                    intNumSeg,
                    dblX,
                    intDOF
            );                            /* Nuevo valor de p */

            System.out.println("num_seg = " + intNumSeg);
            System.out.println("p = " + pActual);
        }

        /* === Resultado final === */
        System.out.printf(
                "x = %.4f    dof = %d    p = %.5f%n",
                dblX,
                intDOF,
                pActual
        );
    }
}
