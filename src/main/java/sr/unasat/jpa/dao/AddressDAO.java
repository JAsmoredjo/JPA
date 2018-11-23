package sr.unasat.jpa.dao;

import sr.unasat.jpa.entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class AddressDAO {
    private EntityManager entityManager;

    public AddressDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Address> selectAllAddress() {
        entityManager.getTransaction().begin();
        String jpql = "select a from Address a";
        TypedQuery<Address> query = entityManager.createQuery(jpql, Address.class);
        List<Address> addressList = query.getResultList();
        entityManager.getTransaction().commit();
        return addressList;
    }


    public Address selectAddress(String name) {
        entityManager.getTransaction().begin();
        String jpql = "select a from Address a where a.name = :name";
        TypedQuery<Address> query = entityManager.createQuery(jpql, Address.class);
        query.setParameter("name", name);
        Address address = query.getSingleResult();
        entityManager.getTransaction().commit();
        return address;
    }

    public void insertAddress(Address address) {
        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();
    }

    public int updateAddress(Address address) {
        entityManager.getTransaction().begin();
        String jpql = "update Address a set a.name = :name where a.id = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", address.getId());
        query.setParameter("name", address.getName());
        int updated = query.executeUpdate();
        entityManager.getTransaction().commit();
        return updated;
    }

    public int deleteAddress(Address address) {
        entityManager.getTransaction().begin();
        String jpql = "delete from Address a where a.name = :name";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("name", address.getName());
        int deleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        return deleted;
    }
}
