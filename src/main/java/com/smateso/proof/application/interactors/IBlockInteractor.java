package com.smateso.proof.application.interactors;

import com.smateso.proof.application.model.Request;
import edu.kit.iai.webis.proofmodels.Block;
import org.springframework.data.domain.Page;

public interface IBlockInteractor {

    Page<Block> getBlocks(final Request request);

    Block getBlock(String id);

    Block createBlock(Block block);

    Block updateBlock(String id, Block block);

    void deleteBlock(String id);

}
