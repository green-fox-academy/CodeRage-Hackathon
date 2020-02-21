package com.hackathon.coderage.toolboxproject.job;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.tool.Tool;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

  @ManyToOne
  @JoinColumn(name = "employee_id", referencedColumnName = "id")
  private AppUser employee;

  @ManyToOne
  @JoinColumn(name = "tool_id", referencedColumnName = "id")
  private Tool tool;

  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  private AppUser customer;

  private String type;
  private String status;
  private long price;
  private long date;

  @CreatedDate
  private Calendar createdAt = Calendar.getInstance();

  public Job(AppUser employee, Tool tool) {
    this.employee = employee;
    this.tool = tool;
  }
}
