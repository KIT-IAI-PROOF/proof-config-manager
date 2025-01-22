package com.smateso.proof.adapter.repositories;

import edu.kit.iai.webis.proofmodels.SourceHandle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceHandleRepository extends JpaRepository<SourceHandle, String>, JpaSpecificationExecutor<SourceHandle> {

    List<SourceHandle> findSourceHandlesByBlockId(@NonNull final String blockId);

}
