package com.viettran.springbootlibrary.service;

import com.viettran.springbootlibrary.dao.MessageRepository;
import com.viettran.springbootlibrary.entity.Message;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void postMessage(Message messageRequest, String userEmail) {
        Message message = new Message(
                messageRequest.getTitle(),
                messageRequest.getQuestion()
        );
        message.setUserEmail(userEmail);
        messageRepository.save(message);
    }
}
