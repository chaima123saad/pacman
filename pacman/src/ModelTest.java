import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void testInitGame() {
        Model model = new Model();
        model.initGame();
        assertEquals(3, model.getLives());
        assertEquals(0, model.getScore());
    }

    @Test
    void testMovePacman() {
        Model model = new Model();
        model.initGame();
        int initialX = model.getPacmanX();
        int initialY = model.getPacmanY();

        model.setReqDx(-1);
        model.setReqDy(0);
        model.movePacman();

        assertEquals(initialX - Model.PACMAN_SPEED, model.getPacmanX());
        assertEquals(initialY, model.getPacmanY());
    }

    @Test
    public void testPacManGhostCollision() {
        Model model = new Model();
        model.initGame();
        int initialPacmanX = model.getPacmanX();
        int initialPacmanY = model.getPacmanY();
        int ghostIndex = 0; 
        int initialGhostX = model.getGhostX(ghostIndex);
        int initialGhostY = model.getGhostY(ghostIndex);
        model.setPacmanPosition(initialGhostX / Model.BLOCK_SIZE, initialGhostY / Model.BLOCK_SIZE);
        assertTrue("Collision entre Pac-Man et le fantôme", hasCollision(model));
        model.setPacmanPosition(initialPacmanX / Model.BLOCK_SIZE,initialPacmanY / Model.BLOCK_SIZE);
        assertFalse("Pas de collision après réinitialisation", hasCollision(model));
    }

    private boolean hasCollision(Model model) {
        int pacmanX = model.getPacmanX();
        int pacmanY = model.getPacmanY();
        int ghostX = model.getGhostX(0); 
        int ghostY = model.getGhostY(0);
        return pacmanX == ghostX && pacmanY == ghostY;
    }






@Test
void testCollectPacDotInvalidCase() {
    Model model = new Model();
    model.initGame();
    model.setPacmanPosition(5, 5);
    model.setScreenData(6, 5, (short) 1); 
    model.movePacman();
    assertEquals(0, model.getScore());
}

@Test
void testMovePacmanIntoWall() {
    Model model = new Model();
    model.initGame();
    model.setPacmanPosition(5, 5);
    model.setScreenData(6, 5, (short) 1); 
    model.setReqDx(1);
    model.setReqDy(0);
    model.movePacman();

    assertEquals(5 * Model.BLOCK_SIZE, model.getPacmanX());
    assertEquals(5 * Model.BLOCK_SIZE, model.getPacmanY());
}

  

}
