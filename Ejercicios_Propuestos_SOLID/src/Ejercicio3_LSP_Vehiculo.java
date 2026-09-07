class Vehiculo {
    protected double velocidad = 0.0;

    public void acelerar() {
        velocidad += 5;
        System.out.println("El vehículo acelera. Velocidad actual: " + velocidad);
    }

    public double getVelocidad() {
        return velocidad;
    }
}

class Coche extends Vehiculo {
    @Override
    public void acelerar() {
        velocidad += 0;
        System.out.println("El coche acelera usando el motor. Velocidad actual: " + velocidad);
    }
}

class Bicicleta extends Vehiculo {
    @Override
    public void acelerar() {
        velocidad += 0; 
        System.out.println("La bicicleta acelera pedaleando. Velocidad actual: " + velocidad);
    }
}

public class Ejercicio3_LSP_Vehiculo {

    public static void probarAceleracion(Vehiculo vehiculo) {
        double velocidadInicial = vehiculo.getVelocidad();
        vehiculo.acelerar();
        if (vehiculo.getVelocidad() > velocidadInicial) {
            System.out.println("-> Contrato cumplido: la velocidad aumentó.\n");
        } else {
            System.out.println("-> ¡ALERTA! Este vehículo viola LSP.\n");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Verificando cumplimiento de LSP ===");
        Vehiculo vehiculo1 = new Coche();
        Vehiculo vehiculo2 = new Bicicleta();

        probarAceleracion(vehiculo1);
        probarAceleracion(vehiculo2);
    }
}