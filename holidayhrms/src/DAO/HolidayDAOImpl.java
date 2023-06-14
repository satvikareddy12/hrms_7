package DAO;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import models.Holiday;

@Repository
public class HolidayDAOImpl implements HolidayDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Holiday> findAllHolidays() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Holiday> cq = cb.createQuery(Holiday.class);
		Root<Holiday> root = cq.from(Holiday.class);
		cq.select(root);
		cq.orderBy(cb.asc(root.get("hday_date")));
		Query<Holiday> query = session.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public Holiday findHolidayById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveHoliday(Holiday holiday) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateHoliday(Holiday holiday) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteHoliday(Holiday holiday) {
		// TODO Auto-generated method stub

	}

}