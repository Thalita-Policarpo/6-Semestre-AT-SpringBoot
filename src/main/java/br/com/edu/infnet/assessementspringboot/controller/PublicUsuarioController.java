package br.com.edu.infnet.assessementspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicUsuarioController {

    @GetMapping("/oimundo")
    public String oimundo() {
        return "Oi mundo publico";
    }
}
