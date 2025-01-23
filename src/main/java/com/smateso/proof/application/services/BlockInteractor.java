package com.smateso.proof.application.services;

import com.smateso.proof.adapter.boundaries.services.IBlockInteractor;
import com.smateso.proof.adapter.boundaries.services.IMessageInteractor;
import com.smateso.proof.adapter.exceptions.NotFoundException;
import com.smateso.proof.adapter.model.Request;
import com.smateso.proof.adapter.repositories.BlockRepository;
import com.smateso.proof.adapter.repositories.SourceHandleRepository;
import com.smateso.proof.adapter.repositories.TargetHandleRepository;
import edu.kit.iai.webis.proofmodels.dao.BlockDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.smateso.proof.adapter.model.Action.*;
import static com.smateso.proof.adapter.utils.NullAwareUtil.getNullPropertyNames;
import static com.smateso.proof.application.utils.CriteriaBuilderHelper.getPath;
import static java.util.Objects.requireNonNull;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;

public class BlockInteractor implements IBlockInteractor {

    private final TargetHandleRepository targetHandleRepository;
    private final SourceHandleRepository sourceHandleRepository;
    private final BlockRepository blockRepository;
    private final IMessageInteractor messageInteractor;

    public BlockInteractor(@NonNull final TargetHandleRepository targetHandleRepository, @NonNull final SourceHandleRepository sourceHandleRepository, @NonNull final BlockRepository blockRepository, @NonNull final IMessageInteractor messageInteractor) {
        this.targetHandleRepository = targetHandleRepository;
        this.sourceHandleRepository = sourceHandleRepository;
        this.blockRepository = blockRepository;
        this.messageInteractor = messageInteractor;
    }

    @Override
    public Page<BlockDao> searchBlocks(@NonNull final Request request) {
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
        return this.blockRepository.findAll((final Root<BlockDao> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            final var filter = request.getFilter();
            if (filter != null) {
                final var items = filter.getItems();
                items.forEach((final Request.FilterItem item) -> predicates.add(builder.equal(getPath(root, item.getField()), item.getValue())));
            }
            return builder.and(predicates.toArray(new Predicate[]{}));
        }, pageable);
    }

    @Override
    public BlockDao getBlock(@NonNull final String id) {
        final var result = this.blockRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("BlockDao with id %s not found".formatted(id));
        }
    }

    @Override
    public BlockDao createBlock(@NonNull final BlockDao blockDao, @NonNull final String sessionKey, @NonNull final String username) {
        final var result = this.blockRepository.save(blockDao);
        this.messageInteractor.blockUpdate(requireNonNull(result.getId()), CREATED, sessionKey, username);
        return result;
    }

    @Override
    public BlockDao updateBlock(@NonNull final String id, @NonNull final BlockDao blockDao, @NonNull final String sessionKey, @NonNull final String username) {
        final var existingBlock = this.getBlock(id);
        copyProperties(blockDao, existingBlock, getNullPropertyNames(blockDao));
        existingBlock.setWorkflow(existingBlock.getWorkflow());
        existingBlock.setTargetHandles(existingBlock.getTargetHandles());
        existingBlock.setSourceHandles(existingBlock.getSourceHandles());
        final var result = this.blockRepository.save(existingBlock);
        this.messageInteractor.blockUpdate(requireNonNull(result.getId()), UPDATED, sessionKey, username);
        return result;
    }

    @Override
    public void deleteBlock(@NonNull final String id, @NonNull final String sessionKey, @NonNull final String username) {
        this.blockRepository.deleteById(id);
        this.messageInteractor.blockUpdate(id, DELETED, sessionKey, username);
    }

    @Override
    public List<BlockDao> getBlocks() {
        return this.blockRepository.findAll();
    }

}
