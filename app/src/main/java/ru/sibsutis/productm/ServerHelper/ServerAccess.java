package ru.sibsutis.productm.ServerHelper;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

class ServerAccess {
    private final String    HOST    =   "90.189.170.220";
    private final int       PORT    =   8888;

    private Socket          sock            = null;
    private BufferedReader  reader          = null;
    private PrintWriter     writer          = null;
    private String          serversMessage  = null;

    private boolean connToServer() {
        try {
            sock = new Socket(HOST, PORT);
            reader = new BufferedReader(new InputStreamReader(sock.getInputStream(), "UTF-8"));
            writer = new PrintWriter(new OutputStreamWriter(sock.getOutputStream(), "UTF-8"));
            return true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void sendMessage(String message) {
        writer.println(message);
        writer.flush();
    }

    private boolean readMessage() {
        try {
            serversMessage = "";
            String msg = "";
            while ((msg = reader.readLine()) != null)
                serversMessage += msg + "\n";
            serversMessage = serversMessage.substring(0, serversMessage.length()-1);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void closeConn() {
        if (sock != null && !sock.isClosed()) {
            try {
                sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                sock = null;
            }
        }
        sock = null;
    }

    private boolean sockIsOpen() {
        if (sock != null && !sock.isClosed())
            return true;
        else
            return false;
    }

    public String request(String msg) {
        if(!connToServer()) return null;
        sendMessage(msg);
        if(!readMessage()) return null;
        closeConn();
        return serversMessage;
    }
}
