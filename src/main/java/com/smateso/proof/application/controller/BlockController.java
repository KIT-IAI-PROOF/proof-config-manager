package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IBlockController;
import com.smateso.proof.adapter.boundaries.services.IBlockInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.BlockDao;
import edu.kit.iai.webis.proofmodels.dto.Block;
import edu.kit.iai.webis.proofmodels.paging.BlockPagingModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.audit.AuditorProvider.getUsername;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Builder
@AllArgsConstructor
public class BlockController implements IBlockController {

    private final IBlockInteractor blockInteractor;

    @NonNull
    private static Block attachLinks(@NonNull final Block block) {
        final var selfLink = linkTo(BlockController.class).slash(block.getId()).withSelfRel();
        block.add(selfLink);
        return block;
    }

    @Override
    public ResponseEntity<BlockPagingModel> searchBlocks(@NonNull final Request request) {
        final var blocks = this.blockInteractor.searchBlocks(request);
        final var result = BlockPagingModel
                .builder()
                .rowCount(blocks.getTotalElements())
                .results(blocks
                        .stream()
                        .map(BlockDao::convert)
                        .map(BlockController::attachLinks)
                        .toList())
                .build();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Block>> getBlocks() {
        final var blocks = this.blockInteractor.getBlocks();
        final var results = blocks
                .stream()
                .map(BlockDao::convert)
                .map(BlockController::attachLinks)
                .toList();
        return ResponseEntity.ok(results);
    }

    @Override
    public ResponseEntity<List<Block>> exportBlocks() {
        return this.getBlocks();
    }

    @Override
    public ResponseEntity<Block> getBlock(final String id) {
        final var block = this.blockInteractor.getBlock(id).convert();
        return ResponseEntity.ok(block);
    }

    @Override
    public ResponseEntity<Block> createBlock(@NonNull final Block block, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var result = this.blockInteractor.createBlock(block.convert(), sessionKey, username).convert();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Block> updateBlock(@NonNull final String blockId, @NonNull final Block block, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var result = this.blockInteractor.updateBlock(blockId, block.convert(), sessionKey, username).convert();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteBlock(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.blockInteractor.deleteBlock(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
