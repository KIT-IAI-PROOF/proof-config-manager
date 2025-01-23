package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IBlockController;
import com.smateso.proof.adapter.boundaries.services.IBlockInteractor;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.application.utils.PropertyCopyHelper;
import edu.kit.iai.webis.proofmodels.dto.BlockDto;
import edu.kit.iai.webis.proofmodels.dto.SourceHandleDto;
import edu.kit.iai.webis.proofmodels.dto.TargetHandleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.application.utils.PropertyCopyHelper.copyBlockProperties;

public class BlockController implements IBlockController {

    private final IBlockInteractor blockInteractor;

    public BlockController(@NonNull final IBlockInteractor blockInteractor) {
        this.blockInteractor = blockInteractor;
    }

    @Override
    public ResponseEntity<List<BlockDto>> searchBlocks(@NonNull final Request request) {
        final var result = this.blockInteractor.searchBlocks(request).stream().map(PropertyCopyHelper::copyBlockProperties);
        return ResponseEntity.ok(result.toList());
    }

    @Override
    public ResponseEntity<List<BlockDto>> getBlocks() {
        final var blocks = this.blockInteractor.getBlocks();
        final var result = blocks.stream().map(PropertyCopyHelper::copyBlockProperties).toList();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<BlockDto> getBlock(final String id) {
        return ResponseEntity.ok(copyBlockProperties(this.blockInteractor.getBlock(id)));
    }

    @Override
    public ResponseEntity<BlockDto> createBlock(@NonNull final BlockDto block, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(copyBlockProperties(this.blockInteractor.createBlock(copyBlockProperties(block), sessionKey, username)));
    }

    @Override
    public ResponseEntity<BlockDto> updateBlock(@NonNull final String blockId, @NonNull final BlockDto block, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(copyBlockProperties(this.blockInteractor.updateBlock(blockId, copyBlockProperties(block), sessionKey, username)));
    }

    @Override
    public ResponseEntity<Void> deleteBlock(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.blockInteractor.deleteBlock(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<SourceHandleDto>> getSourceHandlesByBlock(@NonNull final String id) {
        return ResponseEntity.ok(this.blockInteractor.getSourceHandles(id).stream().map(PropertyCopyHelper::copySourceHandleProperties).toList());
    }

    @Override
    public ResponseEntity<List<TargetHandleDto>> getTargetHandlesByBlock(@NonNull final String id) {
        return ResponseEntity.ok(this.blockInteractor.getTargetHandles(id).stream().map(PropertyCopyHelper::copyTargetHandleProperties).toList());

    }

}
