package com.hackathon.coderage.toolboxproject.appuser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  public AppUser(String name, String role, String qualification) {
    this.name = name;
    this.role = role;
    this.qualification = qualification;
  }
}
