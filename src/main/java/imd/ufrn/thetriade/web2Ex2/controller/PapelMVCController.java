package imd.ufrn.thetriade.web2Ex2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import imd.ufrn.thetriade.web2Ex2.model.Papel;
import imd.ufrn.thetriade.web2Ex2.service.PapelService;
import jakarta.validation.Valid;

// @Controller
public class PapelMVCController {
    @Autowired
    private PapelService papelService;

    @GetMapping("/papel/novopapel")
    public String greetingForm(Model model) {
        model.addAttribute("papel", new Papel());
        return "createPapelForm";
    }

    @PostMapping("/papel")
    public String greetingSubmit(@ModelAttribute @Valid Papel papel,
            Model model) {
        Papel papelCreated = papelService.createPapel(papel);

        model.addAttribute("papel", papelCreated);
        return "seePapel";
    }
}
