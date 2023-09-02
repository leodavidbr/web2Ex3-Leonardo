package imd.ufrn.thetriade.web2Ex2.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa extends AbstractEntity{
    
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @NotEmpty
    private String nome;
    
    @OneToMany(mappedBy="pessoa")
    List<Usuario> usuarios;

    public void addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        usuario.setPessoa(this);
    }
 
    public void removeUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
        // TODO: talvez deletar o Usuario? Analisar
        usuario.setPessoa(null);
    }
}
