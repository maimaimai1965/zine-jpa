package ua.mai.zine.jpa.aa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GroupRoleId implements Serializable {

    @Column(name = "GROUP_ID")
    private Long groupId;

    @Column(name = "ROLE_ID")
    private Long roleId;
}
