package imd.ufrn.thetriade.web2Ex2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import imd.ufrn.thetriade.web2Ex2.exception.ResourceNotFoundException;
import imd.ufrn.thetriade.web2Ex2.model.Papel;
import imd.ufrn.thetriade.web2Ex2.model.Usuario;
import imd.ufrn.thetriade.web2Ex2.repository.PapelRepository;
import imd.ufrn.thetriade.web2Ex2.repository.UsuarioRepository;
import jakarta.validation.Valid;

public class PapelService {
    @Autowired
    private PapelRepository papelRepository;

    @Autowired
    private UsuarioService usuarioService;

    // fix: remover
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Papel> findAll() {
        List<Papel> papeis = new ArrayList<>();
        papelRepository.findAll().forEach(papeis::add);

        return papeis;
    }

    public Papel findPapelById(Long id) {
        return papelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Could not find papel with Id " + id));
    }

    public List<Papel> findPapeisByUsuarioId(Long usuarioId) {
        if (!usuarioService.existsById(usuarioId)) {
            throw new ResourceNotFoundException(
                    "Could not find usuario with Id " + usuarioId);
        }

        return papelRepository.findPapelByUsuariosId(usuarioId);
    }

    public List<Usuario> findUsuariosByPapelId(Long papelId) {
        if (!papelRepository.existsById(papelId)) {
            throw new ResourceNotFoundException(
                    "Not found Papel with id = " + papelId);
        }

        return usuarioRepository.findUsuarioByPapeisId(papelId);
    }

    public Papel createPapel(Papel papel) {
        return papelRepository.save(papel);
    }

    public Papel addPapelToUsuario(Long usuarioId, Papel papelRequest) {
        return usuarioRepository.findById(usuarioId).map(usuario -> {
            long papelId = papelRequest.getId();

            // papel already exists
            if (papelId != 0L) {
                Papel papelRead = papelRepository.findById(papelId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Not found Papel with id = " + papelId));
                usuario.addPapel(papelRead);
                usuarioRepository.save(usuario);
                return papelRead;
            }

            // add and create new Papel
            usuario.addPapel(papelRequest);
            return papelRepository.save(papelRequest);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Not found Usuario with id = " + usuarioId));
    }

    public Papel updatePapel(Long id, Papel papel) {
        if (!papelRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Papel with id " + id + " was not found");
        }

        papel.setId(id);
        return papelRepository.save(papel);
    }

    public void deletePapelFromUsuario(
            @PathVariable(value = "usuarioId") Long usuarioId,
            @PathVariable(value = "papelId") Long papelId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found Usuario with id = " + usuarioId));

        usuario.removePapel(papelId);
        usuarioRepository.save(usuario);

    }

    public void deletePapelById(Long id) {
        papelRepository.deleteById(id);
    }
}
