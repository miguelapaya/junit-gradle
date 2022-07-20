package com.example.junitgradle;

import com.example.junitgradle.entity.Employee;
import com.example.junitgradle.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JunitGradleApplicationTests {

    //Cacl calc = new Cacl();

    @Autowired
    EmployeeRepository employeeRepository;

    /*@Test
    void addition() {

        //given
        int x = 20;
        int y = 30;

        //when
        int res = calc.add(x, y);

        //then
        assertThat(res).isEqualTo(50);
    }

    class Cacl{
        int add(int a, int b){ return a+b;}
    }*/

    @Test
    public void testCreate(){

        Employee emp = new Employee();
        emp.setEmpId(1);
        emp.setEmpName("Miguel");
        emp.setDept("IT");
        employeeRepository.save(emp);
        assertNotNull(employeeRepository.findById(1).get());
    }

    @Test
    public void testReadAll(){
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).size().isGreaterThan(0);
    }

    @Test
    public void testSingleEmployee(){
        Employee emp = employeeRepository.findById(1).get();
        assertEquals("IT", emp.getDept());
    }

    @Test
    public void testUpdateEmployee(){
        Employee emp = employeeRepository.findById(1).get();
        emp.setEmpName("Mikio");
        employeeRepository.save(emp);
        assertNotEquals("Miguel", emp.getEmpName());
    }

    @Test
    public void testDeleteEmployee(){
        employeeRepository.deleteById(1);
        assertThat(employeeRepository.existsById(1)).isFalse();
    }

}
