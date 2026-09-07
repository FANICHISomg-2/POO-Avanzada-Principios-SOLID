class Empleado {
    private String nombre;
    private double salarioBase;
    private String departamento;

    public Empleado(String nombre, double salarioBase, String departamento) {
        this.nombre = nombre;
        this.salarioBase = salarioBase;
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public String getDepartamento() {
        return departamento;
    }
}

class CalculadoraPago {

    public double calcularPagoMensual(Empleado empleado) {
        double bono = calcularBonoPorDepartamento(empleado.getDepartamento());
        return empleado.getSalarioBase() + bono;
    }

    private double calcularBonoPorDepartamento(String departamento) {
        switch (departamento) {
            case "Ventas":
                return 300.0;
            case "Sistemas":
                return 250.0;
            default:
                return 0.0;
        }
    }
}

public class Ejercicio1_SRP_Empleado {
    public static void main(String[] args) {
        Empleado empleado1 = new Empleado("Stephano Pinto", 400.0, "Sistemas");
        Empleado empleado2 = new Empleado("Nicole Huamani", 800.0, "Ventas");

        CalculadoraPago calculadora = new CalculadoraPago();

        System.out.println("****** Cálculo de pago mensual ******");
        System.out.println(empleado1.getNombre() + " -> S/. "
                + calculadora.calcularPagoMensual(empleado1));
        System.out.println(empleado2.getNombre() + " -> S/. "
                + calculadora.calcularPagoMensual(empleado2));
    }
}
