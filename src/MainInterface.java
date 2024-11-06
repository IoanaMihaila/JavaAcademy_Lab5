interface Animal{
    //nu poate avea decat variabile static final
    public String nume="Rex";
    abstract void sunet();

    default void mananca() {//java 8+
        System.out.println("Orice animal poate sa manance");
    }
}
/*
1. Interfetele nu au stari-clasele abstracte au stari
Nu are sens ca orice animal sa se numeasca Rex
2. Interfetele sunt contracte pe care o clasa care o implementeaza trebuie sa o respecte
ex: class Laptop implements USBInterface, TypeCInterface, JackInterface{
..
}

O interfata poate sa extinda mai multe interfete

interface Hub extends UsbInterface, TypeCInterface{
...
}
//Laptop va trebui sa implementeze toate metodele abstracte(din HUb, UsbInterface, TypeCInterface)
*/
public class MainInterface {
}
