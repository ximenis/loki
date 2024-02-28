package tech.esphero.loki.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.esphero.loki.model.Credential;
import tech.esphero.loki.model.Tenant;
import tech.esphero.loki.service.TenantService;
import tech.esphero.loki.service.ValidationService;
import tech.esphero.loki.service.dto.ValidateApiKeyRequest;

import java.util.Set;
import java.util.stream.Collectors;

import static tech.esphero.loki.exceptions.ExceptionEnum.UNAUTHORIZED_API_KEY;
import static tech.esphero.loki.exceptions.ExceptionEnum.checkThrow;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    private final TenantService service;

    @Override
    public void validateApiKey(ValidateApiKeyRequest request) {

        Tenant tenant = service.getByName(request.getTenant());

        checkThrow(!isApiKeyAuthorized(tenant.getCredentials(), request.getApiKey()), UNAUTHORIZED_API_KEY);

    }

    private boolean isApiKeyAuthorized(Set<Credential> apiKeys, String tokenApiKey) {
        return apiKeys.stream()
                .map(Credential::getApiKey)
                .collect(Collectors.toSet())
                .contains(tokenApiKey);
    }

}
