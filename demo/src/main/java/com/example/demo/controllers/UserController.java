package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.UserModel;
import com.example.demo.services.UserService;

// Print to CLI
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * -> El controlador recibe la peticion web -> LLama a un servicio (Ejecuta la
 * logica) -> Repositorio hace la conexión con la BD -> El repositorio utiliza
 * el modelo (tipo de informacion que trae)
 */

@RestController
@RequestMapping("/user") // En que direccion del servidor se activa esta clase
public class UserController {

    // (Opcional)
    private static Logger LOG = LoggerFactory.getLogger(UserController.class); // Tipos de mesajes por consola

    // Importamos el servicio con @Autowired para que se instancie automáticamente
    @Autowired
    UserService userService;

    // GET
    @GetMapping()
    public ArrayList<UserModel> getUsers() {
        return userService.getUsers();
    }

    // POST
    @PostMapping()
    public UserModel postUser(@RequestBody UserModel user) {
        // Print CLI
        System.out.println(user.getEmail());
        System.out.println(user.getId());
        LOG.info(user.getEmail());
        // LOG.error("Message error");

        return this.userService.postUser(user);
    }

    // GET -> USER BY ID
    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }

    // URL: http://localhost:8080/user/query?priority=3

    // /query → Agregar mas rutas
    // GET -> USER BY PRIORITY
    @GetMapping("/query")
    public ArrayList<UserModel> getUserByPriority(@RequestParam("priority") Integer priority) {
        return this.userService.getUserByPriority(priority);
    }

    // GET -> USER BY EMAIL (Cambiar de ruta (path))

    // @GetMapping("/query")
    // public ArrayList<UserModel> getUserByEmail(@RequestParam("email") String
    // email) {
    // return this.userService.getUserByEmail(email);
    // }

    // DELETE USER BY ID
    @DeleteMapping(path = "/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        boolean userDeleted = this.userService.deleteUserById(id);
        if (userDeleted) {
            return "Successful! Data deleted: " + id;
        } else {
            return "ERROR: User does not exist or an error occurred, please try again: " + id;
        }
    }

}
