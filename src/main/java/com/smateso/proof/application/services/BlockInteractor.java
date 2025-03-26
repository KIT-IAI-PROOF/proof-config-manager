package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IBlockInteractor;
import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.BlockRepository;
import edu.kit.iai.webis.proofmodels.dao.BlockDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.List;

import static com.smateso.proof.adapter.model.Action.*;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
import static com.smateso.proof.application.utils.LogMessages.BLOCK_DAO_NOT_FOUND_MESSAGE;
import static com.smateso.proof.application.utils.PropertyCopyHelper.relationalCopyEntityProperties;
import static java.util.Objects.requireNonNull;

@Slf4j
@Builder
@AllArgsConstructor
public class BlockInteractor implements IBlockInteractor {

    private final IMessageInteractor messageInteractor;
    private final BlockRepository blockRepository;

    @Override
    public Page<BlockDao> searchBlocks(@NonNull final Request request) {
        final Specification<BlockDao> specification = createSpecification(request);
        final var pageable = createPageable(request);
        return this.blockRepository.findAll(specification, pageable);
    }

    @Override
    public List<BlockDao> getBlocks() {
        return this.blockRepository.findAll();
    }

    @Override
    public BlockDao getBlock(@NonNull final String id) {
        final var result = this.blockRepository.findById(id);
        if (result.isPresent()) return result.get();
        else throw new NotFoundException(BLOCK_DAO_NOT_FOUND_MESSAGE.formatted(id));
    }

    @Override
    public BlockDao createBlock(@NonNull final BlockDao blockDao, @NonNull final String sessionKey, @NonNull final String username) {
        final var result = this.blockRepository.save(blockDao);
        this.messageInteractor.blockUpdate(requireNonNull(result.getId()), CREATED, sessionKey, username);
        return result;
    }

    @Override
    public BlockDao updateBlock(@NonNull final String id, @NonNull final BlockDao updatingBlock, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingBlock = this.getBlock(id);
        final var result = this.blockRepository.save(relationalCopyEntityProperties(updatingBlock, existingBlock, true, List.of()));
        this.messageInteractor.blockUpdate(requireNonNull(result.getId()), UPDATED, sessionKey, username);
        return result;
    }

    @Override
    public void deleteBlock(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.blockRepository.deleteById(id);
        this.messageInteractor.blockUpdate(id, DELETED, sessionKey, username);
    }

}
