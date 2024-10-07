package org.ifarmr.repository;

import org.ifarmr.entity.Inventory;
import org.ifarmr.entity.User;
import org.ifarmr.enums.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Boolean existsByNameAndItemType(String name, ItemType itemType);

    int countByUser(User user);

    List<Inventory> findByUserIdOrderByDateAcquiredDesc(Long userId);

    // calculates the total inventory quantity.
    @Query("SELECT SUM(CAST(i.quantity AS long)) FROM Inventory i")
    Long findTotalInventory();

    // calculates the total inventory value
    @Query("SELECT SUM(CAST(i.cost AS long) * CAST(i.quantity AS long)) FROM Inventory i")
    Long findTotalInventoryValue();


    // Returns list of inventory created by a particular user
    List<Inventory> findByUserId(Long userId);

    // calculates the total inventory quantity per user
    @Query("SELECT SUM(CAST(i.quantity AS long)) FROM Inventory i WHERE i.user.id = :userId")
    Long findTotalInventoryByUserId(@Param("userId") Long userId);

    // calculates the total inventory value per user
    @Query("SELECT SUM(CAST(i.cost AS long) * CAST(i.quantity AS long)) FROM Inventory i WHERE i.user.id = :userId")
    Long findTotalInventoryValueByUserId(@Param("userId") Long userId);

}
