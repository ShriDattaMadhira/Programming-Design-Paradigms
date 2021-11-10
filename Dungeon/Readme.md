# ABOUT THE PROJECT
The world for our game consists of a dungeon, a network of tunnels and caves that are interconnected 
so that player can explore the entire world by traveling from cave to cave through the tunnels that 
connect them.

# LIST OF FEATURES
1. Creates a dungeon that is represented as a 2D grid.
2. There will be a path from every cave in the dungeon to every other cave in the dungeon.
3. Each dungeon can be constructed with a degree of interconnectivity.
4. Both wrapping(edge locations "wrap" to the other side of the grid) and non-wrapping dungeons 
can be created.
5. One cave is randomly selected as the start and one cave is randomly selected to be the end with
a distance of at least 5.
6. Supports creation of at least three types of treasure: diamonds, rubies, and sapphires.
7. Treasure will be added only to the percentage of caves specified by the user.
8. Provides description of player's current location, player's current bag, and possible moves for 
the player.
9. Provides the ability for the player to pick up the treasure or ignore it.

# HOW TO RUN
1. The JAR file can be run by specifying the following command:
    * Open the terminal/command line and navigate to the folder that contains jar on your computer.
    * The insert the following command to run the project,  
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' java -jar Project03_DungeonModel.jar '

2. No arguments needed to run the jar file.

# HOW TO USE THE PROGRAM
The game is an interactive one. At the beginning of the program, the user is asked to choose some
values for width & height of the dungeon, wrapping/non-wrapping, interconnectivity of the dungeon, 
and the percentage of caves that should contain the treasure.

Then the user will be asked what location he wants to move to next. 
Each location will have 1 to 4 possible moves depending on that cell's connectivity. 

Some locations, if they are caves will also have treasure. 
The user will be asked if he wants to pick that treasure up or not [y-yes / n-no].

When the player reaches the end location randomly picked at the start of the game, the game ends 
with the message saying "You are the end location. GAME OVER!".

# DESCRIPTION OF EXAMPLES
I am submitting 2 example runs. What each of the runs demonstrate is given below.
### RUN 1
/res/ExampleRun_1.txt -  
This run demonstrates the following,
1. A wrapping dungeon,
2. The player starting at the start and reaching the end, and
3. The player's location and description at each step.
### RUN 2
/res/ExampleRun_2.txt -  
This run demonstrates the following,
1. A non-wrapping dungeon,
2. The player starting at the start and reaching the end,
3. The player visiting every location in the dungeon, and
4. The player's location and description at each step.

# DESIGN/MODEL CHANGES
1. The player to Dungeon connection is now a composition. That is, the player cannot exist outside 
of the game. Everytime, the user wants to play, a new player object is created when the game is
being loaded.
2. A new Path class is added which is completely package private and is used by the kruskal's 
implementation while creating the dungeon according to the specifications given.
3. Some extra private helper methods are added to help in achieving our goals.

# ASSUMPTIONS
1. One of dungeon's width and height is greater than 4. 

# LIMITATIONS
1. The fact that we have to have a distance of 5 between the start and end nodes will eliminate the
possibility of creating a lot of smaller dungeons.

# CITATIONS
No citations.
