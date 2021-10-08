ABOUT THE PROJECT
------------------
The Jungle Friends Primate Sanctuary provides a permanent home and high-quality sanctuary care for New World primates who have been cast-off from the pet trade, retired from research, or confiscated by authorities. They are seeking to replace all of their paper records with computer records where they can keep track of the individual animals that are in their care. And we are going to help them do that.



LIST OF FEATURES
------------------
1. The sanctuary can expand the number of Enclosures and Isolation cages in the future.
2. We can move monkeys around from isolation to enclosure and vice versa.
3. All the new monkeys are automatically sent to isolation for medical checkups.
4. We can search where a particular species is housed.
5. Option to get all species and their locations in the sanctuary, regardless of where they are.
6. Option to get all monkeys and their locations in the sanctuary, regardless of where they are.
7. We can produce a sign for the given enclosure with the data of all monkeys in that enclosure.
8. The sanctuary can get a shopping list of all the items it needs to buy to feed the monkeys.



HOW TO RUN
------------
1. The JAR file can be run by specifying the following command:
	* Open the terminal/command line and navigate to the folder that contains jar on your computer.
	* The insert the following command to run the project,
		' java -jar Project-1.jar '

2. No arguments needed to run the jar file.



HOW TO USE THE PROGRAM
-----------------------
The program is not an interactive one. All anyone needs to do is to run the jar file and you should get the outputs. All the functionality specified in the problem description is implemented to the best of my abilities and the time I had doing this assignment.



DESCRIPTION OF EXAMPLES
------------------------
I am submitting 4 example runs. We start with relatively easy input to check the correctness of various features that we implemented. We check for exception handling in the later runs.

Run 1 :
========
/res/MSD_Run_1.txt - Simple test run to check if all the functions are working as expected.


Run 2:
========
/res/MSD_Run_2.txt - Testing moves to enclosure.
EXCEPTION-1: What happens when we try to add a new monkey and there are no isolation cages empty.
EXCEPTION-2: What happens when we try to move monkey to enclosure with no space.
EXCEPTION-3: What happens when we try to move monkey already in enclosure to enclosure.
EXCEPTION-4: What happens when we try to move a monkey that is not in the Sanctuary.


Run 3:
========
/res/MSD_Run_3.txt - Testing moves to isolation. 
EXCEPTION-1: What happens when we try to move monkey already in isolation to isolation. 
EXCEPTION-2: What happens when we try to add a new monkey and there are no isolation cages empty.
EXCEPTION-3: What happens when we try to move a monkey that is not in the Sanctuary.
EXCEPTION-4: What happens when we try to move monkey to isolation with no space.


Run 4:
========
/res/MSD_Run_4.txt - Testing additional functionality.
* Behavior of search species (lookup species) when given species that are not housed in the Sanctuary.
* Behavior of produce sign method when invalid enclosure is given.
* Behavior of produce sign method when an empty enclosure is given.
* Behavior of shopping list method when a food item not in the list is given.


DESIGN/MODEL CHANGES
---------------------
1. The Housing interface is remapped. This interface is now implemented by both Enclosure and Isolation, not by Sanctuary as originally designed.
2. Some of the method names are changed to have better representation of what they are doing.
3. Some of the methods are updated to be private helper methods because of lack of usage from outside for these methods.
4. Methods like searchFood(String) and getAllSpecies(String) are newly added as I forgot to mention them in my original design.



ASSUMPTIONS
------------
1. Monkey's name is unique to itself. The monkey can grow in size, change its favorite food and its quantity.
2. Along with increasing the number of enclosures, each enclosure's size(area) can also be increased to increase capacity in the future.
3. User specifies when to move the monkey from Isolation to enclosure. Thinking here is that, the medical team will do this once they determine the monkey is safe to be allowed into its new environment.
4. Enclosures initially won't have any condition to host a particular species. The first monkey that comes in makes the enclosure its home.


LIMITATIONS
------------
1. Creation of huge amount of monkey objects to test the application on a large scale is a limitation because of no user input. I tested it for 10 monkeys and all the features are working as expected.
2. You cannot empty the enclosure all at once. You have to remove each monkey till all them are out of the enclosure.


CITATIONS
----------
No Citations.




