package ejemplosockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author gilberto.borrego
 */
public class KnockKnockServer {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = null;
            serverSocket = new ServerSocket(4444);
            System.out.println("Listo para conectarse");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new HiloSocket(clientSocket).start();
                System.out.println("Conectado");
            }
            
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
    }
}
