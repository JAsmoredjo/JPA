package sr.unasat.jpa.app;

import sr.unasat.jpa.config.JPAConfiguration;
import sr.unasat.jpa.dao.AddressDAO;
import sr.unasat.jpa.dao.CityDAO;
import sr.unasat.jpa.dao.EmployeeDAO;
import sr.unasat.jpa.dao.McDonaldsDAO;
import sr.unasat.jpa.entities.Address;
import sr.unasat.jpa.entities.City;
import sr.unasat.jpa.entities.Employee;
import sr.unasat.jpa.entities.McDonalds;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.System.out;

public class Application {
    public static void main(String[] args) {
        CityDAO cityDAO = new CityDAO(JPAConfiguration.getEntityManager());
        AddressDAO addressDAO = new AddressDAO((JPAConfiguration.getEntityManager()));
        EmployeeDAO employeeDAO = new EmployeeDAO(JPAConfiguration.getEntityManager());
        McDonaldsDAO mcDonaldsDAO = new McDonaldsDAO(JPAConfiguration.getEntityManager());

        List<City> cityList = cityDAO.selectAllCity();
        List<Address> addressList = addressDAO.selectAllAddress();
        List<Employee> employeeList = employeeDAO.selectAllEmployee();
        List<McDonalds> mcDonaldsList = mcDonaldsDAO.selectAllMcDonalds();

        out.println("\n\nCurrent Table\n");
        cityList.forEach(System.out::println);
        addressList.forEach(System.out::println);
        employeeList.forEach(System.out::println);
        mcDonaldsList.forEach(System.out::println);

        City city = cityDAO.selectCity("c_n1");
        Address address = addressDAO.selectAddress("a_n1");
        Employee employee = employeeDAO.selectEmployee("e_c1");
        McDonalds mcDonalds = mcDonaldsDAO.selectMcDonalds("m_c1");

        city.setName("new");
        city.setDescription("new");
        address.setName("new");
        employee.setCode("new");
        employee.setFirstName("new");
        employee.setLastName("new");
        employee.setGender('v');
        employee.setPhoneNumber("new");
        employee.setEmail("new");
        mcDonalds.setPhoneNumber("new");
        mcDonalds.setCode("new");

        cityDAO.updateCity(city);
        addressDAO.updateAddress(address);
        employeeDAO.updateEmployee(employee);
        mcDonaldsDAO.updateMcDonalds(mcDonalds);

        cityList = cityDAO.selectAllCity();
        addressList = addressDAO.selectAllAddress();
        employeeList = employeeDAO.selectAllEmployee();
        mcDonaldsList = mcDonaldsDAO.selectAllMcDonalds();

        out.println("\n\nUpdate Table\n");
        cityList.forEach(System.out::println);
        addressList.forEach(System.out::println);
        employeeList.forEach(System.out::println);
        mcDonaldsList.forEach(System.out::println);

        mcDonaldsDAO.deleteMcDonalds(mcDonalds);
        cityDAO.deleteCity(city);
        addressDAO.deleteAddress(address);
        employeeDAO.deleteEmployee(employee);

        mcDonaldsList = mcDonaldsDAO.selectAllMcDonalds();
        cityList = cityDAO.selectAllCity();
        addressList = addressDAO.selectAllAddress();
        employeeList = employeeDAO.selectAllEmployee();

        out.println("\n\nDelete Table\n");
        cityList.forEach(System.out::println);
        addressList.forEach(System.out::println);
        employeeList.forEach(System.out::println);
        mcDonaldsList.forEach(System.out::println);

        City city1 = new City();
        city1.setName("c");
        city1.setDescription("c");
        cityDAO.insertCity(city1);

        Address address1 = new Address();
        address1.setName("a");
        addressDAO.insertAddress(address1);

        Employee employee1 = new Employee();
        employee1.setCode("e1");
        employee1.setFirstName("e1");
        employee1.setLastName("e1");
        employee1.setGender('v');
        employee1.setEmail("e1");
        employee1.setPhoneNumber("e1");
        employeeDAO.insertEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setCode("e2");
        employee2.setFirstName("e2");
        employee2.setLastName("e2");
        employee2.setGender('v');
        employee2.setEmail("e2");
        employee2.setPhoneNumber("e2");
        employeeDAO.insertEmployee(employee2);


        McDonalds mcDonalds1 = new McDonalds();
        mcDonalds1.setCode("m");
        mcDonalds1.setPhoneNumber("m");
        mcDonalds1.setCity(city1);

        Address addressNew = addressDAO.selectAddress("a");
        mcDonalds1.setAddress(addressNew);
        Employee employeeNew1 = employeeDAO.selectEmployee("e1");
        Employee employeeNew2 = employeeDAO.selectEmployee("e2");
        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add(employeeNew1);
        employeeSet.add(employeeNew2);
        mcDonalds1.setEmployees(employeeSet);
        mcDonaldsDAO.insertMcDonalds(mcDonalds1);

        mcDonaldsList = mcDonaldsDAO.selectAllMcDonalds();
        cityList = cityDAO.selectAllCity();
        addressList = addressDAO.selectAllAddress();
        employeeList = employeeDAO.selectAllEmployee();

        out.println("\n\nInsert Table\n");
        cityList.forEach(System.out::println);
        addressList.forEach(System.out::println);
        employeeList.forEach(System.out::println);
        mcDonaldsList.forEach(System.out::println);
        JPAConfiguration.shutdown();
    }
}
