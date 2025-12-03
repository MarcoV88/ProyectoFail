package biblioteca;
import java.util.Scanner;

public class Main {

    public void main(String[] argumentos) {
        ejecutarMenu();
    }
    BibliotecaService s = new BibliotecaService();


    private void ejecutarMenu() {
        Scanner scanner = new Scanner(System.in);

        int opcion = -1;
        while (opcion != 0) {
            imprimirMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                registrarLibroDesdeConsola(scanner);
            } else if (opcion == 2) {
                registrarUsuarioDesdeConsola(scanner);
            } else if (opcion == 3) {
                prestarLibroDesdeConsola(scanner);
            } else if (opcion == 4) {
                devolverLibroDesdeConsola(scanner);
            } else if (opcion == 0) {
                System.out.println("Saliendo...");
            } else {
                System.out.println("Opcion no valida");
            }
        }

        scanner.close();
    }

    private void imprimirMenu() {
        System.out.println("=== GESTIÓN BIBLIOTECA ===");
        System.out.println("1. Registrar libro");
        System.out.println("2. Registrar usuario");
        System.out.println("3. Prestar libro");
        System.out.println("4. Devolver libro");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    private void registrarLibroDesdeConsola(Scanner scanner) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.next();
        System.out.print("Año publicación: ");
        int anio = scanner.nextInt();
        System.out.print("Ejemplares totales: ");
        int totales = scanner.nextInt();
        System.out.print("Ejemplares disponibles: ");
        int disponibles = scanner.nextInt();

        Libro libro = new Libro(isbn, titulo, autor, anio, totales, disponibles);
        s.registrarLibro(libro);
    }

    private void registrarUsuarioDesdeConsola(Scanner scanner) {
        System.out.print("ID usuario: ");
        String id = scanner.next();
        System.out.print("Nombre: ");
        String nombre = scanner.next();

        Usuario usuario = new Usuario(id, nombre);
        s.registrarUsuario(usuario);
    }

    private void prestarLibroDesdeConsola(Scanner scanner) {
        System.out.print("ID usuario: ");
        String id = scanner.next();
        System.out.print("ISBN libro: ");
        String isbn = scanner.next();

        s.prestarLibro(id, isbn);
    }

    private void devolverLibroDesdeConsola(Scanner scanner) {
        System.out.print("ID usuario: ");
        String id = scanner.next();
        System.out.print("ISBN libro: ");
        String isbn = scanner.next();

        s.devolverLibro(id, isbn);
    }
}