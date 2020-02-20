package com.hackathon.coderage.toolboxproject.appuser;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppCustomer extends AppUser {

  public AppCustomer(String name) {
    super(name, "Customer", "Private");
  }
}
