/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi_example;

/**
 *
 * @author vasit
 */
import java.io.*;
import java.net.*;
import org.json.JSONObject;

/**
 * Representa una solicitud JSON-RPC.
 */
class JsonRpcRequest {
    private String jsonrpc;  // Versión del protocolo JSON-RPC
    private String method;   // Método a invocar en el servidor
    private JSONObject params;  // Parámetros del método
    private int id;  // Identificador único de la solicitud

    /**
     * Constructor para crear una solicitud JSON-RPC.
     *
     * @param method El nombre del método que se invocará.
     * @param params Los parámetros que se pasan al método.
     * @param id El identificador único de la solicitud.
     */
    public JsonRpcRequest(String method, JSONObject params, int id) {
        this.jsonrpc = "2.0";  // Versión estándar de JSON-RPC
        this.method = method;
        this.params = params;
        this.id = id;
    }

    /**
     * Convierte la solicitud JSON-RPC en un string JSON.
     *
     * @return La solicitud JSON-RPC en formato de texto.
     */
    public String toJson() {
        JSONObject json = new JSONObject();
        json.put("jsonrpc", this.jsonrpc);  // Agrega la versión de JSON-RPC
        json.put("method", this.method);    // Agrega el nombre del método
        json.put("params", this.params);    // Agrega los parámetros del método
        json.put("id", this.id);            // Agrega el identificador de la solicitud
        return json.toString();             // Devuelve el JSON como una cadena
    }
}

/**
 * Cliente que se comunica con un servidor JSON-RPC.
 */
class JsonRpcClient {
    private String serverAddress;  // Dirección del servidor
    private int serverPort;        // Puerto en el que el servidor está escuchando

    /**
     * Constructor para crear un cliente que se conectará a un servidor JSON-RPC.
     *
     * @param serverAddress Dirección del servidor al que se conecta el cliente.
     * @param serverPort El puerto en el que el servidor está escuchando.
     */
    public JsonRpcClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    /**
     * Envía una solicitud JSON-RPC al servidor y recibe una respuesta.
     *
     * @param request La solicitud JSON-RPC que se enviará al servidor.
     * @return La respuesta del servidor en formato JSON.
     */
    public String sendRequest(JsonRpcRequest request) {
        try (Socket socket = new Socket(serverAddress, serverPort)) {
            // Enviar solicitud JSON-RPC al servidor
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(request.toJson().getBytes());  // Escribe los datos de la solicitud en el flujo de salida
            outputStream.flush();

            // Leer la respuesta del servidor
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);  // Agrega la línea leída a la respuesta
            }
            return responseBuilder.toString();  // Devuelve la respuesta completa
        } catch (IOException e) {
            e.printStackTrace();  // Imprime cualquier error de I/O
            return null;  // Si ocurre un error, devuelve null
        }
    }
}

/**
 * Clase principal que inicializa el cliente y envía la solicitud al servidor.
 */
public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";  // Dirección del servidor (localhost para pruebas locales)
        int serverPort = 8080;  // Puerto del servidor

        // Crear solicitud JSON-RPC con parámetros
        JSONObject params = new JSONObject();
        params.put("a", 10);  // Primer parámetro de la suma
        params.put("b", 9);   // Segundo parámetro de la suma
        JsonRpcRequest request = new JsonRpcRequest("suma", params, 2);  // Crear una solicitud con el método "suma"

        // Crear cliente y enviar solicitud
        JsonRpcClient client = new JsonRpcClient(serverAddress, serverPort);
        String response = client.sendRequest(request);  // Enviar la solicitud y recibir la respuesta

        // Mostrar la respuesta del servidor
        System.out.println("Respuesta del servidor: " + response);  // Imprimir la respuesta recibida del servidor
    }
}