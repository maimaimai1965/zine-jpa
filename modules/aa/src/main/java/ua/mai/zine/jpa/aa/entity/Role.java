package ua.mai.zine.jpa.aa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "AA_ROLE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "ROLE_CD", length = 32, nullable = false)
    private String roleCd;

    @Column(name = "ROLE_NAME", length = 128, nullable = false)
    private String roleName;

    @Column(name = "ROLE_TYPE", length = 1, nullable = false)
    private String roleType;

    @Column(name = "NOTE", length = 2000)
    private String note;
}
