package imd.ufrn.thetriade.web2Ex2.controller;

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

import imd.ufrn.thetriade.web2Ex2.model.Papel;
import imd.ufrn.thetriade.web2Ex2.model.Usuario;
import imd.ufrn.thetriade.web2Ex2.service.PapelService;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/papel")
public class PapelController {

    @Autowired
    private PapelService papelService;

    @GetMapping
    public ResponseEntity<List<Papel>> getAllPapeis() {
        List<Papel> papeis = papelService.findAll();

        if (papeis.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(papeis, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Papel> getPapelById(@PathVariable Long id) {
        Papel papel = papelService.findPapelById(id);

        return new ResponseEntity<>(papel, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}/papeis")
    public ResponseEntity<List<Papel>> getPapeisByUsuarioId(
            @PathVariable Long usuarioId) {
        List<Papel> papeis = papelService.findPapeisByUsuarioId(usuarioId);

        return new ResponseEntity<>(papeis, HttpStatus.OK);
    }

    @GetMapping("/papel/{papelId}/usuarios")
    public ResponseEntity<List<Usuario>> getUsuariosByPapelId(
            @PathVariable Long papelId) {
        var usuarios = papelService.findUsuariosByPapelId(papelId);

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Papel> createPapel(@RequestBody @Valid Papel papel) {
        Papel papelCreated = papelService.createPapel(papel);

        return new ResponseEntity<>(papelCreated, HttpStatus.CREATED);
    }

    @PostMapping("/usuario/{usuarioId}/papel")
    public ResponseEntity<Papel> addPapelToUsuario(
            @PathVariable(value = "usuarioId") Long usuarioId,
            @RequestBody @Valid Papel papelRequest) {
        Papel papel = papelService.addPapelToUsuario(usuarioId, papelRequest);

        return new ResponseEntity<>(papel, HttpStatus.CREATED);
    }

    @PutMapping("/papeis/{id}")
    public ResponseEntity<Papel> updatePapel(@PathVariable("id") Long id,
            @RequestBody Papel papelRequest) {
        Papel papel = papelService.updatePapel(id, papelRequest);

        return new ResponseEntity<>(papel, HttpStatus.OK);
    }

    @DeleteMapping("/usuarios/{usuarioId}/papeis/{papelId}")
    public ResponseEntity<HttpStatus> deletePapelFromUsuario(
            @PathVariable(value = "usuarioId") Long usuarioId,
            @PathVariable(value = "papelId") Long papelId) {
        papelService.deletePapelFromUsuario(usuarioId, papelId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/papeis/{id}")
    public ResponseEntity<HttpStatus> deletePapel(@PathVariable("id") Long id) {
        papelService.deletePapelById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}