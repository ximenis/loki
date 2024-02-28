package tech.esphero.loki.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.esphero.loki.model.Tenant;
import tech.esphero.loki.repository.TenantRepository;
import tech.esphero.loki.service.TenantService;

import java.util.Optional;
import java.util.UUID;

import static tech.esphero.loki.exceptions.ExceptionEnum.*;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private final TenantRepository repository;

    @Override
    public Tenant getById(UUID id) {
        Optional<Tenant> optionalTenant = repository.findById(id);
        return optionalTenant.get();
    }

    @Override
    @Cacheable(cacheNames = "tenant", key = "#name")
    public Tenant getByName(String name) {
        Optional<Tenant> optionalClient = repository.findFirstByName(name);
        checkThrow(optionalClient.isEmpty(), TENANT_NOT_FOUND, name);
        Tenant t = optionalClient.get();
        return t;
    }

}
