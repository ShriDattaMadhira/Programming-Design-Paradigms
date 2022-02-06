# ABOUT THE PROJECT
This is a continuation of the last project where we created dungeon and player movement features.
This is a text based adventure game in which the player takes on the role of the protagonist in an 
interactive story driven by exploration and/or puzzle solving. The world for our game consists of a 
dungeon, a network of tunnels and caves that are interconnected so that player can explore the 
entire world by traveling from cave to cave through the tunnels that connect them. The dungeon will 
have treasure, arrows, and monsters in it. The player should successfully navigate through the 
dungeon and get out of it alive.

# LIST OF FEATURES
Adding to the features from the previous project, we now have,
1. The dungeons will now have at least one monster, also called as Otyugh. 
2. Otyughs can be detected by their smell. There are two levels of smell:
   1. A less pungent smell can be detected when there is a single Otyugh 2 positions from the 
   player's current location, and
   2. A more pungent smell either means that there is a single Otyugh 1 position from the player's 
   current location or that there are multiple Otyughs within 2 positions from the player's
   current location.
3. Player entering a cave with an Otyugh that has not been slayed will be eaten.
4. Bows and Arrows(crooked) are added to the dungeon to give player the ability to slay the monster.
   1. A tunnel that has exits to the west and south can have an arrow enter the tunnel from the west
   and exit the tunnel to the south (this is the reason the arrow is called a crooked arrow).
   2. If a cave has exits to east, south, and north and an arrow is coming from east. It can only go
   through the east exit.
5. It takes 2 hits to kill an Otyugh. 
6. Players have a 50% chance of escaping the Otyugh if they enter a cave of an injured Otyugh.

# HOW TO RUN
1. The JAR file can be run by specifying the following command:
   * Open the terminal/command line and navigate to the folder that contains jar on your computer.
   * The insert the following command to run the project,  
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   ' java -jar MSD_Project04_Dungeon_controller.jar <width> <height> <wrapping> <interconnectivity> 
   <treasure_percentage> <number_of_otyughs>'

# HOW TO USE THE PROGRAM
The game is an interactive one. At the beginning of the program, the user is asked to choose some
values for width & height of the dungeon, wrapping/non-wrapping, interconnectivity of the dungeon, 
the percentage of caves that should contain the treasure, and the number of monsters in the dungeon.

Then the player will be given the chance to move, shoot, or pickup the treasure/arrows in the
dungeon. 

If the player selects move, the direction in which to go has to be specified.
If the player selects shoot, the direction in which to shoot and the distance has to be specified.

Some locations, if they are caves will also have treasure and/or arrows.
The user will be asked if he wants to pickup the treasure [1-yes / 0-no].
Same for arrows [1-yes / 0-no].

When the player reaches the end location randomly picked at the start of the game, the game ends
with the message "End location reached. Game is over!".

If in the process of exploration the player gets killed by an Otyugh, the game ends with the message
"Eaten by Otyugh. Game is over!".

# DESCRIPTION OF EXAMPLES
I am submitting 2 example runs. What each of the runs demonstrate is given below.
### RUN 1
/res/ExampleRun_1.txt -  
This run demonstrates the following,
1. A non-wrapping dungeon,
2. Shows the player navigating through the dungeon,
3. Shows the player picking up arrows, and
4. Shows the player being eaten by a Otyugh.
### RUN 2
/res/ExampleRun_2.txt -  
This run demonstrates the following,
1. A wrapping dungeon,
2. Shows the player navigating through the dungeon,
3. Shows the player picking up treasure,
4. Shows the player picking up arrows,
5. Shows the player killing an Otyugh, and
6. Shows the player winning the game by reaching the end.

# DESIGN/MODEL CHANGES
1. Added a new class called AdvancedDungeon that extends DungeonModel class from last project and
adds additional functionality.
   1. Add otyughs to caves.
   2. Add arrows to caves and tunnels.
   3. Add smell to the dungeon because of otyughs.
2. Created a new Otyugh class that contains location and health of an Otyugh.
3. Added number of arrows available to the PlayerImpl class.
4. Added shoot and pickup arrow features to give player additional skills to survive.
5. Designed a controller to run the program by taking inputs from the user.

# ASSUMPTIONS
1. One of dungeon's width and height is greater than 4 (assumption from Project-3).
2. No assumptions taken for this particular project.

# LIMITATIONS
1. The fact that we have to have a distance of 5 between the start and end nodes will eliminate the
   possibility of creating a lot of smaller dungeons.
2. We can have different types of weapons like in project 2.

# CITATIONS
No citations.
