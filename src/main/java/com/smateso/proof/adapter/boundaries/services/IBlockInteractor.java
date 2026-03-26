/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.Block;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IBlockInteractor {

    Page<Block> searchBlocks(@NonNull final Request request);

    Block getBlock(@NonNull final String id);

    Block createBlock(@NonNull final Block block, @Nullable final String sessionKey, @NonNull final String username);

    Block updateBlock(@NonNull final String id, @NonNull final Block block, @Nullable final String sessionKey, @NonNull final String username);

    void deleteBlock(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username);

    List<Block> getBlocks();

}
