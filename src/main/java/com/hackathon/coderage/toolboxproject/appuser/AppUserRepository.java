package com.hackathon.coderage.toolboxproject.appuser;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

  boolean existsByUsernameEquals(String username);

  AppUser findByUsername(String username);

  List<AppUser> findAllByFullName(String fullName);

  List<AppUser> findAllByRole(String role);

  List<AppUser> findAllByQualificationContains(String qualification);

  AppUser findById(long id);

  List<AppUser> findAll();
}
