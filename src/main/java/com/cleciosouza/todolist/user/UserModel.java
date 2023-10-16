package com.cleciosouza.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_users")
public class UserModel {

   @Id
   @GeneratedValue(generator = "UUID")
   private UUID id;

   @Column(unique = true)
   private String username;
   // @Getter & @Setter como @Data do lombok, permite utilizar os metodos para os atributos separadamente
   private String name;
   private String password;

   // getters Buscar/pegar informação
   // setters /alterar / atualizar um atributo privado de uma classe ou inserir 
   @CreationTimestamp
   private LocalDateTime createdAt;
}
