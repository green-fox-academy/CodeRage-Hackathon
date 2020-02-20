package com.hackathon.coderage.toolboxproject.appuser;

import com.hackathon.coderage.toolboxproject.job.Job;
import com.hackathon.coderage.toolboxproject.project.Project;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Users")
public abstract class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private String role;
  private String qualification;

  @OneToMany(
      cascade = CascadeType.ALL,
      mappedBy = "appUser",
      orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Job> jobs = new ArrayList<>();

  @OneToMany(
      cascade = CascadeType.ALL,
      mappedBy = "appUser",
      orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Project> projects = new ArrayList<>();

  public AppUser(String name, String role, String qualification) {
    this.name = name;
    this.role = role;
    this.qualification = qualification;
  }
}
