package imd.ufrn.thetriade.web2Ex2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imd.ufrn.thetriade.web2Ex2.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
    List<Papel> findPapelByUsuariosId(Long usuarioId);
}
