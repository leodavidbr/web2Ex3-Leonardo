package imd.ufrn.thetriade.web2Ex2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imd.ufrn.thetriade.web2Ex2.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findUsuarioByPapeisId(Long papelId);
}
