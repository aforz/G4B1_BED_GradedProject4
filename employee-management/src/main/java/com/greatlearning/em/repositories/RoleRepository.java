package com.greatlearning.em.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.em.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String name);

}
