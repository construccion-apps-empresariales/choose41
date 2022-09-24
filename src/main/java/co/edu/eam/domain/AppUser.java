package co.edu.eam.domain;

import co.edu.eam.dao.RolDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "users")
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campo no puede estar vacio")
    @Size(min = 2, max = 30, message = "Debe ingresar minimo 2 letras y maximo 30")
    private String username;

    @NotNull(message = "El campo no puede estar vacio")
    @Size(min = 3, message = "Debe ingresar minimo 3 caracteres")
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol = getDefaultRol();

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    private Rol getDefaultRol(){
        Rol rol = new Rol();
        rol.setId(2);
        return rol;
    }

    private String encodePassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public void setPassword(String password) {
        this.password = encodePassword(password);
    }
}