package com.hackathon.coderage.toolboxproject.appuser;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

  boolean existsByUsernameEquals(String username);

  AppUser findAppUserByUsernameEquals(String username);

  List<AppUser> findAllByFullNameContains(String fullName);

  List<AppUser> findAllByRole(String role);

  List<AppUser> findAllByQualificationContains(String qualification);
}
