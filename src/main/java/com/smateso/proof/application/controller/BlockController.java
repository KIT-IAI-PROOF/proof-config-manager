package com.smateso.proof.application.controller;

import com.smateso.proof.adapter.boundaries.controller.IBlockController;
import com.smateso.proof.adapter.boundaries.controller.IWorkflowController;
import com.smateso.proof.adapter.boundaries.services.IBlockInteractor;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.Block;
import edu.kit.iai.webis.proofmodels.SourceHandle;
import edu.kit.iai.webis.proofmodels.TargetHandle;
import edu.kit.iai.webis.proofmodels.dto.BlockDto;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.smateso.proof.adapter.config.AuditorProvider.getUsername;
import static com.smateso.proof.adapter.utils.NullAwareUtil.getNullPropertyNames;
import static java.util.Objects.*;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilderDslKt.withRel;

public class BlockController implements IBlockController {

    private final IBlockInteractor blockInteractor;

    public BlockController(@NonNull final IBlockInteractor blockInteractor) {
        this.blockInteractor = blockInteractor;
    }

    @Override
    public ResponseEntity<List<Block>> searchBlocks(@NonNull final Request request) {
        final var result = this.blockInteractor.searchBlocks(request);
        return ResponseEntity.ok(result.stream().toList());
    }

    @Override
    public ResponseEntity<List<Block>> getBlocks() {
        return ResponseEntity.ok(this.blockInteractor.getBlocks());
    }

    @Override
    public ResponseEntity<Block> getBlock(final String id) {
        return ResponseEntity.ok(this.blockInteractor.getBlock(id));
    }

    @Override
    public ResponseEntity<Block> createBlock(@NonNull final Block block, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(this.blockInteractor.createBlock(block, sessionKey, username));
    }

    @Override
    public ResponseEntity<Block> updateBlock(@NonNull final String blockId, @NonNull final Block block, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        return ResponseEntity.ok(this.blockInteractor.updateBlock(blockId, block, sessionKey, username));
    }

    @Override
    public ResponseEntity<Void> deleteBlock(@NonNull final String id, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final var username = getUsername(authentication);
        this.blockInteractor.deleteBlock(id, sessionKey, username);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<SourceHandle>> getSourceHandlesByBlock(@NonNull final String id) {
        return ResponseEntity.ok(this.blockInteractor.getSourceHandles(id));
    }

    @Override
    public ResponseEntity<List<TargetHandle>> getTargetHandlesByBlock(@NonNull final String id) {
        return ResponseEntity.ok(this.blockInteractor.getTargetHandles(id));

    }

}
