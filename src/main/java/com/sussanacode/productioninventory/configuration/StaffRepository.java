package com.sussanacode.productioninventory.configuration;

import com.sussanacode.productioninventory.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    @Query("SELECT s FROM Staff s WHERE s.email = ?1")
    Staff findByEmail(String email);

}
