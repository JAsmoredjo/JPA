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

import java.util.List;

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
        out.println("\n\n");

        Address address1 = new Address();
        address1.setName("a1");
        boolean add1 = false;
        Address address2 = new Address();
        address2.setName("a2");

        List<McDonalds> mcDonaldsList1 = mcDonaldsDAO.selectAllMcDonalds();
        for (McDonalds mcDonalds : mcDonaldsList1) {
            if (!add1) {
                add1 = true;
                mcDonalds.setAddress(address1);
            } else {
                mcDonalds.setAddress(address2);
            }
        }
        addressDAO.insertAddress(mcDonaldsList1);


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

        McDonalds mcDonalds1 = mcDonaldsDAO.selectMcDonalds("m_c1");
        employee2.addMcDonalds(mcDonalds1);
        employeeDAO.insertEmployee(mcDonalds1);


        McDonalds mcDonalds2 = new McDonalds();
        mcDonalds2.setPhoneNumber("m");
        mcDonalds2.setCode("m");
        City city1 = cityDAO.selectCity("c_n1");
        mcDonalds2.setCity(city1);
        Address address3 = addressDAO.selectAddress("a_n2");
        mcDonalds2.setAddress(address3);
        Employee employee3 = employeeDAO.selectEmployee("e_c1");
        employee3.addMcDonalds(mcDonalds2);
        employeeDAO.updateEmployee(employee3);


        McDonalds mcDonalds3 = mcDonaldsDAO.selectMcDonalds("m_c2");
        employee3.addMcDonalds(mcDonalds3);
        employeeDAO.updateEmployee(employee3);


        cityList = cityDAO.selectAllCity();
        addressList = addressDAO.selectAllAddress();
        employeeList = employeeDAO.selectAllEmployee();
        mcDonaldsList = mcDonaldsDAO.selectAllMcDonalds();

        out.println("\n\nInsert Table\n");
        cityList.forEach(System.out::println);
        addressList.forEach(System.out::println);
        employeeList.forEach(System.out::println);
        mcDonaldsList.forEach(System.out::println);


        List<McDonalds> mcDonaldsList2 = mcDonaldsDAO.selectAllMcDonalds();
        McDonalds mcDonalds4 = new McDonalds();
        mcDonalds4.setPhoneNumber("n");
        mcDonalds4.setCode("n");
        mcDonalds4.setCity(city1);
        Address address4 = new Address();
        address4.setName("q");
        addressDAO.insertAddress(address4);
        mcDonalds4.setAddress(address4);
        mcDonaldsList2.add(mcDonalds4);
        for (McDonalds mcDonalds : mcDonaldsList2) {
            mcDonalds.addEmployee(employee3);
        }
        employeeDAO.updateEmployee(employee3);


        cityList = cityDAO.selectAllCity();
        addressList = addressDAO.selectAllAddress();
        employeeList = employeeDAO.selectAllEmployee();
        mcDonaldsList = mcDonaldsDAO.selectAllMcDonalds();

        out.println("\n\nUpdate All Table\n");
        cityList.forEach(System.out::println);
        addressList.forEach(System.out::println);
        employeeList.forEach(System.out::println);
        mcDonaldsList.forEach(System.out::println);
        JPAConfiguration.shutdown();
    }
}
