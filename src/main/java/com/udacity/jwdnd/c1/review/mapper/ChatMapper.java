package com.udacity.jwdnd.c1.review.mapper;

import com.udacity.jwdnd.c1.review.data.ChatForm;
import com.udacity.jwdnd.c1.review.data.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMapper {
    @Select("SELECT * FROM CHATS WHERE messageId = #{messageId}")
    ChatForm getChat(Integer messageId);

    @Select("SELECT * FROM CHATS")
    List<ChatForm> getAllChats();

    @Insert("INSERT INTO CHATS (username, messagetext, messagetype) VALUES (#{username}, #{messageText}, #{messageType})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    Integer insertChat(ChatForm chatForm);

}
