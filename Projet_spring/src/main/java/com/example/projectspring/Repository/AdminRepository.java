
package com.example.projectspring.Repository;

import com.example.projectspring.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository

public interface AdminRepository extends JpaRepository<Admin, String> {
    @Query(value="SELECT * FROM Admin WHERE email= :email", nativeQuery=true)
    Admin findByEmail(@Param("email") String email);

}
