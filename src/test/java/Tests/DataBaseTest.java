package Tests;

import com.mrm.typer.model.DB.DataBase;
import com.mrm.typer.model.entity.JPAEntity;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
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
 * Az adatbázis tesztelése
 * @author marcikaa
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataBaseTest {
    
    DataBase db = DataBase.getDbPeldany();
    
    
    @Before
    public void connectDB() throws Exception{
        db.connectDB();
    }
    
    Long idd;
    JPAEntity entity = new JPAEntity();
    
    @Test
    public void a_saveTest() throws IllegalArgumentException, Exception{
        
        entity.setPlayerName("TESZT1");
        entity.setScore(1444);
        JPAEntity savedEntity = db.save(entity);
        assertNotNull(savedEntity);
        assertNotNull(savedEntity.getId());
        idd = savedEntity.getId();
        assertNotNull(savedEntity.getPlayerName());
        assertNotNull(savedEntity.getScore());
        
    }
    
    /* @Test
    public void b_findByIDTest() throws Exception{
    JPAEntity byID = db.findPlayerByID(idd);
    assertNotNull(byID);
    }*/
    
    @Test
    public void c_findByNameTest() throws IllegalArgumentException, Exception{
        List<JPAEntity> sameName = db.findPlayerByName("TESZT1");
        assertNotNull(sameName);
    }
    
    @Test
    public void d_getAllOrderedByScoreTest(){
        List<JPAEntity> all = db.getAllOrderedByScore();
        assertNotNull(all);
    }
    
    @Test
    public void e_deleteTest() throws IllegalArgumentException, Exception{
        List<JPAEntity> all = db.getAllOrderedByScore();
        JPAEntity toDelete = all.get(0);
        Integer highScore = toDelete.getScore();
        db.delete(toDelete);
        assertEquals(highScore, all.get(0).getScore());
        
    }
    
    
}
