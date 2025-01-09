package com.smateso.proof.adapter.boundaries.controller;

import com.smateso.proof.adapter.model.Message;
import org.springframework.lang.NonNull;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public interface IMessageController {

    @MessageMapping("/edits")
    @SendTo("/updates/edits")
    Message updateEdits(@NonNull final Message message, @NonNull final Authentication authentication);

}
