package ua.mai.zine.jpa.aa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "AA_IP_ADDR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IpAddr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IP_ADDR_ID")
    private Long ipAddrId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "ADDR_ACTION", length = 1, nullable = false)
    private String addrAction;

    @Column(name = "ADDRESS", length = 32, nullable = false)
    private String address;

    @Column(name = "NOTE", length = 2000)
    private String note;
}
