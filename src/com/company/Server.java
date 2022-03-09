package com.company;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public void criarServerSocket(int porta) throws IOException {
        serverSocket = new ServerSocket(porta);
    }

    public Socket esperaConexao() throws IOException {
        Socket socket = serverSocket.accept();
        return socket;

    }

    private void fecharSocket(Socket s) throws IOException {
        s.close();
    }

    public void tratarConexao(Socket socket) throws IOException {
        try {
            ObjectOutput outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream.writeUTF("Hello World!");
            outputStream.flush();
            inputStream.close();
            outputStream.close();
        } catch (IOException  e) {
            System.out.println("Erro no tratamento da conexão com o cliente: " + socket.getInetAddress());
            System.out.println("Erro: " + e.getMessage());

        } finally {
            fecharSocket(socket);

        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            while (true) {
                System.out.println("Aguardando Conexão...");
                server.criarServerSocket(5555);
                while (true) {
                    Socket socket = server.esperaConexao();
                    System.out.println("Cliente conectado!");
                    server.tratarConexao(socket);
                    System.out.println("Cliente finalizado!");
                }
            }
        } catch (IOException e) {

        }
    }
}