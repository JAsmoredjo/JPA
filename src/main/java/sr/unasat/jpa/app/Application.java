package sr.unasat.jpa.app;

import sr.unasat.jpa.config.JPAConfiguration;
import sr.unasat.jpa.dao.CityDAO;
import sr.unasat.jpa.dao.McDonaldsDAO;
import sr.unasat.jpa.entities.City;
import sr.unasat.jpa.entities.McDonalds;

import java.util.List;

import static java.lang.System.out;

public class Application {
    public static void main(String[] args) {
        CityDAO cityDAO = new CityDAO(JPAConfiguration.getEntityManager());
        out.println("\nCurrent Table");
        List<City> cityList = cityDAO.selectAllCity();
        for (City cityTemp : cityList) {
            out.println(cityTemp);
        }

        McDonaldsDAO mcDonaldsDAO = new McDonaldsDAO(JPAConfiguration.getEntityManager());
        List<McDonalds> mcDonaldsList = mcDonaldsDAO.selectAllMcDonalds();
        for (McDonalds mcDonaldsTemp : mcDonaldsList) {
            out.println(mcDonaldsTemp);
        }


        out.println("\nSelect McDonalds by City");
        List<McDonalds> mcDonaldsList1 = mcDonaldsDAO.selectAllMcDonaldsByCity("c_n1");
        for (McDonalds mcDonaldsTemp : mcDonaldsList1) {
            out.println(mcDonaldsTemp);
        }

        City cityNew = new City();
        cityNew.setName("c_n_new");
        cityNew.setDescription("c_n_new");
        cityDAO.insertCity(cityNew);
        out.println("\nTable After City Insert");
        cityList = cityDAO.selectAllCity();
        for (City cityTemp : cityList) {
            out.println(cityTemp);
        }
        mcDonaldsList = mcDonaldsDAO.selectAllMcDonalds();
        for (McDonalds mcDonaldsTemp : mcDonaldsList) {
            out.println(mcDonaldsTemp);
        }

        City citySelect = cityDAO.selectCity("c_n_new");
        McDonalds mcDonalds = new McDonalds();
        mcDonalds.setAddress("m_a_new");
        mcDonalds.setPhoneNumber("m_pn_new");
        mcDonalds.setCode("m_c_new");
        mcDonalds.setCity(citySelect);
        mcDonaldsDAO.insertMcDonalds(mcDonalds);
        out.println("\nTable After McDonalds Insert");
        cityList = cityDAO.selectAllCity();
        for (City cityTemp : cityList) {
            out.println(cityTemp);
        }
        mcDonaldsList = mcDonaldsDAO.selectAllMcDonalds();
        for (McDonalds mcDonaldsTemp : mcDonaldsList) {
            out.println(mcDonaldsTemp);
        }

        citySelect.setName("c_n_new_new");
        citySelect.setDescription("c_d_new_new");
        cityDAO.updateCity(citySelect);
        out.println("\nTable After City Update");
        cityList = cityDAO.selectAllCity();
        for (City cityTemp : cityList) {
            out.println(cityTemp);
        }
        mcDonaldsList = mcDonaldsDAO.selectAllMcDonalds();
        for (McDonalds mcDonaldsTemp : mcDonaldsList) {
            out.println(mcDonaldsTemp);
        }

        McDonalds mcDonaldsSelect = mcDonaldsDAO.selectMcDonalds("m_c_new");
        mcDonaldsSelect.setAddress("m_a_new_new");
        mcDonaldsSelect.setPhoneNumber("m_pn_new_new");
        mcDonaldsSelect.setCode("m_c_new_new");
        mcDonaldsDAO.updateMcDonalds(mcDonaldsSelect);
        out.println("\nTable After McDonalds Update");
        cityList = cityDAO.selectAllCity();
        for (City cityTemp : cityList) {
            out.println(cityTemp);
        }
        mcDonaldsList = mcDonaldsDAO.selectAllMcDonalds();
        for (McDonalds mcDonaldsTemp : mcDonaldsList) {
            out.println(mcDonaldsTemp);
        }

        mcDonaldsDAO.deleteMcDonalds(mcDonaldsSelect);
        out.println("\nTable After McDonalds Delete");
        cityList = cityDAO.selectAllCity();
        for (City cityTemp : cityList) {
            out.println(cityTemp);
        }
        mcDonaldsList = mcDonaldsDAO.selectAllMcDonalds();
        for (McDonalds mcDonaldsTemp : mcDonaldsList) {
            out.println(mcDonaldsTemp);
        }

        cityDAO.deleteCity(citySelect);
        out.println("\nTable After City Delete");
        cityList = cityDAO.selectAllCity();
        for (City cityTemp : cityList) {
            out.println(cityTemp);
        }
        mcDonaldsList = mcDonaldsDAO.selectAllMcDonalds();
        for (McDonalds mcDonaldsTemp : mcDonaldsList) {
            out.println(mcDonaldsTemp);
        }
        JPAConfiguration.shutdown();
    }
}
