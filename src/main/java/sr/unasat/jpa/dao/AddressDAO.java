package sr.unasat.jpa.dao;

import sr.unasat.jpa.entities.Address;
import sr.unasat.jpa.entities.McDonalds;

import javax.persistence.EntityManager;
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

    public void insertAddress(List<McDonalds> mcDonaldsList) {
        entityManager.getTransaction().begin();
        for (McDonalds mcDonalds : mcDonaldsList) {
            if (mcDonalds.getAddress().getId() == 0) {
                entityManager.persist(mcDonalds.getAddress());
            }
        }
        entityManager.getTransaction().commit();
    }

    public void updateAddress(Address address) {
        entityManager.getTransaction().begin();
        entityManager.merge(address);
        entityManager.getTransaction().commit();
    }

    public void deleteAddress(Address address) {
        if (address.getMcDonalds() == null) {
            entityManager.getTransaction().begin();
            entityManager.remove(address);
            entityManager.getTransaction().commit();
        }
    }
}
