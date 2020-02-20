package com.hackathon.coderage.toolboxproject.appuser;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

  List<AppUser> findAllByNameContains(String name);

  List<AppUser> findAllByRole(String role);

  AppUser findByNameIgnoreCase(String username);
}
