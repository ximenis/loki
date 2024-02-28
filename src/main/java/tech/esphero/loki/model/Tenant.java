package tech.esphero.loki.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_tenant")
public class Tenant implements Serializable {

    @Serial
    private static final long serialVersionUID = 3909900948917188964L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    @Fetch(FetchMode.JOIN)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "tenant_id")
    private Set<Credential> credentials;

}
