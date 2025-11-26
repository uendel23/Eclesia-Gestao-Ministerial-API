package com.eclesia.gestao_ministerial.service;

import com.eclesia.gestao_ministerial.exception.ImagemException;
import com.eclesia.gestao_ministerial.model.Imagem;
import com.eclesia.gestao_ministerial.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;
import java.util.UUID;

@Service
public class ImagemService {

    @Autowired
    ImagemRepository imagemRepository;

//    public Imagem salvar(String base64) {
//        if (base64 == null || base64.isBlank()) return null;
//
//        Imagem imagem = new Imagem();
//        imagem.setBase64(base64);
//        return imagemRepository.save(imagem);
//    }

    public Imagem salvarBase64(String arquivoBase64) throws IOException {
        byte[] bytes = Base64.getDecoder().decode(arquivoBase64);

        if (bytes.length > 2 * 1024 * 1024) {
            throw new ImagemException("Arquivo maior que 2MB não permitido");
        }

        // LER IMAGEM
        BufferedImage original = ImageIO.read(new ByteArrayInputStream(bytes));

        // REDIMENSIONAR
        int novaLargura = 800;
        int novaAltura = (original.getHeight() * novaLargura) / original.getWidth();

        Image scaled = original.getScaledInstance(novaLargura, novaAltura, Image.SCALE_SMOOTH);

        BufferedImage redimensionada = new BufferedImage(novaLargura, novaAltura, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = redimensionada.createGraphics();
        g2d.drawImage(scaled, 0, 0, null);
        g2d.dispose();

        // CONVERTER PARA JPG COM COMPRESSÃO
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();

        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.8f);

        writer.setOutput(ImageIO.createImageOutputStream(baos));
        writer.write(null, new IIOImage(redimensionada, null, null), param);
        writer.dispose();

        String base64Final = Base64.getEncoder().encodeToString(baos.toByteArray());

        Imagem img = new Imagem();
        img.setBase64(base64Final);

        return imagemRepository.save(img);
    }



    public Imagem buscar(UUID id) {
        return imagemRepository.findById(id).orElse(null);
    }
}
