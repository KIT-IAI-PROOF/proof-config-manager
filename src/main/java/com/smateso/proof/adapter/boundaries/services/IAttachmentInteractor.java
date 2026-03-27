/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.Attachment;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IAttachmentInteractor {

    Page<Attachment> searchAttachments(@NonNull final Request request);

    Attachment getAttachment(@NonNull final String id);

    Attachment createAttachment(@NonNull final Attachment attachment, @Nullable final String sessionKey, @NonNull final String username);

    Attachment updateAttachment(@NonNull final String id, @NonNull final Attachment attachment, @Nullable final String sessionKey, @NonNull final String username);

    void deleteAttachment(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username);

    List<Attachment> getAttachments();

}
