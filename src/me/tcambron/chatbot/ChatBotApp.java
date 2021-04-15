package me.tcambron.chatbot;

import java.util.Scanner;

/**
 * @author Tyler Cambron
 *
 */
public class ChatBotApp {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ChatBot chavez = new ChatBot("Chavez");
		
		System.out.println("Type \"stop chavez\" to shut down the bot.");
		while (chavez.isActive()) {
			System.out.print(": ");
			String message = input.nextLine();
			ChatResponse response = new ChatResponse(message, chavez.getResponse(), chavez.getName());
			chavez.interact(response);
			System.out.println(" ");
		}
		input.close();
	}
}
