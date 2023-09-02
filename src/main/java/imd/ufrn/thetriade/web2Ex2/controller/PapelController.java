package imd.ufrn.thetriade.web2Ex2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.thetriade.web2Ex2.model.Papel;
import imd.ufrn.thetriade.web2Ex2.repository.PapelRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/papel")
public class PapelController {

    @Autowired
    private PapelRepository papelRepository;

    @GetMapping("{idPapel}")
    public Papel findPapelById(@PathVariable Long idPapel) {
        return papelRepository.findById(idPapel).get();
    }

    @GetMapping
    public List<Papel> getAllPapels() {
        return papelRepository.findAll();
    }

    @PostMapping
    public Papel createPapel(@RequestBody @Valid Papel papel) {
        return papelRepository.save(papel);
    }
}