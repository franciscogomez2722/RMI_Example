/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author vasit
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RegistroUsuarios extends Remote {
    // Método remoto para registrar un usuario
    String registrarUsuario(String nombre) throws RemoteException;

    // Método remoto para obtener la lista de usuarios registrados
    List<String> obtenerUsuarios() throws RemoteException;
}
