package tech.esphero.loki.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.esphero.loki.service.ValidationService;
import tech.esphero.loki.service.dto.ValidateApiKeyRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-key")
public class ApiKeyResource {

    private final ValidationService service;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/validate")
    public void validateApiKey(@Valid @RequestBody ValidateApiKeyRequest request) {
        service.validateApiKey(request);
    }

}
