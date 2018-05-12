package Tests;

import com.mrm.typer.controller.GameController;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;



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
 *
 * @author marcikaa
 */
public class GameControllerTest {

    GameController gc = new GameController();
    
    @Test
    public void testDifficulty(){
        Double[] expected = new Double[] {0.0,0.5};
        Double[] real = new Double[] {(double)gc.getScore(),gc.getDifficultyMultiplier()};
        assertArrayEquals(expected,real);
    }   
    
    @Test
    public void spawnCmpTest(){
        assertNotNull(gc.spawnCmp("s"));
    
    }
    
    
}
