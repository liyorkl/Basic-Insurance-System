package nz.ac.auckland.se281;

// create new class for string manipulator
public class StringEdit {
  // create method for titleCase
  public static String titleCase(String inString) {
    String lowerCaseString = inString.toLowerCase();
    char[] charArrayOfString = lowerCaseString.toCharArray();
    charArrayOfString[0] = Character.toUpperCase(lowerCaseString.charAt(0));
    return String.valueOf(charArrayOfString);
  }

  // create method to check for yes and y as inputs from user to return true
  public static boolean stringToBoolean(String inString) {
    String lowerCaseString = inString.toLowerCase();
    if (lowerCaseString.equals("yes") || lowerCaseString.equals("y")) {
      return true;
    }
    return false;
  }
}
