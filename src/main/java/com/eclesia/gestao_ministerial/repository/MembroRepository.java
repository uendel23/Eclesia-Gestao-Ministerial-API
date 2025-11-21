package com.eclesia.gestao_ministerial.repository;

import com.eclesia.gestao_ministerial.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MembroRepository extends JpaRepository<Membro, UUID> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByTelefone(String telefone);


   @Query("""
           SELECT m\s
            FROM Membro m
            WHERE (:cargo IS NULL OR LOWER(m.cargo) LIKE %:cargo%)
            AND (:ministerio IS NULL OR LOWER(m.ministerio) LIKE %:ministerio%)
            AND (:ativo IS NULL OR m.ativo = :ativo)
           """)
    List<Membro> filtrar(@Param("cargo") String cargo, @Param("ministerio") String ministerio, @Param("ativo") Boolean ativo);
}
