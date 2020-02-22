package com.imoney.payementmotor.payactor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryActor extends JpaRepository<Actor,Long> {
     Actor findByCode(String code);

}
