package com.smateso.proof.application.services;

import com.smateso.proof.application.model.Request;
import edu.kit.iai.webis.proofmodels.Block;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.List;

public interface IBlockInteractor {

    Page<Block> searchBlocks(@NonNull final Request request);

    Block getBlock(@NonNull final String id);

    Block createBlock(@NonNull final Block block, @NonNull final String sessionKey, @NonNull final String username);

    Block updateBlock(@NonNull final String id, @NonNull final Block block, @NonNull final String sessionKey, @NonNull final String username);

    void deleteBlock(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username);

    List<Block> getBlocks();
}
