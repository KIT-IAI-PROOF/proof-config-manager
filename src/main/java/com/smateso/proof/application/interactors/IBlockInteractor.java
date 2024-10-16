package com.smateso.proof.application.interactors;

import com.smateso.proof.application.model.Block;

import java.util.List;

public interface IBlockInteractor {

    List<Block> getBlocks();

    Block getBlock(String id);

    Block createBlock(Block block);

    Block updateBlock(String id, Block block);

    void deleteBlock(String id);

}
