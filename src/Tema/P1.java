package Tema;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

enum Combustibil{
    BENZINA(6.5),
    MOTORINA(5.8);
    private double pretPeLitru;

    Combustibil(double pretPeLitru) {
        this.pretPeLitru = pretPeLitru;
    }

    public double getPretPeLitru() {
        return pretPeLitru;
    }
    public static Combustibil gasesteDupaPret(double pret){
        for(Combustibil c:Combustibil.values()){
            if(c.getPretPeLitru()==pret){
                return c;
            }
        }
        throw  new IllegalArgumentException("Nu exista un tip de combustibil cu pretul: "+pret);
    }
}

class Masina{
    private String marca;
    private String model;
    private int caiPutere;
    private Combustibil combustibil;

    public Masina(String marca, String model, int caiPutere, Combustibil combustibil) {
        this.marca = marca;
        this.model = model;
        this.caiPutere = caiPutere;
        this.combustibil = combustibil;
    }

    public String getMarca() {
        return marca;
    }

    public String getModel() {
        return model;
    }

    public int getCaiPutere() {
        return caiPutere;
    }

    public Combustibil getCombustibil() {
        return combustibil;
    }
}

public class P1 {
    public static List<Masina> combBenzina(List<Masina>masini){
        List<Masina>masiniBenzina=new ArrayList<>();
        for(Masina m:masini){
            if(m.getCombustibil().equals(Combustibil.BENZINA)){
                masiniBenzina.add(m);
            }
        }
        return masiniBenzina;
    }

    public static void main(String[] args) {
        List<Masina>masini=new ArrayList<>();
        masini.add(new Masina("Dacia","Logan",75,Combustibil.BENZINA));
        masini.add(new Masina("BMW","X5",250,Combustibil.BENZINA));
        masini.add(new Masina("Volkswagen","Passat",95,Combustibil.MOTORINA));

        List<Masina>masiniBenzina= combBenzina(masini);
        System.out.println("Masinile care folosesc benzina: ");
        for(Masina masina:masiniBenzina){
            System.out.println(masina.getMarca()+ " "+masina.getModel());
        }
        double pretCautat=6.5;
        try{
            Combustibil combustibil=Combustibil.gasesteDupaPret(pretCautat);
            System.out.println("Combustibilul cu pretul: "+pretCautat+" este: "+combustibil);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
