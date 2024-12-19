package com.smateso.proof.adapter.controller;

import com.smateso.proof.application.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class MessageController {

    // TODO: This Could be a secured http endpoint, also store current edits in database!
    @MessageMapping("/edits")
    @SendTo("/updates/edits")
    public Message greeting(@NonNull final Message message) {
        return message;
    }

}
