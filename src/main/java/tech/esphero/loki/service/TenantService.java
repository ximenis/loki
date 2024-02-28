package tech.esphero.loki.service;

import tech.esphero.loki.model.Tenant;

import java.util.UUID;

public interface TenantService {

    Tenant getById(UUID id);

    Tenant getByName(String name);

}
