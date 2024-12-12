package com.smateso.proof.application.interactors;

import com.smateso.proof.application.model.Request;
import com.smateso.proof.application.repositories.BlockRepository;
import edu.kit.iai.webis.proofmodels.Block;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;

public class BlockInteractor implements IBlockInteractor {

    private final BlockRepository blockRepository;

    public BlockInteractor(final BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    @Override
    public Page<Block> getBlocks(final Request request) {
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
            final var items = filter.getItems();
            items.forEach((final Request.FilterItem item) -> predicates.add(builder.equal(root.get(item.getField()), item.getValue())));
            return builder.and(predicates.toArray(new Predicate[]{}));
        }, pageable);
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
        block.setId(existingBlock.getId());
        return this.blockRepository.save(block);
    }

    @Override
    public void deleteBlock(final String uuid) {
        this.blockRepository.deleteById(uuid);
    }

}
