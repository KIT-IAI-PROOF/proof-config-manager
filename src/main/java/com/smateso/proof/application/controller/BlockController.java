package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IBlockController;
import com.smateso.proof.adapter.boundaries.services.IBlockInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.BlockDao;
import edu.kit.iai.webis.proofmodels.dto.Block;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.relationalCopyEntityProperties;
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
    public ResponseEntity<List<Block>> searchBlocks(@NonNull final Request request) {
        final var blocks = this.blockInteractor.searchBlocks(request);
        final var result = blocks
                .stream()
                .map((final var block) -> relationalCopyEntityProperties(block, new Block(), false, List.of()))
                .map(BlockController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Block>> getBlocks() {
        final var blocks = this.blockInteractor.getBlocks();
        final var result = blocks
                .stream()
                .map((final var block) -> relationalCopyEntityProperties(block, new Block(), false, List.of()))
                .map(BlockController::attachLinks)
                .toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Block>> exportBlocks() {
        return this.getBlocks();
    }

    @Override
    public ResponseEntity<Block> getBlock(final String id) {
        final var block = this.blockInteractor.getBlock(id);
        final var result = relationalCopyEntityProperties(block, new Block(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Block> createBlock(@NonNull final Block update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var block = this.blockInteractor.createBlock(relationalCopyEntityProperties(update, new BlockDao(), false, List.of()), sessionKey, username);
        final var result = relationalCopyEntityProperties(block, new Block(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Block> updateBlock(@NonNull final String blockId, @NonNull final Block update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var block = this.blockInteractor.updateBlock(blockId, relationalCopyEntityProperties(update, new BlockDao(), false, List.of()), sessionKey, username);
        final var result = relationalCopyEntityProperties(block, new Block(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteBlock(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.blockInteractor.deleteBlock(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
