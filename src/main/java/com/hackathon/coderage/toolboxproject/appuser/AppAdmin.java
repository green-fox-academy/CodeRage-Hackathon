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

  public AppAdmin(String name) {
    super(name, "Admin", "Administration");
  }
}
