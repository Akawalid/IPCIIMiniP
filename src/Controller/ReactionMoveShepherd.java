package Controller;

import Model.Shepherd;
import View.World;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ReactionMoveShepherd extends MouseAdapter implements MouseListener {
    //This is the reactor to the shepherd's movement button.
    private Shepherd f;
    public ReactionMoveShepherd(Shepherd f) {

        this.f = f;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //TODO
    }
}
