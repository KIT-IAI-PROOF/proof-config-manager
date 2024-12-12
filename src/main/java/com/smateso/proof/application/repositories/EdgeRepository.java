package com.smateso.proof.application.repositories;

import edu.kit.iai.webis.proofmodels.Edge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EdgeRepository extends JpaRepository<Edge, String>, JpaSpecificationExecutor<Edge> {
}
