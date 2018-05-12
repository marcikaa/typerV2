package Tests;

import com.mrm.typer.model.Result;
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
 *
 * @author marcikaa
 */
public class ResultTest {
    
    @Test
    public void testSetterAndGetterForScore(){
        
        Result res = new Result("Teszt1", "20");
        res.setScore("30");
        assertEquals("30", res.getScore());
        System.out.println("Az eredmény: " + res.getScore() + " Sikerült a set");
        
    }
    
    @Test
    public void testSettersAndGetterForName(){
        
        Result res = new Result("Teszt1", "20");
        res.setName("Teszt2");
        assertEquals("Teszt2", res.getName());
        System.out.println("A név: " + res.getName() + " Sikerült a set");
    }
    
}
