package ua.mai.zine.jpa.aa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "AA_PROFILE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROFILE_ID")
    private Long profileId;

    @Column(name = "PROFILE_CD", length = 32, nullable = false)
    private String profileCd;

    @Column(name = "PSWD_LIFE_TIME", nullable = false)
    private Integer pswdLifeTime;

    @Column(name = "PSWD_GRACE_TIME", nullable = false)
    private Integer pswdGraceTime;

    @Column(name = "PSWD_REUSE_TIME", nullable = false)
    private Integer pswdReuseTime;

    @Column(name = "PSWD_REUSE_MAX", nullable = false)
    private Integer pswdReuseMax;

    @Column(name = "LOGIN_ATTEMP_MAX", nullable = false)
    private Integer loginAttempMax;

    @Column(name = "PSWD_LOCK_TIME", nullable = false)
    private Integer pswdLockTime;

    @Column(name = "PSWD_LEN_MIN", nullable = false)
    private Integer pswdLenMin;

    @Column(name = "PSWD_ALPHANUM", length = 1, nullable = false)
    private String pswdAlphanum;

    @Column(name = "PSWD_CASE", length = 1, nullable = false)
    private String pswdCase;
}
