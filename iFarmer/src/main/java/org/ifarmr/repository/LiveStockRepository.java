package org.ifarmr.repository;

import org.ifarmr.entity.LiveStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiveStockRepository extends JpaRepository<LiveStock, Long> {
}
