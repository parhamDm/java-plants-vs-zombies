/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

/**
 * The game have 45 platforms and each of them is an object inside array we
 * understand whether it is planted sth or not or sth can be planted or not
 *
 * @author Parham Damavandi
 */
public class Platform {

    //each platform is empty or not
    private boolean planted;
    private Plant plantKind;

    public Platform() {
        planted = false;
    }

    public boolean isPlanted() {
        return planted;
    }

    public void setPlanted(boolean planted) {
        this.planted = planted;
    }

    public Plant getPlantKind() {
        return plantKind;
    }

    public void setPlantKind(Plant plantKind) {
        this.plantKind = plantKind;
    }
}
