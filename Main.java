package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) throws Exception {
		
		ServerSocket servidor = new ServerSocket(9000);
		
		while(true) {
			Socket cliente = servidor.accept();
			
			BufferedReader entradaCli = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);
			
			String requisicao = entradaCli.readLine();
			String[] vetorRequisicao = requisicao.split(" ");
			
			if(vetorRequisicao[0].equals("GET")) {
				StringBuilder conteudoPagina = new StringBuilder();
				BufferedReader bufferConteudo = new BufferedReader(
						new FileReader(
								"C:\\\\Users\\\\dti\\\\eclipse-workspace\\\\Servidor\\\\src\\\\main\\\\index.html"));
				
				while(bufferConteudo.readLine() != null) {
					conteudoPagina.append(bufferConteudo.readLine());
					conteudoPagina.append("\n");
				}
				
				saida.println("HTTP/1.1 200 OK");
				saida.println("Content-Length: " + conteudoPagina.length());
				saida.println();
				saida.println(conteudoPagina.toString());
				saida.flush();

				saida.close();
				entradaCli.close();
				cliente.close();
			}
					
			
		}
	}

}
