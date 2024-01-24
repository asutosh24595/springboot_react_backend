package in.asutosh.reactWithSpringBoot.miniProject.service;

import in.asutosh.reactWithSpringBoot.miniProject.entity.Employee;
import in.asutosh.reactWithSpringBoot.miniProject.request.EmpRequest;
import in.asutosh.reactWithSpringBoot.miniProject.response.EmpResponse;

import java.util.List;

public interface EmpService {

    public EmpResponse saveOrUpdateEmp(EmpRequest request);
    public List<Employee> getAllEmployees();

    public Employee getEmployee(Long id);
    public boolean deleteEmp(Long id);
}
