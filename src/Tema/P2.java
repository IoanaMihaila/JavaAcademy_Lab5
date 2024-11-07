package Tema;

import java.util.ArrayList;
import java.util.List;

enum Gen {
    M("Masculin"), F("Feminin");
    private String gender;

    Gen(String gender) {
        this.gender = gender;
    }
}

enum FactorZonal {
    ZONA_1(1),
    ZONA_2(2),
    ZONA_3(3),
    ZONA_4(4),
    ZONA_5(5);
    private int val;

    FactorZonal(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}

enum CoeficientEuro {
    COEF_1(1),
    COEF_2(2),
    COEF_3(3),
    COEF_4(4),
    COEF_5(5),
    COEF_6(6);
    private int val;

    CoeficientEuro(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}

class Persoana {
    private String nume;
    private int varsta;
    private String cnp;
    private Gen gen;
    private List<Taxa> taxe;

    public Persoana(String nume, int varsta, String cnp, Gen gen) {
        this.nume = nume;
        this.varsta = varsta;
        this.cnp = cnp;
        this.gen = gen;
        this.taxe = new ArrayList<>();
    }

    public void adaugaTaxe(Taxa t) {
        taxe.add(t);
    }

    public void initierePlata(Primarie primarie) {
        for (Taxa t : taxe) {
            if (!t.isAchitat()) {
                primarie.platesteTaxa(t);
                break;//oprim cautarea dupa ce am gasit si achitat prima taxa neachitata
            }
        }
    }
}

abstract class Taxa {
    private Persoana persoana;
    private boolean isAchitat = false;

    public abstract double getValoare();

    public Taxa(Persoana persoana) {
        this.persoana = persoana;
        this.persoana.adaugaTaxe(this);
        Primarie.getInstance().addTaxa(this);
    }

    public boolean isAchitat() {
        return isAchitat;
    }

    public void setAchitat(boolean achitat) {
        isAchitat = achitat;
    }
}

class TaxaImpozitCasa extends Taxa {
    private int suprafata;
    private FactorZonal factorZonal; //valori intre 1 si 5

    public TaxaImpozitCasa(Persoana persoana, int suprafata, FactorZonal factorZonal) {
        super(persoana);
        this.suprafata = suprafata;
        this.factorZonal = factorZonal;
    }

    @Override
    public double getValoare() {
        return suprafata * factorZonal.getVal();
    }
}

class TaxaImpozitAuto extends Taxa {
    private String nume;
    private int capacitateCC;
    private CoeficientEuro coeficientEuro;

    public TaxaImpozitAuto(Persoana persoana, String nume, int capacitateCC, CoeficientEuro coeficientEuro) {
        super(persoana);
        this.nume = nume;
        this.capacitateCC = capacitateCC;
        this.coeficientEuro = coeficientEuro;
    }

    @Override
    public double getValoare() {
        return capacitateCC * (7 - coeficientEuro.getVal());
    }
}

class TaxaImpozitVenit extends Taxa {
    private int salariu;

    public TaxaImpozitVenit(Persoana persoana, int salariu) {
        super(persoana);
        this.salariu = salariu;
    }

    @Override
    public double getValoare() {
        return 0.3 * salariu;
    }
}

class Primarie {
    private static Primarie primarie;
    List<Taxa> taxe;

    public Primarie() {
        this.taxe = new ArrayList<>();
    }

    //Metoda statica pentru a obtine instanta unica
    public static Primarie getInstance() {
        if (primarie == null) {
            primarie = new Primarie();
        }
        return primarie;
    }

    public void addTaxa(Taxa t) {
        taxe.add(t);
    }

    //platesteTaxa in primarie, intierePlata in Persoana
    public void platesteTaxa(Taxa t) {
        t.setAchitat(true);
    }

    public List<Taxa> respectaConditia(Conditie c) {
        List<Taxa> taxeCond = new ArrayList<>();
        for (Taxa t : taxe) {
            if (c.isCondRespectata(t)) {
                taxeCond.add(t);
            }
        }
        return taxeCond;
    }
}

interface Conditie {
    public abstract boolean isCondRespectata(Taxa t);
}

class TaxeAchitate implements Conditie {
    @Override
    public boolean isCondRespectata(Taxa t) {
        if (t.isAchitat())
            return true;
        else
            return false;
    }
}

class TaxeValPesteOMie implements Conditie {
    @Override
    public boolean isCondRespectata(Taxa t) {
        if (t.getValoare() > 1000)
            return true;
        else
            return false;
    }
}

class TaxeAchitateSiValPesteCinciSute implements Conditie {
    @Override
    public boolean isCondRespectata(Taxa t) {
        if (t.isAchitat() && t.getValoare() > 500)
            return true;
        else
            return false;
    }
}

class TaxeNeachitateSiValPesteCinciMii implements Conditie {
    @Override
    public boolean isCondRespectata(Taxa t) {
        if (!t.isAchitat() && t.getValoare() > 5000)
            return true;
        else
            return false;
    }
}

public class P2 {
    public static void main(String[] args) {
        Persoana p1 = new Persoana("Tomesc Eliza", 21, "6030327204898", Gen.F);
        Persoana p2 = new Persoana("Onea Mihai", 25, "1990327177379", Gen.M);

        Primarie primarie = Primarie.getInstance();//instanta unica primarie

        Taxa t1 = new TaxaImpozitCasa(p1, 100, FactorZonal.ZONA_3);
        Taxa t2 = new TaxaImpozitAuto(p1, "Dacia", 1600, CoeficientEuro.COEF_4);
        Taxa t3 = new TaxaImpozitVenit(p2, 3000);
        Taxa taxaImpozitVenit = new TaxaImpozitVenit(p2, 20000);

        p1.initierePlata(primarie);
        p2.initierePlata(primarie);

        List<Taxa> taxeAchitate = primarie.respectaConditia(new TaxeAchitate());
        System.out.println("Numar taxe achitate: " + taxeAchitate.size());

        List<Taxa> taxeValPesteOMie = primarie.respectaConditia(new TaxeValPesteOMie());
        System.out.println("Numar taxe cu valoarea peste 1000: " + taxeValPesteOMie.size());

        List<Taxa> taxeAchitateSiValPesteCinciSute = primarie.respectaConditia(new TaxeAchitateSiValPesteCinciSute());
        System.out.println("Numar taxe achitate si cu valoarea peste 500: " + taxeAchitateSiValPesteCinciSute.size());

        List<Taxa> taxeNeachitateSiValPesteCinciMii = primarie.respectaConditia(new TaxeNeachitateSiValPesteCinciMii());
        System.out.println("Numar taxe neachitate si cu valoarea peste 5000: " + taxeNeachitateSiValPesteCinciMii.size());
    }
}
