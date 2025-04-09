/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vasit
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

//UnicastRemoteObject es una clase de Java que proporciona la infraestructura básica 
//para exponer objetos remotos que pueden ser invocados desde otras máquinas.
public class RegistroUsuariosImpl extends UnicastRemoteObject implements RegistroUsuarios {
    private List<String> usuarios;

    // Constructor
    protected RegistroUsuariosImpl() throws RemoteException {
        super();
        usuarios = new ArrayList<>();
    }

    // Implementación del método para registrar un usuario
    public String registrarUsuario(String nombre) throws RemoteException {
        usuarios.add(nombre);
        return "Usuario " + nombre + " registrado con exito.";
    }

    // Implementación del método para obtener la lista de usuarios registrados
    public List<String> obtenerUsuarios() throws RemoteException {
        return usuarios;
    }
}
