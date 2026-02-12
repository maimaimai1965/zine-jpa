package ua.mai.zine.jpa.aa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AA_TRANS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANS_ID")
    private Long transId;

    @Column(name = "TRANS_NAME", length = 200)
    private String transName;

    @Column(name = "NOTE", length = 2000)
    private String note;

    @Column(name = "CREATED_DT", nullable = false)
    private LocalDateTime createdDt;
}
