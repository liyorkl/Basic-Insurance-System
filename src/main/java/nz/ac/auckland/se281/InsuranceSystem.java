package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  ArrayList<Profile> profileDatabase = new ArrayList<Profile>();
  String loadedProfileName = "";
  String emptyString = "";

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).\
  }

  public void printDatabase() {
    // Find the size of the profileDatabase ArrayList
    Integer profileCount = profileDatabase.size();

    // If statement to check if database is empty
    if (profileDatabase.size() == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");
    } else {
      // Check if there is only 1 profile or if there are more so database Header comment is
      // displayed properly
      if (profileCount < 2) {
        MessageCli.PRINT_DB_POLICY_COUNT.printMessage(profileCount.toString(), "", ":");
      } else {
        MessageCli.PRINT_DB_POLICY_COUNT.printMessage(profileCount.toString(), "s", ":");
      }
      // cycle through profileDatabase
      // use an iterable varible for labeling rank of the profile
      profileCount = 1;
      for (Profile p : profileDatabase) {
        p.dbPrintWithPolicy(profileCount, loadedProfileName);
        p.printPolicyDetails();
        profileCount = profileCount + 1;
      }
    }
  }

  public void createNewProfile(String userName, String age) {
    // check if there is a loaded profile
    if (!(loadedProfileName.equals(emptyString))) {
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(loadedProfileName);
      return;
    }
    // Make the userName string titleCase
    userName = StringEdit.titleCase(userName);

    // Check length of userName to ignore less than 3 characters
    if (userName.length() < 3) {
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);
      return; // to escape the method and not create a new profile
    }

    // check if there already exists a name in that is the same as the new input name
    for (Profile p : profileDatabase) {
      // using the String.equals method
      if (userName.equals(p.getUserName())) {
        MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
        return; // to escape the method and not create a new profile
      }
    }

    // use try catch block to check if age is an integer
    try {
      Integer.parseInt(age);
    } catch (Exception e) {
      MessageCli.INVALID_AGE.printMessage(age, userName);
      return; // to escape the method and not create a new profile
    }

    // If code reached here it will be of correct type and praseint can be used without errors
    Integer intAge = Integer.parseInt(age);
    // chech if intAge is less than 0
    if (intAge < 0) {
      MessageCli.INVALID_AGE.printMessage(age, userName);
      return;
    }

    // If code reached here it means that we can create a new profile with no problems
    profileDatabase.add(new Profile(userName, intAge));
  }

  public void loadProfile(String userName) {
    // process in title case
    userName = StringEdit.titleCase(userName);

    // check if database is empty
    if (profileDatabase.isEmpty()) {
      MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(userName);
      return;
    }
    // loop through database to check if userName matches a profile username
    for (Profile p : profileDatabase) {
      if (userName.equals(p.getUserName())) {
        MessageCli.PROFILE_LOADED.printMessage(userName);
        loadedProfileName = userName;
        return;
      }
    }

    // Will only reach this if there was no profile for userName
    MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(userName);
    return;
  }

  public void unloadProfile() {
    if (loadedProfileName.equals(emptyString)) {
      MessageCli.NO_PROFILE_LOADED.printMessage();
      return;
    }
    MessageCli.PROFILE_UNLOADED.printMessage(loadedProfileName);
    loadedProfileName = "";
    return;
  }

  public void deleteProfile(String userName) {
    // process in titlecase
    userName = StringEdit.titleCase(userName);

    // check if currently loaded profile is the one getting deleted
    if (loadedProfileName.equals(userName)) {
      MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(userName);
      return;
    }

    // loop through database to find profile to delete
    int profileID = 0;
    for (Profile p : profileDatabase) {
      if (userName.equals(p.getUserName())) {
        MessageCli.PROFILE_DELETED.printMessage(userName);
        profileDatabase.remove(profileID);
        return;
      }
      profileID++;
    }

    // if profile was not found
    MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(userName);
    return;
  }

  public void createPolicy(PolicyType type, String[] options) {
    // check if there is a loaded profile
    if (loadedProfileName.equals(emptyString)) {
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
      return;
    }

    // create the policy for the loaded profile
    for (Profile p : profileDatabase) {
      if (loadedProfileName.equals(p.getUserName())) {
        switch (type) {
          case CAR:
            p.createCarPolicy(
                Integer.parseInt(options[0]),
                options[1],
                options[2],
                StringEdit.stringToBoolean(options[3]));
            break;
          case HOME:
            p.createHomePolicy(
                Integer.parseInt(options[0]), options[1], StringEdit.stringToBoolean(options[2]));
            break;
          case LIFE:
            p.createLifePolicy(Integer.parseInt(options[0]));
            break;
        }
        return;
      }
    }
  }
}
