package colunga;

import java.awt.event.KeyEvent;

public interface Controllable {
    void onKeyPressed(KeyEvent e);
    void onKeyReleased(KeyEvent e);
}
