/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the 0editor.
 */
package JPvZ;

import static JPvZ.MainWindow.height;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 * <code>graden</code> is main panel of game
 *
 * @author ParhamDamavandi
 */
public final class Garden extends JPanel implements MouseListener {

    //avery plant in this game is in this array
    private final ArrayList<Plant> plants;
    //every zombie in this game is in this array
    private final ArrayList<Zombie> zombies;
    //every zombie in this game is in this array
    private final ArrayList<Sun> suns;
    //this timer redraws Screen and too important
    //all reactions between zombis and plants are handled in this timer
    //for example when zombie reaches sp plant we must reduce health of plant
    private final Timer ZombivsPlant;
    //Image if backgroung game 
    private final ImageIcon mainGarden;
    //is for understanding user is going to plant or not
    private boolean goingToPlant;
    //is for user is going to dig or not
    private boolean goingToDig;
    //every platform is an object
    static Platform platforms[][];
    //oh this class has an inner timer and adds sun to screen for user in particular time
    private AddSunToScreen addSunToScreen;
    //rows for planting
    static int ROW[];
    //image icon for items:)
    private final ImageIcon items = new ImageIcon("Images\\items.png");
    private final ImageIcon sun = new ImageIcon("Images\\Sun_PvZ2.png");
    //sunSeed is image of sun for user who is going to buy
    //plays main game sound
    private SoundPlayer clip;
    private final ImageIcon freezeShooterseed = new ImageIcon("Images\\freze.png");
    //paltforms have size in pixcels odd and even are sp little different
    private final int oddPlatformSizeX = 100;
    private final int evenPlatformSizeX = 85;
    //y size of platform
    private final int ySize = 120;
    //this is one of the most important integers in game and used for counting 
    //sun energy which user has and its defult value
    private int sunEnergy;
    private final int defultSunEnergy = 100;
    //seeds are image of plants for user who is going to buy
    private final ImageIcon sunSeed = new ImageIcon("Images\\sunflowerSeed.png");
    private final ImageIcon peashooterSeed = new ImageIcon("Images\\PeashooterSeed.png");
    private final ImageIcon wallNuttSeed = new ImageIcon("Images\\waalnutseed.png");
    private final ImageIcon suicideSeed = new ImageIcon("Images\\suicideCherry.png");
    private final ImageIcon shovel = new ImageIcon("Images\\shovel.jpg");
    //this label shows sunEnergy which user has
    SunNumbersLabel sunEnergyShower = new SunNumbersLabel("0");
    boolean itsOver;//finishgame
    //this is sp label shows what is planting
    JLabel whatIsPlanting;
    JLabel planting;
    MovementAndInteracting timerOfGame;
    boolean levelFinishState;
    ArrayList<LawnMover> movers;
    MainWindow accessMainWindow;

