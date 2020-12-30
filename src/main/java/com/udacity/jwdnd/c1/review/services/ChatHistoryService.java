package com.udacity.jwdnd.c1.review.services;

import com.udacity.jwdnd.c1.review.data.ChatForm;
import com.udacity.jwdnd.c1.review.data.User;
import com.udacity.jwdnd.c1.review.mapper.ChatMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ChatHistoryService {

    List<String> bannedWords;
    // Everytime we return the html, including the ChatForm, for the chat page, the messageTypeOptions in the form
    // never change, so we can make this static, and public to be accessible to the ChatForm.
    public static List<String> messageTypeOptions;
    private final ChatMapper chatMapper;

    // Write your own constructor, because we need to declare the dependency on the ChatMapper
    public ChatHistoryService(ChatMapper chatMapper) {
        System.out.println("...in PostConstruct for ChatHistoryService");
        bannedWords = Arrays.asList(new String[] {"fuck", "shit", "poop"});
        messageTypeOptions = Arrays.asList(new String[] {"Say", "Shout", "Whisper"});
        this.chatMapper = chatMapper;
    }

    public String replaceChar(String s, int index, char ch) {
        char[] chars = s.toCharArray();
        chars[index] = ch;
        return String.valueOf(chars);
    }

    /*
     Business logic lives in the service. So, it's good to remove banned words here, so they are never saved at all.
     */
    public Integer addChat(ChatForm chatForm) {

        String cleanMtxt = new String(chatForm.getMessageText());
        String lowerMtxt = new String(chatForm.getMessageText().toLowerCase(Locale.ROOT));
        for (String bw : bannedWords) {
            if (lowerMtxt.contains(bw)) {
                int start = lowerMtxt.indexOf(bw);
                int end = start + (bw.length() - 1);
                for (int i = start; i <= end; i++) {
                    cleanMtxt = replaceChar(cleanMtxt, i, '-');
                }
            }
        }
        System.out.println("....cleanMtxt = " + cleanMtxt);
        chatForm.setMessageText(cleanMtxt);
        return chatMapper.insertChat(chatForm);
    }

    public List<ChatForm> getChatHistory() {
        return chatMapper.getAllChats();
    }

    /*
    Business logic lives in the service. So, it's good to format the final message here, not in the template.
     */
    public List<String> getPrettyChatHistory() {
        List<String> prettyChatHistory = new ArrayList<String>();
        List<ChatForm> chatHistory = chatMapper.getAllChats();
        for (ChatForm origChat : chatHistory) {
            String uName = origChat.getUsername();
            String mTxt = origChat.getMessageText();
            String mType = origChat.getMessageType();
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
