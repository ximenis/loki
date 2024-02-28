package tech.esphero.loki.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ValidateApiKeyRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -2881184974510472257L;

    @NotNull
    private String apiKey;

    @NotNull
    private String tenant;

}
