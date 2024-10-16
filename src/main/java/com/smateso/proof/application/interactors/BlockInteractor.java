package com.smateso.proof.application.interactors;

import com.smateso.proof.application.model.Block;
import com.smateso.proof.application.repositories.BlockRepository;

import java.util.List;

public class BlockInteractor implements IBlockInteractor {

    private final BlockRepository blockRepository;

    public BlockInteractor(final BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    @Override
    public List<Block> getBlocks() {
        return this.blockRepository.findAll();
    }

    @Override
    public Block getBlock(final String id) {
        return this.blockRepository.findById(id).orElseThrow();
    }

    @Override
    public Block createBlock(final Block block) {
        return this.blockRepository.save(block);
    }

    @Override
    public Block updateBlock(String uuid, Block block) {
        final var existingBlock = this.blockRepository.findById(uuid).orElseThrow();
        block.setUuid(existingBlock.getUuid());
        block.setId(existingBlock.getId());
        return this.blockRepository.save(block);
    }

    @Override
    public void deleteBlock(final String uuid) {
        this.blockRepository.deleteById(uuid);
    }

}
