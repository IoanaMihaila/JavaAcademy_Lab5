enum Gen {
    M, F
}

enum GenComplex {
    M("Masculin"), F("Feminin");
    private String gender;

    //private nu este necesar -> obiectele finale sunt instantiate direct in cadrul enum-ului
    private GenComplex(String gender) {
        this.gender = gender;
    }
}

enum TabelPeriodic {
    C("Carbon", 2), H("Hidorgen", 1), Na("Sodiu", 3), Ca("Calciu", 2);

    private String name;
    private int valenta;

    private TabelPeriodic(String name, int valenta) {
        this.name = name;
        this.valenta = valenta;
    }

    public static TabelPeriodic findByName(String name) {
        for (TabelPeriodic periodic : TabelPeriodic.values()) {
            if (periodic.name.equals(name)) {
                return periodic;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

}

class Gender {
    public static final String M = "M";
}
class Persoana {
    private String nume;
    private Gen gen;

    public Persoana(String nume, Gen gen) {
        this.nume = nume;
        this.gen = gen;
    }
}

public class Main {
    public static void main(String[] args) {
        Persoana marco = new Persoana("Marco", Gen.M);

        TabelPeriodic calciu = TabelPeriodic.Ca;
        TabelPeriodic calciuCautat = TabelPeriodic.findByName("Calciu");
        System.out.println(TabelPeriodic.C.getName());
    }
}