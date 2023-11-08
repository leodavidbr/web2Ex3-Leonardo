package imd.ufrn.thetriade.web2Ex2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.thetriade.web2Ex2.exception.ResourceNotFoundException;
import imd.ufrn.thetriade.web2Ex2.model.Usuario;
import imd.ufrn.thetriade.web2Ex2.security.SecurityUtils;
import imd.ufrn.thetriade.web2Ex2.service.UsuarioService;

@RestController
public class Authcontroller {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<String> auth(String email, String password) {
        Usuario user;
        try{
            user = usuarioService.findByEmailAndSenha(email, password);
        } 
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password invalid");
        }
        return ResponseEntity.ok(SecurityUtils.buildJwt(user.getEmail()));
    }
}
