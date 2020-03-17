package com.hackathon.coderage.toolboxproject.order;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.dto.OrderRequestDTO;
import com.hackathon.coderage.toolboxproject.tool.Tool;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "orders")
public abstract class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToMany(
      fetch = FetchType.EAGER,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "employees_jobs",
      joinColumns = @JoinColumn(
          name = "order_id",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "appuser_id",
          referencedColumnName = "id"))
  private Set<AppUser> employees;

  @ManyToMany(
      fetch = FetchType.EAGER,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "tools_orders",
      joinColumns = @JoinColumn(
          name = "order_id",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "tool_id",
          referencedColumnName = "id"))
  private Set<Tool> tools;

  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  private AppUser customer;

  private String status = "Ordered";
  private long price;
  private long date;

  @CreatedDate
  private Date createdAt = new Date();

  public Order(Set<AppUser> employees, Set<Tool> tools, OrderRequestDTO request) {
    this.employees = employees;
    this.tools = tools;
    this.date = request.getDate();
  }
}
