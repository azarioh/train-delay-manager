package be.aza.web.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import be.aza.web.model.Travel;

@Repository
public class TravelRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Travel travel) {
        em.persist(travel);
    }

	public Travel findByName(String name) {
        return (Travel) em.createNamedQuery(Travel.FIND_BY_NAME).setParameter("name",name).getSingleResult();
	}

	public List<Travel> findAll() {
        return em.createNamedQuery(Travel.FIND_ALL).getResultList();
	}
}