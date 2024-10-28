package com.es.stockcontrol.controller.api;

import com.es.stockcontrol.model.RespuestaHTTP;
import com.es.stockcontrol.model.User;

/**
 * The interface User controller api.
 */
public interface UserControllerAPI {

    /**
     * Login respuesta http.
     *
     * @param userInput the user input
     * @param passInput the pass input
     * @return the respuesta http
     */
    RespuestaHTTP<User> login(String userInput, String passInput);
}
