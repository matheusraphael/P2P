package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Estabelecendo conexão...");
            Socket socket = new Socket("localhost",5555);
            System.out.println("Conexão estabelecida!");
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            System.out.println("Enviando mensagem...");
            String mensagem = "Hello";
            outputStream.writeUTF(mensagem);
            outputStream.flush(); // liberar o buffer para envio
            System.out.println("Mensagem "+mensagem+" enviada!");

            mensagem = inputStream.readUTF();
            System.out.println("Repopsta: "+ mensagem);

            inputStream.close();
            outputStream.close();
            socket.close();
        }catch (IOException exception){
            System.out.println("Erro no cliente: "+exception);
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE,null,exception);
        }
    }
}



