//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initialization();
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        boolean end = false;
        while (!end) {
            showMenu();
            int option = readOption();
            System.out.println(" ");
            switch (option) {
                case 1 -> addProduct();
                case 2 -> System.out.println(Shoe.getShoes());
                case 3 -> searchProduct();
                case 4 -> deleteProduct();
                case 5 -> createOrder();
                case 6 -> System.out.println(Order.getOrders());
                case 7 -> {
                    System.out.println("bye bye");
                    end = true;
                }
                default -> System.out.println("Ingrese alguna opcion");
            }

        }

    }


    //Menu opciones
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

    //Opciones del menu
    private static void addProduct(){
        String name = "";
        double price = 0;
        int quantityStock = 0;
        boolean botin = false;
        int condition = 0;

        boolean end = false;

        while(end == false){
            System.out.println("¿que quiere crear?");
            System.out.println("1. zapatilla");
            System.out.println("2. botin");
            int option = readOption();
           switch (option) {
               case 1 -> {
                   end = true;
                   botin = false;
               }
               case 2 -> {
                   end = true;
                   botin = true;
               }
               default -> {
                   end = false;
                   System.out.println("Ingrese un dato correctamente");
               }
           }
        }
        System.out.println("Ingrese el nombre del producto");
        name = scanner.nextLine().trim();
        System.out.println("Ingrese el precio del producto");
        price = scanner.nextDouble();
        System.out.println("¿cuanto stock hay actualmente?");
        quantityStock = scanner.nextInt();

        if(botin == true){
            end = false;
            while(end == false){
                System.out.println("Ingrese el estado del producto (en porcentaje)");
                condition = readOption();
                if(condition >= 1 && condition <= 100){
                    end = true;
                }
            }
            Botin newBotin = new Botin(name,price,quantityStock,condition);
        }
        else{
            Shoe newShoe = new Shoe(name,price,quantityStock);
        }
    }

    private static void searchProduct(){
        System.out.println("Ingrese el Id");
        String id = scanner.nextLine().trim();
        Shoe shoe = getById(id);
        if(shoe == null) {
            System.out.println("No existe un producto con ese ID");
        }
        else{
            System.out.println(shoe);
            System.out.println("Ingrese 1 para editar, cualquier otro numero para no hacerlo");
            int enter = readOption();
            if(enter == 1){update(shoe);}
        }
    }

    private static void deleteProduct(){
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

    private static void createOrder(){
        boolean end = false;
        String id;
        ArrayList<Shoe> shoes = new ArrayList<>();
        int option;

        System.out.println("Ingrese el id de cada producto que quieras agregar, ingrese cualquier id incorrecto para terminar");
        while(!end){
            id =  scanner.nextLine().trim();
            Shoe shoe = getById(id);
            if(shoe == null) {
                System.out.println("No existe un producto con ese ID ¿quiere terminar?");
                System.out.println("1. si");
                System.out.println("cualquier otro numero. no");
                option = readOption();
                if(option == 1){
                    if(shoes.size() >= 0){
                        Order order = new Order(shoes);
                    }
                    end = true;
                }
            }
            else{
                shoes.add(shoe);
            }
        }
    }

    //funciones de la lista de zapatos
    public static Shoe getById(String id) {
        return Shoe.getShoes().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    public static void update(Shoe shoe) {
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

    private static void initialization(){
        Shoe shoe1 = new Shoe("zapato1", 150000, 15);
        Shoe shoe2 = new Shoe("zapato2", 200000, 20);
        Shoe shoe3 = new Shoe("zapato3", 100000, 20);
        Botin botin1 = new Botin("Botin1", 500000, 100, 100);
        Botin botin2 = new Botin("Botin2", 25000, 25, 75);
        Botin botin3 = new Botin("Botin3", 1000000, 10, 100);

        List<Shoe> shoes1 = new ArrayList<>();
        shoes1.add(shoe1);
        shoes1.add(shoe3);
        shoes1.add(botin1);
        Order order1 = new Order(shoes1);

        List<Shoe> shoes2 = new ArrayList<>();
        shoes2.add(shoe2);
        shoes2.add(botin2);
        shoes2.add(botin3);
        Order order2 = new Order(shoes2);
    }
}