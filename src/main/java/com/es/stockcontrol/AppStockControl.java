package com.es.stockcontrol;

import com.es.stockcontrol.controller.impl.ProductoController;
import com.es.stockcontrol.controller.impl.ProveedorController;
import com.es.stockcontrol.controller.impl.UserController;
import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.model.Proveedor;
import com.es.stockcontrol.model.RespuestaHTTP;
import com.es.stockcontrol.model.User;
import com.es.stockcontrol.repository.ProductoRepository;
import com.es.stockcontrol.repository.ProveedorRepository;
import com.es.stockcontrol.repository.UserRepository;
import com.es.stockcontrol.service.ProductoService;
import com.es.stockcontrol.service.ProveedorService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class AppStockControl {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PSC");
        EntityManager em = emf.createEntityManager();

        UserRepository userRepository = new UserRepository(em);
        ProductoRepository productoRepository = new ProductoRepository(em);
        ProveedorRepository proveedorRepository = new ProveedorRepository(em);

        ProductoService productoService = new ProductoService(em);
        ProveedorService proveedorService = new ProveedorService(proveedorRepository);

        UserController userController = new UserController(userRepository);
        ProductoController productoController = new ProductoController(productoService);
        ProveedorController proveedorController = new ProveedorController(proveedorService);

        Scanner scan = new Scanner(System.in);
        boolean login = false;
        User user = new User();

        // Login
        do {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("""
                    ******************************************************
                    ****    Bienvenid@ a StockControl               ******
                    ******************************************************
                    Introduzca su usuario y contrasena para continuar (0 para salir)
                    """);
            System.out.print("user: ");
            String userInput = scan.nextLine();

            if ("0".equalsIgnoreCase(userInput)) {
                System.out.println("Saliendo...");
                System.exit(0);
            } else {
                System.out.print("password: ");
                String passwordInput = scan.nextLine();

                RespuestaHTTP<User> respuestaHTTP = userController.login(userInput, passwordInput);

                try {
                    if (respuestaHTTP.getCodigo() == 200) {
                        if (respuestaHTTP.getObj() != null) {
                            System.out.println("Bienvenid@");
                            user = respuestaHTTP.getObj();
                            login = true;
                        } else {
                            System.err.println("¡INTRODUCE EL OBJETO EN LA RESPUESTA HTTP DESDE EL CONTROLLER!");
                        }
                    } else {
                        System.out.printf("Error en el login\n\t-codigo %d\n\t-%s\n", respuestaHTTP.getCodigo(), respuestaHTTP.getMensaje());
                    }
                } catch (Exception e) {
                    System.out.println("Error controlado");
                }
            }
        } while (!login);

        // Gestión de stock
        String opc;
        do {
            System.out.println("""
                    ******************************************************
                    ****            APP STOCK CONTROL               ******
                    ******************************************************
                    1. Alta producto
                    2. Baja producto
                    3. Modificar nombre producto
                    4. Modificar stock producto
                    5. Get producto por id
                    6. Get productos con stock
                    7. Get productos sin stock
                    8. Get proveedores de un producto
                    9. Get todos los proveedores
                    0. Salir
                    """);
            System.out.print("Seleccione una opción: ");
            opc = scan.nextLine();

            try {
                switch (opc) {
                    case "1":
                        altaProducto(productoController);
                        break;
                    case "2":
                        bajaProducto(productoController);
                        break;
                    case "3":
                        modificarNombreProducto(productoController);
                        break;
                    case "4":
                        modificarStockProducto(productoController);
                        break;
                    case "5":
                        getProductoPorId(productoController);
                        break;
                    case "6":
                        getProductosConStock(productoController);
                        break;
                    case "7":
                        getProductosSinStock(productoController);
                        break;
                    case "8":
                        getProveedoresDeUnProducto(proveedorController);
                        break;
                    case "9":
                        getTodosLosProveedores(proveedorController);
                        break;
                    case "0":
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Error en la elección");
                        break;
                }
            } catch (Exception e) {
                System.out.println("ERROR CONTROLADO");
            }
        } while (!opc.equals("0"));
    }

    public static void altaProducto(ProductoController productoController) {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Alta producto");

        System.out.println("DETALLES PRODUCTO");
        System.out.print("categoria: ");
        String categoriaProducto = scan.nextLine();
        System.out.print("nombre: ");
        String nombreProducto = scan.nextLine();
        System.out.print("precio sin IVA: ");
        String precioSinIva = scan.nextLine();
        System.out.print("descripcion: ");
        String descripcionProducto = scan.nextLine();

        System.out.println("DETALLES PROVEEDOR");
        System.out.print("nombre: ");
        String nombreProveedor = scan.nextLine();
        System.out.print("direccion: ");
        String direccionProveedor = scan.nextLine();

        RespuestaHTTP<Producto> respuesta = productoController.altaProducto(categoriaProducto, nombreProducto, precioSinIva, descripcionProducto, nombreProveedor, direccionProveedor);

        if (respuesta != null && respuesta.getCodigo() == 201) {
            System.out.printf("PRODUCTO INSERTADO CORRECTAMENTE\n%s", respuesta.getObj().toString());
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }
    }

    public static void bajaProducto(ProductoController productoController) {
        Scanner scan = new Scanner(System.in);
        System.out.println("2. Baja producto");

        System.out.print("Introduzca el id del producto: ");
        String idProducto = scan.nextLine();
        RespuestaHTTP<Producto> respuesta = productoController.bajaProducto(idProducto);

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.printf("OPERACION EXITOSA");
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }
    }

    public static void modificarNombreProducto(ProductoController productoController) {
        Scanner scan = new Scanner(System.in);
        System.out.println("3. Modificar nombre producto");
        System.out.print("Introduzca el id del producto: ");
        String idProducto = scan.nextLine();
        System.out.print("Introduzca el nuevo nombre del producto: ");
        String nuevoNombre = scan.nextLine();
        RespuestaHTTP<Producto> respuesta = productoController.modificarNombreProducto(idProducto, nuevoNombre);

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.printf("OPERACION EXITOSA");
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }
    }

    public static void modificarStockProducto(ProductoController productoController) {
        Scanner scan = new Scanner(System.in);
        System.out.println("4. Modificar stock producto");

        System.out.print("Introduzca el id del producto: ");
        String idProducto = scan.nextLine();
        System.out.print("Introduzca el nuevo stock: ");
        String nuevoStock = scan.nextLine();
        RespuestaHTTP<Producto> respuesta = productoController.modificarStockProducto(idProducto, nuevoStock);

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.printf("OPERACION EXITOSA");
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }
    }

    public static void getProductoPorId(ProductoController productoController) {
        Scanner scan = new Scanner(System.in);
        System.out.println("5. Get producto por id");

        System.out.print("Introduzca el id del producto: ");
        String idProducto = scan.nextLine();
        RespuestaHTTP<Producto> respuesta = productoController.getProducto(idProducto);

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.printf("OPERACION EXITOSA\n%s", respuesta.getObj().toString());
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }
    }

    public static void getProductosConStock(ProductoController productoController) {
        System.out.println("6. Get productos con stock");

        RespuestaHTTP<List<Producto>> respuesta = productoController.getProductosConStock();

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.printf("OPERACION EXITOSA");
            respuesta.getObj().forEach(producto -> {
                System.out.println(producto.toString());
            });
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }
    }

    public static void getProductosSinStock(ProductoController productoController) {
        System.out.println("7. Get productos sin stock");

        RespuestaHTTP<List<Producto>> respuesta = productoController.getProductosSinStock();

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.printf("OPERACION EXITOSA");
            respuesta.getObj().forEach(producto -> {
                System.out.println(producto.toString());
            });
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }
    }

    public static void getProveedoresDeUnProducto(ProveedorController proveedorController) {
        Scanner scan = new Scanner(System.in);
        System.out.println("8. Get proveedores de un producto");

        System.out.print("Introduzca el id del producto: ");
        String idProducto = scan.nextLine();
        Long idProductoLong = Long.parseLong(idProducto);
        RespuestaHTTP<List<Proveedor>> respuesta = proveedorController.getProveedoresProducto(idProductoLong);

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.printf("OPERACION EXITOSA");
            respuesta.getObj().forEach(proveedor -> {
                System.out.println(proveedor.toString());
            });
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }
    }

    public static void getTodosLosProveedores(ProveedorController proveedorController) {
        System.out.println("9. Get todos los proveedores");

        RespuestaHTTP<List<Proveedor>> respuesta = proveedorController.getTodosProveedores();

        if (respuesta != null && respuesta.getCodigo() == 200) {
            System.out.printf("OPERACION EXITOSA");
            respuesta.getObj().forEach(proveedor -> {
                System.out.println(proveedor.toString());
            });
        } else {
            System.out.printf("Error en la operacion\n\t-codigo %d\n\t-%s\n", respuesta.getCodigo(), respuesta.getMensaje());
        }
    }
}
