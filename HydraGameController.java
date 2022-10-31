package se2203b.assignments1;

//Importing necessary imports
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.Random;

/*
    This is the Controller class of the HydraGame
    This is used to instantiate variables and create methods for Action Events
 */

public class HydraGameController{

    //Creating images for the heads
    //Putting them into an Array and an ArrayList - the ArrayList is used for collision purposes
    Image hydraHead1 = new Image("file:src/main/resources/se2203b/assignments1/HeadSize1.png");
    Image hydraHead2 = new Image("file:src/main/resources/se2203b/assignments1/HeadSize2.png");
    Image hydraHead3 = new Image("file:src/main/resources/se2203b/assignments1/HeadSize3.png");
    Image hydraHead4 = new Image("file:src/main/resources/se2203b/assignments1/HeadSize4.png");
    Image hydraHead5 = new Image("file:src/main/resources/se2203b/assignments1/HeadSize5.png");
    Image hydraHead6 = new Image("file:src/main/resources/se2203b/assignments1/HeadSize6.png");
    Image arrayOfHydra[] = new Image[]{hydraHead1, hydraHead2, hydraHead3, hydraHead4, hydraHead5, hydraHead6};
    private ArrayList<ImageView> placedHydras = new ArrayList<>();

    //Creating the needed JavaFX controllers/containers
    @FXML
    private Slider sizeSlider;
    @FXML
    private AnchorPane hydraContainer;
    @FXML
    private Button playBtn;

    //This will be used to track the number of heads that are attacked
    private int updatedCount = 0;

    //This method is applied to the "Start" button
    /*
    Depending on the slider, it will grab that value and add the image with the use of the Image Array
    Resizing the height and the width and relocating the ImageView around in the AnchorPane
    Adding Action-Event properties to the ImageView, if clicked it will remove the ImageView and use the addImage method
     */
    @FXML
    public void onPlayClick() {
        updatedCount = 0;
        Random random = new Random();
        int sliderValue = (int) sizeSlider.getValue();
        playBtn.setDisable(true);
        sizeSlider.setDisable(true);
        ImageView hydraHolder = new ImageView();
        hydraHolder.setImage(arrayOfHydra[sliderValue - 1]);
        hydraHolder.setFitHeight(40);
        hydraHolder.setFitWidth(40);
        hydraHolder.setTranslateX(random.nextInt(700));
        hydraHolder.setTranslateY(random.nextInt(700));
        hydraContainer.getChildren().add(hydraHolder);
        placedHydras.add(hydraHolder);
        hydraHolder.setOnMouseClicked(mouseEvent -> {
            hydraContainer.getChildren().remove(hydraHolder);
            updatedCount++;
                if (hydraHolder.getImage().equals(arrayOfHydra[1])) {
                    for (int i = 0; i < randomValue(); i++) {
                        addImage(0);
                    } }
                else if (hydraHolder.getImage().equals(arrayOfHydra[2])) {
                    for (int i = 0; i < randomValue(); i++) {
                        addImage(1);
                    } }
                else if (hydraHolder.getImage().equals(arrayOfHydra[3])) {
                    for (int i = 0; i < randomValue(); i++) {
                        addImage(2);
                    } }
                else if (hydraHolder.getImage().equals(arrayOfHydra[4])) {
                    for (int i = 0; i < randomValue(); i++) {
                        addImage(3);
                    } }
                else if (hydraHolder.getImage().equals(arrayOfHydra[5])) {
                    for (int i = 0; i < randomValue(); i++) {
                        addImage(4);
                    } }
        });
    }

    /*
    This is the addImage method which creates a new ImageView of n-1 size
    Sets the width, height, and relocates while also checking for collision and if the game is over
    Recursively calls itself and adds properties to the new Image Views it is creating
     */
        @FXML
        private void addImage (int n) {
            Random random = new Random();
            ImageView hydraHolder = new ImageView();
            hydraHolder.setImage(arrayOfHydra[n]);
            hydraHolder.setFitHeight(40);
            hydraHolder.setFitWidth(40);
            hydraContainer.getChildren().add(hydraHolder);
            placedHydras.add(hydraHolder);
           hydraHolder.setTranslateX(random.nextInt(700));
           hydraHolder.setTranslateY(random.nextInt(700));
            while (collisionCheck(hydraHolder)) {
            hydraHolder.setTranslateX(random.nextInt(700));
            hydraHolder.setTranslateY(random.nextInt(700));
       }
            hydraHolder.setOnMouseClicked(mouseEvent -> {
                hydraContainer.getChildren().remove(hydraHolder);
                updatedCount++;
                winnerWinnerChickenDinner();
                if (hydraHolder.getImage().equals(arrayOfHydra[1])) {
                    for (int i = 0; i < randomValue(); i++) {
                        addImage(0);
                    } }
                else if (hydraHolder.getImage().equals(arrayOfHydra[2])) {
                    for (int i = 0; i < randomValue(); i++) {
                        addImage(1);
                    } }
                else if (hydraHolder.getImage().equals(arrayOfHydra[3])) {
                    for (int i = 0; i < randomValue(); i++) {
                        addImage(2);
                    } }
                else if (hydraHolder.getImage().equals(arrayOfHydra[4])) {
                    for (int i = 0; i < randomValue(); i++) {
                        addImage(3);
                    } }
                else if (hydraHolder.getImage().equals(arrayOfHydra[5])) {
                    for (int i = 0; i < randomValue(); i++) {
                        addImage(4);
                    } }
            });
        }

        //This method is used to randomly decide whether 2 or 3 heads will spawn per click
        public static int randomValue () {
            int min = 2;
            int max = 4;
            int randomNumber = (int) (Math.random() * (max - min)) + min;
            return randomNumber;    }

        //This method is attached to the "Reset" button which will reset the game
        @FXML
        public void onResetClick () {
            hydraContainer.getChildren().clear();
            playBtn.setDisable(false);
            sizeSlider.setDisable(false);
            sizeSlider.setValue(4);
    }

        /*
        This method is used to check for Collision
        Earlier, we placed each of the new images created in an ArrayList
        The for loop iterates through the ArrayList and checks whether they intersect with the new ImageView that is
        created, it must not be the same as itself, or it will break
         */
        public boolean collisionCheck(ImageView currentHydraLocation) {
        boolean status = false;
            for (ImageView i : placedHydras) {
                if (currentHydraLocation != i) {
                    if (i.getBoundsInParent().intersects(currentHydraLocation.getBoundsInParent())) {
                        status = true;
                        break;
                    }
                }
            }
            return status;
        }

        /*
        This method checks for when the game is finished
        It is called at every single attack
        I added a Label and these are the properties
         */
        public void winnerWinnerChickenDinner() {
        if (hydraContainer.getChildren().isEmpty()) {
            Label winnerText = new Label("Good Job! You have cut off " + updatedCount + " Hydra heads!");
            hydraContainer.getChildren().add(winnerText);
            winnerText.setAlignment(Pos.CENTER);
            winnerText.setTranslateX(240);
            winnerText.setTranslateY(300);
            winnerText.setFont(new Font("Cambria",20));
            winnerText.setTextFill(Color.color(1,0.2,0.2));
        }
        }
    }