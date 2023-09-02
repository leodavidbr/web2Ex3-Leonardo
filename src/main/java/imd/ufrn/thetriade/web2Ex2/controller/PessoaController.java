package imd.ufrn.thetriade.web2Ex2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.thetriade.web2Ex2.model.Pessoa;
import imd.ufrn.thetriade.web2Ex2.repository.PessoaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("{idPessoa}")
    public Pessoa findPessoaById(@PathVariable Long idPessoa) {
        return pessoaRepository.findById(idPessoa).get();
    }

    @GetMapping
    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }

    @PostMapping
    public Pessoa createPessoa(@RequestBody @Valid Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @GetMapping("/comecaCom/{comecoNome}")
    public List<Pessoa> getAllPessoasNomeStartsWith(@PathVariable String comecoNome) {
        return pessoaRepository.findByNomeStartsWithIgnoreCase(comecoNome);
    }

}
