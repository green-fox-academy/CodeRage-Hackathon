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
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String username;
  private String password;
  private String fullName;
  private String role;
  private String qualification;
  private int hourlyWage;

  @ManyToMany(cascade = CascadeType.ALL)
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Job> jobs = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Job> orders = new ArrayList<>();

  public AppUser(String username, String password, String fullName, String qualification) {
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.qualification = qualification;
  }
}
