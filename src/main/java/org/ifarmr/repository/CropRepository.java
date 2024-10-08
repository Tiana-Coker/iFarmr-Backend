package org.ifarmr.repository;

import org.ifarmr.entity.Crop;
import org.ifarmr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CropRepository extends JpaRepository<Crop, Long> {

    Optional<Crop> findByCropName(String name);

    List<Crop> findByUser_Username(String username);

    int countByUser(User user);

    List<Crop> findByUserIdOrderBySowDateDesc(Long userId);

    // Fetch all crops by user ID
    List<Crop> findByUserId(Long userId);

    // Count total crops by user ID
    @Query("SELECT COUNT(c) FROM Crop c WHERE c.user.id = :userId")
    Long countTotalCropsByUserId(Long userId);

    // Count crops by user ID and status 'Mature'
    @Query("SELECT COUNT(c) FROM Crop c WHERE c.user.id = :userId AND c.status = 'Mature'")
    Long countTotalMatureCropsByUserId(Long userId);

    // Count crops by user ID and status 'Flowering'
    @Query("SELECT COUNT(c) FROM Crop c WHERE c.user.id = :userId AND c.status = 'Flowering'")
    Long countTotalFloweringCropsByUserId(Long userId);

}
