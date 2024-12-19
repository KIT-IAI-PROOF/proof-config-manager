package com.smateso.proof.application.services;

import com.smateso.proof.application.model.Action;
import com.smateso.proof.application.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
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
public class MessageInteractor implements IMessageInteractor {

    private static final String ENTITY_WORKFLOW = "workflows";
    private static final String ENTITY_EXECUTION = "executions";
    private static final String ENTITY_TEMPLATE = "templates";
    private static final String ENTITY_BLOCK = "blocks";
    private final SimpMessagingTemplate simpMessagingTemplate;

    public MessageInteractor(@NonNull final SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    /**
     * Publishes an update event for a workflow entity with the specified details.
     * The method generates a message containing the entity type, identifier, action performed,
     * session key, and username, and sends this message to the appropriate messaging channels
     * for further processing and client notifications.
     *
     * @param id         The unique identifier of the workflow being updated. Must not be null.
     * @param action     The action performed on the workflow (e.g., CREATED, UPDATED, DELETED). Must not be null.
     * @param sessionKey The session key associated with the current update operation. Must not be null.
     * @param username   The username of the individual performing the update action. Must not be null.
     */
    @Override
    public void workflowUpdate(@NonNull final String id, @NonNull final Action action, @NonNull final String sessionKey, @NonNull final String username) {
        sendUpdate(ENTITY_WORKFLOW, id, action, sessionKey, username);
    }

    /**
     * Publishes an update event for an execution entity with the specified details.
     * The method generates a message containing the entity type, identifier,
     * action performed, session key, and username, and sends this message
     * to the appropriate messaging channels for further processing and client notifications.
     *
     * @param id         The unique identifier of the execution entity being updated. Must not be null.
     * @param action     The action performed on the execution entity (e.g., CREATED, UPDATED, DELETED). Must not be null.
     * @param sessionKey The session key associated with the current update operation. Must not be null.
     * @param username   The username of the individual performing the update action. Must not be null.
     */
    @Override
    public void executionUpdate(@NonNull final String id, @NonNull final Action action, @NonNull final String sessionKey, @NonNull final String username) {
        sendUpdate(ENTITY_EXECUTION, id, action, sessionKey, username);
    }

    /**
     * Publishes an update event for a template entity with the specified details.
     * The method generates a message containing the entity type, identifier,
     * action performed, session key, and username, and sends this message to
     * the appropriate messaging channels for further processing and client notifications.
     *
     * @param id         The unique identifier of the template being updated. Must not be null.
     * @param action     The action performed on the template (e.g., CREATED, UPDATED, DELETED). Must not be null.
     * @param sessionKey The session key associated with the current update operation. Must not be null.
     * @param username   The username of the individual performing the update action. Must not be null.
     */
    @Override
    public void templateUpdate(@NonNull final String id, @NonNull final Action action, @NonNull final String sessionKey, @NonNull final String username) {
        sendUpdate(ENTITY_TEMPLATE, id, action, sessionKey, username);
    }

    /**
     * Publishes an update event for a block entity with the specified details.
     * The method generates a message containing the entity type, identifier,
     * action performed, session key, and username, and sends this message
     * to the appropriate messaging channels for further processing and client notifications.
     *
     * @param id         The unique identifier of the block being updated. Must not be null.
     * @param action     The action performed on the block (e.g., CREATED, UPDATED, DELETED). Must not be null.
     * @param sessionKey The session key associated with the current update operation. Must not be null.
     * @param username   The username of the individual performing the update action. Must not be null.
     */
    @Override
    public void blockUpdate(@NonNull final String id, @NonNull final Action action, @NonNull final String sessionKey, @NonNull final String username) {
        sendUpdate(ENTITY_BLOCK, id, action, sessionKey, username);
    }

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
    private void sendUpdate(@NonNull String entity, @NonNull String id, @NonNull Action action, @NonNull String sessionKey, @NonNull String username) {
        final var message = Message.builder()
                .id(id)
                .entity(entity)
                .action(action)
                .sessionKey(sessionKey)
                .user(username)
                .build();
        this.simpMessagingTemplate.convertAndSend("/updates/%s".formatted(entity), message);
        this.simpMessagingTemplate.convertAndSend("/updates/%s/%s".formatted(entity, id), message);
    }

}