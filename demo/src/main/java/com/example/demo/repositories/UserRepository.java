package com.example.demo.repositories;

import java.util.ArrayList;

import com.example.demo.models.UserModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// extends CrudRepository<Tipo de datao, Tipo de identificador (ID)>
// extends CrudRepository<UserModel, Long>

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {

    // Métodos abstractos

    // Método para buscar por prioridad (No se tiene que programar la consulta)
    // priority 5 user premium (Depende de la lógica del negocio)
    public abstract ArrayList<UserModel> findByPriority(Integer priority);

    // Otros métodos

    // findByName
    public abstract ArrayList<UserModel> findByName(String name);

    // findByEmail
    public abstract ArrayList<UserModel> findByEmail(String email);

}
