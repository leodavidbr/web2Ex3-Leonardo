package imd.ufrn.thetriade.web2Ex2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.thetriade.web2Ex2.exception.ResourceNotFoundException;
import imd.ufrn.thetriade.web2Ex2.model.Papel;
import imd.ufrn.thetriade.web2Ex2.model.Usuario;
import imd.ufrn.thetriade.web2Ex2.repository.PapelRepository;
import imd.ufrn.thetriade.web2Ex2.repository.UsuarioRepository;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/papel")
public class PapelController {

    @Autowired
    private PapelRepository papelRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Papel>> getAllPapeis() {
        List<Papel> papeis = new ArrayList<>();

        papelRepository.findAll().forEach(papeis::add);

        if (papeis.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(papeis, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Papel> getPapelById(@PathVariable Long id) {
        Papel papel = papelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Could not find papel with Id " + id));

        return new ResponseEntity<>(papel, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}/papeis")
    public ResponseEntity<List<Papel>> getPapeisByUsuarioId(
            @PathVariable Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException(
                    "Could not find usuario with Id " + usuarioId);
        }

        List<Papel> papeis = papelRepository.findPapelByUsuariosId(usuarioId);
        return new ResponseEntity<>(papeis, HttpStatus.OK);
    }

    @GetMapping("/papeis/{papelId}/usuarios")
    public ResponseEntity<List<Usuario>> getUsuariosByPapelId(
            @PathVariable Long papelId) {
        if (!papelRepository.existsById(papelId)) {
            throw new ResourceNotFoundException(
                    "Not found Papel with id = " + papelId);
        }

        List<Usuario> usuarios = usuarioRepository
                .findUsuarioByPapeisId(papelId);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping("/papel")
    public ResponseEntity<Papel> createPapel(@RequestBody @Valid Papel papel) {
        Papel papelCreated = papelRepository.save(papel);

        return new ResponseEntity<>(papelCreated, HttpStatus.CREATED);
    }

    @PostMapping("/usuarios/{usuarioId}/papeis")
    public ResponseEntity<Papel> addPapel(
            @PathVariable(value = "usuarioId") Long usuarioId,
            @RequestBody @Valid Papel papelRequest) {
        Papel papel = usuarioRepository.findById(usuarioId).map(usuario -> {
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

        return new ResponseEntity<>(papel, HttpStatus.CREATED);
    }

    @PutMapping("/papeis/{id}")
    public ResponseEntity<Papel> updatePapel(@PathVariable("id") long id,
            @RequestBody Papel papelRequest) {
        Papel papel = papelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "PapelId " + id + "not found"));

        papel.setNome(papelRequest.getNome());
        papel.setPrioridade(papelRequest.getPrioridade());

        return new ResponseEntity<>(papelRepository.save(papel), HttpStatus.OK);
    }

    @DeleteMapping("/usuarios/{usuarioId}/papeis/{papelId}")
    public ResponseEntity<HttpStatus> deletePapelFromUsuario(
            @PathVariable(value = "usuarioId") Long usuarioId,
            @PathVariable(value = "papelId") Long papelId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found Usuario with id = " + usuarioId));

        usuario.removePapel(papelId);
        usuarioRepository.save(usuario);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/papeis/{id}")
    public ResponseEntity<HttpStatus> deletePapel(@PathVariable("id") long id) {
        papelRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}