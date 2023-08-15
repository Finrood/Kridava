package server;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class is the server
 * He receives informations from the controller
 * 
 * @author Samuel Petre, Arnaud Renard, Quentin Puttemans Groupe 12
 * @version Version 1
 */
public class Server {
	public static void main(String[] args) {
		try {
			boolean stop = false;
			ServerSocket s = new ServerSocket(7469);
			
			while(!stop) {
				Socket soc = s.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				String stage = in.readLine();
				
				while(stage != null) {
					System.out.println("You died at level: " + stage);
					break;
				}
			}
			
			s.close();
			
		} catch (IOException e) {
			System.out.println("Le serveur a crash");
		}
	}
}
