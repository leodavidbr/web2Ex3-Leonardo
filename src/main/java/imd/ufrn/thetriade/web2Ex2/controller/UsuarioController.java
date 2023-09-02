package imd.ufrn.thetriade.web2Ex2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.thetriade.web2Ex2.model.Usuario;
import imd.ufrn.thetriade.web2Ex2.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("{idUsuario}")
    public Usuario findUsuarioById(@PathVariable Long idUsuario) {
        return usuarioRepository.findById(idUsuario).get();
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody @Valid Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

}
