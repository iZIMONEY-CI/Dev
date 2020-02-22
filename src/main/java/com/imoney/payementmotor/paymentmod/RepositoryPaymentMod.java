package com.imoney.payementmotor.paymentmod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPaymentMod extends JpaRepository<PaymentMod,Long> {
}
