package com.mrm.typer.model.DB;

import com.mrm.typer.model.entity.JPAEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ez az osztály felelős az adatbáziskapcsolat irányításáért.
 * @author marcikaa
 */
@Slf4j
public class DataBase {
    //CHECKSTYLE:OFF
    private static Logger logger = LoggerFactory.getLogger(DataBase.class);
    private static final DataBase DB_PELDANY = new DataBase();
    
    @PersistenceContext(unitName = "typerDB")
    private EntityManager em;
    
    private DataBase() {
    }
    //CHECKSTYLE:ON
    /**
     * Visszaadja az adatbázis példányát.
     * @return referencia a példányra
     */
    public static DataBase getDbPeldany() {
        return DB_PELDANY;
    }
    
    /**
     * Entitymanager megnyitása (adatbáziskapcsolat).
     *
     * @throws Exception JPA hiba esetén
     */
    public void connectDB() throws Exception {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("typerDB");
        em = emFactory.createEntityManager();
        logger.trace("Adatbázis kapcsolat OK.");
    }
    
    /**
     * Entitymanager lezárása.
     */
    public void disconnectDB() {
        if (connected()) {
            em.close();
            logger.trace("Disconnect OK.");
        }
        em = null;
    }
    
    /**
     * Megnézi hogy az em létezik és nyitva van-e.
     * @return nyitva van-e
     */
    public boolean connected() {
        
        return em != null && em.isOpen();
    }
    
    /**
     * @param entity menteni kíván entitás
     *
     * @return mentett entitás
     *
     * @throws IllegalStateException ha nincs adatbázis-kapcsolat
     * @throws IllegalArgumentException ha a menteni kívánt film címe
     * érvénytelen ({@code null})
     * @throws Exception JPA hiba esetén
     */
    public JPAEntity save(JPAEntity entity) throws IllegalStateException, IllegalArgumentException, Exception {
        
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
        }
        
        if (entity == null) {
            throw new IllegalArgumentException("A mentendő entitás null!");
        }
        
        try {
            em.getTransaction().begin();
            
            if (entity.getId() == null) {
                em.persist(entity);
            } else {
                em.merge(entity);
            }
            
            em.getTransaction().commit();
            
            return entity;
        } catch (PersistenceException e) {
            
            logger.error("JPA lekérdezési hiba!");
            throw new Exception("JPA hiba!"+ e);
        }
    }
    
    
    /**
     * Nevek és pontok listázása.
     * @return egy listával ami tárolja az összes nevet és pontot
     * @throws IllegalStateException ha nincs adatbázis kapcsolat
     */
    public List<JPAEntity> getAllOrderedByScore() throws IllegalStateException {
        
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
        }
        
        return (List<JPAEntity>) em.createNamedQuery("JPAEntity.getAllOrderedByScore").getResultList();
    }
}
