package com.eclesia.gestao_ministerial.repository;

import com.eclesia.gestao_ministerial.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MembroRepository extends JpaRepository<Membro, UUID> {

    boolean existsByCpf(String cpf);

    boolean exixtsByEmail(String email);

    boolean existsByTelefone(String telefone);
}
