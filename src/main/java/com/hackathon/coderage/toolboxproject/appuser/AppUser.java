package com.hackathon.coderage.toolboxproject.appuser;

import com.hackathon.coderage.toolboxproject.job.Job;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public abstract class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private String role;
  private String qualification;
  private String password;

  @ManyToMany(cascade = CascadeType.ALL)
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Job> jobs = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Job> orders = new ArrayList<>();

  public AppUser(String name, String role, String password, String qualification) {
    this.name = name;
    this.role = role;
    this.password = password;
    this.qualification = qualification;
  }
}
