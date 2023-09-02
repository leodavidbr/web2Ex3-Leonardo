package imd.ufrn.thetriade.web2Ex2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imd.ufrn.thetriade.web2Ex2.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    public List<Pessoa> findByNomeStartsWithIgnoreCase(String comecoNome);
}
