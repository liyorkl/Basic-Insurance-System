package nz.ac.auckland.se281;

public class PolicyHome extends Policy {
  // create unique variables for this class
  private String address;

  // constructor
  public PolicyHome(Integer sumInsured, String address, boolean rental) {
    super(sumInsured);
    this.address = address;
    // check if home is a rental
    if (rental) {
      basePremium = (2 * sumInsured) / 100;
    } else {
      basePremium = (1 * sumInsured) / 100;
    }
    discountedPremium = basePremium;
  }

  // override parent function
  @Override
  public void printPolicy() {
    // use MessageCli for prompt
    MessageCli.PRINT_DB_HOME_POLICY.printMessage(
        address,
        Integer.toString(sumInsured),
        Integer.toString(basePremium),
        Integer.toString(discountedPremium));
    ;
  }
}
