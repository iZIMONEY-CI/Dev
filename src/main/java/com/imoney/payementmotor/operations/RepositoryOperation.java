package com.imoney.payementmotor.operations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryOperation extends JpaRepository<Operation,Long> {
}
