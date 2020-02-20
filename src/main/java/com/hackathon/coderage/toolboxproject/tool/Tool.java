package com.hackathon.coderage.toolboxproject.tool;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  private int hourlyPrice;

  public Tool(String name, int hourlyPrice) {
    this.name = name;
    this.hourlyPrice = hourlyPrice;
  }
}
