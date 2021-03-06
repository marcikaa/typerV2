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
import static org.junit.Assert.*;
import static com.mrm.typer.model.LetterGenerator.generateLetterToPush;
import org.junit.Test;

/**
 * A {@code LetterGenerator} osztályt tesztelő osztály.
 * @author marcikaa
 */
public class LetterGeneratorTest {
    
    @Test
    public void testIfGeneratedLetterIsNotNull(){
        String let = generateLetterToPush();
        assertNotEquals("",let);
    
    }   
    
    
    
}
