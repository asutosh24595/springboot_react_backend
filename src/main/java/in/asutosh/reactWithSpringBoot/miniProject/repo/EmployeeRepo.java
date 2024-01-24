package in.asutosh.reactWithSpringBoot.miniProject.repo;

import in.asutosh.reactWithSpringBoot.miniProject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    public boolean existsByEmpId(Long id);
    public Employee findByEmpId(Long id);
    public boolean existsByEmail(String email);
}
