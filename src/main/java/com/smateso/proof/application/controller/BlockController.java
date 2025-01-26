package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IBlockController;
import com.smateso.proof.adapter.boundaries.services.IBlockInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dao.BlockDao;
import edu.kit.iai.webis.proofmodels.dto.BlockDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.copyEntityProperties;

@Builder
@AllArgsConstructor
public class BlockController implements IBlockController {

    private final IBlockInteractor blockInteractor;

    @Override
    public ResponseEntity<List<BlockDto>> searchBlocks(@NonNull final Request request) {
        final var blocks = this.blockInteractor.searchBlocks(request);
        final var result = blocks.stream().map((final var block) -> copyEntityProperties(block, new BlockDto(), false, List.of())).toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<BlockDto>> getBlocks() {
        final var blocks = this.blockInteractor.getBlocks();
        final var result = blocks.stream().map((final var block) -> copyEntityProperties(block, new BlockDto(), false, List.of())).toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<BlockDto> getBlock(final String id) {
        final var block = this.blockInteractor.getBlock(id);
        final var result = copyEntityProperties(block, new BlockDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<BlockDto> createBlock(@NonNull final BlockDto update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var block = this.blockInteractor.createBlock(copyEntityProperties(update, new BlockDao(), false, List.of()), sessionKey, username);
        final var result = copyEntityProperties(block, new BlockDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<BlockDto> updateBlock(@NonNull final String blockId, @NonNull final BlockDto update, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        final var block = this.blockInteractor.updateBlock(blockId, copyEntityProperties(update, new BlockDao(), false, List.of()), sessionKey, username);
        final var result = copyEntityProperties(block, new BlockDto(), false, List.of());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteBlock(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.blockInteractor.deleteBlock(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

}
