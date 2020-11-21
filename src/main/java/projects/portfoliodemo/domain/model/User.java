package projects.portfoliodemo.domain.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "username")
//overwrites annotations in @Data
@ToString(exclude = "password")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;
    private Boolean active = Boolean.FALSE;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "username",
                    referencedColumnName = "username"),
            indexes = @Index(
                    name = "users_roles_username_idx",
                    columnList = "username"))
    @Column(name = "role")
    private Set<String> roles;

}
