package ua.mai.zine.jpa.aa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "AA_GROUP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    private Long groupId;

    @Column(name = "GROUP_CD", length = 32, nullable = false)
    private String groupCd;

    @Column(name = "GROUP_NAME", length = 64)
    private String groupName;

    @Column(name = "NOTE", length = 2000)
    private String note;
}
