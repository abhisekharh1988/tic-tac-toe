# Tic Tac Toe Game
Tic-tac-toe (American English), noughts and crosses (Commonwealth English), or Xs and Os, is a paper-and-pencil game for two players, X and O, who take turns marking the spaces in a 3×3 grid. The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row is the winner. It is a solved game with a forced draw assuming best play from both players.

## Customization

Combination of humen and machines can play the game. For human being application looks for input from console.
Application has its own AI processor which decides the input by itself for machines.

### Player Types
Valid Types = HUMAN, COMPUTER

###Symbol

Here player can select their preferred symbol if the given input passes following validations 
1) Given symbol shouldn't be more than one character
2) Given symbol shouldn't be blank
3) Player1 shouldn't select O and Player2 shouldn't select X as these are default values

If one of the above validations fails, default preferred symbol gets assigned. 
Player1 - default symbol - X
Player2 - default symbol - O

### Input
Each user gets their turn in round robin fashion. In one game a user cannot provide invalid input more than three times. Otherwise, game gets aborted. 

## Build Configuration
### Prerequisites
1) jdk 1.11
2) gradle 6

### Steps
1) set player type in src/main/resources/config.properties. Valid values are "HUMAN" and "COMPUTER". 
2) gradle clean build
3) Copy the tic-tac-toe-<version>.jar from /build/libs directory
4) java -jar tic-tac-toe-<version>.jar

