package Tests;

import com.mrm.typer.model.DB.DataBase;
import com.mrm.typer.model.entity.JPAEntity;
import java.util.ArrayList;
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
    
    JPAEntity entity = new JPAEntity();
    
    @Test
    public void a_saveTest() throws IllegalArgumentException, Exception{
        
        entity.setPlayerName("Mukodik az Adatbazis");
        entity.setScore(1);
        JPAEntity savedEntity = db.save(entity);
        assertNotNull(savedEntity);
        assertNotNull(savedEntity.getId());
        assertNotNull(savedEntity.getPlayerName());
        assertNotNull(savedEntity.getScore());
        
    }
    
    @Test
    public void b_getAllOrderedByScoreTest(){
        List<JPAEntity> all = db.getAllOrderedByScore();
        assertNotNull(all);
    }
    
}
