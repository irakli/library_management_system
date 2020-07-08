package ge.mycompany.lms.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "enabled")
    @ColumnDefault(value = "true")
    @JsonIgnore
    private Boolean enabled;

    @Transient
    @JsonIgnore
    private String confirmPassword;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "user_authorities",
            joinColumns = { @JoinColumn(name = "user_id",referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id",referencedColumnName = "id") }
    )
    @JsonManagedReference
    @JsonIgnore
    private List<Authority> authorities;





}