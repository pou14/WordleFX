# WordleFX in Java
## Warnings
- After downloading and combining the files, please update the file paths in the code for the gameLog, savedFile, savedLog, and words files located in "/src/main/resources/com/example/*.txt"

## Rules
- Guess the hidden word in 6 tries.
- Each guess must be a valid 5-letter word, you cannot enter random letters.
- Press the enter button to submit the guess.
- Press the del button to delete the previous letter.
- After your submission, the color of the tiles will change
<picture>
  <source media="(prefers-color-scheme: dark)" srcset="https://github.com/pou14/WordleFX/assets/104444622/d141fdbe-8625-4ba3-a56a-6732223ac80f">
  <source media="(prefers-color-scheme: light)" srcset="https://github.com/pou14/WordleFX/assets/104444622/d141fdbe-8625-4ba3-a56a-6732223ac80f">
  <img alt="Input Example" src="https://github.com/pou14/WordleFX/assets/104444622/d141fdbe-8625-4ba3-a56a-6732223ac80f">
</picture>  

- The letter **H** is in the word and the right spot.  
- The letters **A** and **Y** are in the word but in the wrong spot.  
- The letter **P** is not in the word in any spot.  

## **Functional Buttons**
- **Start:** Switch to the gameboard.
- **Stat**: Switch to the statistics page to show the current win count, etc.
- **Rule:** Display the rules of the game.
- **Reset:** Reset the whole board and choose a new word.
- **Load:** Load previously saved game files.
- **Save:** Save the current board and status to the file.
- **Home:** Return to the home page.
- **WinStat:** Switch to the winning statistics page after winning or losing a game.
- **Back:** Return to the previous board page.

## **Error Log and Input Buttons.**
- **ErrorLog:** Displays the status of the current game.
  1. **"You got this!"** indicates there are no errors.
  2. **"Word limit reached!"** indicates that the user has already entered 5 letters.
  3. **"Fill in a blank!"** indicates that there are no more letters to delete.
  4. **"Fill in all blanks!"** indicates that the user is trying to check answers without filling in all blanks for the given row.
  5. **"Word not in the list"** indicates that the user entered a word not in the word bank.
  6. **"No more input!"** indicates that the game has already been won or lost.
  7. **"File saved!"** indicates that the current board has been saved.
  8. **"File loaded!"** indicates that the saved file has been loaded and updated on the board.
- **Enter:** Checks if the input matches the random word chosen.
- **Del:** Deletes the previously entered element.  
- **Keyboard:** Select a button to make inputs.






