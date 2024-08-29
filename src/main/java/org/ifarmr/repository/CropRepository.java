package org.ifarmr.repository;

import org.ifarmr.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CropRepository extends JpaRepository<Crop, Long> {
    Optional<Crop> findByCropName(String name);
}
