package imd.ufrn.thetriade.web2Ex2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import imd.ufrn.thetriade.web2Ex2.model.Papel;
import imd.ufrn.thetriade.web2Ex2.repository.PapelRepository;

public class PapelService {
    @Autowired
    private PapelRepository papelRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Papel> findAll() {
        List<Papel> papeis = new ArrayList<>();
        papelRepository.findAll().forEach(papeis::add);

        return papeis;
    }
}
