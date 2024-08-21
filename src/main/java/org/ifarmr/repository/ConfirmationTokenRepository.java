package org.ifarmr.repository;

import org.ifarmr.entity.ConfirmationTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenModel, Long> {

    Optional<ConfirmationTokenModel> findByToken(String token);
}
