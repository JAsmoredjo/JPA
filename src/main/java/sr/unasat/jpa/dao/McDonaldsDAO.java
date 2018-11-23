package sr.unasat.jpa.dao;

import sr.unasat.jpa.entities.City;
import sr.unasat.jpa.entities.McDonalds;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class McDonaldsDAO {
    private EntityManager entityManager;

    public McDonaldsDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<McDonalds> selectAllMcDonalds() {
        entityManager.getTransaction().begin();
        String jpql = "select m from McDonalds m";
        TypedQuery<McDonalds> query = entityManager.createQuery(jpql, McDonalds.class);
        List<McDonalds> mcDonaldsList = query.getResultList();
        entityManager.getTransaction().commit();
        return mcDonaldsList;
    }

    public List<McDonalds> selectAllMcDonaldsByCity(String city) {
        entityManager.getTransaction().begin();
        String jpql = "select m from McDonalds m where m.city.name = :city";
        TypedQuery<McDonalds> query = entityManager.createQuery(jpql, McDonalds.class);
        query.setParameter("city", city);
        List<McDonalds> mcDonaldsList = query.getResultList();
        entityManager.getTransaction().commit();
        return mcDonaldsList;
    }

    public McDonalds selectMcDonalds(String code) {
        entityManager.getTransaction().begin();
        String jpql = "select m from McDonalds m where m.code = :code";
        TypedQuery<McDonalds> query = entityManager.createQuery(jpql, McDonalds.class);
        query.setParameter("code", code);
        McDonalds mcDonalds = query.getSingleResult();
        entityManager.getTransaction().commit();
        return mcDonalds;
    }

    public void insertMcDonalds(McDonalds mcDonalds) {
        entityManager.getTransaction().begin();
        entityManager.persist(mcDonalds);
        entityManager.getTransaction().commit();
    }

    public int updateMcDonalds(McDonalds mcDonalds) {
        entityManager.getTransaction().begin();
        String sql = "update McDonalds m set m.phoneNumber = :phoneNumber, m.code = :code, m.city = :city, m.address = :address where m.id = :id";
        Query query = entityManager.createQuery(sql);
        query.setParameter("id", mcDonalds.getId());
        query.setParameter("phoneNumber", mcDonalds.getPhoneNumber());
        query.setParameter("code", mcDonalds.getCode());
        query.setParameter("city", mcDonalds.getCity());
        query.setParameter("address", mcDonalds.getAddress());
        int updated = query.executeUpdate();
        entityManager.getTransaction().commit();
        return updated;
    }

    public int deleteMcDonalds(McDonalds mcDonalds) {
        entityManager.getTransaction().begin();
        String sql = "delete from McDonalds m where m.code = :code";
        Query query = entityManager.createQuery(sql);
        query.setParameter("code", mcDonalds.getCode());
        int deleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        return deleted;
    }
}
