package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IMessageController;
import com.smateso.proof.adapter.model.Message;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;

public class MessageController implements IMessageController {

    @Override
    public Message updateEdits(@NonNull final Message message, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        message.setUser(username);
        return message;
    }

}
