package imd.ufrn.thetriade.web2Ex2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import imd.ufrn.thetriade.web2Ex2.model.Papel;

@Controller
public class PapelMVCController {
    @GetMapping("/novopapel")
    public String greetingForm(Model model) {
        model.addAttribute("papel", new Papel());
        return "form";
    }

    @PostMapping("/novopapel")
    public String greetingSubmit(@ModelAttribute Papel papel, Model model) {

        model.addAttribute("papel", papel);
        return "result";
    }
}
