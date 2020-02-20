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

  private int hourlyWage = 0;

  public AppSpecialist(
      String username, String password, String fullName, String qualification, int hourlyWage) {
    super(username, password, fullName, qualification);
    this.hourlyWage = hourlyWage;
  }
}
