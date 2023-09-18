package imd.ufrn.thetriade.web2Ex2.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;

    @NotBlank
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinTable(name = "usuario_tem_papeis", joinColumns = {
            @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
                    @JoinColumn(name = "papel_id") })
    private Set<Papel> papeis = new HashSet<>();

    public void addPapel(Papel papel) {
        this.papeis.add(papel);
        papel.getUsuarios().add(this);
    }

    public void removePapel(long papelId) {
        Papel papel = this.papeis.stream().filter(p -> p.getId() == papelId)
                .findFirst().orElse(null);
        if (papel != null) {
            this.papeis.remove(papel);
            papel.getUsuarios().remove(this);
        }
    }
}
