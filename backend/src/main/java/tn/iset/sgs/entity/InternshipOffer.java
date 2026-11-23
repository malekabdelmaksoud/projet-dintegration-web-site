package tn.iset.sgs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class InternshipOffer {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @NotBlank private String title;
  @Column(length=3000) private String description;
  private LocalDate deadline;
  private String status = "OUVERTE";
  @ManyToOne(optional=false) private Company company;

  public Long getId(){return id;} public String getTitle(){return title;}
  public void setTitle(String v){title=v;} public String getDescription(){return description;}
  public void setDescription(String v){description=v;} public LocalDate getDeadline(){return deadline;}
  public void setDeadline(LocalDate v){deadline=v;} public String getStatus(){return status;}
  public void setStatus(String v){status=v;} public Company getCompany(){return company;}
  public void setCompany(Company v){company=v;}
}
