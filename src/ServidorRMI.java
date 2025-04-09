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

public class ServidorRMI {
    public static void main(String[] args) {
        try {
            // Crear la implementación del objeto remoto
            RegistroUsuariosImpl obj = new RegistroUsuariosImpl();

            // Crear el registro RMI en el puerto 1099
            Registry registro = LocateRegistry.createRegistry(1099);

            // Publicar el objeto en el registro RMI con el nombre "RegistroUsuarios"
            registro.rebind("RegistroUsuarios", obj);

            System.out.println("Servidor RMI listo...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

