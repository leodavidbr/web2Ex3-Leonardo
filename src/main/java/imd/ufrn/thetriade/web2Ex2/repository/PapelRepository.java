package imd.ufrn.thetriade.web2Ex2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import imd.ufrn.thetriade.web2Ex2.model.Papel;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {
}
