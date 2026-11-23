package tn.iset.sgs.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class InternshipApplication {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  private String studentName;
  private String studentEmail;
  private String subject;
  private LocalDate startDate;
  private LocalDate endDate;
  private LocalDate submittedAt = LocalDate.now();
  private String status = "EN_ATTENTE";
  @Column(length=1500) private String comment;
  @ManyToOne(optional=false) private InternshipOffer offer;

  public Long getId(){return id;} public String getStudentName(){return studentName;}
  public void setStudentName(String v){studentName=v;} public String getStudentEmail(){return studentEmail;}
  public void setStudentEmail(String v){studentEmail=v;} public String getSubject(){return subject;}
  public void setSubject(String v){subject=v;} public LocalDate getStartDate(){return startDate;}
  public void setStartDate(LocalDate v){startDate=v;} public LocalDate getEndDate(){return endDate;}
  public void setEndDate(LocalDate v){endDate=v;} public LocalDate getSubmittedAt(){return submittedAt;}
  public String getStatus(){return status;} public void setStatus(String v){status=v;}
  public String getComment(){return comment;} public void setComment(String v){comment=v;}
  public InternshipOffer getOffer(){return offer;} public void setOffer(InternshipOffer v){offer=v;}
}
