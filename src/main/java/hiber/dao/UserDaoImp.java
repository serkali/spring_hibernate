package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

    @Override
    public User getByUserCar(String model, int series) {
        String HQL = "from User user " +
                "left join fetch user.car " +
                "where user.car.model = :model and user.car.series = :series";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(HQL);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getSingleResult();
    }

}
