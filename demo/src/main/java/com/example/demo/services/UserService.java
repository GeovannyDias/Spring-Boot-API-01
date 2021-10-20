package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Clase de tipo servicio

@Service
public class UserService {
    // Declarar como una variable
    // @Autowired -> No tengamos que instanciar automatimente spring ya detecta

    @Autowired
    UserRepository userRepository;

    // GET USERS
    public ArrayList<UserModel> getUsers() {
        // (ArrayList<UserModel>) → Casting para retornar un JSON
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    // SAVE USER and UPDATE USER (Detecta si el objeto tiene ID para actulizar)
    // Validar si el usuario existe para que no se guarde al intentar actualizar
    public UserModel postUser(UserModel user) {
        // Save user and return user with its id
        return userRepository.save(user);
    }

    // GET USER BY ID
    // Type Opcional (Si no existe el ID y falla no causa error)
    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // GET USER BY PRIORITY
    public ArrayList<UserModel> getUserByPriority(Integer priority) {
        return userRepository.findByPriority(priority);
    }

    // GET USER BY EMAIL
    public ArrayList<UserModel> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // DELETE USER BY ID
    public boolean deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            // TO DO: handle exception
            System.out.println("ERROR: delete data");
            return false;
        }
    }

}
