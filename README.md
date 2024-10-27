
# Tower Defense CBL 2IP90

A Tower Defense game made for the CBL assignment in the 2IP90 course at TU/e. 

The game has a single map where mobs spawn in waves and walk along the lanes to the end of the map. Once the mobs reach this end it will deduct health from the player and once all the health is gone the game ends. 

To stop the mobs from reaching this end, the player can place and upgrade towers that do damage to the mobs. There is a health-bar above the mob indicating its health. Towers have a certain range which can be seen when clicking on it. They can also be upgraded, for a certain amount of money, or sold, for the original price. Money can be earned either be killing mobs or selling towers. Certain mobs will yield more money to the players when killed than others. Stronger mobs will start spawning from wave 5 and onwards.  In total the game has 2 different towers and 2 unique mobs.

Every time a tower shoots a mob, a sound plays. This can be disabled from the settings in the main menu. In this menu a game can also be started, which in turn opens a new menu. From this new menu the difficulty can be selected.  Depending on the difficulty, the health of the player will either be halved (hard) or doubled (easy) with the normal amount being 100.

Upon the end of the game a game-over screen will appear displaying the number of waves survived during the game and an option to return to the main menu. The game can then be played again as everything has been reset. 

Two topics of choice had to be chosen as subjects to research during the development of the game. For this game these topics were Trello and pathfinding. Trello was used to help the development of the game and pathfinding was important to make the mobs walk along the lanes and indicate where towers could be placed.




## Demo

### Gameplay

![gameplay](.github/assets/game-1.PNG?raw=true)
![gameplay](.github/assets/game-2.PNG?raw=true)

### Menus

![main menu](.github/assets/main-menu.PNG?raw=true)
![mode menu](.github/assets/mode-menu.PNG?raw=true)
![settings menu](.github/assets/settings-menu.PNG?raw=true)
![game over menu](.github/assets/game-over.PNG?raw=true)






## Pathfinding

To make sure the mobs walk along a specified path, pathfinding had to be used. As this is a more challenging subject to implement, it was chosen as our topic of choice. By working on this topic, we came to understand more about the graph data structure and about algorithms like breadth first search. 

During the making of our tile map using the software called Tiled, we indicated that certain tiles were part of the pathfinding. We marked a start and end point, followed by marking the lane where the mobs are supposed to be walking on. Inside our code we made a graph, where every tile in our tile map is a vertex. We then connected all the vertexes that were marked. This means that we now have a graph where only the lane (including the start and end point) vertexes are connected to each other. 

Now a breadth first search in done from the start point to create a HashMap containing the path. Mobs have a vertex they are on and when they need to go to the next vertex the game will access the HashMap to find the next vertex in the path. This continues until they reach their destination.

The graph that was made can now also be used to detect whether a tower should be able to be placed on a certain location. If a vertex does not have any neighbours, means that it is not part of the lane, and a tower should be able to be placed there.

By implementing pathfinding, the tile map could be changed on the fly without having to rework the entire code. This is because the code only looks for the lane markings inside the tile map and doesn’t rely on any hardcoded values. Mobs walking along a lane is also essential for a tower defense game and implementing algorithms for this purpose is a lot cleaner than hard coding a certain path for the mobs to walk.

The main source that we used was: https://www.redblobgames.com/pathfinding/a-star/introduction.html. Even though it talks about the a* algorithm for the most part, the beginning section has all the information to implement pathfinding using a breadth first search.

## Trello

As the idea for the game emerged, we found the need to have a clear and easy way to list all the features the project should have and order them on a priority basis. We found that Trello could be a good helpful to the project, and we decided to incorporate it into the project as our topic of choice. Trello can be used by teams to help with the management of a project, as it has built in task boards.

Tasks boards in Trello contain lists. These lists can be named to anything but should be named according to their purpose, and what they are going to be tracking. Inside these list cards can be made with a title, a description, a label and a member. The labels can be used a way to indicate priority.

![card in list](.github/assets/list-image.PNG?raw=true)

The way we used Trello was as a board to track our backlog. 
We would write everything we wanted to do with the game inside the Trello board with a description, evaluated and ranked the priority accordingly (by putting a label on it) and then put it inside one of the lists.

![card](.github/assets/card-image.PNG?raw=true)

We made 4 lists: To Do, Doing, Check and Done. Anything that still had to be done was going to be in the To Do list. Whenever someone picked up a card in the To Do list, they would assign themselves to the card, as a member, and drag it to Doing to indicate that they were working on it. Once the card was done it would be dragged to the Check list so that someone else could check if everything was working as expected. After this it got dragged to the Done list. 

![dragging](.github/assets/dragging.gif?raw=true)

This helped us tremendously during the project as we could now see what everyone was working on and not have conflicting tasks. It is also a good indicator of the progress of the project, as in one look all the still needed and already done tasks can be seen. To fit our needs, we created an automation script to sort the To Do list based on priority. This script would put all priority 1 cards at the top and afterward priority 2 and 3 respectively. With this script it was easier to assess the status of our project and make decisions on what card to work on next.

![automation](.github/assets/automation.gif?raw=true)


Overall Trello was a big help during the project and our knowledge of it has increased by creating different lists and using the automation scripts. Some sources that we used were: 
https://www.hipporello.com/blog/what-is-trello-and-what-is-it-good-for
https://trello.com/guide/automate-anything

![entire trello board](.github/assets/entire-board.gif?raw=true)

## Run Locally

Clone the project

```bash
  git clone https://github.com/AugustDaniel/cbl-2ip90.git
```

Run the Main file located in the src folder


