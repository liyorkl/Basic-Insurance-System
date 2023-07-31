package nz.ac.auckland.se281;

public abstract class Policy {
  protected Integer sumInsured = 0;
  protected Integer basePremium;
  protected Integer discountedPremium;

  public Policy(Integer sumInsured) {
    this.sumInsured = sumInsured;
  }

  public abstract void printPolicy();

  public void twoPolicyDiscount() {
    discountedPremium = (90 * basePremium) / 100;
  }

  public void moreThanThreePolicyDiscount() {
    discountedPremium = (80 * basePremium) / 100;
  }

  public Integer getDisountedPremium() {
    return discountedPremium;
  }
}
