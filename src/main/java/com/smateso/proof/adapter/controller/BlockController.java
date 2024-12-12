package com.smateso.proof.adapter.controller;

import com.smateso.proof.application.interactors.IBlockInteractor;
import com.smateso.proof.application.model.Request;
import edu.kit.iai.webis.proofmodels.Block;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/block")
public class BlockController implements IBlockController {

    private final IBlockInteractor blockInteractor;

    public BlockController(@Qualifier("blockInteractor") final IBlockInteractor blockInteractor) {
        this.blockInteractor = blockInteractor;
    }

    @Override
    public ResponseEntity<List<Block>> getBlocks(final Request request) {
        final var result = this.blockInteractor.getBlocks(request);
        return ResponseEntity.ok(result.stream().toList());
    }

    @Override
    public ResponseEntity<Block> getBlock(final String id) {
        return ResponseEntity.ok(this.blockInteractor.getBlock(id));
    }

    @Override
    public ResponseEntity<Block> createBlock(final Block block) {
        return ResponseEntity.ok(this.blockInteractor.createBlock(block));
    }

}
