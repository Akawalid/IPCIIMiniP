package Controller;

import Model.Farmer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FarmerController extends MouseAdapter implements MouseListener {

    private Farmer f;
    public FarmerController(Farmer f) {
        this.f = f;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        f.move();
    }
}
