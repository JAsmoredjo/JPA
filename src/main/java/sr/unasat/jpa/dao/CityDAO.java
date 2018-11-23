package sr.unasat.jpa.dao;

import sr.unasat.jpa.entities.City;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

    public int updateCity(City city) {
        entityManager.getTransaction().begin();
        String sql = "update City c set c.name = :name, c.description = :description where c.id = :id";
        Query query = entityManager.createQuery(sql);
        query.setParameter("id", city.getId());
        query.setParameter("name", city.getName());
        query.setParameter("description", city.getDescription());
        int updated = query.executeUpdate();
        entityManager.getTransaction().commit();
        return updated;
    }

    public int deleteCity(City city) {
        entityManager.getTransaction().begin();
        String sql = "delete from City c where c.name = :name";
        Query query = entityManager.createQuery(sql);
        query.setParameter("name", city.getName());
        int deleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        return deleted;
    }
}
