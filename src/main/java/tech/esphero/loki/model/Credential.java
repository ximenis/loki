package tech.esphero.loki.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Where(clause = "deleted_at is null")
@Table(name = "t_credential")
@SQLDelete(sql = "UPDATE t_credential SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
public class Credential extends AbstractModel implements Serializable {

    private static final long serialVersionUID = 8255236451230911152L;

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "application")
    private String application;

    @NotNull
    @Column(name = "owner")
    private String owner;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "api_key")
    private String apiKey;

}