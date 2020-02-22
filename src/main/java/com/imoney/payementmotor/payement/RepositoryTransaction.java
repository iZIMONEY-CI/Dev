package com.imoney.payementmotor.payement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryTransaction extends JpaRepository<Transaction,Long> {

}
