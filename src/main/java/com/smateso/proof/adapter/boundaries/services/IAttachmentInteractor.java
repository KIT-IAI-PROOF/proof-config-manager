package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.AttachmentDao;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IAttachmentInteractor {

    Page<AttachmentDao> searchAttachments(@NonNull final Request request);

    AttachmentDao getAttachment(@NonNull final String id);

    AttachmentDao createAttachment(@NonNull final AttachmentDao attachmentDao, @Nullable final String sessionKey, @NonNull final String username);

    AttachmentDao updateAttachment(@NonNull final String id, @NonNull final AttachmentDao attachmentDao, @Nullable final String sessionKey, @NonNull final String username);

    void deleteAttachment(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username);

    List<AttachmentDao> getAttachments();

}
