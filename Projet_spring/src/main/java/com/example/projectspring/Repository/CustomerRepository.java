
package com.example.projectspring.Repository;

import com.example.projectspring.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository

public interface CustomerRepository extends JpaRepository<Client, String> {
    @Query(value="SELECT * FROM Client WHERE email= :email", nativeQuery=true)
    Client findByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM Client WHERE email= :email", nativeQuery=true)
    void deleteByEmail(@Param("email") String email);



    @Transactional
    @Modifying
    @Query(value="UPDATE Client SET points_fidelite = :points_fidelite WHERE email= :email", nativeQuery=true)
    void addPoint(@Param("points_fidelite") int points_fidelite, @Param("email") String email);



}
