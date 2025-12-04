package com.eclesia.gestao_ministerial.repository;

import com.eclesia.gestao_ministerial.enums.StatusMembro;
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
            AND (:status IS NULL OR m.status = :status)           
           """)
    List<Membro> filtrar(@Param("cargo") String cargo, @Param("ministerio") String ministerio, @Param("status") StatusMembro status);

   @Query("""
           SELECT CASE WHEN COUNT(m) > 0 THEN TRUE  ELSE FALSE END
           FROM  Membro m\s
           WHERE m.cpf = :cpf OR m.email = :email OR m.telefone = :telefone
          \s""")
    boolean ExistisDuplicado(@Param("cpf") String cpf, @Param("email") String email, @Param("telefone")String telefone );

   @Query("""
           SELECT m from  Membro m\s
           WHERE lOWER(m.nomeCompleto) LIKE LOWER(CONCAT( '%', :nome, '%'))
          \s""")
    List<Membro> buscarPorNome(@Param("nome") String nome);
}
