abstract class Forma {
    public abstract void dibujar();
}

class Circulo extends Forma {
    @Override
    public void dibujar() {
        System.out.println("Dibujando un círculo o_o");
    }
}

class Rectangulo extends Forma {
    @Override
    public void dibujar() {
        System.out.println("Dibujando un rectángulo []");
    }
}

class Triangulo extends Forma {
    @Override
    public void dibujar() {
        System.out.println("Dibujando un triángulo /\\");
    }
}

public class Ejercicio2_OCP_Forma {
    public static void main(String[] args) {
        Forma[] formas = {
                new Circulo(),
                new Rectangulo(),
                new Triangulo()
        };

        System.out.println("=== Dibujando figuras (OCP) ===");
        for (Forma forma : formas) {
            forma.dibujar();
        }
    }
}