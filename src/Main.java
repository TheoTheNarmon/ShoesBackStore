//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
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
                case 2 -> {System.out.println(Shoe.getShoes());}
                case 3 -> {
                    System.out.println("Ingrese el Id");
                    /*String id = scanner.nextLine().trim();
                    Shoe shoe = getById(id);
                    if(shoe == null) {
                        System.out.println("No existe un producto con ese ID");
                    }
                    else{
                        System.out.println(shoe);
                        System.out.println("Ingrese 1 para editar, cualquier otro numero para no hacerlo");
                        int enter = readOption();
                        switch (enter) {
                            case 1 -> {update(shoe);}
                        }
                    }*/
                }
                case 4 ->{
                    System.out.println("Ingrese el Id");
                    String id = scanner.nextLine().trim();
                    Shoe shoe = getById(id);
                    if(shoe == null) {
                        System.out.println("No existe un producto con ese ID");
                    }
                    else{
                        shoe.delete();
                    }
                }
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


    public static Shoe getById(String id) {
        return Shoe.shoes.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    public void update(Shoe shoe) {
        final Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option;
        while(exit == false){
            System.out.println("¿Que quiere actualizar?.");
            System.out.println("1. Nombre.");
            System.out.println("2. Precio.");
            System.out.println("3. Stock.");
            if(shoe instanceof Botin){ System.out.println("4. Condicion."); }
            System.out.println("5. Volver.");
            option = readOption();

            switch (option) {
                case 1 -> {
                    System.out.println("escribe el nombre: ");
                    String enter = scanner.nextLine().trim();
                    shoe.setName(enter);
                    System.out.println("Nombre cambiado a " + shoe.getName());
                }
                case 2 -> {
                    System.out.println("escribe el precio: ");
                    try {
                        Double newPrice = scanner.nextDouble();
                        shoe.setPrice(newPrice);
                        System.out.println("Precio cambiado a " + shoe.getPrice());
                    } catch (NumberFormatException e) {
                        System.out.println("Error: El texto no es un número válido.");
                    }
                }
                case 3 -> {
                    System.out.println("escribe el stock: ");
                    try{
                        int enter = scanner.nextInt();
                        shoe.setStock(enter);
                        System.out.println("Stock cambiado a " + shoe.getStock());
                    } catch (NumberFormatException e) {
                        System.out.println("Error: El texto no es un número válido.");
                    }
                }
                case 4 -> {
                    if(shoe instanceof Botin){
                        try{
                            int enter = scanner.nextInt();
                            if(enter >= 0 &&  enter <= 100){
                                shoe.setStock(enter);
                                System.out.println("Condicion cambiada a " + ((Botin) shoe).getCondition());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: El texto no es un número válido.");
                        }
                    }
                    else{System.out.println("Ingrese alguna opcion correcta");}
                }
                case 5 -> {exit = true;}
                default -> System.out.println("Ingrese alguna opcion correcta");
            }
        }
    }
}