# Snake Game Documentation

## Overview

Welcome to the documentation for the Snake Game project. This classic Snake Game has a unique twist - players have ammunition, and zombie snakes are on the hunt. The game features a scoreboard, adjustable map size, persistent game saving, and various other components.

## Table of Contents

1. [Introduction](#introduction)
2. [Game Components](#game-components)
   - [AmmoFruit.java](#ammofruitjava)
   - [Apple.java](#applejava)
   - [Fruit.java](#fruitjava)
   - [ColorChanger.java](#colorchangerjava)
   - [GameIOHandler.java](#gameiohandlerjava)
   - [LeaderBoard.java](#leaderboardjava)
   - [LeaderBoardFrame.java](#leaderboardframejava)
   - [LeaderBoardHandler.java](#leaderboardhandlerjava)
   - [MapOptionsFrame.java](#mapoptionsframejava)
   - [MenuButton.java](#menubuttonjava)
   - [MenuFrame.java](#menuframejava)
   - [PButton.java](#pbuttonjava)
   - [SidePanel.java](#sidepaneljava)
   - [SnakeFrame.java](#snakeframejava)
   - [SnakeGame.java](#snakegamejava)
   - [SnakePanel.java](#snakepaneljava)
   - [StatsPanel.java](#statspaneljava)
   - [Projectile.java](#projectilejava)
   - [Snake.java](#snakejava)
   - [ZSnake.java](#zsnakejava)
3. [Game Features](#game-features)
   - [Scoreboard](#scoreboard)
   - [Adjustable Map Size](#adjustable-map-size)
   - [Ammunition Mechanism](#ammunition-mechanism)
   - [Zombie Snakes](#zombie-snakes)
4. [Usage](#usage)

## Introduction

The Snake Game is a classic arcade game with added features to make it more challenging and exciting. Players navigate a snake through the game arena, collecting fruits, avoiding obstacles, and shooting zombie snakes with ammunition.

## Game Components

### AmmoFruit.java

A special type of fruit in the game that provides ammunition to the player. When the snake consumes this fruit, it gains additional firepower to shoot at zombie snakes.

### Apple.java

Standard fruit in the game that the snake can consume to increase its length and score.

### Fruit.java

An interface or base class representing various types of fruits in the game, including standard fruits and special ones like AmmoFruit.

### ColorChanger.java

A component responsible for changing the color scheme or appearance of the player controlled Snake, adding visual variety to the gameplay.

### GameIOHandler.java

Handles saving and loading game states.

### LeaderBoard.java

Keeps track of the top scores in the game. Players aim to achieve high scores to appear on the leaderboard.

### LeaderBoardFrame.java

Graphical interface displaying the leaderboard, providing players with a visual representation of the top scores.

### LeaderBoardHandler.java

Manages the logic behind updating and maintaining the leaderboard, so it stays persistent.

### MapOptionsFrame.java

A graphical interface allowing players to customize game settings, including map size and other options.

### MenuButton.java

A clickable button in the game menu, facilitating navigation and interaction with different menu options.

### MenuFrame.java

Graphical interface displaying the main menu of the game, providing options for starting a new game, accessing settings, or viewing the leaderboard.

### PButton.java

A styled button in the game, other buttons use it as parent.

### SidePanel.java

Displays additional information or options on the side of the game screen, enhancing the overall gaming experience.

### SnakeFrame.java

Graphical interface representing the main gameplay screen, where the snake moves and interacts with other game elements.

### SnakeGame.java

The main class orchestrating the game, handling initialization, game loops, and overall control flow.

### SnakePanel.java

A graphical panel displaying the snake and other dynamic elements during gameplay.

### StatsPanel.java

Displays various statistics during the game, such as the player's score, ammunition count, and other relevant information.

### Projectile.java

Represents projectiles or bullets that the snake can shoot, possibly used against zombie snakes.

### Snake.java

Implements the classic snake entity, determining its movement and behavior.

### ZSnake.java

Represents zombie snakes that actively pursue the player. They add an element of danger and challenge to the game.
## Game Features

### Scoreboard

Persistent scoreboard that keeps track of the highest scores.

### Adjustable Map Size

The map can be adjusted using the "Map Options" button on the starting screen.


### Ammunition Mechanism

Player can shoot projectiles until it has run out of ammo, displayed in the StatsPanel. More ammo can be collected by picking up AmmoFruits, which are blue.

### Zombie Snakes

The maximum number of snakes is three, and they move in a random direction towards the player, but I adjusted it so they have more chance moving horizontally, so it looks more natural.

## Usage

The game is played with the ASDW and SPACE keys. In addition to the rules of the classic Snake, the game can end when the player hits a Zombie Snake. A zombie snake can eat the tail of the the player, so it loses length.

