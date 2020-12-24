package com.udacity.jwdnd.c1.review.services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ChatHistoryService {

    List<Map<String, String>> chatHistory;
    List<String> bannedWords;
    // Everytime we return the html, including the ChatForm, for the chat page, the messageTypeOptions in the form
    // never change, so we can make this static, and public to be accessible to the ChatForm.
    public static List<String> messageTypeOptions;

    @PostConstruct
    public void postConstruct() {
        System.out.println("...in PostConstruct for ChatHistoryService");
        chatHistory = new ArrayList<Map<String, String>>();
        bannedWords = Arrays.asList(new String[] {"fuck", "shit", "poop"});
        messageTypeOptions = Arrays.asList(new String[] {"Say", "Shout", "Whisper"});
    }

    public String replaceChar(String s, int index, char ch) {
        char[] chars = s.toCharArray();
        chars[index] = ch;
        return String.valueOf(chars);
    }

    /*
     Business logic lives in the service. So, it's good to remove banned words here, so they are never saved at all.
     */
    public void addChat(String un, String mtxt, String mtype) {
        Map<String, String> chat = new HashMap<String, String>();
        chat.put("username", un);

        String cleanMtxt = new String(mtxt);
        String lowerMtxt = new String(mtxt.toLowerCase(Locale.ROOT));
        for (String bw : bannedWords) {
            if (lowerMtxt.contains(bw)) {
                int start = lowerMtxt.indexOf(bw);
                int end = start + (bw.length() - 1);
                for (int i = start; i <= end; i++) {
                    cleanMtxt = replaceChar(cleanMtxt, i, '-');
                }
            }
        }
        chat.put("messageText", cleanMtxt);
        chat.put("messageType", mtype);
        chatHistory.add(chat);
    }

    public List<Map<String, String>> getChatHistory() {
        return chatHistory;
    }

    /*
    Business logic lives in the service. So, it's good to format the final message here, not in the template.
     */
    public List<String> getPrettyChatHistory() {
        List<String> prettyChatHistory = new ArrayList<String>();
        for (Map<String, String> origChat : chatHistory) {
            String uName = origChat.get("username");
            String mTxt = origChat.get("messageText");
            String mType = origChat.get("messageType");
            String prettyMTxt;
            switch (mType) {
                case "Say": prettyMTxt = new String(mTxt);
                    break;
                case "Shout": prettyMTxt = new String(mTxt.toUpperCase());
                    break;
                case "Whisper": prettyMTxt = new String(mTxt.toLowerCase());
                    break;
                default: prettyMTxt = "Unable to format original message text";
                    break;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(uName);
            sb.append(": ");
            sb.append(prettyMTxt);
            sb.append("<br>");
            prettyChatHistory.add(sb.toString());
        }
        return prettyChatHistory;
    }
}
