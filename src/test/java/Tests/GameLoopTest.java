package Tests;


import com.mrm.typer.model.GameLoop;
import static org.junit.Assert.*;
import org.junit.Test;
/*
* Copyright 2018 Márton Szabó.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

/**
 * A {@code GameLoop} osztályt tesztelő osztály.
 * @author marcikaa
 */
public class GameLoopTest {
    GameLoop gl = new GameLoop();
    
    
    
    @Test
    public void testSetterAndGetterForLivesLeft(){
        gl.setLivesLeft(30);
        assertTrue(30 == gl.getLivesLeft());
        assertEquals("30", gl.getLivesLeftString());
    }
    
    @Test
    public void testSetterAndGetterForMisses(){
        gl.setMissedKeyPresses(20);
        assertTrue(20 == gl.getMissedKeyPresses());
        assertEquals("20", gl.getMissedKeyPressesString());
    }
    
    @Test
    public void testSetterAndGetterForScores(){
        gl.setScore(40);
        assertTrue(40 == gl.getScore());
        assertEquals("40", gl.getScoreString());
    }
    
    @Test
    public void testSetterAndGetterForDifficulty(){
        gl.setDifficultyMultiplier(100);
        assertTrue(100 == gl.getDifficultyMultiplier());
        
    }
    
    
    
}
