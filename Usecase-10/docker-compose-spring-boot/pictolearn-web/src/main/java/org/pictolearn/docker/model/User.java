package org.pictolearn.docker.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="users")
public class User implements Serializable{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1694243216355681366L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  @NotNull
  @Size(min = 3, max = 80)
  private String email;
  
  @NotNull
  @Size(min = 2, max = 80)
  private String name;

  public User() { }

  public User(long id) { 
    this.id = id;
  }

  public User(String email, String name) {
    this.email = email;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long value) {
    this.id = value;
  }

  public String getEmail() {
    return email;
  }
  
  public void setEmail(String value) {
    this.email = value;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("User [id=");
	builder.append(id);
	builder.append(", email=");
	builder.append(email);
	builder.append(", name=");
	builder.append(name);
	builder.append("]");
	return builder.toString();
}
  
} // class User
