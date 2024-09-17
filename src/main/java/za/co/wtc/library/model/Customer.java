package za.co.wtc.library.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "title")
  private String title;

  @Column(name = "id_number")
  private String idNumber;

  @Column(name = "email")
  private String email;

  @Column(name = "membership_start_date")
  private LocalDateTime memberShipStartDate;

  @Column(name = "membership_expire_date")
  private LocalDateTime memberShipExpireDate;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private Set<Address> addresses = new HashSet<>();

  public Customer() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(String idNumber) {
    this.idNumber = idNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDateTime getMemberShipStartDate() {
    return memberShipStartDate;
  }

  public void setMemberShipStartDate(LocalDateTime memberShipStartDate) {
    this.memberShipStartDate = memberShipStartDate;
  }

  public LocalDateTime getMemberShipExpireDate() {
    return memberShipExpireDate;
  }

  public void setMemberShipExpireDate(LocalDateTime memberShipExpireDate) {
    this.memberShipExpireDate = memberShipExpireDate;
  }

  public Set<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(Set<Address> addresses) {
    this.addresses = addresses;
  }
}
