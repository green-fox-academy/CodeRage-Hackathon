package com.hackathon.coderage.toolboxproject.appuser;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppSpecialist extends AppUser {

  private String qualification;
  private int hourlyWage = 0;

  public AppSpecialist(String name, String role, String qualification, int hourlyWage) {
    super(name, role);
    this.qualification = qualification;
    this.hourlyWage = hourlyWage;
  }
}
