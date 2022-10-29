package br.com.activesoft.cadastro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import br.com.activesoft.cadastro.models.Usuario;
import br.com.activesoft.cadastro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("users")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/user")
    public ResponseEntity createUser(String nome) {

        repository.save(new Usuario(nome));

        return ResponseEntity.ok("Adicionado com sucesso");
    }

    @PutMapping("/user")
    public ResponseEntity updateUser(@RequestBody Usuario usuario) {

        if (Objects.isNull(usuario.getId()))
            ResponseEntity.badRequest().body("O Id do usuário não pode ser nulo!");

        Optional<Usuario> usuarioOp = this.repository.findById(usuario.getId());
        if (usuarioOp.isPresent()) {

            Usuario userUpdated = usuarioOp.get();
            userUpdated.setNome(usuario.getNome());

            this.repository.save(userUpdated);

            return ResponseEntity.ok(userUpdated);
        } else {
           return ResponseEntity.badRequest().body("Usuario nao encontrado");
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity delete(Long id) {

        if (Objects.isNull(id))
            ResponseEntity.badRequest().body("O Id do usuário não pode ser nulo!");

        Optional<Usuario> usuarioOp = this.repository.findById(id);
        if (usuarioOp.isPresent()) {

            this.repository.delete(usuarioOp.get());

            return ResponseEntity.ok("Usuario "+ usuarioOp.get().getNome() +" deletado com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Usuario nao encontrado");
        }

    }


}
