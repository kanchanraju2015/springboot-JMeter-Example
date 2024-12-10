package com.briz.springbootjmeteradvancetest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController
{
	// OPEN BIN FOLDER OF JMETER AND TYPE ->java -jar ApacheJMeter.jar
	// AUTOMATICALLY THERE WILL BE BE TEST PLAN
	// RIGHT CLICK ON TEST PLAN THEN ADD THREAD GROUP THEN PASS NUMBER OF THREAD CAN BE EXECUTED
	// THEN RIGHT CLICK ON THREAD GROUP AND SELECT SAMPLER THEN HTTP REQUEST FILL THE DETAILS AS REQUIRED 
	//paths working :http://localhost:8080:/all,/by/2,del/2,partial/2,/save
	// note the header manager for json data working fine plz check the database then process.
	// also the data must be present for deleting,updating into the database 
	// data will be inserted into the database depends u select the threads all duplicates check .
@Autowired
EmployeeRepository erepo;
@PostMapping("/save")
public String save(@RequestBody Employee employee)
{
	erepo.save(employee);
	return "data saved";
}
@GetMapping("/all")
public List<Employee> all()
{
	return erepo.findAll();
}
@GetMapping("/by/{id}")
public Optional<Employee> byid(@PathVariable int id)
{
	return erepo.findById(id);
}
@PutMapping("/update/{id}")
public Employee upd(@PathVariable int id,@RequestBody Employee employee)
{
Employee emp=erepo.findById(id).get();
emp.setName(employee.getName());
emp.setAge(employee.getAge());
emp.setCity(employee.getCity());
 return erepo.save(emp);
}
@PatchMapping("/partial/{id}")
public Employee partial(@PathVariable int id,@RequestBody Employee employee)
{
Employee emp=erepo.findById(id).get();
emp.setName(employee.getName());
 return erepo.save(emp);
}
@DeleteMapping("/del/{id}")
public String delbyid(@PathVariable int id)
{
	erepo.deleteById(id);
	return "data deleted";
}
}
