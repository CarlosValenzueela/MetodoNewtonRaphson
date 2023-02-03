/**
 * Paquete newtonraphson.
 */
package newtonraphson;

/**
 * Importación del Scanner, que sirve para los valores de entrada
 */
import java.util.Scanner;

/*Nombre del archivo: asignacion03_00000207256
Nombre de alumno: Carlos Antonio Valenzuela Valdez
Matricula: 00000207256
Fecha de creación: 16/09/2020 */
/**
 * El programa newtonRaphson ayuda a encontrar las raices de una función ya
 * establecida. Al ingresar un dato de entrada inicial, ademas de un "error que
 * se usara para aproximarse a la respuesta. Este nos ayuda a mejorar la
 * velocidad al momento de querer encontrar raices, ya que es muy rapido y facil
 * hacerlo funcionar. Nos brinda todos los datos con los que opera para dar el
 * resultado así como las iteraciones que necesito para dar con dicha respuesta.
 */

/*
Pasos a seguir en el programa.
1.- Escójanse un valor inicial y de igual forma un error aproximado.
2.- Con los datos ingresados se trabajara en la proxima aproximacion
3.- Se comparara los resultados para obtener un resultado 
4.- Se calcula una nueva aproximación a la raíz.
5.- Decídase si la nueva aproximación es tan exacta como se
desea. Si es así los cálculos terminan, de otra manera
regrese al paso 3.
 */
/**
 * Clase Main llamada NewtonRaphson donde se mandaran a llamar los métodos así
 * como se registraran los datos.
 *
 * @author Carlos Antonio Valenzuela Valdez
 */
public class NewtonRaphson {

    /**
     * Método main de la clase NewtonRaphson para dar ejecución al programa
     *
     * @param args Argumentos del parametro
     */
    public static void main(String[] args) {
        //Declaracion del método Scanner
        Scanner teclado = new Scanner(System.in);

        //Variables para los datos de entrada
        double valorInicial = 0.0, errorAprox = 0.0;

        //Funcionalidad del programa.
        System.out.println("El programa newtonRaphson ayuda a encontrar las raices de una función ya\n"
                + "establecida. Al ingresar un dato de entrada inicial, ademas de un\n"
                + "error que se usara para aproximarse a la respuesta. Este nos ayuda a mejorar\n"
                + "la velocidad al momento de querer encontrar raices, ya que es muy rapido y\n"
                + "facil hacerlo funcionar. Nos brinda todos los datos con los que opera para\n"
                + "dar el resultado así como las iteraciones que necesito para dar con dicha\n"
                + "respuesta.");

        System.out.println("\n F(x) = x³ + 3X - 1");
        System.out.println("\n F(y) = 3x² + 3");

        //Solicitud de datos
        System.out.println("\nSolicitud de los datos de entrada");
        System.out.print("Ingrese el valor inicial: ");
        valorInicial = teclado.nextDouble();

        System.out.print("Ingrese el error aproximado máximo: ");
        errorAprox = teclado.nextDouble();

        //Llamaada al método de bisección
        NewtonRaphson main = new NewtonRaphson();
        main.newtonRaphson(valorInicial, errorAprox);
    }

    /**
     * Método que hara el proceso de cambio de variables mediante el uso de otro
     * método para calcular la ordenada de Y
     *
     * @param valorInicial Valor inicial que se tomara
     * @param errorAproxMax Error aproximado para dar con la respuesta
     */
    public void newtonRaphson(double valorInicial, double errorAproxMax) {
        //Declaración de variables
        int iteraciones = 1;
        double funcionXi = 0.0, derivadaXi = 0.0, errorAprox = 0.0, XiNueva = 0.0, funcionXiNueva = 0.0, derivadaXiNueva = 0.0;
        boolean continuar = true;

        System.out.println("\n\n \t\t\t\t\t Tabla de valores ");
        System.out.print("\n\nIteración     Xi\t   F(Xi)\tF'(Xi)\t\tea");
        //Ciclo para saber iteraciones, así como para proseguir con los pasos del algoritmo
        while (continuar) {

            //Asignación de valores
            funcionXi = f(valorInicial);
            derivadaXi = fDerivada(valorInicial);
            XiNueva = valorInicial - (funcionXi / derivadaXi);
            funcionXiNueva = f(XiNueva);
            derivadaXiNueva = fDerivada(XiNueva);

            if (iteraciones == 1) {
                imprimirValores2(iteraciones, valorInicial, funcionXi, derivadaXi);
                iteraciones++;
            }

            if (XiNueva == 0) {
                imprimirValores(iteraciones, valorInicial, funcionXi, derivadaXi, errorAprox);

                break;
            }

            errorAprox = ((XiNueva - valorInicial) / XiNueva) * 100;

            //Si el error aproximado es menor al error aproximado maximo, se finaliza el programa y se despliega la tabla.
            if (errorAproxMax > Math.abs(errorAprox)) {
                imprimirValores(iteraciones, XiNueva, funcionXiNueva, derivadaXiNueva, errorAprox);
                continuar = false;
                break;
            }

            if (continuar) {
                //Dar el valor que cambio a las variables.
                imprimirValores(iteraciones, XiNueva, funcionXiNueva, derivadaXiNueva, errorAprox);
                valorInicial = XiNueva;
                iteraciones++;
            }

        }
        System.out.println("\n\nResultados obtenidos: ");
        System.out.printf("Valor de la raíz: %.6f \n", XiNueva);
        System.out.printf("Valor de la función para la raíz: %.6f \n", funcionXiNueva);
        System.out.printf("Error aproximado en ultima iteracón: %.6f \n", errorAprox);
        System.out.println("Número de iteraciones requeridas para encontrar raíz: " + (iteraciones - 1));

    }

    /**
     * Método que regresa la ordenada de Y recibiendo X como parametro
     *
     * @param x Valor al que se le sacara la ordenada
     * @return Valor de la función
     */
    public double f(double x) {
        double funcion = 0.0;

        funcion = Math.pow(x, 3) + (3 * x) - 1;

        return funcion;

    }

    /**
     * Método con el cual se manejara la derivada de la función.
     *
     * @param x Valor con el que trabajara en la derivada
     * @return Valor de la función derivada.
     */
    public double fDerivada(double x) {
        double funcion = 0.0f;

        funcion = (3 * Math.pow(x, 2)) + 3;
        return funcion;

    }

    /**
     * Método para imprimir la tabla de valores
     *
     * @param iteraciones Número de iteraciones necesarias para dar con el
     * resultado
     * @param vI Valor inicial
     * @param fXi Funcion de valor inicial
     * @param fXii Funcion de la derivada del valor inicial
     * @param ea Error aproximado.
     */
    public void imprimirValores(int iteraciones, double vI, double fXi, double fXii, double ea) {
        System.out.printf("\n%d     \t    %6.6f    %10.6f    %9.6f    %9.6f  ", iteraciones, vI, fXi,
                fXii, ea);

    }

    /**
     * Método para imprimir la tabla de valores
     *
     * @param iteraciones Número de iteraciones necesarias para dar con el
     * resultado
     * @param vI Valor inicial
     * @param fXi Funcion de valor inicial
     * @param fXii Funcion de la derivada del valor inicial
     *
     */
    public void imprimirValores2(int iteraciones, double vI, double fXi, double fXii) {
        System.out.printf("\n%d     \t    %6.6f    %10.6f    %9.6f", iteraciones, vI, fXi,
                fXii);

    }

}
