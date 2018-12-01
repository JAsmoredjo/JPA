package sr.unasat.jpa.dao;

import sr.unasat.jpa.entities.City;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CityDAO {
    private EntityManager entityManager;

    public CityDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<City> selectAllCity() {
        entityManager.getTransaction().begin();
        String jpql = "select c from City c";
        TypedQuery<City> query = entityManager.createQuery(jpql, City.class);
        List<City> cityList = query.getResultList();
        entityManager.getTransaction().commit();
        return cityList;
    }


    public City selectCity(String name) {
        entityManager.getTransaction().begin();
        String jpql = "select c from City c where c.name = :name";
        TypedQuery<City> query = entityManager.createQuery(jpql, City.class);
        query.setParameter("name", name);
        City city = query.getSingleResult();
        entityManager.getTransaction().commit();
        return city;
    }

    public void insertCity(City city) {
        entityManager.getTransaction().begin();
        entityManager.persist(city);
        entityManager.getTransaction().commit();
    }

    public void updateCity(City city) {
        entityManager.getTransaction().begin();
        entityManager.merge(city);
        entityManager.getTransaction().commit();
    }

    public void deleteCity(City city) {
        if (city.getMcDonalds() == null) {
            entityManager.getTransaction().begin();
            entityManager.remove(city);
            entityManager.getTransaction().commit();
        }
    }
}
