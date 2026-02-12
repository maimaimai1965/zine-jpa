package ua.mai.zine.jpa.aa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "AA_GROUP_ROLE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupRole {

    @EmbeddedId
    private GroupRoleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("groupId")
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "ROLE_ID")
    private Role role;
}
