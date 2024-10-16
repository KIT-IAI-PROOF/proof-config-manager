package com.smateso.proof.application.repositories;

import com.smateso.proof.application.model.Handle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandleRepository extends JpaRepository<Handle, String> {
}
