package tn.iset.sgs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Company {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  @NotBlank private String name;
  private String address;
  private String sector;
  private String email;
  private String phone;
  private boolean active = true;

  public Long getId(){return id;} public String getName(){return name;}
  public void setName(String v){name=v;} public String getAddress(){return address;}
  public void setAddress(String v){address=v;} public String getSector(){return sector;}
  public void setSector(String v){sector=v;} public String getEmail(){return email;}
  public void setEmail(String v){email=v;} public String getPhone(){return phone;}
  public void setPhone(String v){phone=v;} public boolean isActive(){return active;}
  public void setActive(boolean v){active=v;}
}
