package com.smateso.proof.adapter.repositories;

import edu.kit.iai.webis.proofmodels.dao.SourceHandleDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceHandleRepository extends JpaRepository<SourceHandleDao, String>, JpaSpecificationExecutor<SourceHandleDao> {

    List<SourceHandleDao> findSourceHandlesByBlockId(@NonNull final String blockId);

}
