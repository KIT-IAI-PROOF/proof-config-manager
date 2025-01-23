package com.smateso.proof.adapter.repositories;

import edu.kit.iai.webis.proofmodels.dao.TargetHandleDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetHandleRepository extends JpaRepository<TargetHandleDao, String>, JpaSpecificationExecutor<TargetHandleDao> {

    List<TargetHandleDao> findTargetHandlesByBlockId(@NonNull final String blockId);

}
