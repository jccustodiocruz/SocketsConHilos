package ejemplosockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jc
 */
public class HiloSocket extends Thread {

    Socket socket;

    @Override
    public void run() {
        try {
            PrintWriter out = null;
            BufferedReader in = null;
            String mensaje = null;
            String inputLine, outputLine;

            KnockKnockProtocol kkp = new KnockKnockProtocol();
            
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            outputLine = kkp.processInput(null);
            out.println(outputLine);
            
            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye.")) {
                    break;
                }
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(HiloSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public HiloSocket(Socket socket) {
        this.socket = socket;
    }

}
