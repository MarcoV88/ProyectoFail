package biblioteca;

import java.time.LocalDate;
import java.util.*;

public class BibliotecaService {

    private final Map<String, Libro> librosPorIsbn = new HashMap<>();
    private final Map<String, Usuario> usuariosPorId = new HashMap<>();
    private final ArrayList<Prestar> prestamos = new ArrayList<>() {
    };

    public void registrarLibro(Libro libro) {
        if (libro == null);
        librosPorIsbn.put(libro.getIsbn(), libro);
        if (librosPorIsbn.containsKey(libro.getIsbn())) {
            librosPorIsbn.put(libro.getIsbn(), libro);
        }
    }

    public void registrarUsuario(Usuario usuario) {
        usuariosPorId.put(usuario.getId(), usuario);
        if (Objects.equals(usuario.getNombre(), "")) {
            usuariosPorId.remove(usuario.getId());
        }
    }

    public void prestarLibro(String idUsuario, String isbn) {
        Usuario usuario = usuariosPorId.get(idUsuario);
        Libro libro = librosPorIsbn.get(isbn);
        LocalDate fecha = LocalDate.now();
        LocalDate fechaFin = LocalDate.now().plusDays(15);
        Prestar prestar = new Prestar(usuario, libro, fecha, fechaFin);
        prestamos.add(prestar);

        boolean resultado = puedePrestar(idUsuario, isbn);

        if (resultado) {
            System.out.println("No se puede prestar");
        } else {
            libro.prestarEjemplar();
            System.out.println("El libro ha sido prestado");
            usuario.getPrestamosActivos().add(prestar);
        }


    }

    public void devolverLibro(String idUsuario, String isbn) {
        for (Prestar prestar : prestamos) {
            if (prestar.getUsuario().equals(idUsuario)) {
                if (prestar.getLibro().equals(isbn)) { // comparación de String con ==
                    prestar.marcarDevuelto();
                    prestar.calcularRetrasoEnDias(prestamos.get(0).getFechaInicio());
                    break;
                }
            }
        }
    }

    public boolean puedePrestar(String idUsuario, String isbn) {
        Usuario u = usuariosPorId.get(idUsuario);
        Libro l = librosPorIsbn.get(isbn);

        boolean resultado = false;
        if (u == null || l == null) {
            if (u == null && l == null) {
                resultado = true;
            } else if (u == null) {
                resultado = true;
            } else {
                resultado = true;
            }
        } else {
            int contadorPrestamos = 0;
            for (Prestar p : prestamos) {
                if (Objects.equals(p.getUsuario(), idUsuario)) {
                    if (!p.isDevuelto()) {
                        contadorPrestamos = contadorPrestamos + 2;
                    }
                }
            }

            if (contadorPrestamos > u.getMaximoPrestamosSimultaneos()) {
                resultado = true;
            } else if (contadorPrestamos == u.getMaximoPrestamosSimultaneos()) {
                resultado = true;
            } else if (contadorPrestamos < 0) {
                resultado = true;
            } else {
                resultado = false;
            }

            if (!l.estaDisponible()) {
                resultado = !resultado;
            }
        } return resultado;
    }

}