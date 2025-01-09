package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IBlockInteractor;
import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.BlockRepository;
import edu.kit.iai.webis.proofmodels.Block;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.smateso.proof.adapter.model.Action.*;
import static com.smateso.proof.adapter.utils.NullAwareUtil.getNullPropertyNames;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;

public class BlockInteractor implements IBlockInteractor {

    private final BlockRepository blockRepository;
    private final com.smateso.proof.adapter.boundaries.services.IMessageInteractor IMessageInteractor;

    public BlockInteractor(@NonNull final BlockRepository blockRepository, @NonNull final IMessageInteractor IMessageInteractor) {
        this.blockRepository = blockRepository;
        this.IMessageInteractor = IMessageInteractor;
    }

    @Override
    public Page<Block> searchBlocks(@NonNull final Request request) {
        final Pageable pageable;
        if (request.getPagination() != null) {
            if (request.getSort() != null) {
                final var sort = by(request.getSort().stream().map((final Request.Sort requestSort) -> new Sort.Order(Sort.Direction.fromString(requestSort.getSort()), requestSort.getField())).toList());
                pageable = of(request.getPagination().getPage(), request.getPagination().getPageSize(), sort);
            } else {
                pageable = of(request.getPagination().getPage(), request.getPagination().getPageSize());
            }
        } else {
            pageable = Pageable.unpaged();
        }
        return this.blockRepository.findAll((final Root<Block> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            final var filter = request.getFilter();
            if (filter != null) {
                final var items = filter.getItems();
                items.forEach((final Request.FilterItem item) -> predicates.add(builder.equal(root.get(item.getField()), item.getValue())));
            }
            return builder.and(predicates.toArray(new Predicate[]{}));
        }, pageable);
    }

    @Override
    public Block getBlock(@NonNull final String id) {
        final var result = this.blockRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("Block with id %s not found".formatted(id));
        }
    }

    @Override
    public Block createBlock(@NonNull final Block block, @NonNull final String sessionKey, @NonNull final String username) {
        final var result = this.blockRepository.save(block);
        this.IMessageInteractor.blockUpdate(result.getId(), CREATED, sessionKey, username);
        return result;
    }

    @Override
    public Block updateBlock(@NonNull final String id, @NonNull final Block block, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingBlock = this.getBlock(id);
        copyProperties(block, existingBlock, getNullPropertyNames(block));
        final var result = this.blockRepository.save(existingBlock);
        this.IMessageInteractor.blockUpdate(result.getId(), UPDATED, sessionKey, username);
        return result;
    }

    @Override
    public void deleteBlock(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.blockRepository.deleteById(id);
        this.IMessageInteractor.blockUpdate(id, DELETED, sessionKey, username);
    }

    @Override
    public List<Block> getBlocks() {
        return this.blockRepository.findAll();
    }

}
