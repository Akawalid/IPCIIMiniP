package Controller;

import Model.Shepherd;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FarmerController extends MouseAdapter implements MouseListener {

    private Shepherd f;
    public FarmerController(Shepherd f) {
        this.f = f;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        f.move();
    }
}
