package com.cleciosouza.todolist.user;
/*
 * Modificador
 * public
 * private
 * protected
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;
    /*
     * String (texto)
     * Integer (int) números inteiros
     * Double (double) números 0.0000
     * Float (float) números 0.0000
     * char (A C)
     * Date (data)
     * void
    */
    /*
     * Body
    */
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        // System.out.println(userModel.getUsername());
        var user = this.userRepository.findByUsername(userModel.getUsername());
        if(user != null) {            
            // Mensagem de erro
            // Status code
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body:"Usuário já esxiste");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já esxiste");
        }

        var passwordHashred = BCrypt.withDefaults()
        .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}
