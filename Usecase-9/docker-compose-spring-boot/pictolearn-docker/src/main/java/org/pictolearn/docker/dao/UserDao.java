package org.pictolearn.docker.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pictolearn.docker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO with helper methods to interact with hibernate.
 * 
 * @author agane
 *
 */
@Repository
@Transactional
public class UserDao {
  
  @Autowired
  private SessionFactory _sessionFactory;
  
  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }
  
  /**
   * Save the object to the db
   * @param user
   * @return
   */
  public Long save(User user) {
    return (Long) getSession().save(user);
  }
  
  /**
   * delete the object
   * @param user
   */
  public void delete(User user) {
    getSession().delete(user);
    return;
  }
  
  /**
   * returns all the users
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<User> getAll() {
    return getSession().createQuery("from User").list();
  }
  
  /**
   * find user by email
   * @param email
   * @return
   */
  public User getByEmail(String email) {
    return (User) getSession().createQuery(
        "from User where email like ':email")
        .setParameter("email", email)
        .uniqueResult();
  }

  /**
   * find user by id
   * @param id
   * @return
   */
  public User getById(long id) {
    return (User) getSession().load(User.class, id);
  }

  /**
   * Update user
   * @param user
   */
  public void update(User user) {
    getSession().update(user);
    return;
  }

} 
