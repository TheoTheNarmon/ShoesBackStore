//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        boolean end = false;
        while (!end) {
            showMenu();
            int option = readOption();
            System.out.println(" ");
            switch (option) {
                case 1 -> System.out.println("producto agregado");
                case 2 -> System.out.println("productos enlistados");
                case 3 -> System.out.println("producto buscado");
                case 4 -> System.out.println("producto eliminado");
                case 5 -> System.out.println("pedido creado");
                case 6 -> System.out.println("pedidos enlistados");
                case 7 -> {
                    System.out.println("bye bye");
                    end = true;
                }
                default -> System.out.println("Ingrese alguna opcion");
            }

        }

    }
    private static void showMenu() {
        System.out.println("=== MENÚ ===");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Listar Productos");
        System.out.println("3. Buscar/Actualizar Producto");
        System.out.println("4. Eliminar Producto");
        System.out.println("5. Crear Pedido");
        System.out.println("6. Listar Pedidos");
        System.out.println("7. Salir");
        System.out.print("Elige una opción: ");
    }
    private static int readOption() {
        String enter = scanner.nextLine().trim();
        try {
            return Integer.parseInt(enter);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}