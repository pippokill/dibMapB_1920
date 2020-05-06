/*
 * Copyright (C) 2020 pierpaolo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package di.uniba.map.b.lab.rete.esercizi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author pierpaolo
 */
public class MessengerThread extends Thread {

    private final Socket socket;

    private final MessengerData md;

    private PrintWriter out = null;

    public MessengerThread(Socket socket, MessengerData md) {
        this.socket = socket;
        this.md = md;
    }

    public MessengerThread(Socket socket, MessengerData md, String name) {
        this(socket, md);
        this.setName(name);
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Connessione accettata: " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            while (true) {
                String str = in.readLine();
                if (str != null) {
                    if (str.startsWith("#name")) {
                        String name = str.substring(str.indexOf(" ")).trim();
                        try {
                            md.addUser(name, this);
                            out.println("#ok");
                        } catch (Exception ex) {
                            out.println("#error " + ex.getMessage());
                        }
                    } else if (str.startsWith("#send")) {
                        int idx1 = str.indexOf(" ");
                        int idx2 = str.indexOf(" ", idx1 + 1);
                        String name = str.substring(idx1, idx2).trim();

                        String msg = str.substring(idx2).trim();
                        try {
                            md.sendMessage(name, msg);
                            out.println("#ok");
                        } catch (Exception ex) {
                            out.println("#error " + ex.getMessage());
                        }
                    } else if (str.startsWith("#remove")) {
                        String name = str.substring(str.indexOf(" ")).trim();
                        try {
                            md.removeUser(name);
                            out.println("#ok");
                        } catch (Exception ex) {
                            out.println("#error " + ex.getMessage());
                        }
                    } else {
                        out.println("#error Comando sconosciuto");
                    }
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            System.out.println("closing...");
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}
