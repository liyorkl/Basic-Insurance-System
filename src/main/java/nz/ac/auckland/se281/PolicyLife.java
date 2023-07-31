package nz.ac.auckland.se281;

// policy life class for a life policy
public class PolicyLife extends Policy {

  public PolicyLife(Integer sumInsured, Integer age) {
    super(sumInsured);
    basePremium = ((100 + age) * sumInsured) / 10000;
    discountedPremium = basePremium;
  }

  // override parent function for printPolicy
  @Override
  public void printPolicy() {
    // use MessageCli for prompt
    MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
        Integer.toString(sumInsured),
        Integer.toString(basePremium),
        Integer.toString(discountedPremium));
    ;
  }
}
