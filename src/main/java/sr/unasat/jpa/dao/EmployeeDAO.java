package sr.unasat.jpa.dao;

import sr.unasat.jpa.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeDAO {
    private EntityManager entityManager;

    public EmployeeDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Employee> selectAllEmployee() {
        entityManager.getTransaction().begin();
        String jpql = "select e from Employee e";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        List<Employee> employeeList = query.getResultList();
        entityManager.getTransaction().commit();
        return employeeList;
    }


    public Employee selectEmployee(String code) {
        entityManager.getTransaction().begin();
        String jpql = "select e from Employee e where e.code = :code";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        query.setParameter("code", code);
        Employee employee = query.getSingleResult();
        entityManager.getTransaction().commit();
        return employee;
    }

    public void insertEmployee(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }

    public int updateEmployee(Employee employee) {
        entityManager.getTransaction().begin();
        String jpql = "update Employee e set e.code = :code, e.firstName = :firstName, e.lastName = :lastName, e.gender = :gender, e.phoneNumber = :phoneNumber, e.email = :email where e.id = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", employee.getId());
        query.setParameter("code", employee.getCode());
        query.setParameter("firstName", employee.getFirstName());
        query.setParameter("lastName", employee.getLastName());
        query.setParameter("gender", employee.getGender());
        query.setParameter("phoneNumber", employee.getPhoneNumber());
        query.setParameter("email", employee.getEmail());
        int updated = query.executeUpdate();
        entityManager.getTransaction().commit();
        return updated;
    }

    public int deleteEmployee(Employee employee) {
        entityManager.getTransaction().begin();
        String jpql = "delete from Employee e where e.code = :code";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("code", employee.getCode());
        int deleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        return deleted;
    }
}
