package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Profile {

  private String userName;
  private Integer age;
  private ArrayList<Policy> policies = new ArrayList<Policy>();
  private boolean hasLifePolicy = false;

  public Profile() {
    this.userName = "";
    this.age = 0;
  }

  public Profile(String userName, Integer age) {
    this.userName = userName;
    this.age = age;
    MessageCli.PROFILE_CREATED.printMessage(userName, age.toString());
  }

  public void createCarPolicy(
      Integer sumInsured, String makeAndModel, String licensePlate, boolean mechanicalBreakdown) {
    policies.add(
        new PolicyCar(sumInsured, makeAndModel, licensePlate, mechanicalBreakdown, this.age));
    // do a discount check
    discountCheck();
    MessageCli.NEW_POLICY_CREATED.printMessage("car", this.userName);
  }

  public void createHomePolicy(Integer sumInsured, String address, boolean rental) {
    policies.add(new PolicyHome(sumInsured, address, rental));

    // do a discount check
    discountCheck();
    MessageCli.NEW_POLICY_CREATED.printMessage("home", this.userName);
  }

  public void createLifePolicy(Integer sumInsured) {
    // check if profile age is over 100 as we are unable to insure for people aged over 100
    if (this.age > 100) {
      MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(this.userName);
      // stop the method here
      return;
    }

    // check if they dont have a life policy as one profile can only have one
    if (!this.hasLifePolicy) {
      policies.add(new PolicyLife(sumInsured, this.age));
      // do a discount check
      discountCheck();
      MessageCli.NEW_POLICY_CREATED.printMessage("life", this.userName);

      // set has life policy to true so they can't add another one
      this.hasLifePolicy = true;
    } else {
      MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(this.userName);
    }
  }

  public void discountCheck() {
    // method for checking for multiple policies
    // check for 2 and more than 2
    if (policies.size() == 2) {
      for (Policy policy : policies) {
        policy.twoPolicyDiscount();
      }
    } else if (policies.size() > 2) {
      for (Policy policy : policies) {
        policy.moreThanThreePolicyDiscount();
      }
    }
  }

  public void dbPrintWithPolicy(int id, String loadedProfileName) {
    // find total amount of premium to pay
    Integer totalPremium = 0;
    for (Policy policy : policies) {
      totalPremium = totalPremium + policy.getDisountedPremium();
    }

    // check for loaded profile to add *** at the front
    if (loadedProfileName.equals(this.getUserName())) {
      // check if number of policies is 1 where grammar for that is policy instead of policies
      if (this.getNumberOfPolicies() == 1) {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "*** ",
            Integer.toString(id),
            this.getUserName(),
            Integer.toString(this.getAge()),
            Integer.toString(this.getNumberOfPolicies()),
            "y",
            Integer.toString(totalPremium));
      } else {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "*** ",
            Integer.toString(id),
            this.getUserName(),
            Integer.toString(this.getAge()),
            Integer.toString(this.getNumberOfPolicies()),
            "ies",
            Integer.toString(totalPremium));
      }
    } else {
      // check if number of policies is 1 where grammar for that is policy instead of policies
      if (this.getNumberOfPolicies() == 1) {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "",
            Integer.toString(id),
            this.getUserName(),
            Integer.toString(this.getAge()),
            Integer.toString(this.getNumberOfPolicies()),
            "y",
            Integer.toString(totalPremium));
      } else {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "",
            Integer.toString(id),
            this.getUserName(),
            Integer.toString(this.getAge()),
            Integer.toString(this.getNumberOfPolicies()),
            "ies",
            Integer.toString(totalPremium));
      }
    }
  }

  public void printPolicyDetails() {

    if (policies.isEmpty()) {
      return;
    }

    for (Policy p : policies) {
      p.printPolicy();
    }
  }

  // getters
  public String getUserName() {
    return this.userName;
  }

  public Integer getAge() {
    return this.age;
  }

  public Integer getNumberOfPolicies() {
    return policies.size();
  }
}
