package ua.mai.zine.jpa.aa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AA_PSWD_HIST")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PswdHist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PSWD_HIST_ID")
    private Long pswdHistId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "PASSWORD", length = 32, nullable = false)
    private String password;

    @Column(name = "CREATED_DT", nullable = false)
    private LocalDateTime createdDt;
}
