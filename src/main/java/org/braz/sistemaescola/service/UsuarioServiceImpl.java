package org.braz.sistemaescola.service;

import org.braz.sistemaescola.entities.Usuario;
import org.braz.sistemaescola.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            Usuario usuario = usuarioRepository.findByEmail(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuário inválido!");
        }

        List<SimpleGrantedAuthority> roles = List.of(new SimpleGrantedAuthority(usuario.getDiscriminatorValue()));
        return new User(usuario.getEmail(), usuario.getPassword(), roles);
    }

}
