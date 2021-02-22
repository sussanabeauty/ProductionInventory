package com.sussanacode.productioninventory.configuration;

import com.sussanacode.productioninventory.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {


}
