package com.recrale.accesos.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.recrale.accesos.entidades.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}
