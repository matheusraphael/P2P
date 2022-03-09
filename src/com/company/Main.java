package com.company;


import java.io.IOException;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try {
            Server server = new Server();
            System.out.println("Aguardando Conexão...");
            server.criarServerSocket(5555);
            Socket socket = server.esperaConexao();
            System.out.println("Cliente conectado!");
            server.tratarConexao(socket);
            System.out.println("Cliente finalizado!");
        }catch (IOException e){

        }
    }
}
