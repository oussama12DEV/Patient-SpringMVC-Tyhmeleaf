package com.example.patientmvcv2.web;

import com.example.patientmvcv2.Entiter.patient;
import com.example.patientmvcv2.repository.PatientRepository;

import javax.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor

public class PaientController {
    private PatientRepository patientRepository;

    @GetMapping(path = "/index")
    private String patient(Model module,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<patient> patientPagege = patientRepository.findByNomContains(keyword, PageRequest.of(page, size));
        module.addAttribute("ListePatient", patientPagege.getContent());
        module.addAttribute("page", new int[patientPagege.getTotalPages()]);
        module.addAttribute("Currentpage", page);
        module.addAttribute("keyword", keyword);
        return "patient";

    }

    @GetMapping("/deleete")
    private String delete(int id, int page, String keyword) {
        patientRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }



    @GetMapping("/fourmpatients")
    private String formpatient(Model model) {
        model.addAttribute("patient", new patient());

        return "formpatient";
    }



    @PostMapping("/save")
    private String save(Model model, @Valid patient patient, BindingResult bindingResult,
                        @RequestParam(defaultValue = "") String keyword,
                        @RequestParam(defaultValue = "0") int page) {
        if (bindingResult.hasErrors()) return "formpatient";
        patientRepository.save(patient);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;

    }

    @GetMapping("/edit")
    private String Edit(int id, Model model,
                        @RequestParam(name = "keyword") String keyword,
                        @RequestParam(name = "page") int page) {
        patient pati = patientRepository.findById(id).orElse(null);
        if (pati == null) throw new RuntimeException("patient no trouvalbel");
        model.addAttribute("patient", pati);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        System.out.print("hujuuhh");
        return "editpatient";

    }
}
