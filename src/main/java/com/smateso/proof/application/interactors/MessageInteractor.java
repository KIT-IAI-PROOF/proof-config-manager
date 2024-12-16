package com.smateso.proof.application.interactors;

import com.smateso.proof.application.model.Action;
import com.smateso.proof.application.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class MessageInteractor {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    public void workflowUpdate(@NonNull final String id, @NonNull final Action action, @NonNull final String sessionKey, @NonNull final String username) {
        final var message = Message.builder()
                .id(id)
                .entity("workflow")
                .action(action)
                .sessionKey(sessionKey)
                .user(username)
                .build();
        this.simpMessagingTemplate.convertAndSend("/updates/workflows", message);
        this.simpMessagingTemplate.convertAndSend("/updates/workflows/%s".formatted(id), message);
    }

    public void executionUpdate(@NonNull final String id, @NonNull final Action action, @NonNull final String sessionKey, @NonNull final String username) {
        final var message = Message.builder()
                .id(id)
                .entity("execution")
                .action(action)
                .sessionKey(sessionKey)
                .user(username)
                .build();
        this.simpMessagingTemplate.convertAndSend("/updates/executions", message);
        this.simpMessagingTemplate.convertAndSend("/updates/executions/%s".formatted(id), message);
    }

    public void templateUpdate(@NonNull final String id, @NonNull final Action action, @NonNull final String sessionKey, @NonNull final String username) {
        final var message = Message.builder()
                .id(id)
                .entity("template")
                .action(action)
                .sessionKey(sessionKey)
                .user(username)
                .build();
        this.simpMessagingTemplate.convertAndSend("/updates/templates", message);
        this.simpMessagingTemplate.convertAndSend("/updates/templates/%s".formatted(id), message);
    }

    public void blockUpdate(@NonNull final String id, @NonNull final Action action, @NonNull final String sessionKey, @NonNull final String username) {
        final var message = Message.builder()
                .id(id)
                .entity("block")
                .action(action)
                .sessionKey(sessionKey)
                .user(username)
                .build();
        this.simpMessagingTemplate.convertAndSend("/updates/blocks", message);
        this.simpMessagingTemplate.convertAndSend("/updates/blocks/%s".formatted(id), message);
    }
}
