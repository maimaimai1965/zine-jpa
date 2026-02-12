package ua.mai.zine.jpa.aa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AA_USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "UNAME", length = 64, nullable = false)
    private String uname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFILE_ID", nullable = false)
    private Profile profile;

    @Column(name = "USER_TYPE", length = 1, nullable = false)
    private String userType;

    @Column(name = "PASSWORD", length = 32)
    private String password;

    @Column(name = "STATE", length = 1, nullable = false)
    private String state;

    @Column(name = "AUTH_TYPE", length = 1, nullable = false)
    private String authType;

    @Column(name = "SHORT_NAME", length = 32, nullable = false)
    private String shortName;

    @Column(name = "FULL_NAME", length = 64)
    private String fullName;

    @Column(name = "SMS_NO", length = 16)
    private String smsNo;

    @Column(name = "EMAIL", length = 128)
    private String email;

    @Column(name = "CREATED_DT", nullable = false)
    private LocalDateTime createdDt;

    @Column(name = "UPDATED_DT")
    private LocalDateTime updatedDt;

    @Column(name = "LAST_PSWD_DT", nullable = false)
    private LocalDateTime lastPswdDt;

    @Column(name = "EXPIRATION_DT")
    private LocalDateTime expirationDt;

    @Column(name = "LOCKED_DT")
    private LocalDateTime lockedDt;

    @Column(name = "LOGIN_ATTEMP", nullable = false)
    private Integer loginAttemp;

    @Column(name = "NOTE", length = 2000)
    private String note;
}
