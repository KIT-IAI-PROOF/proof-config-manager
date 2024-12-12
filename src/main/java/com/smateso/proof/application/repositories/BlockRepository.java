package com.smateso.proof.application.repositories;

import edu.kit.iai.webis.proofmodels.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<Block, String>, JpaSpecificationExecutor<Block> {
}
