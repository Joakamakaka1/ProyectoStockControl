package com.es.stockcontrol.controller.impl;


import com.es.stockcontrol.controller.api.UserControllerAPI;
import com.es.stockcontrol.model.RespuestaHTTP;
import com.es.stockcontrol.model.User;
import com.es.stockcontrol.repository.UserRepository;

public class UserController implements UserControllerAPI {

    private UserRepository UserRepository;

    public UserController(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    @Override
    public RespuestaHTTP<User> login(String userInput, String passInput) {
        if (userInput.isEmpty() || passInput.isEmpty()) {
            return new RespuestaHTTP<User>(400, "El usuario o la contraseña estan vacios", null);
        }

        User u = UserRepository.buscarPorId(userInput);

        if (u == null) {
            return new RespuestaHTTP<User>(404, "El usuario no existe", null);
        } else if (!u.getPassword().equals(passInput)) {
            return new RespuestaHTTP<User>(401, "La contraseña es incorrecta", null);
        } else {
            return new RespuestaHTTP<User>(200, "Login correcto", u);
        }
    }
}
