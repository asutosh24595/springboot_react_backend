package in.asutosh.reactWithSpringBoot.miniProject.controller;

import in.asutosh.reactWithSpringBoot.miniProject.entity.Employee;
import in.asutosh.reactWithSpringBoot.miniProject.request.EmpRequest;
import in.asutosh.reactWithSpringBoot.miniProject.response.EmpResponse;
import in.asutosh.reactWithSpringBoot.miniProject.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class EmpController {
    private EmpService service;
    @Autowired
    public EmpController(EmpService service){
        this.service = service;
    }

    @PostMapping("/create-employee")
    public ResponseEntity<EmpResponse> upsert(@RequestBody EmpRequest request){
        EmpResponse empResponse = service.saveOrUpdateEmp(request);
        if(empResponse.getSuccMsg()!=null){
            return new ResponseEntity<>(empResponse, HttpStatus.OK);
        }else if(empResponse.getErrMsg().contains("Account already exists with same email")){
            System.out.println("Error Response: " + empResponse.getErrMsg());
            return new ResponseEntity<>(empResponse, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(empResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> list = service.getAllEmployees();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/edit-employee/{id}")
    public ResponseEntity<Employee> updateEmp(@PathVariable Long id){
        Employee emp = service.getEmployee(id);
        return new ResponseEntity<>(emp,HttpStatus.OK);
    }


    @DeleteMapping("/employee/{id}")
    public ResponseEntity<EmpResponse> deleteEmp(@PathVariable Long id){
        boolean isDeleted = service.deleteEmp(id);
        EmpResponse response = new EmpResponse();
        if(isDeleted){
            response.setSuccMsg("Account with Employee ID: "+ id + " deleted successfully");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            response.setErrMsg("Error occurred while deleting the account");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
}
