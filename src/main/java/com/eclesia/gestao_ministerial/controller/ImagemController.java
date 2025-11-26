package com.eclesia.gestao_ministerial.controller;
import com.eclesia.gestao_ministerial.model.Imagem;
import com.eclesia.gestao_ministerial.service.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.util.UUID;

@Controller
public class ImagemController {

    @Autowired
    private ImagemService imagemService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImagem(@RequestParam("arquivo") String arquivo) {

        try{
            Imagem img = imagemService.salvarBase64(arquivo);
            return ResponseEntity.ok("Imagem salva com ID " + img.getId());
        } catch (Exception e){
            return ResponseEntity.status(500).body("Error ao salvar a imagem");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> downloadImagem(@PathVariable UUID id) {
        Imagem img = imagemService.buscar(id);

        if (img == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(img.getBase64());
    }

}



