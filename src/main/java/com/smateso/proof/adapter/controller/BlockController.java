package com.smateso.proof.adapter.controller;

import com.smateso.proof.application.model.Block;
import com.smateso.proof.application.repositories.BlockRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/block")
public class BlockController implements IBlockController {

    private final BlockRepository blockRepository;

    public BlockController(final BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    @Override
    public ResponseEntity<List<Block>> getBlocks() {
        return ResponseEntity.ok(blockRepository.findAll());
    }

}
