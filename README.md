# ProperFreeze
A simple freeze plugin that doesn't teleport you constantly or glitch your screen.
This repo just serves as an example and can be improved further.

## Features
* Doesn't use PlayerMoveEvent or PlayerTeleportEvent.
* Doesn't constantly teleport you or glitch your screen.

## How it works
* Set player's walk speed to 0.
* Set player's fly speed to 0.
* Set player's food level to 0.
* Set player's sprint to false.
* Give player Jump Boost 200 (makes it so you can't jump)

Unfreezing the player is as simple as reverting those values to what they were originally.

## Permissions
`properfreeze.staff` - Access to /freeze and alerts.

### Note
I'm not sure if there is a way to move if frozen by using a hacked client, but if you're paranoid about that type of situation, you could implement a task that checks their location every 2 seconds.
