package tech.esphero.loki.service;

import tech.esphero.loki.service.dto.ValidateApiKeyRequest;

public interface ValidationService {

    void validateApiKey(ValidateApiKeyRequest request);

}
