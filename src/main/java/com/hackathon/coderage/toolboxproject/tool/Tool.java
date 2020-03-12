package com.hackathon.coderage.toolboxproject.tool;

import com.hackathon.coderage.toolboxproject.job.Job;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tools")
public class Tool {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private int dailyPrice;

  @ManyToMany(mappedBy = "tools")
  private List<Job> jobs = new ArrayList<>();

  public Tool(String name, int dailyPrice) {
    this.name = name;
    this.dailyPrice = dailyPrice;
  }
}
