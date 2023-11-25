package Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

}
