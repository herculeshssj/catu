
package br.com.hslife.catu.util;

public class TipoLogradouro {

    private static String[] estados = {"Aeroporto","Alameda","Apartamento",
    "Avenida","Beco","Bloco","Caminho","Escadinho","Estação","Estrada",
    "Fazenda","Fortaleza","Galeria","Ladeira","Largo","Praça","Parque","Praia",
    "Quadra","Quilômetro","Quinta","Rodovia","Rua","Super Quadra","Travessa",
    "Viaduto","Vila"};


    // Não quero que esta classe seja instanciada
    private TipoLogradouro(){

    }

    public static String[] getTipoLogradouro(){
        return estados;
    }

    public static String getTipoLogradouro(int i){
        return estados[i];
    }

    public static int getTamanho() {
        return estados.length;
    }

}

