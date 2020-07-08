package ge.mycompany.lms.usermanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ge.mycompany.lms.usermanagement.entities.Authority;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "username should not be empty")
    private String userName;

    @Column(name = "password")
    @JsonIgnore
    @NotBlank(message = "password should not be empty")
    @Size(min = 6,message = "size must be more than 6 chars")
    private String password;

    @Column(name = "enabled")
    @ColumnDefault(value = "true")
    @JsonIgnore
    private Boolean enabled;

    @Transient
    @JsonIgnore
    @NotBlank(message = "confirm password should not be empty")
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