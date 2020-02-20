package com.hackathon.coderage.toolboxproject.job;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.tool.Tool;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "jobs")
public abstract class Job {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToMany(mappedBy = "jobs")
  private List<AppUser> employees = new ArrayList<>();

  @ManyToMany(mappedBy = "jobs")
  private List<Tool> tools = new ArrayList<>();

  @ManyToOne
  private AppUser customer;

  private String type;
  private long startTime;
  private long endTime;
  private String status;
  private long price;

  @CreatedDate
  private Date createdAt = new Date();

  public Job(List<AppUser> employees, List<Tool> tools) {
    this.employees = employees;
    this.tools = tools;
  }
}
