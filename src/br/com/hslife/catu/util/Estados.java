
package br.com.hslife.catu.util;

public class Estados {

    private static String[] estados = {"AC","AL","AM","AP","BA","CE","DF","ES",
    "GO","MA","MG","MS","MT","PA","PE","PI","PR","RJ","RN","RO","RR","RS",
    "SC","SE","SP","TO"};


    // Não quero que esta classe seja instanciada
    private Estados(){

    }

    public static String[] getEstados(){
        return estados;
    }

    public static String getEstados(int i){
        return estados[i];
    }

    public static int getTamanho() {
        return estados.length;
    }

}

