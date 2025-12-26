package com.renan.bankingtranscationsapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class BankAccount implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 512, unique = true)
  @NotBlank(message = "Full Name is mandatory")
  private String fullName;

  @Column(name = "sin_number")
  @Min(value = 1, message = "Sin Number Must be above 0!")
  @NotNull(message = "You did not type the sinNumber attribute. Sin Number Must be above 0!")
  private Long sinNumber;

  @Column
  @NotNull(message = "You did not type the balance attribute. The balance Must be above 0!")
  private Double balance;

  @Column
  @NotBlank(message = "Login credential is mandatory")
  private String login;

  @Column
  @NotBlank(message = "Password is mandatory")
  private String password;

  public BankAccount(
      String fullName, Long sinNumber, Double balance, String login, String password) {
    this.fullName = fullName;
    this.sinNumber = sinNumber;
    this.balance = balance;
    this.login = login;
    this.password = password;
  }

  public BankAccount() {}

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Long getSinNumber() {
    return sinNumber;
  }

  public void setSinNumber(Long sinNumber) {
    this.sinNumber = sinNumber;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    BankAccount that = (BankAccount) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
