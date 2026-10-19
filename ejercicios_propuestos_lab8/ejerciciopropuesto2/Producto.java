public class Producto {
    int id;
    String nombre;
    String categoria;
    double precio;

    public Producto(int id, String nombre, String categoria, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    // Devuelve el valor de un campo segun su nombre
    public Object valor(String campo) {
        switch (campo.trim().toLowerCase()) {
            case "id": return id;
            case "nombre": return nombre;
            case "categoria": return categoria;
            case "precio": return precio;
            default: throw new IllegalArgumentException("Campo no valido: " + campo);
        }
    }
}
