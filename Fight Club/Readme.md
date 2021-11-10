# ABOUT THE PROJECT
The Fight Club is a role-playing game that pit players against each other in the arena or the 
battlefield. The winners of these battles tend to depend upon the abilities of the players, 
the gear that they have at their disposal, and, a bit, on their luck.

# LIST OF FEATURES
1. Create players with different attributes like strength, dexterity, charisma, and constitution.
2. Create arena which the players enter to fight.
3. Factories for gear and weapons are present to create and supply equipment for the players before
the battle.
4. There are different types of gears - Headgear, Magic Potions, Belts of different sizes, and 
Footwear - that can be used by the player to enhance his abilities.
5. There are five different types of weapons - Katana sword, Broad sword, Two Handed sword, Axe, and
Flail - that can be used by the player to emerge victorious at the end of the battle.
6. Each gear equipped effects the player in some way. Some gears will have bad effect, while some 
will have positive effect.
7. Weapons also have the power ranges. These power ranges depend on the abilities of the player 
using the weapon.
8. Before the battle, the players are displayed to the spectators with all their equipped gear, 
enhanced abilities, and weapons.
9. Turn to turn live stream of the battle to get the spectators closer to the action than ever before.
10. There is an option for a re-match if the user feels the result is unfair.

# HOW TO RUN
1. The JAR file can be run by specifying the following command:
    * Open the terminal/command line and navigate to the folder that contains jar on your computer.
    * The insert the following command to run the project,  
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' java -jar MSD-Project-2.jar '

2. No arguments needed to run the jar file.

# HOW TO USE THE PROGRAM
The game is not an interactive one for the most part. However, at the end of each battle, the user 
can choose to opt for a re-match by pressing "y".  
The question is prompted to the user with the options [y-yes / n-no]. Selecting "y" will start a 
re-match between the players, otherwise the session ends there.

# DESCRIPTION OF EXAMPLES
I am submitting 3 example runs. We start with relatively easy input to check the correctness of 
various features that we implemented. We check for exception handling in the latter runs.
### RUN 1
/res/Run1.txt -  
Simple test run to check if all the functions are working as expected.
### RUN 2
/res/Run2.txt -  
This run checks how the program responds to the request of a rematch by the player.
### RUN 3
/res/Run3.txt -  
This run includes various exceptions/errors that might occur during the run time of 
the program. Namely, the null pointer/illegal argument exceptions that might occur while creation or 
copying of objects.

# DESIGN/MODEL CHANGES
1. Included packages for armory, gears, players, and arena.
2. There are small changes in the access modifiers for some functions and variables.
3. Added enumeration for belt size. 
4. There are small changes to the return types of some functions.
5. Some functions are removed, or renamed during the process.

# ASSUMPTIONS
1. Gears' and weapons' abilities last for the entire duration of the battle.
2. The battle itself will not go over 1000 turns (500 turns for each player). The assumption here is
that the players get tired by the time they reach 1000 rounds.
3. There is no option for the tie game. We can call it a tie if the match reaches the 1000 rounds, 
and we do not have a definitive winner yet. (This feature is not implemented and is a last minute 
idea).

# LIMITATIONS
1. Player not being able to choose his/hers own gear and weapon.
2. Too much randomness in the program makes it boring sometimes as it can become lopsided in its results.
3. The program could have been better with more interaction.

# CITATIONS
No citations.
