package com.helpdesk.security.repository;

import com.helpdesk.security.model.Reclamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamoRepository extends JpaRepository<Reclamo, Long> {
    //User findByUsername(String username);
}