    public Garden(MainWindow mw) {
        /*
         * size of panel and location in windows handled here
         */

        accessMainWindow = mw;
        SoundPlayer starting = new SoundPlayer("start");
        starting.play();
        setSize(1123, height);
        setLocation(0, 0);
        //generating main clip of game and replaying it
        whatIsPlanting = new JLabel();
        levelFinishState = false;
        whatIsPlanting.setSize(300, 50);
        whatIsPlanting.setLocation(600, 20);
        whatIsPlanting.setFont(new Font("nazanin", Font.BOLD, 35));
        whatIsPlanting.setForeground(new Color(178, 34, 34));
        add(whatIsPlanting);
        planting = new JLabel();
        planting.setForeground(new Color(178, 34, 34));
        planting.setFont(new Font("nazanin", Font.BOLD, 35));
        planting.setSize(200, 50);
        planting.setLocation(830, 20);
        add(planting);
        clip = new SoundPlayer("gamesound");
        clip.repetivePlay();
        sunEnergy = defultSunEnergy;
        setLayout(null);
        add(this.sunEnergyShower);
        ROW = new int[5];
        ROW[0] = 0;
        ROW[4] = 0;
        ROW[1] = 0;
        ROW[2] = 0;
        ROW[3] = 0;
        platforms = new Platform[9][5];
        createMovers();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                platforms[i][j] = new Platform();
            }
        }
        goingToPlant = false;
        goingToDig = false;
        addMouseListener(this);
        addSunToScreen = new AddSunToScreen();
        plants = new ArrayList<Plant>();
        zombies = new ArrayList<Zombie>();
        suns = new ArrayList<Sun>();
        timerOfGame = new MovementAndInteracting(zombies, plants, suns, movers);
        mainGarden = new ImageIcon("Images\\garden.jpg");
        ZombivsPlant = new Timer(8, (ActionEvent e) -> {
            sunEnergyShower.setText(sunEnergy + "");
            gameOverChecker();
            levelFinish();
            labeller();
            repaint();
            Safedelete();
        });
        ZombivsPlant.start();
    }

    public void setGoingToPlant(boolean goingToPlant) {
        this.goingToPlant = goingToPlant;
    }

    public void setGoingToDig(boolean goingToDig) {
        this.goingToDig = goingToDig;
    }
    Graphics g;

    /**
     * a paint component draws all things in game panel and it should be
     * repainted
     *
     * @param g is graphic
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(mainGarden.getImage(), 0, 0, mainGarden.getImageObserver());
        this.g = g;
        for (LawnMover mover : movers) {
            g.drawImage(mover.icon.getImage(), mover.locationX, mover.locationY, null);
        }
        //drowplants
        for (Plant plant : plants) {
            if (plant instanceof PeaShooter) {
                PeaShooter help = (PeaShooter) plant;
                g.drawImage(help.pea.getImage(), help.peaX, help.peaY, help.pea.getImageObserver());
            }
            if (plant.plantIcon != null) {
                g.drawImage(plant.getPlantIcon().getImage(), plant.x, plant.y, plant.getPlantIcon().getImageObserver());
            }
            //  drow zombies
        }
        for (Zombie a : zombies) {

            if (a instanceof ManjanigZombie) {
                ManjanigZombie helpz = (ManjanigZombie) a;
                g.drawImage(helpz.ball.getImage(), (int) helpz.ballX, helpz.ballY, helpz.ball.getImageObserver());
            }
            if (a.zombieIcon != null) {
                g.drawImage(a.zombieIcon.getImage(), a.x, a.y, a.zombieIcon.getImageObserver());
            }

        }
        //drow suns
        for (Sun sun : suns) {
            g.drawImage(sun.sun.getImage(), sun.x, sun.y, sun.sun.getImageObserver());
        }
        /*
         * in each lever certain plants are avalable to user
         * the in high levels he/she can use more plants 
         */
        g.drawImage(items.getImage(), 0, 0, items.getImageObserver());
        if (MainWindow.LEVELNUMBER > 0) {
            g.drawImage(sunSeed.getImage(), 80, 6, sunSeed.getImageObserver());
            g.drawImage(peashooterSeed.getImage(), 140, 7, peashooterSeed.getImageObserver());
        }
        if (MainWindow.LEVELNUMBER > 2) {
            g.drawImage(freezeShooterseed.getImage(), 200, 7, freezeShooterseed.getImageObserver());
            g.drawImage(wallNuttSeed.getImage(), 260, 7, wallNuttSeed.getImageObserver());
        }
        if (MainWindow.LEVELNUMBER > 3) {
            g.drawImage(suicideSeed.getImage(), 320, 7, suicideSeed.getImageObserver());
        }
        g.drawImage(shovel.getImage(), 460, 0, null);

        if (levelFinishState) {
            ImageIcon a = new ImageIcon("Images//SaveLife.png");
            g.drawImage(a.getImage(), 250, 200, a.getImageObserver());
        }
        if (levelFinishState && MainWindow.LEVELNUMBER == 6) {
            ImageIcon a = new ImageIcon("Images//finish.png");
            g.drawImage(a.getImage(), 250, 200, a.getImageObserver());
        }
    }

    /**
     * adding a zombie to game
     *
     * @param i is kind of zombie we want to add 0 normal zombie 1 bucket head
     * zombie 2 athlete zombie 3 manjanig zombie
     */
    public void addZombie(int i) {
        Random rand = new Random();
        int n = rand.nextInt(5);
        Zombie zombie = null;
        if (i == 0) {
            zombie = new Zombie(940, (n) * ySize - 10 + 90, n + 1);
        }
        if (i == 1) {
            zombie = new ConeHeadZombie(940, (n) * ySize - 10 + 90, n + 1);
        }
        if (i == 2) {
            zombie = new AthleteZombie(940, (n) * ySize - 10 + 90, n + 1);
        }
        if (i == 3) {
            zombie = new ManjanigZombie(940, (n) * ySize - 10 + 90, n + 1);
        }
        //adding them to row
        ROW[n]++;
        //and finally adding them to zombies array
        zombies.add(zombie);
    }

    /**
     * adding plant is based on user inputs and her/his sun energy
     *
     * @param e where mouse clicked
     * @param kind is kind of plant we are going to plant
     */
    public void addPlant(MouseEvent e, int kind) {
        //getting x,y of clicked place
        int[] x = {e.getX(), e.getY()};
        if (e.getY() < 70) {
            //plantType = 0;
            return;
        }
        //cheking is planted or not
        int[] platformLocated = locationFinder(x);
        try {
            if (platformLocated[0] == 0 && platformLocated[1] == 0) {
                plantType = 0;
                return;
            }
        } catch (Exception ex) {
            plantType = 0;
            return;
        }
        int xy = platformLocated[0];
        int y = platformLocated[1];
        if (platforms[platformLocated[0] - 1][platformLocated[1] - 1].isPlanted()) {
            plantType = 0;
            return;
        }

        platforms[platformLocated[0] - 1][platformLocated[1] - 1].setPlanted(true);
        int[] location = locationSetter(platformLocated);
        //adding plant base on kind
        if (kind == 2) {
            PeaShooter a = new PeaShooter(location[0], location[1], y, xy);
            plants.add(a);
            sunEnergy -= 100;
        }
        if (kind == 4) {
            WallNut a = new WallNut(location[0], location[1], y, xy);
            plants.add(a);
            sunEnergy -= 50;
        }
        if (kind == 1) {
            SunFlower a = new SunFlower(location[0], location[1], y, xy);
            sunEnergy -= 50;
            plants.add(a);
        }
        if (kind == 3) {
            SnowPea a = new SnowPea(location[0], location[1], y, xy);
            plants.add(a);
            sunEnergy -= 175;
        }
        if (kind == 5) {
            SuicideCherry a = new SuicideCherry(location[0], location[1], y, xy);
            plants.add(a);
            sunEnergy -= 150;
        }
        //no plant is going to plant
        plantType = 0;

    }

    private int[] locationFinder(int[] location) {
        if (location[0] < 40) {
            int a[] = {0, 0};
            return a;
        }
        if (location[1] < 90) {
            int a[] = {0, 0};
            return a;
        }
        location[0] -= 40;
        int xx = 0;
        for (int i = 1; i <= 9; i++) {
            if (i % 2 == 1) {
                xx += oddPlatformSizeX;
            } else {
                xx += evenPlatformSizeX;
            }

            if (location[0] < xx) {
                int[] a = {i, (location[1] - 90) / ySize + 1};
                return a;
            }
        }
        return null;
    }

    private int[] locationSetter(int location[]) {
        int sum = 22;
        int x = location[0];
        for (int i = 1; i < x; i++) {
            if (i % 2 == 1) {
                sum += oddPlatformSizeX;
            } else {
                sum += evenPlatformSizeX;
            }
        }
        sum += 10;
        location[1] = ((location[1] - 1) * ySize);
        if (location[0] == 0 && location[1] == 0) {
            int a[] = {0, 0};
            return a;
        }
        int[] returner = {sum, location[1] + 90};
        return returner;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    int plantType;

    /**
     *
     * @param e is where mouse clicked
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 3) {
            plantType = -1;
            goingToDig = false;
            goingToPlant = false;
            whatIsPlanting.setText("");
            planting.setText("");
            return;
        }
        if (sunCheker(e)) {
            return;
        };

        if (plantTypeChecker(e)) {
            return;
        }
        if (levelFinishState) {
            goToNextLevel();
            return;
        }
        if (goingToDig) {
            digPlant(e);
            goingToDig = false;
        }
        if (goingToPlant) {
            addPlant(e, plantType);
            goingToPlant = !goingToPlant;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void digPlant(MouseEvent e) {
        //getting x,y of clicked place
        int[] x = {e.getX(), e.getY()};
        if (x[1] < 90) {
            goingToDig = false;
            return;
        }
        //cheking is planted or not
        int[] platformLocated = locationFinder(x);
        try {
            if (!platforms[platformLocated[0] - 1][platformLocated[1] - 1].isPlanted()) {
                return;
            }
            //oh love catching exceptions
        } catch (Exception ex) {
            goingToDig = false;
            plantType = -1;
            return;
        }
        //digging in platforms
        platforms[platformLocated[0] - 1][platformLocated[1] - 1].setPlanted(false);
        int[] location = locationSetter(platformLocated);
        for (Plant a : plants) {
            if (a.x == location[0] && a.y == location[1]) {
                p = a;
            }
        }
    }
    Plant p = null;

    /**
     * a plant when is dead or removed,it may be in shooting state. we remove it
     * from array when our plant is not shooing
     *
     * @param p is plant we want to remove
     */
    private void Safedelete() {
        if (p == null) {
            return;
        }

        if (Barkhord.eating.isPlaying()) {
            Barkhord.eating.stop();
        }
        /*
         * when a plant faces a zombie and dies the zombie which is facng whith
         * it must die
         */
        walkZombie(p);
        if (!(p instanceof PeaShooter)) {
            plants.remove(p);
            return;
        }
        PeaShooter ps = (PeaShooter) p;
        if (ps.peaX < 900) {
            ps.x = 10000;
            ps.plantIcon = null;
        } else {
            plants.remove(p);
        }
    }

    /**
     * a zombie will walk of no plant is in its way
     *
     * @param plant is plant that it has accrossed with.
     */
    private void walkZombie(Plant plant) {

        for (Zombie zombie : zombies) {
            if (zombie.row != plant.row) {
                continue;
            }
            if (zombie.x > plant.x - 30 && zombie.x < plant.x + plant.plantIcon
                    .getIconWidth() - 20) {
                zombie.startWalking();
            }
        }
    }

    /**
     * if mouse click something it is important to understand it is clicked on
     * sun or not
     *
     * @param e
     */
    private boolean sunCheker(MouseEvent e) {

        if (suns.isEmpty()) {
            return false;
        }
        int[] locationClicked = {e.getX(), e.getY()};
        Sun help = null;
        for (Sun sun : suns) {
            int y = sun.sun.getIconHeight();
            int x = sun.sun.getIconWidth();
            if (locationClicked[0] > sun.x && locationClicked[0] < sun.x + x) {
                if (locationClicked[1] > sun.y && locationClicked[1] < sun.y + y) {
                    sun.dead = true;
                    sunEnergy += 25;
                    sunEnergyShower.setText(sunEnergy + "");
                    return true;
                }
            }
        }
        return false;
    }

    public void labeller() {

        if (goingToDig) {
            planting.setText("برداشت");
            whatIsPlanting.setText("");
            return;
        }
        switch (plantType) {
            case 1:
                whatIsPlanting.setText("گل آفتابگردان ");
                break;
            case 2:
                whatIsPlanting.setText("نخود سبز پرت کن");
                break;
            case 3:
                whatIsPlanting.setText("نخود یخی پرت کن");
                break;
            case 4:
                whatIsPlanting.setText("گردوی فداکار");
                break;
            case 5:
                whatIsPlanting.setText("گیلاس انتحاری");
                break;
            case 0:
                whatIsPlanting.setText("");
                planting.setText("");
                break;
            case -1:
                whatIsPlanting.setText("");

        }
        if (plantType > 0) {
            planting.setText("کاشت:");

        }

    }

    /**
     * this part gets inputs from user and checks what kind of plant user wants
     * to add to his garden
     *
     * @param e is where user clicked
     * @return true if player clicks on appropriate location
     */
    private boolean plantTypeChecker(MouseEvent e) {
        if (e.getY() > 90) {
            return false;
        }
        if (e.getX() > 80 && e.getX() < 80 + 55 && sunEnergy >= 50) {
            goingToPlant = true;
            plantType = 1;
        }
        if (e.getX() > 140 && e.getX() < 140 + 55 && sunEnergy >= 100) {
            goingToPlant = true;
            plantType = 2;
        }
        if (MainWindow.LEVELNUMBER > 2) {
            if (e.getX() > 200 && e.getX() < 200 + 55 && sunEnergy >= 175) {
                goingToPlant = true;
                plantType = 3;
            }
            if (e.getX() > 260 && e.getX() < 260 + 55 && sunEnergy >= 50) {

                goingToPlant = true;
                plantType = 4;
            }
        }
        if (MainWindow.LEVELNUMBER > 3) {
            if (e.getX() > 320 && e.getX() < 320 + 55 && sunEnergy >= 150) {
                goingToPlant = true;
                plantType = 5;
            }
        }
        goingToDig = false;
        if (e.getX() > 460 && e.getX() < 460 + 70) {
            plantType = -10;
            goingToDig = true;
            goingToPlant = false;
        }
        return true;

    }

    /**
     * when a zombie reaches to end of garden player losses game
     */
    public void gameOverChecker() {
        for (Zombie zombie : zombies) {
            if (zombie.x < -100) {
                accessMainWindow.state = "GAMEOVER";
                accessMainWindow.switchState();
                ZombivsPlant.stop();
                clip.stop();
                Barkhord.eating.stop();
                timerOfGame.getTimer().stop();
            }
        }
    }

    /**
     * if time is over and there is no zombies the level is over and state of
     * game will be changed
     */
    private synchronized void levelFinish() {

        if (itsOver && zombies.isEmpty()) {
            // MainWindow.state = "LEVEL";
            //MainWindow.STATECHANGED = true;
            levelFinishState = true;
            ZombivsPlant.stop();
            clip.stop();
            Barkhord.eating.stop();
            timerOfGame.getTimer().stop();
            plants.stream().filter((plant) -> (plant instanceof SunFlower)).forEach((_item) -> {
                SunFlower a = (SunFlower) _item;
                a.shootSun.stop();
            });
        }
    }

    /**
     * when player finish the game this will be appear
     */
    private void goToNextLevel() {

        accessMainWindow.state = "LEVEL";
        accessMainWindow.switchState();
    }

    private void createMovers() {
        movers = new ArrayList<LawnMover>();
        for (int i = 0; i < 5; i++) {
            movers.add(new LawnMover(140 + 120 * i, i + 1));
        }
    }
}
