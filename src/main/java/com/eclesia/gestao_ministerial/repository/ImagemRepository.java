package com.eclesia.gestao_ministerial.repository;

import com.eclesia.gestao_ministerial.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImagemRepository extends JpaRepository<Imagem, UUID> {
}
