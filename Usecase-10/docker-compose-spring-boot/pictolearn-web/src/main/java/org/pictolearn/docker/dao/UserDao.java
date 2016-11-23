package org.pictolearn.docker.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pictolearn.docker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDao {
  
  @Autowired
  private SessionFactory _sessionFactory;
  
  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }
  
  public Long save(User user) {
    return (Long) getSession().save(user);
  }
  
  public void delete(User user) {
    getSession().delete(user);
    return;
  }
  
  @SuppressWarnings("unchecked")
  public List<User> getAll() {
    return getSession().createQuery("from User").list();
  }
  
  public User getByEmail(String email) {
    return (User) getSession().createQuery(
        "from User where email = :email")
        .setParameter("email", email)
        .uniqueResult();
  }

  public User getById(long id) {
    return (User) getSession().get(User.class, id);
  }

  public void update(User user) {
    getSession().update(user);
    return;
  }

} 
