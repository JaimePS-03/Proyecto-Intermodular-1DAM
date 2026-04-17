package util;

import java.util.Scanner;

// CLASE ENFOCADA PARA PEDIR TEXTO EN LA CONSOLA
// MÉTODOS STATIC Y CONSTRUCTOR PRIVADO, NO QUEREMOS OBJETOS DE ESTA CLASE

public class EntradaTexto {
    private static Scanner sc = new Scanner(System.in);

    private EntradaTexto() {
    }

    /**
     * Nos permite pedir una cadena de texto
     * @param mensaje El texto que vamos a mostrarle al usuario
     * @return Un scanner, nos permite recoger información
     */
    public static String pedirString(String mensaje){
        System.out.println(mensaje);
        return sc.nextLine();
    }

    /**
     * Nos permite pedir un número entero
     * @param mensaje El texto que vamos a mostrarle al usuario
     * @return Un scanner, nos permite recoger la información
     */
    public static int pedirInt(String mensaje){
        int numero = 0;
        do {
            System.out.println(mensaje);
            try{
                numero = Integer.parseInt(sc.nextLine());
                return numero;
            } catch (NumberFormatException e) {
                System.out.println("Error");
            }
        }while(true);
    }

    /**
     * Nos permite pedir un número entero
     * @param mensaje El texto que vamos a mostrarle al usuario
     * @param min Nos permite establecer un valor mínimo para los enteros
     * @param max Nos permite establecer un valor máximo para los enteros
     * @return Un scanner, nos permite recoger la información
     */
    public static int pedirIntRango(String mensaje, int min, int max){
        String numeroString = "";
        int numero = 0;
        do {
            System.out.println(mensaje);
            numeroString = sc.nextLine();
            try{
                numero = Integer.parseInt(numeroString);
                if(numero > min && numero < max) {
                    return numero;
                }else{
                  System.out.println("Rango de numero no válido");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error");
            }
        }while(true);
    }

    /**
     * Nos permite pedir un número con decimales
     * @param mensaje El texto que vamos a mostrarle al usuario
     * @return Un scanner, nos permite recoger la información
     */
    public static double pedirDouble(String mensaje){
        double numero = 0;
        do {
            System.out.println(mensaje);
            try{
                numero = Double.parseDouble(sc.nextLine());
                return numero;
            } catch (NumberFormatException e) {
                System.out.println("Error");
            }
        }while(true);
    }

    /**
     * Nos permite pedir una confirmación para una operación
     * @param mensaje El texto que vamos a mostrarle al usuario
     * @return La confirmación o denegación de la opción
     */
    public static boolean pedirConfirmar(String mensaje){
        String opcionConfirmar;
        do{
            System.out.println(mensaje);
            opcionConfirmar = sc.nextLine();
            try{
                if(opcionConfirmar.equalsIgnoreCase("s")){
                    return true;
                }else if(opcionConfirmar.equalsIgnoreCase("n")){
                    return false;
                }else{
                    System.out.println("Opcion no válida");
                }
            } catch (Exception e) {
                System.out.println("Error");
            }
        }while(true);
    }
}
