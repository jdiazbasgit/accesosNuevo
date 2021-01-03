package com.recrale.accesos.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.recrale.accesos.entidades.User;

public interface UsuarioRepository extends CrudRepository<User, Integer> {

}
