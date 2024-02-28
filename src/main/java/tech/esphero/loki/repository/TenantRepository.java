package tech.esphero.loki.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.esphero.loki.model.Tenant;

import java.util.Optional;
import java.util.UUID;

public interface TenantRepository extends JpaRepository<Tenant, UUID> {

    Page<Tenant> findAll(Pageable pageable);

    Optional<Tenant> findFirstByName(String name);

}
