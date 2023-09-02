package imd.ufrn.thetriade.web2Ex2.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "papel")
public class Papel {
    
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String nome;
    
    @ManyToMany(mappedBy="papeis")
    private Set<Usuario> usuarios;

    public void addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        usuario.getPapeis().add(this);
    }
 
    public void removeUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
        usuario.getPapeis().remove(this);
    }
}