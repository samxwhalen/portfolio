package main.java.com;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ColorLibraryApp {

        Map<String, User> userLibraries = new HashMap<>();

        public static void main(String[] args) {

            ColorLibraryApp  application = new ColorLibraryApp();
            application.run();
        }

        private void run() {
            Scanner userInput = new Scanner(System.in);

            System.out.println("** Let's get to know you first **");
            System.out.println("*********************************");
            System.out.println("");


            // User information
            // can encapsulate this in a class??

            System.out.println("please enter your information followed by commas - \n" +
                    "first name, last name, email address, birthday(MM/DD/YYYY), preferred medium, and location");
            String userInfo = userInput.nextLine();
            String[] splittingInfo = userInfo.split(", ");

            User newUser = createNewUser(splittingInfo[0], splittingInfo[1], splittingInfo[2], splittingInfo[3], splittingInfo[4], splittingInfo[5]);

            //can store data once I learn SQL


            //     some way to test that people are being stored in the map
            for (User user : userLibraries.values()) {
                System.out.println(user.toString());
            }


            // create color object

            System.out.print("What color do you want to log? ");
            String userHue = userInput.nextLine();

        }

        public User createNewUser(String firstName, String lastName, String emailAddress, String birthday,
                                      String preferredArtisticMedium, String primaryLocation) {

            User newUser = new User(firstName, lastName, emailAddress,
                    birthday, preferredArtisticMedium, primaryLocation);
            userLibraries.put(lastName, newUser);

            return newUser;

        }

        public List<String> createPaletteInterpretation() {
            return null;
        }

        public List<String> compareColorAssociationToOther() {
            return null;
        }

        public List<String> compareColorSignificance() {
            return null;
        }
}

