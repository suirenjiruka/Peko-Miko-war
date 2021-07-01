# Final-Project

Design Report:
    *** 首先是內容有點多，所以我會在這裡補一需影片沒提到的內容，並且會錄製另外一段正式的試玩畫面**
    *** 除此之外，設計上我會畫出我的思路圖，另外放在資料夾內***

遊戲玩法:
    Player 1
         A: 左移
         D: 右移
         S: 向下跳(可以在空中時往下，或著從木製平台上下來)
         W: 向上跳(在空中最多兩段)
         E: 普攻(技能3施放時會替換成必殺技)
         Q: 防禦(減輕傷害，防禦期間不能移動)
         1: 技能1
         2: 技能2
         3: 技能3
    Player 2
         ←: 左移
         →: 右移
         ↓: 向下跳
         ↑: 向上跳
         小鍵盤0: 普攻
         ctrl: 防禦
         小鍵盤1: 技能1
         小鍵盤2: 技能2
         小鍵盤3: 技能3
角色:  (PS. 本來想弄2P色的，但時間來不急，所以選同一隻角色可能會搞混)
    Peko: HP:3500  MP:2800(初始750， 每次更新回8) 普攻傷害60 移速:20
        skill 1: MP消耗200 自身前方隨機召喚3個蘿蔔飛彈
        skill 2: MP消耗400 對手頭上施放兔子攻擊，在於對手處於同高度時分裂向兩側攻擊
        skill 3: 必殺技期間每秒MP消耗6，每發攻擊消耗55 普攻轉成追蹤導彈

    Miko: HP:2500  MP:2200(初始300 ， 每次更新回7) 普攻傷害80 移速:25
        skill 1: MP消耗110 自身前方設置炸彈(一段時間未觸發會消失)
        skill 2: MP消耗300 自身前方召喚貓咪施放攻擊
        skill 3: 必殺技期間每秒MP消耗5，每發攻擊消耗45 普攻轉成一次4發的步槍子彈
地圖:
    木製平台:可從正下方跳上來，也可以透過下移鍵直接往下跳
    石製平台:玩家無法通過，只能從兩側上去，也無法直接下移
    共有機制: 隨機地圖處產生藥水(紅藥回復HP300， 藍藥回復MP450， 會避開產生於邊界)
    PEKOLAND: 兩側隨機的蘿蔔飛彈
    天空城: 特殊道具翅膀: 短時間內賦予跳躍次數無限(取消二段跳的限制)
            最上方有會移動的地板，最下方無地板，沒有踩在平台會墜落並產生傷害
    樓頂: 地圖平台隨時間變動
          中立型敵人在邊界來回移動，一段時間會自動消失
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





