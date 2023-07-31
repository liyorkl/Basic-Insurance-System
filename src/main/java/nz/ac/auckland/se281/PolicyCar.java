package nz.ac.auckland.se281;

public class PolicyCar extends Policy {
  // create unique variables for this class
  private String makeAndModel;
  private String licensePlate;

  // constructor
  public PolicyCar(
      Integer sumInsured,
      String makeAndModel,
      String licensePlate,
      boolean mechanicalBreakdown,
      Integer age) {
    super(sumInsured);
    this.makeAndModel = makeAndModel;
    this.licensePlate = licensePlate;

    // check if age is less than 25
    if (age < 25) {
      basePremium = (15 * sumInsured) / 100;
    } else {
      basePremium = (10 * sumInsured) / 100;
    }

    // check if care is covered for mechanical breakdown
    if (mechanicalBreakdown) {
      basePremium = basePremium + 80;
    }
    discountedPremium = basePremium;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  // override parent function
  @Override
  public void printPolicy() {
    // use MessageCli for prompt
    MessageCli.PRINT_DB_CAR_POLICY.printMessage(
        makeAndModel,
        Integer.toString(sumInsured),
        Integer.toString(basePremium),
        Integer.toString(discountedPremium));
  }
}
