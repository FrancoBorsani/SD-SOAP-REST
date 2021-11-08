package com.helpdesk.security.repository;

import com.helpdesk.security.model.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
    //User findByUsername(String username);
}
