package in.asutosh.reactWithSpringBoot.miniProject.service;

import in.asutosh.reactWithSpringBoot.miniProject.entity.Employee;
import in.asutosh.reactWithSpringBoot.miniProject.repo.EmployeeRepo;
import in.asutosh.reactWithSpringBoot.miniProject.request.EmpRequest;
import in.asutosh.reactWithSpringBoot.miniProject.response.EmpResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    private EmployeeRepo repo;
    @Autowired
    public EmpServiceImpl(EmployeeRepo repo){
        this.repo = repo;
    }
    @Override
    public EmpResponse saveOrUpdateEmp(EmpRequest request) {

        EmpResponse response = new EmpResponse();
        Long id = request.getEmpId();
        boolean ifExistsById = repo.existsByEmpId(id);
        boolean ifExistsByEmail = repo.existsByEmail(request.getEmail());
        if(!ifExistsById && !ifExistsByEmail ){
            Employee employee = new Employee();
            BeanUtils.copyProperties(request,employee);
            repo.save(employee);
            response.setSuccMsg("Employee account created successfully");
        }else if(!ifExistsById && ifExistsByEmail){
            response.setErrMsg("Account already exists with same email");
        }else if(ifExistsById){
            Employee emp = repo.findByEmpId(id);
            BeanUtils.copyProperties(request,emp);
            repo.save(emp);
            response.setSuccMsg("Employee account updated successfully");
        }else{
            response.setErrMsg("Failed. There was an error!");
        }
        return response;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> list = repo.findAll();
        return list;
    }

    @Override
    public Employee getEmployee(Long id) {
        Employee byEmpId = repo.findByEmpId(id);
        return byEmpId;
    }

    @Override
    public boolean deleteEmp(Long id) {
        boolean exists = repo.existsByEmpId(id);
        if(exists){
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
