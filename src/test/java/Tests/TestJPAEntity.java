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
package Tests;

import com.mrm.typer.model.GameLoop;
import com.mrm.typer.model.entity.JPAEntity;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * A JPA entitást tesztelő osztály.
 * @author marcikaa
 */
public class TestJPAEntity {
    
    JPAEntity entity = new JPAEntity();
    
    @Test
    public void testSetterAndGetterForID(){
        entity.setId((long)100);
        assertTrue(100 == entity.getId());
    }
    
    @Test
    public void testSetterAndGetterForName(){
        entity.setPlayerName("Teszt3");
        assertEquals("Teszt3", entity.getPlayerName());
    }
    
    @Test
    public void testSetterAndGetterForScore(){
        entity.setScore(30);
        assertTrue(30 == entity.getScore());
    }
    
    
}
