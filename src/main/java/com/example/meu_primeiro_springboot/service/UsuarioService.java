package com.example.meu_primeiro_springboot.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.meu_primeiro_springboot.model.Usuario;
import com.example.meu_primeiro_springboot.repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Usuario registrarUsuario(String username, String password){
        String senhaCriptografada = passwordEncoder.encode(password);
        Usuario usuario = new Usuario(username, senhaCriptografada);
        return usuariosRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorUsername(String username){
        return usuariosRepository.findByUsername(username);
    }
}
