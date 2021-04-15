package me.tcambron.chatbot;

import java.util.Random;

public interface ChatTone {
	final String[] CONFIRMATION_WORDS = { "ok", "yes", "indeed", "agree", "accept", "approve" };
	final String[] DENIAL_WORDS = { "no", "deny", "disapprove" };
	final String[] POSITIVE_WORDS = { "happy", "excite", "good", "great"};
	final String[] NEGATIVE_WORDS = { "sad", "boring", "bored", "bad", "terrible" };
	final String[] VERBS = { "walk", "run", "sleep", "eat", "meet", "travel", "fly", "swim", "do", "jog", "type", 
			"think", "attempt", "ask", "change", "choose", "talk", "yell", "whisper", "count", "cook", "cut", "cry", 
			"discuss", "distribute", "drive", "drink", "exist" };
	final String[] INTRODUCTION_WORDS = { "hello", "hi", "greetings", "meet you", "hey" };
	final String[] WELLBEING_QUESTIONS = { "how are you", "are you well", "youre well" };
	final String[] STUCK_PHRASES = {"How was your day?", "What is your favorite food?", "How tall are you?"};
	
	public default String getResponseFromMessage(ChatResponse chatResponse) {
		String verb = null;
		boolean confirm = false;
		boolean deny = false;
		boolean introduction = false;
		boolean askedWellbeing = false;
		boolean positive = false;
		boolean negative = false;
		boolean askedQuestion = false;
		for (String v: VERBS) {
			if (chatResponse.getMessage().contains(v)) {
				verb = v;
			}
		}
		for (String c: CONFIRMATION_WORDS) {
			if (chatResponse.getMessage().contains(c)) {
				confirm = true;
			} 
		}
		for (String d: DENIAL_WORDS) {
			if (chatResponse.getMessage().contains(d)) {
				deny = true;
			} 
		}
		for (String i: INTRODUCTION_WORDS) {
			if (chatResponse.getMessage().contains(i)) {
				introduction = true;
			}
		}
		for (String wb: WELLBEING_QUESTIONS) {
			if (chatResponse.getMessage().contains(wb)) {
				askedWellbeing = true;
			}
		}
		for (String p: POSITIVE_WORDS) {
			if (chatResponse.getMessage().contains(p)) {
				positive = true;
			}
		}
		for (String n: NEGATIVE_WORDS) {
			if (chatResponse.getMessage().contains(n)) {
				negative = true;
			}
		}
		if (chatResponse.getMessage().contains("?")) {
			askedQuestion = true;
		}
		
		String botMsg = "";
		if (introduction) {
			botMsg += "Hello! ";
		}
		if (askedWellbeing) {
			botMsg += "I am doing well. ";
		}
		if (askedQuestion) {
			botMsg += "I dont get to do much... ";
		}
		if (confirm && deny) {
			if (verb != null) {
				botMsg += "Im not quite sure what you mean, are you " + verb + "ing? ";
			}
		} else if (!confirm && deny) {
			if (verb != null) {
				botMsg += "Why are you " + verb + "ing? ";
			}
			
			if (chatResponse.getLastResponse() != null) {
				botMsg += "I'm sorry. ";
			}
		} else if (confirm && !deny) {
			if (verb != null) {
				botMsg += "I hope you're happy " + verb + "ing! ";
			}
			
			if (chatResponse.getLastResponse() != null) {
				botMsg += "That is good. ";
			}
		}else if (positive && !negative) {
			botMsg += "That's good! ";
			if (verb != null) {
				botMsg += "Happy " + verb + "ing! ";
			}
		} else if (!positive && negative) {
			botMsg += "I'm sorry to hear that. ";
			if (verb != null) {
				botMsg += "Why are you " + verb + "ing?";
			}
		} else if (positive && negative) {
			botMsg += "I'm not sure how to feel. ";
			if (verb != null) {
				botMsg += "Why are you " + verb + "ing?";
			}
		}
		
		if (botMsg.equals("")) {
			Random r = new Random();
			botMsg = STUCK_PHRASES[r.nextInt(STUCK_PHRASES.length)];
			if (chatResponse.getLastResponse() != null ) {
				for (String sp: STUCK_PHRASES) {
					if (chatResponse.getLastResponse().getResponse().contains(sp)) {
						botMsg = "Cool!";
					}
				}
			}		
		}
		
		return botMsg;
	}
}
