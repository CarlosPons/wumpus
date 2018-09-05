Wumpus
======

A simple test designed for the interview process at Connected Health Services. 

Please fork this repo in your account and develop a Java program that implements the rules for the Wumpus Game (see included Powerpoint). You are expected to write the complete game logic, and use simple text interactions to drive the game play (like the text-based adventure games from the old days, http://en.wikipedia.org/wiki/Text-based_game).

Bonus points for unit tests ;).

Please use Eclipse as your IDE and whatever version of Java you are most confortable with (Scala or Groovy also welcomed). Use whatever framework you prefer for your unit tests.


Chosen solution
---------------

To create the app I used Java7 and Maven3.2 (in the POM I just added JUnit4 for testing). I tried to keep as simple as possible. To develop the app I used a Model-View-Controller pattern. I created a simple dependency injection to manage the java project.

With JUnit I tested init and controller methods. The view is a simple console print so I didn't use a test for it.

To execute the app just do:

    mvn clean install

It will show test results and will create a JAR file. To run the app type:

    java -jar ./target/wumpus.jar


DON'T LET YOUR GOBLIN DIE!

              .,+* Hunt the Wumpus *+,.
              ────────────┬────────────
   ┌───────────────┐      │
   │               ├──────┘
   │  !!!    ╀   * │
   │ <o.O> 0x│ 0x▒ │
   │ [^^^]   ╛   ▀ │
   │               ╞╗
   └───╥───────────┘
