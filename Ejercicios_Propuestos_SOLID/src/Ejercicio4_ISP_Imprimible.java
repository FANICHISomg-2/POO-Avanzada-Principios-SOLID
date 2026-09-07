interface Imprimible {
    void imprimir();
}

interface Escaneable {
    void escanear();
}

class Impresora implements Imprimible {
    @Override
    public void imprimir() {
        System.out.println("Impresora: imprimiendo documento...");
    }
}

class ImpresoraMultifuncional implements Imprimible, Escaneable {
    @Override
    public void imprimir() {
        System.out.println("Multifuncional: imprimiendo documento...");
    }

    @Override
    public void escanear() {
        System.out.println("Multifuncional: escaneando documento...");
    }
}

public class Ejercicio4_ISP_Imprimible {

    public static void usarDispositivo(Imprimible dispositivo) {
        dispositivo.imprimir();
        if (dispositivo instanceof Escaneable) {
            ((Escaneable) dispositivo).escanear();
        } else {
            System.out.println("(Este dispositivo no puede escanear)");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Aplicación de impresión (ISP) ===");

        Impresora impresoraSimple = new Impresora();
        ImpresoraMultifuncional multifuncional = new ImpresoraMultifuncional();

        System.out.println("-- Impresora simple --");
        usarDispositivo(impresoraSimple);

        System.out.println("\n-- Impresora multifuncional --");
        usarDispositivo(multifuncional);
    }
}
