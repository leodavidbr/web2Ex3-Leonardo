package imd.ufrn.thetriade.web2Ex2.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @ManyToOne
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;

    @Id
    @GeneratedValue
    private Long idUsuario;

    @ManyToMany
    @JoinTable(name = "usuario_tem_papeis", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
            @JoinColumn(name = "papel_id") })
    private Set<Papel> papeis;

    public void addUsuario(Papel papel) {
        this.papeis.add(papel);
        papel.getUsuarios().add(this);
    }

    public void removeUsuario(Papel papel) {
        this.papeis.remove(papel);
        papel.getUsuarios().remove(this);
    }
}
