package org.ifarmr.repository;

import org.ifarmr.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepository extends JpaRepository<Crop, Long> {
}
