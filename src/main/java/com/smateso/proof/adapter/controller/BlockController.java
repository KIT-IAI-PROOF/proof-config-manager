package com.smateso.proof.adapter.controller;

import com.smateso.proof.application.interactors.IBlockInteractor;
import com.smateso.proof.application.model.Request;
import edu.kit.iai.webis.proofmodels.Block;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/block")
public class BlockController implements IBlockController {

    private final IBlockInteractor blockInteractor;

    public BlockController(@Qualifier("blockInteractor") @NonNull final IBlockInteractor blockInteractor) {
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
        final Jwt jwt = (Jwt) authentication.getPrincipal();
        final var username = jwt.getClaimAsString("preferred_username");
        return ResponseEntity.ok(this.blockInteractor.createBlock(block, sessionKey, username));
    }

    @Override
    public ResponseEntity<Block> updateBlock(@NonNull final String blockId, @NonNull final Block block, @NonNull final String sessionKey, @NonNull final Authentication authentication) {
        final Jwt jwt = (Jwt) authentication.getPrincipal();
        final var username = jwt.getClaimAsString("preferred_username");
        return ResponseEntity.ok(this.blockInteractor.updateBlock(blockId, block, sessionKey, username));
    }

}
