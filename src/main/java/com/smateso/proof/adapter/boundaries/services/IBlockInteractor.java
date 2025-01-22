package com.smateso.proof.adapter.boundaries.services;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.Block;
import edu.kit.iai.webis.proofmodels.SourceHandle;
import edu.kit.iai.webis.proofmodels.TargetHandle;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.List;

public interface IBlockInteractor {

    Page<Block> searchBlocks(@NonNull final Request request);

    Block getBlock(@NonNull final String id);

    Block createBlock(@NonNull final Block block, @NonNull final String sessionKey, @NonNull final String username);

    Block updateBlock(@NonNull final String id, @NonNull final Block block, @NonNull final String sessionKey, @NonNull final String username);

    void deleteBlock(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username);

    List<Block> getBlocks();

    List<SourceHandle> getSourceHandles(@NonNull final String id);

    List<TargetHandle> getTargetHandles(@NonNull final String id);
}
