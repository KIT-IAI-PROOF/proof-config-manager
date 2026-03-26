/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IBlockInteractor;
import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.exceptions.AlreadyExistsException;
import com.smateso.proof.adapter.exceptions.InUseException;
import com.smateso.proof.adapter.exceptions.MismatchException;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.BlockRepository;
import edu.kit.iai.webis.proofmodels.dao.BlockDao;
import edu.kit.iai.webis.proofmodels.dto.Block;
import edu.kit.iai.webis.proofmodels.mapper.BlockMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;

import static com.smateso.proof.adapter.model.Action.*;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createPageable;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.createSpecification;
import static java.util.Objects.requireNonNull;

@Slf4j
@AllArgsConstructor
public class BlockInteractor implements IBlockInteractor {

    private static final String ENTITY_BLOCK = "blocks";
    private final IMessageInteractor messageInteractor;
    private final BlockRepository blockRepository;
    private final BlockMapper blockMapper;

    @Override
    public Page<Block> searchBlocks(@NonNull final Request request) {
        final Specification<BlockDao> specification = createSpecification(request);
        final var pageable = createPageable(request);
        final var result = this.blockRepository.findAll(specification, pageable);
        return result.map(this.blockMapper::convert);
    }

    @Override
    public List<Block> getBlocks() {
        final var result = this.blockRepository.findAll();
        return this.blockMapper.convert(result);
    }

    @Override
    public Block getBlock(@NonNull final String id) {
        final var result = this.blockRepository.findById(id);
        if (result.isPresent()) return this.blockMapper.convert(result.get());
        else throw new NotFoundException("Block with id %s not found".formatted(id));
    }

    @Override
    public Block createBlock(@NonNull final Block block, @Nullable final String sessionKey, @NonNull final String username) {
        if (Objects.nonNull(block.getId()) && !blockRepository.existsById(block.getId())) {
            final var result = this.blockRepository.save(this.blockMapper.convert(block));
            this.messageInteractor.sendUpdate(ENTITY_BLOCK, requireNonNull(result.getId()), CREATED, sessionKey, username);
            return this.blockMapper.convert(result);
        } else {
            throw new AlreadyExistsException("Block with id %s already exists".formatted(block.getId()));
        }
    }

    @Override
    public Block updateBlock(@NonNull final String id, @NonNull final Block update, @Nullable final String sessionKey, @NonNull final String username) {
        if (!Objects.equals(id, update.getId())) {
            throw new MismatchException("ID mismatch: Path ID (%s) does not match Request ID (%s)".formatted(id, update.getId()));
        }
        if (this.blockRepository.existsById(id)) {
            final var result = this.blockRepository.save(this.blockMapper.convert(update));
            this.messageInteractor.sendUpdate(ENTITY_BLOCK, requireNonNull(result.getId()), UPDATED, sessionKey, username);
            return this.blockMapper.convert(result);
        } else {
            throw new NotFoundException("Block with id %s not found".formatted(id));
        }
    }

    @Override
    public void deleteBlock(@NonNull final String id, @Nullable final String sessionKey, @NonNull final String username) {
        try {
            this.blockRepository.deleteById(id);
            this.messageInteractor.sendUpdate(ENTITY_BLOCK, id, DELETED, sessionKey, username);
        } catch (final DataIntegrityViolationException e) {
            throw new InUseException("Block with id %s is still in use by another entity".formatted(id));
        }
    }

}
