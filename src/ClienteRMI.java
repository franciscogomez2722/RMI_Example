/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vasit
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class ClienteRMI {
    public static void main(String[] args) {
        try {
            // Conectarse al registro RMI en la dirección del servidor
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);

            // Obtener la referencia al objeto remoto
            RegistroUsuarios stub = (RegistroUsuarios) registro.lookup("RegistroUsuarios");

            // Llamar al método remoto para registrar un usuario
            String respuesta = stub.registrarUsuario("Juan");
            System.out.println("Respuesta del servidor: " + respuesta);

            // Obtener la lista de usuarios registrados
            List<String> usuarios = stub.obtenerUsuarios();
            System.out.println("Usuarios registrados: " + usuarios);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
