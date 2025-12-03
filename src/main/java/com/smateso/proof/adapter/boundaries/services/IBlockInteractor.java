package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.BlockDao;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IBlockInteractor {

    Page<BlockDao> searchBlocks(@NonNull final Request request);

    BlockDao getBlock(@NonNull final String id);

    BlockDao createBlock(@NonNull final BlockDao blockDao, @Nullable final String sessionKey, @NonNull final String username);

    BlockDao updateBlock(@NonNull final String id, @NonNull final BlockDao blockDao, @Nullable final String sessionKey, @NonNull final String username);

    void deleteBlock(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username);

    List<BlockDao> getBlocks();

}
