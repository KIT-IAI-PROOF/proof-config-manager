/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IBlockController;
import com.smateso.proof.adapter.boundaries.services.IBlockInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.Block;
import edu.kit.iai.webis.proofmodels.paging.BlockPagingModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.audit.AuditorProvider.getUsername;

@Builder
@AllArgsConstructor
public class BlockController implements IBlockController {

    private final IBlockInteractor blockInteractor;

    @Override
    public ResponseEntity<BlockPagingModel> searchBlocks(@NonNull final Request request) {
        final var blocks = this.blockInteractor.searchBlocks(request);
        final var result = BlockPagingModel
                .builder()
                .rowCount(blocks.getTotalElements())
                .results(blocks.toList())
                .build();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Block>> getBlocks() {
        final var blocks = this.blockInteractor.getBlocks();
        return ResponseEntity.ok(blocks);
    }

    @Override
    public ResponseEntity<List<Block>> exportBlocks() {
        return this.getBlocks();
    }

    @Override
    public ResponseEntity<Block> getBlock(final String id) {
        final var block = this.blockInteractor.getBlock(id);
        return ResponseEntity.ok(block);
    }

    @Override
    public ResponseEntity<Block> createBlock(@NonNull final Block block, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        final var result = this.blockInteractor.createBlock(block, sessionKey, username);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Block> updateBlock(@NonNull final String blockId, @NonNull final Block block, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        final var result = this.blockInteractor.updateBlock(blockId, block, sessionKey, username);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteBlock(@NonNull final String id, @NonNull final Authentication authentication, @Nullable final String sessionKey) {
        final var username = getUsername(authentication);
        this.blockInteractor.deleteBlock(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
