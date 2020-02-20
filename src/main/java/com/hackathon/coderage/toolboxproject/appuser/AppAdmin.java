package com.hackathon.coderage.toolboxproject.appuser;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppAdmin extends AppUser {

  public AppAdmin(String username, String password, String fullName) {
    super(username, password, fullName, "Administration");
  }
}
