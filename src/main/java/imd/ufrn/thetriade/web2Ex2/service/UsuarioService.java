package imd.ufrn.thetriade.web2Ex2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import imd.ufrn.thetriade.web2Ex2.exception.ResourceNotFoundException;
import imd.ufrn.thetriade.web2Ex2.model.Usuario;
import imd.ufrn.thetriade.web2Ex2.repository.PessoaRepository;
import imd.ufrn.thetriade.web2Ex2.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarioRepository.findAll().forEach(usuarios::add);

        return usuarios;
    }

    public Usuario findUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario with id = " + id + " not found"));
    }


    public Usuario findByEmailAndSenha(String email, String senha){
        return usuarioRepository.findByEmailAndSenha(email, senha)
                .orElseThrow(() -> new ResourceNotFoundException(
                      "Usuario ou Senha errados"));
    }

    public Usuario createUsuario(Usuario usuario) {
        usuario.setId(null);
        Long pessoaId = usuario.getPessoa().getId();

        usuario.setPessoa(pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pessoa with id = " + pessoaId + " not found")));

        return usuarioRepository.save(usuario);
    }

    public Usuario updateById(Long id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Usuario with id " + id + " was not found");
        }

        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuarioById(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

    public void deleteAllUsuarios() {
        usuarioRepository.deleteAll();
    }

    public boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }

}
