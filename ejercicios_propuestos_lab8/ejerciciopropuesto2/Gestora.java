import java.util.*;

// Guarda los registros en un arreglo de objetos y hace las consultas en Java (sin SQL)
public class Gestora {
    private Producto[] registros = new Producto[0];

    public void cargar(List<Producto> lista) {
        registros = lista.toArray(new Producto[0]);
    }

    public void consultar(String[] campos, String campoCond, String operador, String valorCond,
                          String campoOrden, boolean descendente, int limite) {
        // a) campos a mostrar (si no se indica ninguno, todos)
        if (campos.length == 0)
            campos = new String[] {"id", "nombre", "categoria", "precio"};

        // b) condicion sobre un campo (WHERE)
        List<Producto> res = new ArrayList<>();
        for (Producto p : registros)
            if (campoCond.isEmpty() || cumple(p, campoCond, operador, valorCond))
                res.add(p);

        // c) orden ascendente o descendente (ORDER BY)
        if (!campoOrden.isEmpty()) {
            Comparator<Producto> cmp = (a, b) -> comparar(a.valor(campoOrden), b.valor(campoOrden));
            if (descendente) cmp = cmp.reversed();
            res.sort(cmp);
        }

        // d) limite de registros (LIMIT)
        if (limite > 0 && limite < res.size())
            res = res.subList(0, limite);

        // Mostrar solo los campos pedidos (columnas del SELECT)
        System.out.println(String.join(" | ", campos));
        for (Producto p : res) {
            String fila = "";
            for (String c : campos) fila += (fila.isEmpty() ? "" : " | ") + p.valor(c);
            System.out.println(fila);
        }
        System.out.println("(" + res.size() + " registro(s))");
    }

    private boolean cumple(Producto p, String campo, String operador, String valor) {
        Object v = p.valor(campo);
        int c;
        if (v instanceof Number) c = Double.compare(((Number) v).doubleValue(), Double.parseDouble(valor));
        else c = v.toString().compareToIgnoreCase(valor);
        if (operador.equals("=")) return c == 0;
        if (operador.equals(">")) return c > 0;
        if (operador.equals("<")) return c < 0;
        throw new IllegalArgumentException("Operador no valido: " + operador);
    }

    private int comparar(Object a, Object b) {
        if (a instanceof Number)
            return Double.compare(((Number) a).doubleValue(), ((Number) b).doubleValue());
        return a.toString().compareToIgnoreCase(b.toString());
    }
}
