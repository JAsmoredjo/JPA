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

        cityList = cityDAO.selectAllCity();
        addressList = addressDAO.selectAllAddress();
        employeeList = employeeDAO.selectAllEmployee();
        mcDonaldsList = mcDonaldsDAO.selectAllMcDonalds();

        out.println("\n\nDelete Table\n");
        cityList.forEach(System.out::println);
        addressList.forEach(System.out::println);
        employeeList.forEach(System.out::println);
        mcDonaldsList.forEach(System.out::println);
        JPAConfiguration.shutdown();
    }
}
