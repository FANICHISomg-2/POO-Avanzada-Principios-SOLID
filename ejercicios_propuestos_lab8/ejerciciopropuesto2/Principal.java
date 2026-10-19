import java.sql.*;
import java.util.*;

public class Principal {
    static final String URL = "jdbc:sqlite:productos.db";
    static final String CLAVE = "1234";               // clave para confirmar los cambios
    static final Scanner sc = new Scanner(System.in);
    static Connection con;

    public static void main(String[] args) {
        try {
            con = DriverManager.getConnection(URL);
            try (Statement st = con.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS productos "
                        + "(id integer PRIMARY KEY, nombre text, categoria text, precio real)");
            }
            con.setAutoCommit(false);                 // las transacciones las controlamos nosotros

            int op = 0;
            while (op != 6) {
                try {
                    System.out.println("\n1. Mostrar\n2. Ingresar\n3. Actualizar\n4. Borrar\n5. Consultar (Gestora)\n6. Salir");
                    op = Integer.parseInt(leerTexto("Opcion: "));
                    if (op == 1) mostrar();
                    else if (op == 2) ingresar();
                    else if (op == 3) actualizar();
                    else if (op == 4) borrar();
                    else if (op == 5) consultar();
                    else if (op != 6) System.out.println("Opcion no valida.");
                } catch (NumberFormatException e) {
                    System.out.println("Error: ingrese un numero valido.");
                    op = 0;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (SQLException e) {
                    con.rollback();                   // ante un error se revierte
                    System.out.println("Error de base de datos (revertido): " + e.getMessage());
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void mostrar() throws SQLException {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM productos")) {
            while (rs.next())
                System.out.println(rs.getInt("id") + " | " + rs.getString("nombre") + " | "
                        + rs.getString("categoria") + " | S/ " + rs.getDouble("precio"));
        }
    }

    static void ingresar() throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO productos VALUES (?,?,?,?)")) {
            ps.setInt(1, Integer.parseInt(leerTexto("ID: ")));
            ps.setString(2, leerTexto("Nombre: "));
            ps.setString(3, leerTexto("Categoria: "));
            ps.setDouble(4, Double.parseDouble(leerTexto("Precio: ")));
            ps.executeUpdate();
            confirmar();
        }
    }

    static void actualizar() throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "UPDATE productos SET nombre = ?, categoria = ?, precio = ? WHERE id = ?")) {
            ps.setInt(4, Integer.parseInt(leerTexto("ID a actualizar: ")));
            ps.setString(1, leerTexto("Nuevo nombre: "));
            ps.setString(2, leerTexto("Nueva categoria: "));
            ps.setDouble(3, Double.parseDouble(leerTexto("Nuevo precio: ")));
            if (ps.executeUpdate() > 0) confirmar();
            else { con.rollback(); System.out.println("Error: no existe ese ID."); }
        }
    }

    static void borrar() throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("DELETE FROM productos WHERE id = ?")) {
            ps.setInt(1, Integer.parseInt(leerTexto("ID a borrar: ")));
            if (ps.executeUpdate() > 0) confirmar();
            else { con.rollback(); System.out.println("Error: no existe ese ID."); }
        }
    }

    // Ejercicio 2: los registros se pasan a la Gestora y la consulta se resuelve en Java
    static void consultar() throws SQLException {
        List<Producto> lista = new ArrayList<>();
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM productos")) {
            while (rs.next())
                lista.add(new Producto(rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("categoria"), rs.getDouble("precio")));
        }
        Gestora g = new Gestora();
        g.cargar(lista);

        String txt = leerTexto("Campos a mostrar (ej: nombre,precio; vacio = todos): ");
        String[] campos = txt.isEmpty() ? new String[0] : txt.split("\\s*,\\s*");
        String campoCond = leerTexto("Campo de la condicion (vacio = ninguna): ");
        String operador = "=", valor = "";
        if (!campoCond.isEmpty()) {
            operador = leerTexto("Operador (=, >, <): ");
            valor = leerTexto("Valor: ");
        }
        String campoOrden = leerTexto("Campo para ordenar (vacio = ninguno): ");
        boolean desc = !campoOrden.isEmpty() && leerTexto("Descendente? (s/n): ").equalsIgnoreCase("s");
        int limite = Integer.parseInt(leerTexto("Maximo de registros (0 = todos): "));

        g.consultar(campos, campoCond, operador, valor, campoOrden, desc, limite);
    }

    // Si la clave es correcta se confirma (commit); si no, se revierte (rollback)
    static void confirmar() throws SQLException {
        if (leerTexto("Clave para confirmar los cambios: ").equals(CLAVE)) {
            con.commit();
            System.out.println("Cambios confirmados (commit).");
        } else {
            con.rollback();
            System.out.println("Clave incorrecta: cambios revertidos (rollback).");
        }
    }

    static String leerTexto(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }
}
