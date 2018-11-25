/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.integration.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.process.integration.shipping.domain.ShippingBill;

@Repository
public interface ShippingRepository extends JpaRepository<ShippingBill, Long> { }
