# Final-Project

Design Report:
    *** 首先是內容有點多，所以我會在這裡補一需影片沒提到的內容，並且會錄製另外一段正式的試玩畫面**

Starting:
    using cmd and run #java Main

Demo playing link: https://youtu.be/7g9T1Txa44M 

playing introduction:

    Player 1

         A: move left

         D: move right

         S: jump down(down from air or above platform)

         W: jump(at most twice on air)

         E: normal attack(during skill 3 period change into special attack)

         Q: defend(alleviate damag, can't move during defending period)

         1: skill 1

         2: skill 2

         3: skill 3

    Player 2
         ←: move left

         →: move right

         ↓: jump down

         ↑: jump

         num 0:  normal attack

         right ctrl: defend

         num 1: skill 1

         num 2: skill 2

         num 3: skill 3

角色:  (PS. perhaps updating 2P colorin future, but know the same character looks identical)

    Peko: HP:3500  MP:2800(initial 750， ecover 8 earch frame) normal attack damage 60, moving speed:20
        skill 1: MP consumption: 200, randomly summon three carrot missiles.
        skill 2: MP consumption:400, call a rabbit on enemy's head, and fission into two at the smae height with enemy.
        skill 3: consume 6 MP every second, each attack additionally consume 55 mp, special attack is a tracking rocket.

    Miko: HP:2500  MP:2200(initial 300 ， ecover 7 earch frame) normal attack damage 80, moving speed:25
        skill 1: MP consumption: 110, setting a sakura bomb.(dissppearing after a period of time without triggered by stepped on)
        skill 2: MP consumption:300, call a cat and deal damage in the front.
        skill 3: consume  MP every second, each attack additionally consume 5 mp, special attack is 4 shots bullets.

地圖:

    wooden platform: can penetrate

    rock platform: can't penetarte

    potions: randomly show up in map (red one recover HP300， blue one recover MP450)


Design:
    view:
        Gameview: provided the Frame of the game, using JFrame, and there are other three class inherited Jpanel, respectively for title page, chosing page, game page(I call it Canvas). 
        it also will plays the BGM in aother thread. 

    Map_all:
        titlepage: called by Gameview, the class will set everything about title page.

        chosepage: called by Gameview, the class will set everything about chosing page.

        Map: the most important part of my code, the map update the game process, and control all the chararters and objects in the map, most of judging logic of the game are in this class(like the health potion or magic potion).

        Pekoland: inherited from map, responsible for the specific mechanism of Pekoland (the carrot missile)

        skyland: inherited from map, responsible for the specific mechanism of skyland (moving plane ,,fall condition and wing)

        top: inherited from map, responsible for the specific mechanism of top(chaging plane and the neutral enemy)

        plane: a class for plane(both of two kinds)

    Gamecontrol: (Basicly, this part is almost the same as the TA's one)
        Gameloop: an abstract class for game, starting gameloop and stop gameloop.

        Game: handle the event from the Canvas(Canvas will listen the event from our keyboard). Actually, the gameloop call update of Map class to handle the event will happen as the time pass, the Game class will ask Map class to handle the event as player's command.

    character:
        CharacterImage: read the picture for the two character, it will called by CharacterProtype.

        CharacterProtype: It will handle most mechanism for character(except the skill).

        Peko: a class for the character, Peko, determining the basic abilitiy, skill, vocal and image.

        Miko: a class for the character, Miko.

    Myobject: 
        *** objects is divided to two kinds, one will effect by gravity(fall == 1), one won't(fall == 0). And object has its own update mechanism(most of them is for the purpose of judging whether the attack hits its opponent)***

        object: the proto type of all objects.

        carrot: a class of carrot missile, for Peko's skill 1 and the map of Pekoland. it won't effected by gravity.

        donchan: an attack for Peko's skill 2, just like carrot in some degree. it won't effect by gravity.

        carrotbomb : an attack for Peko's skill 3. it won't effect by gravity.

        sakura: an attack for Miko's skill 1. it will effect by gravity.(the fallhandle function in Map class will help deal with it.)

        mikocat: an attack for Miko's skill 2. it won't effect by gravity.

        bullet: an attack for Miko's skill 3. it won't effect by gravity.

        potion: a class responsible for the two kinds of potion(red and blue).it will effect by gravity.

        wing: a class for the specific tool in the map of skyland, it will enable player to jump infinite times in a short period.

        enemy: a neutral creature in the map of top. it will effect by gravity.

        Media: identical with as TA's one. responsible for Audio output.





