/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.model.Action;
import com.smateso.proof.adapter.model.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * The MessageInteractor class is responsible for facilitating the communication of entity update events
 * to clients using a messaging framework.
 * It uses the SimpMessagingTemplate to publish updates on
 * predefined messaging channels for different entities such as workflows, executions, templates, and blocks.
 * Each update message contains details about the entity, action performed, session key, and username.
 * This class encapsulates methods to publish update messages specific to various entities and a common
 * functionality for handling the generation and delivery of these messages.
 */
@Slf4j
@AllArgsConstructor
public class MessageInteractor implements IMessageInteractor {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final AmqpAdmin amqpAdmin;

    /**
     * Publishes an update event for a specified entity with the provided details.
     * The method generates a message containing the entity type, identifier, action performed,
     * session key, and username, then sends this message to the appropriate messaging
     * channels for further processing or client notifications.
     *
     * @param entity     The type of entity being updated (e.g., workflow, execution, template, block). Must not be null.
     * @param id         The unique identifier of the entity being updated. Must not be null.
     * @param action     The action performed on the entity (e.g., CREATED, UPDATED, DELETED). Must not be null.
     * @param sessionKey The session key associated with the current update operation. Must not be null.
     * @param username   The username of the individual performing the update action. Must not be null.
     */
    @Override
    public void sendUpdate(@NonNull final String entity, @NonNull final String id, @NonNull final Action action, @Nullable final String sessionKey, @Nullable final String username) {
        final var message = Message.builder()
                .id(id)
                .entity(entity)
                .action(action)
                .sessionKey(sessionKey)
                .user(username)
                .build();
        this.simpMessagingTemplate.convertAndSend("/topic/updates", message);
    }

    @Override
    public void deleteBlockLoggingQueues(@NonNull final Iterable<String> blockIds, @NonNull final String executionId) {
        log.info("Deleting logging queues for blocks");
        blockIds.forEach((final var blockId) -> {
            final var queue = QueueBuilder.durable("logs.block.%s.execution.%s".formatted(blockId, executionId)).build();
            final var binding = BindingBuilder.bind(queue).to(new TopicExchange("logs")).with("logs.blocks." + blockId);
            this.amqpAdmin.removeBinding(binding);
            this.amqpAdmin.deleteQueue(queue.getName());
        });
    }

    @Override
    public void deleteExecutionLoggingQueue(@NonNull final String executionId) {
        log.info("Deleting logging queue for execution");
        final var queue = QueueBuilder.durable("logs.execution.%s".formatted(executionId)).build();
        final var binding = BindingBuilder.bind(queue).to(new TopicExchange("logs")).with("logs.execution." + executionId);
        this.amqpAdmin.removeBinding(binding);
        this.amqpAdmin.deleteQueue(queue.getName());
    }

}