package sr.unasat.jpa.dao;

import sr.unasat.jpa.entities.Employee;
import sr.unasat.jpa.entities.McDonalds;

import javax.persistence.EntityManager;
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

    public void updateMcDonalds(McDonalds mcDonalds) {
        entityManager.getTransaction().begin();
        entityManager.merge(mcDonalds);
        entityManager.getTransaction().commit();
    }

    public void deleteMcDonalds(McDonalds mcDonalds) {
        entityManager.getTransaction().begin();
        while (mcDonalds.getEmployees().iterator().hasNext()) {
            Employee employee = mcDonalds.getEmployees().iterator().next();
            mcDonalds.removeEmployee(employee);
        }
        entityManager.remove(mcDonalds);
        entityManager.getTransaction().commit();
    }
}
